package app.controller;

import app.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createOrder(){
        ModelAndView mv = new ModelAndView("orderEdit");
        mv.addObject("gApiKey", G_API_KEY);
        return mv;
    }
}
