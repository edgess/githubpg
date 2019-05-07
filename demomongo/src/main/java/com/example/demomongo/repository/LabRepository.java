package com.example.demomongo.repository;

import com.example.demomongo.entity.Lab;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author edge
 * @date 2019/1/16-10:49
 */
public interface LabRepository extends MongoRepository<Lab,Integer> {
//    List<Lab> findByAbcLike(String abc);

}
