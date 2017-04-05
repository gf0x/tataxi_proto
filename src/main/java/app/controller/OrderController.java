package app.controller;

import app.dto.AjaxResponseBody;
import app.entity.Order;
import app.pojo.Place;
import app.service.DepartmentService;
import app.service.OrderService;
import app.utils.GeocodingUtil;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Alex_Frankiv on 04.04.2017.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Value("${google.API_KEY}")
    private String G_API_KEY;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private OrderService orderService;

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
            placeTo.setAddress(order.getFrom().getAddress());
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
}
