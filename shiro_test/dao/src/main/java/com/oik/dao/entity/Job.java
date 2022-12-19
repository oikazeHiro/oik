package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("sys_job")
@ApiModel(value = "Job对象", description = "")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("任务id")
    @TableId(value = "JOB_ID", type = IdType.ASSIGN_ID)
    private Long jobId;

    @ApiModelProperty("spring bean名称")
    @TableField("BEAN_NAME")
    private String beanName;

    @ApiModelProperty("方法名")
    @TableField("METHOD_NAME")
    private String methodName;

    @ApiModelProperty("参数")
    @TableField("PARAMS")
    private String params;

    @ApiModelProperty("cron表达式")
    @TableField("CRON_EXPRESSION")
    private String cronExpression;

    @ApiModelProperty("任务状态  0：正常  1：暂停")
    @TableField("`STATUS`")
    private String status;

    @ApiModelProperty("备注")
    @TableField("REMARK")
    private String remark;

    @TableField("UPDATE_USERNAME")
    private String updateUsername;

    @TableField("UPDATE_USER")
    private Long updateUser;

    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    @TableField("CREATE_USERNAME")
    private String createUsername;

    @TableField("CREATE_USER")
    private Long createUser;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
