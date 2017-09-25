package com.example.spring.dao;

/**
 * Created by elh on 06.09.17.
 */
import java.util.List;

import com.example.spring.model.Person;

public interface PersonDao {
    void add(Person person);
    List<Person> listPersons();
}