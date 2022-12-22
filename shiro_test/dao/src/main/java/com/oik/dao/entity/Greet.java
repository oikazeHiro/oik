package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-12-15
 */
@Getter
@Setter
@TableName("sys_greet")
@ApiModel(value = "Greet对象", description = "")
@Accessors(chain = true)
public class Greet implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Integer id;

    @TableField("greet")
    private String greet;

    @TableField("creat_by")
    private String creatBy;
    @TableField("sort")
    private Integer sort;

    @TableField("creat_time")
    private LocalDateTime creatTime;

    @TableField("update_by")
    private String updateBy;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
