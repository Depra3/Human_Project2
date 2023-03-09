package com.project2.project2;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberDAO extends MongoRepository<MemberDTO,String> {
    MemberDTO findByUid(String Uid);
    MemberDTO findByAdmin(String Admin);
}
