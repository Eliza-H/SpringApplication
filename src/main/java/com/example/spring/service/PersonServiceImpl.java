package com.example.spring.service;

/**
 * Created by elh on 06.09.17.
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring.dao.PersonDao;
import com.example.spring.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao userDao;

    @Transactional
    @Override
    public void add(Person person) {
        userDao.add(person);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Person> listPersons() {
        return userDao.listPersons();
    }

}
