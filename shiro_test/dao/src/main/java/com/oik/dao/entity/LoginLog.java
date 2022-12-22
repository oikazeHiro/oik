package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-11-18
 */
@Getter
@Setter
@TableName("sys_login_log")
@ApiModel(value = "LoginLog对象", description = "")
@Accessors(chain = true)
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    @TableId("USERNAME")
    private String username;

    @ApiModelProperty("登录时间")
    @TableField("LOGIN_TIME")
    private LocalDateTime loginTime;

    @ApiModelProperty("登录地点")
    @TableField("LOCATION")
    private String location;

    @ApiModelProperty("IP地址")
    @TableField("IP")
    private String ip;
}
