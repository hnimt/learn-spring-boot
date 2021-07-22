package com.minh.lab08crud.repository;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.minh.lab08crud.model.Person;

import org.springframework.util.ResourceUtils;

public class PersonDao extends Dao<Person> {

    public PersonDao(String csvFile) {
        this.readCSV(csvFile);
    }

    @Override
    public void readCSV(String csvFile) {
        try {
            File file = ResourceUtils.getFile("classpath:static/" + csvFile);
            CsvMapper mapper = new CsvMapper();
            CsvSchema schema = CsvSchema.emptySchema().withHeader().withColumnSeparator('|');
            ObjectReader oReader = mapper.readerFor(Person.class).with(schema);
            Reader reader = new FileReader(file);
            MappingIterator<Person> mi = oReader.readValues(reader);

            while (mi.hasNext()) {
                Person person = mi.next();
                this.collections.add(person);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        
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
        if (collections.isEmpty()) {
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
        get(person.getId()).ifPresent(existperson -> {
            existperson.setName(person.getName());
            existperson.setJob(person.getJob());
            existperson.setGender(person.getGender());
            existperson.setBirthDay(person.getBirthDay());
        });
    }

    @Override
    public void delete(Person person) {
        deleteByID(person.getId());
        
    }

    @Override
    public void deleteByID(int id) {
        get(id).ifPresent(existperson -> collections.remove(existperson));
    }

    @Override
    public List<Person> searchByKeyword(String keyword) {
        return collections
        .stream()
        .filter(person -> person.matchWithKeyword(keyword))
        .collect(Collectors.toList());
    }
    
}
