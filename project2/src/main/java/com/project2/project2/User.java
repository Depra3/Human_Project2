package com.project2.project2;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users") // DB안에 users라는 콜렉션에 저장(table 개념)
public class User {

    @Id
    private String id;
    private String uid;
    private String pw;
    private String nickname;
    private String name;
    private String email;
    private String mobile;
    private String admin;
    private String sort;
}
