package com.minh.Lab10FileUpload.repository;

import com.minh.Lab10FileUpload.entity.Job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JobDao extends Dao<Job>{

    public JobDao() {
        collections = new ArrayList<>(Arrays.asList(
                new Job("Developer"),
                new Job("Banker")
        ));
    }

    @Override
    public List<Job> getAll() {
        return collections;
    }

    @Override
    public Optional<Job> get(int id) {
        return Optional.empty();
    }

    @Override
    public void add(Job job) {
        collections.add(job);
    }

    @Override
    public void update(Job job) {

    }

    @Override
    public void delete(Job job) {

    }

    @Override
    public void deleteById(int id) {

    }
}
