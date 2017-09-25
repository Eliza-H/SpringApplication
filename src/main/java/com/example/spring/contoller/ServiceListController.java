//package com.example.spring.contoller;
//
//
//import com.example.spring.model.Person;
//import com.example.spring.service.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/service/")
//public class ServiceListController {
//
//    @Autowired
//    PersonService personService;
//
//    @RequestMapping("test")
//    public List<Person> test() {
//        List<Person> persons = personService.listPersons();
//        return persons;
//    }
//}