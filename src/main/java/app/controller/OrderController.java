package app.controller;

import app.entity.Worker;
import app.pojo.AjaxResponseBody;
import app.entity.Order;
import app.pojo.CarDriver;
import app.pojo.ClientOrder;
import app.pojo.Place;
import app.service.CarDriverService;
import app.service.DepartmentService;
import app.service.OrderService;
import app.service.WorkerService;
import app.utils.GeocodingUtil;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Timestamp;

/**
 * Created by Alex_Frankiv on 04.04.2017.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private static final String ROLE_ADMIN ="ADMIN";

    @Value("${google.API_KEY}")
    private String G_API_KEY;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CarDriverService carDriverService;
    @Autowired
    private WorkerService workerService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createOrder() {
        ModelAndView mv = new ModelAndView("orderEdit");
        mv.addObject("gApiKey", G_API_KEY);
        mv.addObject("departments", departmentService.getAll(false));
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody doCreateOrder(Principal principal, @RequestBody Order order) {
        GeoApiContext context = new GeoApiContext().setApiKey(G_API_KEY);
        GeocodingResult[] resultsFrom;
        try {
            resultsFrom = GeocodingApi.geocode(context, order.getFrom().getAddress()).await();
            Place placeFrom = new Place();
            placeFrom.setAddress(order.getFrom().getAddress());
            placeFrom.setLat(resultsFrom[0].geometry.location.lat);
            placeFrom.setLng(resultsFrom[0].geometry.location.lng);
            order.setFrom(placeFrom);
        } catch (Exception e) {
            return new AjaxResponseBody("400", "Bad Request: Illegal address of current location");
        }
        GeocodingResult[] resultsTo;
        try {
            resultsTo = GeocodingApi.geocode(context, order.getTo().getAddress()).await();
            Place placeTo = new Place();
            placeTo.setAddress(order.getTo().getAddress());
            placeTo.setLat(resultsTo[0].geometry.location.lat);
            placeTo.setLng(resultsTo[0].geometry.location.lng);
            order.setTo(placeTo);
        } catch (Exception e) {
            return new AjaxResponseBody("400", "Bad Request: Illegal address of destination");
        }
        order.setStartTime(new Timestamp(System.currentTimeMillis()));
        if(!GeocodingUtil.areFromOneCity(resultsFrom[0], resultsTo[0]))
            return new AjaxResponseBody("400", "Markers' location mismatch! They should be from one city");
        order.setClient(principal.getName());
        orderService.insert(order);
        return new AjaxResponseBody("200", "OK");
    }

    @RequestMapping(value = "/receipt/{id}", method = RequestMethod.GET)
    public ModelAndView getReciept(@PathVariable int id, Principal principal, HttpServletRequest request){
        ModelAndView mv = new ModelAndView("orderGeneralView");
        mv.addObject("gApiKey", G_API_KEY);
        ClientOrder clientOrder = orderService.getClientOrderById(id);
        CarDriver carDriver =carDriverService.getByOrderId(id);
        Worker dispatcher = workerService.getDispatcherByOrderId(id);
        if(!clientOrder.getClient().getLogin().equals(principal.getName())
                && !carDriver.getDriver().getLogin().equals(principal.getName())
                && !dispatcher.getLogin().equals(principal.getName())
                && !request.isUserInRole(ROLE_ADMIN))
            return new ModelAndView("403");
        mv.addObject("client_order", clientOrder);
        mv.addObject("car_driver", carDriver);
        mv.addObject("dispatcher", dispatcher);
        return mv;
    }

    @RequestMapping(value = "/rate/{id}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody rateOrder(@PathVariable int id, Principal principal, @RequestBody int rateMark){
        ClientOrder clientOrder = orderService.getClientOrderById(id);
        if(!principal.getName().equals(clientOrder.getClient().getLogin()))
            return new AjaxResponseBody("403", "Access denied");
        clientOrder.getOrder().setFeedback(rateMark);
        orderService.update(clientOrder.getOrder());
        return new AjaxResponseBody("200", "OK");
    }
}
