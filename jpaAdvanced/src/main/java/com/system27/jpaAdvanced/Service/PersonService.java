package com.system27.jpaAdvanced.Service;

import com.system27.jpaAdvanced.Model.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAllPerson();

    Person addPerson(Person person);

    List<Person> findByAgeBetween(String startAge, String endAge);

    List<Person> findByAgeOrderByNameDesc(String name);

    List<Person> findByNameLike(String likePattern);

    List<Person> findByAge(String age);

    List<Person> findAllPersonJPQLbyLenSort();

    List<Person> findAllPersonJPQL();

    List<Person> findAllPersonWithPagination(String pageNo);

    void insertPerson(Person person);

    Person findPersonByAgeAndNameJpql(String age, String name);

    List<Person> findByAgeAndName(String age, String name);
}
