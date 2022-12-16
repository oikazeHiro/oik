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
@TableName("sys_job_log")
@ApiModel(value = "JobLog对象", description = "")
public class JobLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("任务日志id")
      @TableId(value = "LOG_ID", type = IdType.AUTO)
    private Long logId;

    @ApiModelProperty("任务id")
    @TableField("JOB_ID")
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

    @ApiModelProperty("任务状态    0：成功    1：失败")
    @TableField("`STATUS`")
    private String status;

    @ApiModelProperty("失败信息")
    @TableField("`ERROR`")
    private String error;

    @ApiModelProperty("耗时(单位：毫秒)")
    @TableField("TIMES")
    private Long times;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;
}
