package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId("USER_ID")
    private Long userId;

    @ApiModelProperty("用户名")
    @TableField("USERNAME")
    private String username;

    @ApiModelProperty("密码")
    @TableField("`PASSWORD`")
    private String password;

    @ApiModelProperty("部门ID")
    @TableField("DEPT_ID")
    private Long deptId;

    @ApiModelProperty("邮箱")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty("联系电话")
    @TableField("MOBILE")
    private String mobile;

    @ApiModelProperty("状态 0无效 1有效")
    @TableField("`STATUS`")
    private Integer status;

    @ApiModelProperty("最近访问时间")
    @TableField("LAST_LOGIN_TIME")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("性别 0男 1女 2保密")
    @TableField("SSEX")
    private Integer ssex;

    @ApiModelProperty("描述")
    @TableField("DESCRIPTION")
    private String description;

    @ApiModelProperty("用户头像")
    @TableField("AVATAR")
    private String avatar;

    @TableField(value = "CREATE_USERNAME", fill = FieldFill.INSERT)
    private String createUsername;

    @TableField(value = "CREATE_USER_ID", fill = FieldFill.INSERT)
    private Long createUserId;

    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "UPDATE_USERNAME", fill = FieldFill.INSERT_UPDATE)
    private String updateUsername;

    @ApiModelProperty("修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(value = "UPDATE_USER_ID", fill = FieldFill.INSERT_UPDATE)
    private Long updateUserId;


}
