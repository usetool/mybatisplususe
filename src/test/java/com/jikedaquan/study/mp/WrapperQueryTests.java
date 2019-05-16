package com.jikedaquan.study.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jikedaquan.study.mp.entity.User;
import com.jikedaquan.study.mp.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WrapperQueryTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void testAllEq() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        Map<String, Object> param = new HashMap<>();
        param.put("name", "Tom");
        param.put("age", 28);
        param.put("version", null);
        userQueryWrapper.allEq(param);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
        //SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND name = 'Tom' AND version IS NULL AND age = 28
    }

    @Test
    public void testIn() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        userQueryWrapper.in("id", 1, 2, 3);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
        //SELECT id,name,age,email,create_time,update_time,version,deleted FROM user WHERE deleted=0 AND id IN (1,2,3)
    }

    @Test
    public void testSet() {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        User user = userMapper.selectById(2L);
        if (null != user) {
            userUpdateWrapper
                    .set("name", user.getName() + ":u1")
                    .set("age", 40)
                    .eq("id", user.getId());
            userMapper.update(user, userUpdateWrapper);
        }
    }

    @Test
    public void testDiySql(){
        List<User> userList = userMapper.findUserByName("Tom");
        userList.forEach(System.out::println);
    }
}
