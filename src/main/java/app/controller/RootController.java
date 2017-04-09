package app.controller;

import app.dao.WorkerDao;
import app.entity.User;
import app.entity.Worker;
import app.pojo.CarDriver;
import app.service.CarDriverService;
import app.service.UserService;
import app.service.WorkerService;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by Alex_Frankiv on 14.02.2017.
 */
@Controller
@RequestMapping(value = "/", headers = "Accept=*/*")
public class RootController {

    private static Logger logger = LoggerFactory.getLogger(RootController.class.getSimpleName());

    private static String ROOT_CTL_TAG = "ROOT_CTL";

    @Autowired
    private UserService userService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private CarDriverService carDriverService;

    @Value("${google.API_KEY}")
    private String G_API_KEY;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView setOnline(Principal principal) {
        ModelAndView mv = new ModelAndView("home");
        logger.info(ROOT_CTL_TAG, "RootController handles GET request");
        workerService.setOnline(new Worker(principal.getName()));
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postHandler(@RequestBody String address) throws Exception {
        ModelAndView mv = new ModelAndView("home");
        logger.info(ROOT_CTL_TAG, "Handling POST request; API_KEY: " + G_API_KEY);
        GeoApiContext context = new GeoApiContext().setApiKey(G_API_KEY);
        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        mv.addObject("lat", (results.length > 0) ? results[0].geometry.location.lat : "no results");
        mv.addObject("lng", (results.length > 0) ? results[0].geometry.location.lng : "no results");

//        Worker worker = new Worker();
//        worker.setDeptId(29);
//        for(Worker dr : workerService.getFreeByDispatcher(worker))
//            System.out.println(dr);

        //Caching test
        User user = userService.get("alex");
        System.out.println("POST: " + user);

        return mv;
    }

    @RequestMapping("/pre_logout")
    public String setOffline(Principal principal) {
        if (principal != null)
            workerService.setOffline(new Worker(principal.getName()));
        return "redirect: /logout";
    }
}
