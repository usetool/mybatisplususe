package com.jikedaquan.study.mp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jikedaquan.study.mp.entity.User;
import com.jikedaquan.study.mp.mapper.UserMapper;
import com.jikedaquan.study.mp.utils.IdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CRUDTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void testIdWorker() {
        long id = new IdWorker().nextId();
        System.out.println(id);
        id = new IdWorker().nextId();
        System.out.println(id);

    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(2L);
        user.setName("Jackie");
        int result = userMapper.updateById(user);
        System.out.println(result);

    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("leguan");
        user.setAge(20);
        user.setEmail("4849111@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectBatchIds() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectByMap() {
        Map<String, Object> param = new HashMap<>();
        param.put("name", "jack");
        param.put("age", 18);
        List<User> users = userMapper.selectByMap(param);
        users.forEach(System.out::println);
    }

    @Test
    public void testSelectPage() {
        //构建分页条件第二页每页显示3条
        Page<User> page = new Page<>(2, 3);
        //使用分页条件查询，不使用其他条件
        userMapper.selectPage(page, null);
        //获取分页后查询出的记录
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
        System.out.println("是否有下一页：" + page.hasNext());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("总记录数：" + page.getTotal());
    }

    @Test
    public void testSelectMap(){
        Page<User> page = new Page<>(2, 3);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);
    }

    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1128212430124097543L);
        System.out.println(result);
    }

    @Test
    public void testDeleteBatchIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(1128212430124097540L,1128212430124097541L,1128212430124097542L));
        System.out.println(result);
    }

    @Test
    public void testLogicDelete(){
        int result=userMapper.deleteById(1L);
        System.out.println(result);
    }

    @Test
    public void testOptimisticLocker1() {
        User user = userMapper.selectById(1128212430124097543L);
        user.setName("修改后");
        int result = userMapper.updateById(user);
        if (result == 1) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }


    @Test
    public void testOptimisticLocker2() {
        User user = userMapper.selectById(1128212430124097543L);
        user.setName("修改后");
        user.setVersion(user.getVersion() - 1);//测试旧版本
        int result = userMapper.updateById(user);
        if (result == 1) {
            System.out.println("修改成功");
        } else {
            System.out.println("修改失败");
        }
    }
}
