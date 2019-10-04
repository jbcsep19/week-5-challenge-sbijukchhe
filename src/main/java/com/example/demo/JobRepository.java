package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface JobRepository extends CrudRepository<Job, Long> {
    ArrayList<Job> findByAuthorContainingIgnoreCase(String author);
    ArrayList<Job> findByTitleContainingIgnoreCase(String title);
    ArrayList<Job> findByPhoneNum(String phoneNum);
    ArrayList<Job> findByDate(String date);
    void deleteByTitle(String title);

}
