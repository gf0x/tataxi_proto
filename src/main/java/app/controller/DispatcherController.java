package app.controller;

import app.entity.Car;
import app.entity.Worker;
import app.pojo.AjaxResponseBody;
import app.pojo.CarDriver;
import app.service.CarDriverService;
import app.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/car_drivers", method = RequestMethod.GET)
    public ModelAndView getCarDrivers(Principal principal){
        ModelAndView mv = new ModelAndView("carDriver");
        mv.addObject("car_drivers", carDriverService.getCarDriversForDispatcher(workerService.get(principal.getName())));
        return mv;
    }

    @RequestMapping(value = "/cancel_car_driver_pair", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody cancelCarDriverPair(@RequestBody CarDriver carDriver, Principal principal){
        if(carDriverService.cancelCarDriverPair(carDriver.getCar(), carDriver.getDriver(), workerService.get(principal.getName()))>0)
        return new AjaxResponseBody("200", "Pair canceled");
        else
            return new AjaxResponseBody("555", "Could not cancel pair");
    }
}
