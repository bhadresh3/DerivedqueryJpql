package com.system27.jpaAdvanced.Service;

import com.system27.jpaAdvanced.Model.Person;
import com.system27.jpaAdvanced.Repo.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepo personRepo;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Person> findAllPerson() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
        Root<Person> rootEntry = cq.from(Person.class);
        CriteriaQuery<Person> all = cq.select(rootEntry);
        TypedQuery<Person> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    @Transactional // Not thread safe so transactional require.
    public Person addPerson(Person person) {
        entityManager.persist(person);
        return person;
    }

    @Override
    public List<Person> findByAgeBetween(String startAge, String endAge) {
        return personRepo.findByAgeBetween(Integer.parseInt(startAge), Integer.parseInt(endAge));
    }

    @Override
    public List<Person> findByAgeOrderByNameDesc(String age) {
        return personRepo.findByAgeOrderByNameDesc(Integer.parseInt(age));
    }

    @Override
    public List<Person> findByNameLike(String likePattern) {
        return personRepo.findByNameLike(likePattern);
    }

    @Override
    public List<Person> findByAge(String age) {
        return personRepo.findByAge(Integer.parseInt(age));
    }

    @Override
    public List<Person> findAllPersonJPQL() {
        return personRepo.findAllPersonUsingJPQL(Sort.by(Sort.Direction.ASC,"name"));
    }

    @Override
    public List<Person> findAllPersonWithPagination(String pageNo) {
        Page<Person> pagedResult = personRepo.findAllPersonWithPagination
                (PageRequest.of(Integer.parseInt(pageNo), 2, Sort.by(Sort.Direction.ASC,"id")));
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void insertPerson(Person person) {
        personRepo.insertPerson(person.getName(),person.getAge(),person.getSalary());
    }

    @Override
    public Person findPersonByAgeAndNameJpql(String age, String name) {
        return personRepo.findPersonByAgeAndName(Integer.parseInt(age), name);
    }

    @Override
    public List<Person> findByAgeAndName(String age, String name) {
        return personRepo.findByAgeAndName(Integer.parseInt(age), name);
    }

    @Override
    public List<Person> findAllPersonJPQLbyLenSort() {
        return personRepo.findAllPersonUsingJPQL(JpaSort.unsafe("LENGTH(name)"));
    }

}
