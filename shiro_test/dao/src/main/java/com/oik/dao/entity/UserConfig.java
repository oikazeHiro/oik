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
@TableName("sys_user_config")
@ApiModel(value = "UserConfig对象", description = "")
@Accessors(chain = true)
public class UserConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId("USER_ID")
    private Long userId;

    @ApiModelProperty("系统主题 dark暗色风格，light明亮风格")
    @TableField("THEME")
    private String theme;

    @ApiModelProperty("系统布局 side侧边栏，head顶部栏")
    @TableField("LAYOUT")
    private String layout;

    @ApiModelProperty("页面风格 1多标签页 0单页")
    @TableField("MULTI_PAGE")
    private String multiPage;

    @ApiModelProperty("页面滚动是否固定侧边栏 1固定 0不固定")
    @TableField("FIX_SIDERBAR")
    private String fixSiderbar;

    @ApiModelProperty("页面滚动是否固定顶栏 1固定 0不固定")
    @TableField("FIX_HEADER")
    private String fixHeader;

    @ApiModelProperty("主题颜色 RGB值")
    @TableField("COLOR")
    private String color;
}
