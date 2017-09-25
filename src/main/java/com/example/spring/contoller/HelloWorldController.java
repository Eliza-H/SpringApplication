package com.example.spring.contoller;

/**
 * Created by elh on 10.09.17.
 */

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.spring.contoller.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.example.spring.model.UserProfile;
import com.example.spring.service.UserProfileService;
import com.example.spring.service.UserService;

@Controller
public class HelloWorldController {

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    UserService userService;


    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(Locale locale, ModelMap model) {
        model.addAttribute("greeting", "Hi, Welcome to mysite");
        return "home";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "admin";
    }

    @RequestMapping(value = "/db", method = RequestMethod.GET)
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "dba";
    }

    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }



    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String newRegistration() {
        return "signup";
    }

    /*
     * This method will be called on form submission, handling POST request It
     * also validates the user input
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String saveRegistration(@RequestBody MultiValueMap var) {

        UserRequest.UserRequestBuilder builder = UserRequest.builder();
        builder.login((String) var.getFirst("login"));
        builder.firstName((String) var.getFirst("firstName"));
        builder.lastName((String) var.getFirst("lastName"));
        builder.password((String) var.getFirst("password"));
        builder.email((String) var.getFirst("email"));

        userService.save(builder.build());
        return "registrationsuccess";
    }


    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }


    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }


}