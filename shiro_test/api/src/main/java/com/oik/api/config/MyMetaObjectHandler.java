package com.oik.api.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.oik.util.dto.UserDTO;
import com.oik.util.redis.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author oik
 *  mybatis-plus 自动插入值
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        // 或者
        this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        // 或者
        //this.fillStrategy(metaObject, "createTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
        String createUser = "";
        String id = "";
        try {
            UserDTO user = UserHolder.getUser();
            createUser = user.getUsername();
            id = user.getUserId();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        this.strictInsertFill(metaObject, "createUserId", String.class, id);
        this.strictInsertFill(metaObject, "createUsername", String.class, createUser);
        this.strictInsertFill(metaObject, "updateUserId", String.class, id);
        this.strictInsertFill(metaObject, "updateUsername", String.class, createUser);

        //this.setFieldValByName("createUser",user.getUsername(),metaObject); //


    }


    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
//        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        String updateUser = "";
        String id = "";
        try {
            UserDTO user = UserHolder.getUser();
            updateUser = user.getUsername();
            id = user.getUserId();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        this.setFieldValByName("updateUsername", updateUser, metaObject);
        this.setFieldValByName("updateUserId", id, metaObject);
    }
}
