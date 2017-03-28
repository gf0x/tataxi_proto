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

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @Transactional
    public ModelAndView registerClient(@RequestParam String login, @RequestParam String real_name, @RequestParam String email,
                                       @RequestParam String home_address, @RequestParam String phone_num,
                                       @RequestParam String password, @RequestParam String pass_conf){
        ModelAndView mv = new ModelAndView("login");
        User user = new User();
        user.setLogin(login);
        user.setAuthRole(CLIENT_ROLE);
        user.setEnabled(1);
        user.setPswd(password);
        if (pass_conf.equals(password))
            userService.insert(user);
        Client client = new Client();
        client.setLogin(login);
        client.setEmail(email);
        client.setRealName(real_name);
        client.setHomeAddress(home_address);
        client.setPhoneNumber(phone_num);
        //TO-DO: validate phone number as well
        clientService.insert(client);
        mv.addObject("registered", true);
        return mv;
    }
}
