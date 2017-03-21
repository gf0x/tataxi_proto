package app.controller;

import app.entity.User;
import app.service.UserService;
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
import org.springframework.web.servlet.ModelAndView;

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

    @Value("${google.API_KEY}")
    private String G_API_KEY;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getHandler(){
        ModelAndView mv = new ModelAndView("home");
        logger.info(ROOT_CTL_TAG, "RootController handles GET request");

        //Caching test
        User user = userService.get("alex");
        System.out.println(user);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postHandler(@RequestBody String address) throws Exception {
        ModelAndView mv = new ModelAndView("home");
        logger.info(ROOT_CTL_TAG, "Handling POST request; API_KEY: "+G_API_KEY);
        GeoApiContext context = new GeoApiContext().setApiKey(G_API_KEY);
        GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
        mv.addObject("lat", (results.length > 0) ? results[0].geometry.location.lat : "no results");
        mv.addObject("lng", (results.length > 0) ? results[0].geometry.location.lng : "no results");

        //Caching test
        User user = userService.get("alex");
        System.out.println("POST: "+user);

        return mv;
    }
}
