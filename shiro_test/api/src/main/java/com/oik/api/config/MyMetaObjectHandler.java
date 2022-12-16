package com.oik.api.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.oik.util.dto.UserDTO;
import com.oik.util.redis.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        //this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐使用)
        // 或者
        this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
        // 或者
        //this.fillStrategy(metaObject, "createTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
        String createUser = "";
        try {
            UserDTO user = UserHolder.getUser();
            createUser = user.getUsername();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        this.strictInsertFill(metaObject, "createUser", String.class, createUser);
        this.strictInsertFill(metaObject, "updateUser", String.class, createUser);

        //this.setFieldValByName("createUser",user.getUsername(),metaObject); //


    }


    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        //this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
        // 或者
        this.strictUpdateFill(metaObject, "updateTime", () -> LocalDateTime.now(), LocalDateTime.class); // 起始版本 3.3.3(推荐)
        // 或者
        //this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
        String updateUser = "";
        try {
            UserDTO user = UserHolder.getUser();
            updateUser = user.getUsername();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        this.strictInsertFill(metaObject, "updateUser", String.class, updateUser);
    }
}
