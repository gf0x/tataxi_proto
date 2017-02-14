package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getHandler(){
        ModelAndView mv = new ModelAndView("home");
        logger.info("TRASH", "RootController handles GET rquest");
        return mv;
    }
}
