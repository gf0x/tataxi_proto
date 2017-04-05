package app.controller;

import app.dto.AjaxResponseBody;
import app.entity.Department;
import app.service.DepartmentService;
import app.utils.GeocodingUtil;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        ModelAndView mv = new ModelAndView("deptEdit");
        mv.addObject("gApiKey", G_API_KEY);
        mv.addObject("firstEdit", true);
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody doCreateDept(@RequestBody Department dept){
        //TO-DO: backend validation
        System.out.print(dept);
        //validate
        GeoApiContext context = new GeoApiContext().setApiKey(G_API_KEY);
        GeocodingResult[] city;
        try {
            city = GeocodingApi.geocode(context, dept.getCity()).await();
        } catch (Exception e) {
            return new AjaxResponseBody("400", "Invalid city name");
        }
        if(city.length<0)
            return new AjaxResponseBody("400", "Invalid city name");
        String cityValid = GeocodingUtil.getCity(city[0]);
        if(!dept.getCity().equals(cityValid))
            return new AjaxResponseBody("400", "Invalid city name. Maybe you meant: "+cityValid+'?');
        departmentService.insert(dept);
        return new AjaxResponseBody("200", "OK");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editDept(@PathVariable int id){
        ModelAndView mv = new ModelAndView("deptEdit");
        mv.addObject("gApiKey", G_API_KEY);
        mv.addObject("department", departmentService.get(id));
        mv.addObject("firstEdit", false);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public AjaxResponseBody doEditDept(@RequestBody Department dept){
        System.out.print(dept);
        departmentService.update(dept);
        return new AjaxResponseBody("200", "OK");
    }
}
