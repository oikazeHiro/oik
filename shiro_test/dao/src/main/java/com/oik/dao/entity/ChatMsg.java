package com.oik.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
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
 * @since 2022-12-22
 */
@Getter
@Setter
@TableName("t_chat_msg")
@ApiModel(value = "ChatMsg对象", description = "")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChatMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty("发送者的id")
    @TableField("send_id")
    private String sendId;

    @ApiModelProperty("接收者的id")
    @TableField("accept_id")
    private String acceptId;

    @ApiModelProperty("消息")
    @TableField("msg")
    private String msg;

    @ApiModelProperty("接收状态")
    @TableField("sign_flag")
    private Integer signFlag;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public ChatMsg(String sendId, String acceptId,String msg) {
        this.sendId = sendId;
        this.acceptId = acceptId;
        this.msg = msg;
    }
}
