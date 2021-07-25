package com.minh.Lab10FileUpload.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private int id;
    @Size(min = 5, max = 30, message = "Name must between 5 and 30")
    private String name;
    private String job;
    private String gender;

    private String birthDate;
    private MultipartFile photo;

    public Person(int id, String name, String job, String gender, String birthDate) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.gender = gender;
        this.birthDate = birthDate;
    }
}
