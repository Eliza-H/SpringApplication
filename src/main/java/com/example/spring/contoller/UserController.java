package com.example.spring.contoller;


import com.example.spring.model.Person;
import com.example.spring.model.User;
import com.example.spring.service.PersonService;
import com.example.spring.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/userController/")
public class UserController {

    @Autowired
    PersonService personService;

    @Autowired
    UserService userService;

    @RequestMapping("welcome.html")
    public ModelAndView firstPage() {
        return new ModelAndView("welcome");
    }

    @RequestMapping("test")
    public List<Person> test() {
        List<Person> persons = personService.listPersons();
        return persons;
    }
//test
    @RequestMapping("userDaoImpl")
    public List<User> test2() {
        List<User> users = userService.listOfUsers();
        return users;
    }


    @RequestMapping("add")
    @SneakyThrows
    public boolean add() {
        personService.add(new Person("Artem", "Shtepenko", "cyber007@gmail.com"));
        return true;
    }
}