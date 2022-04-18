package be.vdab.restclient.controllers;

import be.vdab.restclient.restclients.ReqRestClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller @RequestMapping("users")
public class UserController {
    private final ReqRestClient client;

    public UserController(ReqRestClient client) {
        this.client = client;
    }

    @GetMapping("{id}")
    public ModelAndView getUser(@PathVariable long id) {
        var mov = new ModelAndView("user");
        client.findById(id).ifPresent(user -> mov.addObject(user));
        return mov;
    }
}
