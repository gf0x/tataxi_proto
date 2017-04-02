package app.controller;

import app.dto.AjaxResponseBody;
import app.entity.Department;
import app.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alex_Frankiv on 30.03.2017.
 */
@Controller
@RequestMapping("/dept")
public class DepartmentController {

    @Value("${google.API_KEY}")
    private String G_API_KEY;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createDept(){
        ModelAndView mv = new ModelAndView("deptCreate");
        mv.addObject("gApiKey", G_API_KEY);
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody doCreateDept(@RequestBody Department dept){
        //TO-DO: backend validation
        System.out.print(dept);
        departmentService.insert(dept);
        return new AjaxResponseBody("200", "OK");
    }
}
