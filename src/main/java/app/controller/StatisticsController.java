package app.controller;

import app.service.CarService;
import app.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alex_Frankiv on 11.04.2017.
 */
@Controller
@RequestMapping("/stats")
public class StatisticsController {

    @Autowired
    private CarService carService;
    @Autowired
    private WorkerService workerService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public ModelAndView getCarsOfSystem(){
        ModelAndView mv = new ModelAndView("extraCarStats");
        mv.addObject("cars", carService.getStatsByBrandModel());
        return mv;
    }

    @RequestMapping(value = "/veterans", method = RequestMethod.GET)
    public ModelAndView getVeteransOfSystem(){
        ModelAndView mv = new ModelAndView("driverVeterans");
        mv.addObject("drivers", workerService.getDriversWhoTriedAllCarsInTheirDept());
        return mv;
    }
}
