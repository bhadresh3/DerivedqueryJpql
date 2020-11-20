package com.system27.jpaAdvanced.Repo;

import com.system27.jpaAdvanced.Model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

    /*
    Derived query
     */

    List<Person> findByAge(Integer  age);

    // case sensitive
    List<Person> findByNameLike(String likePattern);

    //findByNameIgnoreCase
    //List<Student> findByNameContainingIgnoreCase(String name);

    // matches exact name only
    //case Sensiive
    List<Person> findByAgeOrderByNameDesc(Integer age);

    List<Person> findByAgeBetween(Integer startAge, Integer endAge);

    List<Person> findByAgeAndName(Integer age, String Name);

    @Query(value = "SELECT p FROM Person p")
    List<Person> findAllPersonUsingJPQL(Sort sort);

    @Query(value = "SELECT p FROM Person p ORDER BY id")
    Page<Person> findAllPersonWithPagination(Pageable pageable);

    @Modifying
    @Query(
            value = "insert into Person (name, age, salary) values (:name, :age, :salary)",
            nativeQuery = true)
    void insertPerson(@Param("name") String name, @Param("age") Integer age,
                      @Param("salary") Integer salary);

    @Query("SELECT p FROM Person p WHERE p.age = :age and p.name = :name")
    Person findPersonByAgeAndName(@Param("age") Integer age,
                                         @Param("name") String name);

}
