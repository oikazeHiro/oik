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
@TableName("sys_dict")
@ApiModel(value = "Dict对象", description = "")
@Accessors(chain = true)
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典ID")
    @TableId(value = "DICT_ID", type = IdType.ASSIGN_UUID)
    private String dictId;

    @ApiModelProperty("键")
    @TableField("KEYY")
    private Long keyy;

    @ApiModelProperty("值")
    @TableField("VALUEE")
    private String valuee;

    @ApiModelProperty("字段名称")
    @TableField("FIELD_NAME")
    private String fieldName;

    @ApiModelProperty("表名")
    @TableField("TABLE_NAME")
    private String tableName;

    @ApiModelProperty("0 一级")
    @TableField("FATHER_ID")
    private String fatherId;

    @ApiModelProperty("创建时间")
    @TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("创建人ID")
    @TableField("CREATE_USER_ID")
    private String createUserId;

    @TableField("CREATE_USERNAME")
    private String createUsername;

    @ApiModelProperty("最后一次修改时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    @ApiModelProperty("最后一次修改人ID")
    @TableField("UPDATE_USER_ID")
    private Long updateUserId;

    @TableField("UPDATE_USERNAME")
    private String updateUsername;

    @ApiModelProperty("逻辑删除：1：生效0：失效")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty("其他判断依据")
    @TableField("OTHER_KEYY")
    private String otherKeyy;

    @TableField("SORT")
    private Integer sort;

    @TableField(exist = false)
    private List<Dict> children;

}
