package com.edge.daojpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test,Integer> {
    List<Test> findFirstByOrderByIdDesc();
    List<Test> findFirstBy();

}
