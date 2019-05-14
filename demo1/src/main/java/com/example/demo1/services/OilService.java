package com.example.demo1.services;

import com.example.demo1.entity.Oil;
import com.example.demo1.mainslave.DataSourceContextHolder;
import com.example.demo1.mainslave.TargetDataSource;
import com.example.demo1.mapper.OilMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    //    @Cacheable(value = "HelloWorldCache", key = "'oil_'+#id")
    @TargetDataSource(value = "first")
    public Oil queryById(int id) {
        return oilMapper.queryById(id);
    }

    @TargetDataSource(value = "first")
    public List<Oil> getall() {
        return oilMapper.getall();
    }

}
