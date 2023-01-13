package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_log")
@ApiModel(value = "Log对象", description = "")
@Accessors(chain = true)
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("日志ID")
    @TableId(value = "ID", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("操作用户")
    @TableField("USERNAME")
    private String username;

    @ApiModelProperty("url")
    @TableField("URL")
    private String url;

    @ApiModelProperty("操作内容")
    @TableField("OPERATION")
    private String operation;

    @ApiModelProperty("耗时")
    @TableField("TIME")
    private Long time;

    @ApiModelProperty("操作方法")
    @TableField("METHOD")
    private String method;

    @ApiModelProperty("方法参数")
    @TableField("PARAMS")
    private String params;

    @ApiModelProperty("操作者IP")
    @TableField("IP")
    private String ip;

    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty("操作地点")
    @TableField("location")
    private String location;

    @ApiModelProperty("应答码")
    @TableField("RESPONSE_CODE")
    private Integer responseCode;

    @ApiModelProperty("应答内容")
    @TableField("RESPONSE_TEXT")
    private String responseText;

    @ApiModelProperty("0系统内部操作日志, 1系统外部请求日志")
    @TableField("TYPE")
    private String type;

    public Log(String username, String url, String operation, Long time, String method, String params, String ip, LocalDateTime createTime, String location, Integer responseCode, String responseText, String type) {
        this.username = username;
        this.url = url;
        this.operation = operation;
        this.time = time;
        this.method = method;
        this.params = params;
        this.ip = ip;
        this.createTime = createTime;
        this.location = location;
        this.responseCode = responseCode;
        this.responseText = responseText;
        this.type = type;
    }
}
