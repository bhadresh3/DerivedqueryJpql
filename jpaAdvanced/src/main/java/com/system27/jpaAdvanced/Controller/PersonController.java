package com.system27.jpaAdvanced.Controller;

import com.system27.jpaAdvanced.Model.Person;
import com.system27.jpaAdvanced.Service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAllPerson(){
        return personService.findAllPerson();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    // exact age
    @RequestMapping(value=("/age"), method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findByAge(@RequestParam(value = "age") String age)
    {
        return personService.findByAge(age);
    };

    // bhadr%
    @RequestMapping(value=("/name"), method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findByNameLike(@RequestParam("likePattern") String likePattern){
        return personService.findByNameLike(likePattern);
    }

    //matches exact
    // case sensitive
    @RequestMapping(value = ("/{age}/Desc"), method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findByAgeOrderByNameDesc(String age){
        return personService.findByAgeOrderByNameDesc(age);
    }

    // includes boundary
    @RequestMapping(value = ("/inBetween"),method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    List<Person> findByAgeBetween(@RequestParam String startAge, @RequestParam String endAge){
        return personService.findByAgeBetween(startAge, endAge);
    }

    // exact age
    @RequestMapping(value=("/nameAge"), method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findByAgeAndName(@RequestParam(value = "age") String age,
                                         @RequestParam(value = "name") String name)
    {
        return personService.findByAgeAndName(age, name);
    }

    @RequestMapping(value = ("/jpqlFindAll"), method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAllPersonJPQL(){
        return personService.findAllPersonJPQL();
    }

    @RequestMapping(value = ("/jpqlFindAllSortByNameLen"), method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAllPersonJPQLByNameLen(){
        return personService.findAllPersonJPQLbyLenSort();
    }

    // starts with zero
    @RequestMapping(value = ("/jpqlFindPage"), method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAllPersonWithPagination(@RequestParam("pageNo") String pageNo){
        return personService.findAllPersonWithPagination(pageNo);
    }

    @RequestMapping(value = ("/insertPerson"), method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void insertPerson(@RequestBody Person person){
         personService.insertPerson(person);
    }

    @RequestMapping(value = ("/ageAndNameJpql"), method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Person findPersonByAgeAndNameJpql(@RequestParam("age") String age, @RequestParam("name") String name){
        return personService.findPersonByAgeAndNameJpql(age, name);
    }
}
