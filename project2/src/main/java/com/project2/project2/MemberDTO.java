package com.project2.project2;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "member")
public class MemberDTO {

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
