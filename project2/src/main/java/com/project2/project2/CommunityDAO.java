package com.project2.project2;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommunityDAO extends MongoRepository <CommunityDTO,String> {
   CommunityDTO findby_Id(String _id);
}