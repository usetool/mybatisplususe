package com.jikedaquan.study.mp.entity.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

//需要将自定义填充控制器注册为组件
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOGGER= LoggerFactory.getLogger(MyMetaObjectHandler.class);

    //insert操作时要填充的字段
    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ...");
        //根据属性名字设置要填充的值
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("version",1,metaObject);
    }

    //update操作时要填充的字段
    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("start insert fill ...");
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
