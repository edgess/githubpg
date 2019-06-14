package com.edge.dao.server;

import com.edge.dao.mapper.Log2Mapper;
import com.edge.entity.Log2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author edge
 * @date 2019/6/14-15:19
 */
@Component
public class Log2Service {
    @Autowired
    Log2Mapper log2Mapper;

    public List<Log2> getall() {
        return log2Mapper.findall();
    }

    public int insertlog2(Log2 log2) {
        return log2Mapper.insert(log2);
    }
}
