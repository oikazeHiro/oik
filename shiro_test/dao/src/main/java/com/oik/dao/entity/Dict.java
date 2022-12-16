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
@TableName("sys_dict")
@ApiModel(value = "Dict对象", description = "")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("字典ID")
      @TableId(value = "DICT_ID", type = IdType.AUTO)
    private Long dictId;

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
    @TableField("`TABLE_NAME`")
    private String tableName;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty("创建人名称")
    @TableField("CREATE_USERNAME")
    private String createUsername;

    @ApiModelProperty("创建人ID")
    @TableField("CREATE_USER_ID")
    private Integer createUserId;

    @ApiModelProperty("最后一次修改时间")
    @TableField("MODIFY_TIME")
    private LocalDateTime modifyTime;

    @ApiModelProperty("最后一次修改人名称")
    @TableField("MODIFY_USERNAME")
    private String modifyUsername;

    @ApiModelProperty("最后一次修改人ID")
    @TableField("MODIFY_USER_ID")
    private Integer modifyUserId;

    @ApiModelProperty("逻辑删除：1：生效0：失效")
    @TableField("`STATUS`")
    private String status;

    @ApiModelProperty("其他判断依据")
    @TableField("OTHER_KEYY")
    private String otherKeyy;
}
