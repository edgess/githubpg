package com.example.demo5;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByAgeLessThan(Integer age);

    List<User> findByNameLike(String name);

    List<User> findByNameLikeAndAgeGreaterThan(String name, Integer age);



}
