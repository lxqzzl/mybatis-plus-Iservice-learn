package com.generator.mppre.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * @Description TestDO实体类
 * @author lxq
 * @since 2020-09-20 18:19:08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_test")
@ApiModel(value="TestDO对象model", description="TestDO原始数据类型，与表中字段一一对应")
public class TestDO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     *  主键id(数据库自增)
     */
    @ApiModelProperty(value = "主键id(数据库自增)")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     *  uuid（后台定义）
     */
    @ApiModelProperty(value = "uuid（后台定义）")
    @TableField("uuid")
    private String uuid;
    /**
     *  创建时间(时间戳)，格式为YY-MM-DD hh:mm:ss
     */
    @ApiModelProperty(value = "创建时间(时间戳)，格式为YY-MM-DD hh:mm:ss")
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;
    /**
     *  创建人id（系统默认为0）
     */
    @ApiModelProperty(value = "创建人id（系统默认为0）")
    @TableField("gmt_create_id")
    private Long gmtCreateId;
    /**
     *  最近修改时间(时间戳)，格式为YY-MM-DD hh:mm:ss
     */
    @ApiModelProperty(value = "最近修改时间(时间戳)，格式为YY-MM-DD hh:mm:ss")
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;
    /**
     *  修改人id（系统默认为0）
     */
    @ApiModelProperty(value = "修改人id（系统默认为0）")
    @TableField("gmt_modified_id")
    private Long gmtModifiedId;
    /**
     *  备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;
    /**
     *  名称
     */
    @ApiModelProperty(value = "名称")
    @TableField("test_name")
    private String testName;
    /**
     *  年龄
     */
    @ApiModelProperty(value = "年龄")
    @TableField("test_age")
    private Integer testAge;
    /**
     *  性别
     */
    @ApiModelProperty(value = "性别")
    @TableField("test_gender")
    private Integer testGender;

    
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TestDO{" +
         "id=" + id
         +", uuid=" + uuid 
         +", gmtCreate=" + gmtCreate 
         +", gmtCreateId=" + gmtCreateId 
         +", gmtModified=" + gmtModified 
         +", gmtModifiedId=" + gmtModifiedId 
         +", remark=" + remark 
         +", testName=" + testName 
         +", testAge=" + testAge 
         +", testGender=" + testGender 
        +"}";
    }
}
