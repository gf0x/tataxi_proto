package app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alex_Frankiv on 28.03.2017.
 */
@Controller
@RequestMapping("/client")
public class ClientController {

    private static Logger logger = LoggerFactory.getLogger(RootController.class.getSimpleName());

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerClient(@RequestParam String login, @RequestParam String real_name, @RequestParam String email,
                                       @RequestParam String home_address, @RequestParam String phone_num,
                                       @RequestParam String password){
        ModelAndView mv = new ModelAndView("login");
        System.out.println("Login: "+login);
        System.out.println("Real name: "+real_name);
        System.out.println("Email: "+email);
        System.out.println("Home address: "+home_address);
        System.out.println("Phone num: "+phone_num);
        System.out.println("Password: "+password);

        mv.addObject("registered", true);
        return mv;
    }
}
