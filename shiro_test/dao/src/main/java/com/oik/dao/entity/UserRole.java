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
@TableName("sys_user_role")
@ApiModel(value = "UserRole对象", description = "")
@Accessors(chain = true)
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId("USER_ID")
    private String userId;

    @ApiModelProperty("角色ID")
    @TableField("ROLE_ID")
    private String roleId;
}
