package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Getter
@Setter
@TableName("sys_test")
@ApiModel(value = "Test对象", description = "")
@Accessors(chain = true)
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("FIELD1")
    private String field1;

    @TableField("FIELD2")
    private Integer field2;

    @TableField("FIELD3")
    private String field3;

    @TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
