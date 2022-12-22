package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
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
@TableName("sys_role_menu")
@ApiModel(value = "RoleMenu对象", description = "")
@Accessors(chain = true)
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("ROLE_ID")
    private Long roleId;

    @TableField("MENU_ID")
    private Long menuId;
}
