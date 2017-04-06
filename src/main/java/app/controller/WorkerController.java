package app.controller;

import app.dto.AjaxResponseBody;
import app.entity.Car;
import app.entity.Worker;
import app.service.DepartmentService;
import app.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alex_Frankiv on 03.04.2017.
 */
@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Value("${google.API_KEY}")
    private String G_API_KEY;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private WorkerService workerService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createWorker(){
        ModelAndView mv = new ModelAndView("workerEdit");
        mv.addObject("gApiKey", G_API_KEY);
        mv.addObject("departments", departmentService.getAll(true));
        mv.addObject("firstEdit", true);
        return mv;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody doCreateWorker(@RequestBody Worker worker){
        //TO-DO: backend validation
        System.out.print(worker);
        if (workerService.insert(worker)<1)
            return  new AjaxResponseBody("500", "Could not create worker");
        return new AjaxResponseBody("200", "OK");
    }

    @RequestMapping(value = "/edit/{login}", method = RequestMethod.GET)
    public ModelAndView editDept(@PathVariable String login){
        ModelAndView mv = new ModelAndView("workerEdit");
        mv.addObject("gApiKey", G_API_KEY);
        Worker worker = workerService.get(login);
        mv.addObject("worker", worker);
        mv.addObject("department", departmentService.get(worker.getDeptId()));
        mv.addObject("firstEdit", false);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponseBody doEditDept(@RequestBody Worker worker){
        workerService.update(worker);
        return new AjaxResponseBody("200", "OK");
    }
}
