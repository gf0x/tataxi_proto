package app.controller;

import app.entity.Client;
import app.entity.User;
import app.service.ClientService;
import app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    private static final String CLIENT_ROLE = "ROLE_CLIENT";
    private static final int ENABLED = 1;

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional
    public ModelAndView registerClient(@RequestParam String login, @RequestParam String real_name, @RequestParam String email,
                                       @RequestParam String home_address, @RequestParam String phone_num,
                                       @RequestParam String password, @RequestParam String pass_conf) {
        ModelAndView mv = new ModelAndView("login");
        if (!pass_conf.equals(password))
            throw new IllegalArgumentException("BACKEND: AUTH: password confirmation failed!");
        Client client = new Client(login, real_name, email, home_address, phone_num);
        client.setAuthRole(CLIENT_ROLE);
        client.setPswd(password);
        client.setEnabled(ENABLED);
        //TO-DO: validate phone number as well
        if (clientService.insert(client) > 0)
            mv.addObject("registered", true);
        else
            mv.addObject("registered", false);
        return mv;
    }
}
