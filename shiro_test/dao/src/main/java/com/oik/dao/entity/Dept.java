package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2022-12-16
 */
@Getter
@Setter
@TableName("sys_dept")
@ApiModel(value = "Dept对象", description = "")
@Accessors(chain = true)
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门ID")
    @TableId(value = "DEPT_ID", type = IdType.ASSIGN_ID)
    private Long deptId;

    @ApiModelProperty("上级部门ID")
    @TableField("PARENT_ID")
    private Long parentId;

    @ApiModelProperty("部门名称")
    @TableField("DEPT_NAME")
    private String deptName;

    @ApiModelProperty("排序")
    @TableField("ORDER_NUM")
    private Long orderNum;

    @TableField(value = "UPDATE_USER",fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @ApiModelProperty("修改时间")
    @TableField(value = "UPDATE_TIME",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("`STATUS`")
    private String status;

    @TableField(value = "CREATE_USER",fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "CREATE_USERNAME",fill = FieldFill.INSERT)
    private String createUsername;

    @TableField(value = "UPDATE_USERNAME",fill = FieldFill.INSERT_UPDATE)
    private String updateUsername;


}
