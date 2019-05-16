package com.jikedaquan.study.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jikedaquan.study.mp.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface UserMapper extends BaseMapper<User> {
    List<User> findUserByName(@Param("name")String name);
}
