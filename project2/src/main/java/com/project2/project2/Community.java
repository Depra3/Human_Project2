package com.project2.project2;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "community")
public class Community{

    @Id
    private String id;
    private String num;
    private String title;
    private String content;
    private String author;
    private String req_date;
    private String mod_date;
}