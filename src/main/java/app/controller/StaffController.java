package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alex_Frankiv on 06.04.2017.
 */

/**
 * Controller that handles dispatchers' and drivers' requests
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(){
        ModelAndView mv = new ModelAndView();

        return mv;
    }
}
