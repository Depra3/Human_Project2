package com.project2.project2;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Document(collection = "community")
public class Community{

    @Id
    private String c_id;
    private Integer num;
    private String title;
    private String content;
    private String author;
    private Date req_date;
    private Date mod_date;

    // @Override
    // public String toString(){
    //     return String.format("Community[")
    // }
}