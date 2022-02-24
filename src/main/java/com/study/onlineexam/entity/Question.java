package com.study.onlineexam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 陈则法
 * @since 2022-01-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Question对象", description="")
public class Question implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "题目编号")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "题目标题")
    private String title;

    @ApiModelProperty(value = "题目内容")
    private String content;

    @ApiModelProperty(value = "题目类型")
    private Integer type;

    @ApiModelProperty(value = "选项A")
    @TableField("answerA")
    private String answerA;

    @ApiModelProperty(value = "选项B")
    @TableField("answerB")
    private String answerB;

    @ApiModelProperty(value = "选项C")
    @TableField("answerC")
    private String answerC;

    @ApiModelProperty(value = "选项D")
    @TableField("answerD")
    private String answerD;

    @ApiModelProperty(value = "题目答案")
    private String answer;

    @ApiModelProperty(value = "题目解析")
    private String analysis;

    @ApiModelProperty(value = "题目分值")
    private Integer score;

    @ApiModelProperty(value = "题目难度")
    private Integer level;

    @ApiModelProperty(value = "课程ID")
    private Long subjectId;

    @ApiModelProperty(value = "题目状态")
    private Integer state;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;


}
