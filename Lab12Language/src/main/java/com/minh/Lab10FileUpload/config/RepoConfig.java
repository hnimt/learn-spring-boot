package com.minh.Lab10FileUpload.config;

import com.minh.Lab10FileUpload.entity.Person;
import com.minh.Lab10FileUpload.repository.JobDao;
import com.minh.Lab10FileUpload.repository.PersonDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {

    @Bean
    public PersonDao personDao() {
        return new PersonDao();
    }
    @Bean
    public JobDao jobDao() {
        return new JobDao();
    }
}
