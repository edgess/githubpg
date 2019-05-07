package com.example.demomongo;

import com.example.demomongo.entity.Lab;
import com.example.demomongo.entity.User;
import com.example.demomongo.mango.MongoAutoidUtil;
import com.example.demomongo.mapper.RoleMapper;
import com.example.demomongo.mapper.UserMapper;
import com.example.demomongo.repository.LabRepository;
import com.example.demomongo.shiro.PasswordUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemomongoApplicationTests {
    @Autowired
    LabRepository labRepository;
    @Autowired
    MongoAutoidUtil mongoAutoidUtil;
    @Autowired
    RoleMapper  roleMapper;
    @Autowired
    UserMapper userMapper;

    @Test
    public void contextLoads6()  {
        System.out.println(userMapper.findall());
        System.out.println(roleMapper.findall());
    }

    @Test
    public void contextLoads5() throws CloneNotSupportedException, ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2018-09-09");
        int compareTo = date1.compareTo(date2);
        System.out.println(compareTo);
    }

    @Test
    public void contextLoads4() throws CloneNotSupportedException, ParseException {
        User user = new User();
        user.setPassword(DigestUtils.md5Hex("gg"));
        user.setUsername("gg");
//        user.setExpireddate(new SimpleDateFormat("yyyy-MM-dd").parse("2018-09-09"));
        userMapper.insert(user);
    }

    @Test
    public void contextLoads3() throws CloneNotSupportedException {
//        System.out.println(userMapper.findRoles("edge"));
        Object obj = new SimpleHash("MD5", "123456");
        System.out.println(obj);

    }

    @Test
    public void contextLoads2() throws CloneNotSupportedException {
        System.out.println(DigestUtils.md5Hex("edge"));
        System.out.println(DigestUtils.sha1Hex("edge"));
        System.out.println(DigestUtils.sha256Hex("edge"));
        System.out.println(DigestUtils.sha384Hex("edge"));
        System.out.println(PasswordUtil.generate("edge"));
        System.out.println(PasswordUtil.verify("edge", "c2361ce9951729701a43fa49d48c6038a77ed9be1887b82f"));
    }
    @Test
    public void contextLoads1() throws CloneNotSupportedException {
        System.out.println("roro --"+userMapper.findRoles("roro"));
        System.out.println("lele --"+userMapper.findRoles("lele"));
        System.out.println("lele --"+userMapper.findPermission("lele"));
    }

    @Test
    public void contextLoads() throws CloneNotSupportedException {
//        for (int i = 0; i <10 ; i++) {
//            Lab lab = new Lab();
//            lab.setId(mongoAutoidUtil.getNextSequence("lab"));
//            lab.setCreateTime(new Date());
//            lab.setTp(Integer.toString(i));
//            lab.setHd(Integer.toString(i));
//            labRepository.save(lab);
//        }

//        Lab lab = new Lab();
//        lab.setId(mongoAutoidUtil.getNextSequence("lab"));
//        lab.setCreateTime(new Date());
//        lab.setTp("22");
//        lab.setHd("22");
//        lab.setAbc("ddd");
//        labRepository.save(lab);

//        Lab lab =new Lab(mongoAutoidUtil.getNextSequence("lab"),"11","11",new Date());
//        labRepository.save(lab);

       // System.out.println(JSONArray.toJSONString(labRepository.findById(mongoAutoidUtil.getNextSequence("lab")-1)));
//        System.out.println(JSONArray.toJSONString(labRepository.findByAbcLike("ddd")));


        Lab lab1 =new Lab();
        lab1.setTp("111");
        Lab lab2 = (Lab) lab1.clone();
        lab1.setTp("333");
        System.out.println(lab1.getTp());
        System.out.println(lab2.getTp());
        String aaa = "eee";
        String bbb = "eee";
        System.out.println(aaa.hashCode());
        System.out.println(bbb.hashCode());
    }

}