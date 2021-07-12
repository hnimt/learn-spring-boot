package vn.techmaster.basicthymeleaf.minh.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vn.techmaster.basicthymeleaf.minh.model.Person;

@Repository
public class PersonRepository {
    private ArrayList<Person> people;

    public PersonRepository() {
        people = new ArrayList<>(List.of(
            new Person("Trinh Minh Cuong", "Viet Nam", "1975-11-27", "Male"),
            new Person("Ly Hoang Duc", "Viet Nam", "2007-07-08", "Male"),
            new Person("John Terry", "UK", "1972-11-02", "Male"),
            new Person("Lionel Messi", "Argentina", "1975-11-02", "Male"),
            new Person("Chiang Jian", "China", "2001-05-02", "Female"),
            new Person("Lyan Kuwai", "Singapore", "2001-05-02", "Female")
        ));
    }

    public List<Person> getPeople(){
        return people;
    }

}
