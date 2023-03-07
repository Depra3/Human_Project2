package com.project2.project2;

// import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommRepository extends MongoRepository<Community, Integer>{
    // List<Community> findByNum(Integer num);
    
}
