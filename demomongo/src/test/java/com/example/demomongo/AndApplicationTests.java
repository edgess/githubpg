package com.example.demomongo;

import com.alibaba.fastjson.JSONObject;

import com.example.demomongo.entity.Dept;
import com.example.demomongo.mapper.DeptMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AndApplicationTests {

    @Autowired
    DeptMapper deptMapper;

    @Test
    public void contextLoads() {

        List<Dept> deptResult = new ArrayList<>();

        List<Dept> deptList = deptMapper.findall();

        Map<Integer, Dept> deptMap = new HashMap<>();

        for (Dept p : deptList) {
            deptMap.put(p.getId(), p);
        }

        for (Dept p : deptList) {
            Dept child = p;
            if (child.getPid() == 0) {
                deptResult.add(p);
            } else {
                Dept parent = deptMap.get(child.getPid());
                parent.getChildren().add(child);
            }
        }

        System.out.println(JSONObject.toJSONString(deptResult));

        view(deptResult,"-");

    }

    public void view(List<Dept> depts,String line) {
        String line2="-"+line;
        for(Dept de :depts){
            System.out.println(line2+de.getDeptname());
            if (null != de.getChildren()) {
                view(de.getChildren(),line2);
            }
        }
    }
}

