package com.edge.dao.server;

import com.edge.dao.mapper.Log2Mapper;
import com.edge.dao.mapper.UtestMapper;
import com.edge.entity.Log2;
import com.edge.entity.Utest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author edge
 * @date 2019/6/14-15:19
 */
@Component
public class UtestService {
    @Autowired
    UtestMapper utestMapper;

    public List<Utest> getall() {
        return utestMapper.findall();
    }

    public int insertUtest(Utest utest) {
        return utestMapper.insert(utest);
    }
}
