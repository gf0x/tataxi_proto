package app.controller;

import app.entity.Car;
import app.entity.Order;
import app.entity.Worker;
import app.pojo.AjaxResponseBody;
import app.pojo.CarDriver;
import app.pojo.OrderCar;
import app.service.CarDriverService;
import app.service.CarService;
import app.service.OrderService;
import app.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by Alex_Frankiv on 08.04.2017.
 */
@Controller
@RequestMapping("/staff/dispatcher")
public class DispatcherController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private CarDriverService carDriverService;
    @Autowired
    private CarService carService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/car_drivers", method = RequestMethod.GET)
    public ModelAndView getCarDrivers(Principal principal) {
        ModelAndView mv = new ModelAndView("carDriver");
        Worker self = workerService.get(principal.getName());
        mv.addObject("car_drivers", carDriverService.getCarDriversForDispatcher(self));
        try {
            mv.addObject("cars", carService.getFreeCarsByDispatcher(self));
            mv.addObject("drivers", workerService.getFreeByDispatcher(self));
        } catch (Exception e) {
            return new ModelAndView("403");
        }
        return mv;
    }

    @RequestMapping(value = "/cancel_car_driver_pair", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody cancelCarDriverPair(@RequestBody CarDriver carDriver, Principal principal) {
        Car car = carService.get(carDriver.getCar().getId());
        Worker driver = workerService.get(carDriver.getDriver().getLogin());
        try {
            if (carDriverService.cancelCarDriverPair(car, driver, workerService.get(principal.getName())) > 0)
                return new AjaxResponseBody("200", "Pair canceled");
            else
                return new AjaxResponseBody("555", "Could not cancel pair");
        } catch (Exception ex) {
            return new AjaxResponseBody("403", ex.getMessage());
        }
    }

    @RequestMapping(value = "/create_car_driver_pair", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody createCarDriverPair(@RequestBody CarDriver carDriver, Principal principal) {
        Car car = carService.get(carDriver.getCar().getId());
        Worker driver = workerService.get(carDriver.getDriver().getLogin());
        try {
            if (carDriverService.createCarDriverPair(car, driver, workerService.get(principal.getName())) > 0)
                return new AjaxResponseBody("200", "Pair created");
            else
                return new AjaxResponseBody("555", "Could not create pair");
        } catch (Exception ex) {
            return new AjaxResponseBody("403", ex.getMessage());
        }
    }

    @RequestMapping(value = "/car_drivers/modal", method = RequestMethod.GET)
    public ModelAndView refreshModal(Principal principal) {
        ModelAndView mv = new ModelAndView("carDriverModal");
        Worker self = workerService.get(principal.getName());
        try {
            mv.addObject("cars", carService.getFreeCarsByDispatcher(self));
            mv.addObject("drivers", workerService.getFreeByDispatcher(self));
        } catch (Exception e) {
            return new ModelAndView("403");
        }
        return mv;
    }

    @RequestMapping(value = "/car_drivers/list", method = RequestMethod.GET)
    public ModelAndView refreshList(Principal principal) {
        ModelAndView mv = new ModelAndView("carDriverList");
        Worker self = workerService.get(principal.getName());
        mv.addObject("car_drivers", carDriverService.getCarDriversForDispatcher(self));
        return mv;
    }

    @RequestMapping(value = "/orders_awaiting", method = RequestMethod.GET)
    public ModelAndView getOrdersAwaiting(Principal principal) {
        ModelAndView mv = new ModelAndView("deptOrders");
        Worker self = workerService.get(principal.getName());
        mv.addObject("car_drivers", carDriverService.getAwiwatingForOrder(self));
        mv.addObject("client_orders", orderService.getAwaitingForDispatcher(self));
        return mv;
    }

    @RequestMapping(value = "/orders_awaiting/list", method = RequestMethod.GET)
    public ModelAndView getOrdersAwaitingList(Principal principal) {
        ModelAndView mv = new ModelAndView("deptOrdersList");
        Worker self = workerService.get(principal.getName());
        mv.addObject("car_drivers", carDriverService.getAwiwatingForOrder(self));
        mv.addObject("client_orders", orderService.getAwaitingForDispatcher(self));
        return mv;
    }

    @RequestMapping(value = "/orders_awaiting/accept", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody acceptOrder(@RequestBody OrderCar orderCar, Principal principal){
        try {
            orderService.accept(orderCar.getOrder(), orderCar.getCar(), workerService.get(principal.getName()));
        }catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage());
        }
        return new AjaxResponseBody("200", "OK");
    }

    @RequestMapping(value = "/orders_awaiting/decline", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody declineOrder(@RequestBody Order order, Principal principal){
        System.out.println(order);
        try {
            orderService.decline(order, workerService.get(principal.getName()));
        }catch (Exception e) {
            return new AjaxResponseBody("500", e.getMessage());
        }
        return new AjaxResponseBody("200", "OK");
    }

    @RequestMapping(value = "/all_orders", method = RequestMethod.GET)
    public ModelAndView allOrders(Principal principal){
        ModelAndView mv = new ModelAndView("allOrdersView");
        mv.addObject("orders", orderService.getAllForDispatcher(new Worker(principal.getName())));
        return mv;
    }
}
