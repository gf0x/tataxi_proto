package app.controller;

import app.entity.Order;
import app.entity.Worker;
import app.pojo.AjaxResponseBody;
import app.service.DepartmentService;
import app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Alex_Frankiv on 09.04.2017.
 */
@Controller
@RequestMapping("/staff/driver")
public class DriverController {

    @Value("${google.API_KEY}")
    private String G_API_KEY;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/current_order")
    public ModelAndView currentDrive(Principal principal){
        ModelAndView mv = new ModelAndView("driverOrder");
        mv.addObject("client_order", orderService.getCurrentOrderDriver(new Worker(principal.getName())));
        mv.addObject("gApiKey", G_API_KEY);
        return mv;
    }

    @RequestMapping(value = "/finish_drive", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody finishDrive(@RequestBody Order order, Principal principal){
        orderService.finishOrder(order);
        return  new AjaxResponseBody("200", "OK");
    }

    @RequestMapping(value = "/all_orders", method = RequestMethod.GET)
    public ModelAndView allOrders(Principal principal){
        ModelAndView mv = new ModelAndView("allOrdersView");
        mv.addObject("orders", orderService.getAllForDriver(new Worker(principal.getName())));
        return mv;
    }
}
