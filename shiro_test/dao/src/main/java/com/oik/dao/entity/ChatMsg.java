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
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author oik
 * @since 2022-12-22
 */
@Getter
@Setter
@TableName("t_chat_msg")
@ApiModel(value = "ChatMsg对象", description = "")
@Accessors(chain = true)
public class ChatMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId("id")
    private Long id;

    @ApiModelProperty("发送者的id")
    @TableField("send_id")
    private Long sendId;

    @ApiModelProperty("接收者的id")
    @TableField("accept_id")
    private Long acceptId;

    @ApiModelProperty("消息")
    @TableField("msg")
    private String msg;

    @ApiModelProperty("接收状态")
    @TableField("sign_flag")
    private Byte signFlag;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}
