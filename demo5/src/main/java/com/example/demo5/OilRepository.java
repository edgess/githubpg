package com.example.demo5;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OilRepository extends JpaRepository<Oil, Integer> {

    // @Query("from Oil o order by o.id desc")
    // public List<Oil> findAlldescByQuery();
    @Query("select o.date from Oil o order by o.id desc")
    List<Oil> getall();

    @Query(value = "select o.date from Oil o order by o.id desc", countQuery = "select count(*) from Oil o", nativeQuery = true)
    Page<Oil> getallByPage(Pageable pageable);

    List<Oil> findByIdLessThan(Integer id);


}
