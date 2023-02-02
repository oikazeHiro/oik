package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "")
@Accessors(chain = true)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    @TableId(value = "ROLE_ID", type = IdType.ASSIGN_UUID)
    private String roleId;

    @ApiModelProperty("角色名称")
    @TableField("ROLE_NAME")
    private String roleName;

    @ApiModelProperty("角色描述")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField(value = "UPDATE_TIME", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField("STATUS")
    private Integer status;

    @TableField(value = "CREATE_USERNAME", fill = FieldFill.INSERT)
    private String createUsername;

    @TableField(value = "CREATE_USER_ID", fill = FieldFill.INSERT)
    private String createUserId;

    @TableField(value = "UPDATE_USERNAME", fill = FieldFill.INSERT_UPDATE)
    private String updateUsername;

    @TableField(value = "UPDATE_USER_ID", fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

    @TableField("DATA_SCOPE")
    private Integer dataScope;

    @TableField(exist = false)
    private List<String> perms = new ArrayList<>();

}
