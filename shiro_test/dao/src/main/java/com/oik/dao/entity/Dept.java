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
@TableName("sys_dept")
@ApiModel(value = "Dept对象", description = "")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门ID")
      @TableId(value = "DEPT_ID", type = IdType.AUTO)
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
}
