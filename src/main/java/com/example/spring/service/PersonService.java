package com.example.spring.service;

/**
 * Created by elh on 06.09.17.
 */
import java.util.List;

import com.example.spring.model.Person;

public interface PersonService {
    void add(Person person);
    List<Person> listPersons();
}