package com.example.spring.contoller;

/**
 * Created by elh on 08.09.17.
 */
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/welcome")
public class WelcomeController {

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome(Map<String, Object> model) {
        ModelAndView mav = new ModelAndView("welcome");
        model.put("message", this.message);
        return mav;
    }
}