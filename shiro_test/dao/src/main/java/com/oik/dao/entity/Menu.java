package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "")
@Accessors(chain = true)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单/按钮ID")
    @TableId(value = "MENU_ID", type = IdType.ASSIGN_UUID)
    private String menuId;

    @ApiModelProperty("上级菜单ID")
    @TableField("PARENT_ID")
    private String parentId;

    @ApiModelProperty("菜单/按钮名称")
    @TableField("MENU_NAME")
    private String menuName;

    @ApiModelProperty("对应路由path")
    @TableField("PATH")
    private String path;

    @ApiModelProperty("对应路由组件component")
    @TableField("COMPONENT")
    private String component;

    @ApiModelProperty("权限标识")
    @TableField("PERMS")
    private String perms;

    @ApiModelProperty("图标")
    @TableField("ICON")
    private String icon;

    @ApiModelProperty("类型 0菜单 1按钮")
    @TableField("TYPE")
    private String type;

    @TableField("ORDER_NUM")
    private Long orderNum;

    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("`STATUS`")
    private String status;

    @TableField(value = "CREATE_USERNAME", fill = FieldFill.INSERT)
    private String createUsername;

    @TableField(value = "CREATE_USER_ID", fill = FieldFill.INSERT)
    private String createUserId;

    @TableField(value = "UPDATE_USERNAME", fill = FieldFill.INSERT_UPDATE)
    private String updateUsername;

    @TableField(value = "UPDATE_USER_ID", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

    @TableField(exist = false)
    private List<Menu> children;
}
