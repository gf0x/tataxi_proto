package app.controller;

import app.pojo.AjaxResponseBody;
import app.entity.Car;
import app.service.CarService;
import app.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alex_Frankiv on 03.04.2017.
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Value("${google.API_KEY}")
    private String G_API_KEY;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createCar(){
        ModelAndView mv = new ModelAndView("carEdit");
        mv.addObject("gApiKey", G_API_KEY);
        mv.addObject("departments", departmentService.getAll(true));
        mv.addObject("firstEdit", true);
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody doCreateCar(@RequestBody Car car){
        //TO-DO: backend validation
        carService.insert(car);
        return new AjaxResponseBody("200", "OK");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editCar(@PathVariable int id){
        ModelAndView mv = new ModelAndView("carEdit");
        mv.addObject("gApiKey", G_API_KEY);
        Car car = carService.get(id);
        mv.addObject("car", car);
        mv.addObject("department", departmentService.get(car.getDeptId()));
        mv.addObject("firstEdit", false);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody doEditCar(@RequestBody Car car){
        carService.update(car);
        return new AjaxResponseBody("200", "OK");
    }
}
