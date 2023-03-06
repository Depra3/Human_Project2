package com.project2.project2;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommRepository extends MongoRepository<Community, Integer>{
    Community findByNum(Integer num);
}
