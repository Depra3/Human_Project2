package com.project2.project2;
import org.springframework.data.mongodb.repository.MongoRepository;

// import java.util.List;

public interface MemberDAO extends MongoRepository<MemberDTO,String> {
    MemberDTO findByUid(String Uid);
    // List<MemberDTO> 
}
