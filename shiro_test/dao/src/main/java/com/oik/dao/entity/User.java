package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 *
 * </p>
 *
 * @author oik
 * @since 2022-11-18
 */
@Data
@TableName("sys_user")
@ApiModel(value = "User对象", description = "")
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
      @TableId(value = "USER_ID", type = IdType.AUTO)
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
    @TableField("`DESCRIPTION`")
    private String description;

    @ApiModelProperty("用户头像")
    @TableField("AVATAR")
    private String avatar;

    @TableField("CREATE_USER")
    private String createUser;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @TableField("UPDATE_USER")
    private String updateUser;

    @ApiModelProperty("修改时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;
}
