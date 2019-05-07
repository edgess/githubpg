package com.example.demo1.services;

import com.example.demo1.entity.Oil;
import com.example.demo1.mapper.OilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OilService {
    @Autowired
    OilMapper oilMapper;

    public int insertOil(Oil oil) {
        return oilMapper.insertOil(oil);
    }

    public void insertOils(List<Oil> oils) {
        oilMapper.insertOils(oils);
    }

    public Oil queryById(int id) {
        return oilMapper.queryById(id);
    }

    //@Cacheable(value = {"aaa"})
    public List<Oil> getall() {
        return oilMapper.getall();
    }

}
