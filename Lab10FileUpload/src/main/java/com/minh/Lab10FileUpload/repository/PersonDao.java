package com.minh.Lab10FileUpload.repository;

import com.minh.Lab10FileUpload.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PersonDao extends Dao<Person> {

    public PersonDao(){
        collections = new ArrayList<>();
    }

    @Override
    public List<Person> getAll() {
        return collections;
    }

    @Override
    public Optional<Person> get(int id) {
        return collections.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public void add(Person person) {
        int id;
        if (collections.isEmpty()){
            id = 1;
        } else {
            Person lastPerson = collections.get(collections.size() - 1);
            id = lastPerson.getId() + 1;
        }
        person.setId(id);
        collections.add(person);
    }

    @Override
    public void update(Person person) {
        get(person.getId()).ifPresent(existPerson -> {
            existPerson.setName(person.getName());
            existPerson.setJob(person.getJob());
            existPerson.setGender(person.getGender());
            existPerson.setBirthDate(person.getBirthDate());
            existPerson.setPhoto(person.getPhoto());
        });
    }

    @Override
    public void delete(Person person) {
        deleteById(person.getId());
    }

    @Override
    public void deleteById(int id) {
        get(id).ifPresent(existPerson -> collections.remove(existPerson));
    }
}
