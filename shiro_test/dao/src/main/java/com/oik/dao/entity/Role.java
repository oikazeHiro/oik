package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "")
@ToString
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
      @TableId(value = "ROLE_ID", type = IdType.AUTO)
    private Long roleId;

    @ApiModelProperty("角色名称")
    @TableField("ROLE_NAME")
    private String roleName;

    @ApiModelProperty("角色描述")
    @TableField("REMARK")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @TableField("MODIFY_TIME")
    private LocalDateTime modifyTime;

    @TableField("`STATUS`")
    private String status;

    @TableField("CREATE_USERNAME")
    private String createUsername;

    @TableField("CREATE_USER_ID")
    private Long createUserId;

    @TableField("MODIFY_USERNAME")
    private String modifyUsername;

    @TableField("MODIFY_USER_ID")
    private Long modifyUserId;

    @TableField("DATA_SCOPE")
    private Byte dataScope;

    @TableField(exist = false)
    private LocalDateTime startTime;

    @TableField(exist = false)
    private LocalDateTime endTime;
}
