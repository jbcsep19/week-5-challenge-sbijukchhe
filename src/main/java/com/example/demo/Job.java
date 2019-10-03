package com.example.demo;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    @DateTimeFormat(pattern ="yyyy-mm-dd")
    private Date postedDate;
    private String author;
    private String phoneNum;

}
