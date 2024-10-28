package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 电影评论
 *
 * @author 
 * @email
 * @date 2021-04-05
 */
@TableName("dianying_commentback")
public class DianyingCommentbackEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DianyingCommentbackEntity() {

	}

	public DianyingCommentbackEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 评论电影
     */
    @TableField(value = "dianying_id")

    private Integer dianyingId;


    /**
     * 评论人
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 评论时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 评论内容
     */
    @TableField(value = "dianying_commentback_content")

    private String dianyingCommentbackContent;


    /**
     * 类型
     */
    @TableField(value = "commentback_types")

    private Integer commentbackTypes;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：评论电影
	 */
    public Integer getDianyingId() {
        return dianyingId;
    }


    /**
	 * 获取：评论电影
	 */

    public void setDianyingId(Integer dianyingId) {
        this.dianyingId = dianyingId;
    }
    /**
	 * 设置：评论人
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：评论人
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：评论时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：评论时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：评论内容
	 */
    public String getDianyingCommentbackContent() {
        return dianyingCommentbackContent;
    }


    /**
	 * 获取：评论内容
	 */

    public void setDianyingCommentbackContent(String dianyingCommentbackContent) {
        this.dianyingCommentbackContent = dianyingCommentbackContent;
    }
    /**
	 * 设置：类型
	 */
    public Integer getCommentbackTypes() {
        return commentbackTypes;
    }


    /**
	 * 获取：类型
	 */

    public void setCommentbackTypes(Integer commentbackTypes) {
        this.commentbackTypes = commentbackTypes;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DianyingCommentback{" +
            "id=" + id +
            ", dianyingId=" + dianyingId +
            ", yonghuId=" + yonghuId +
            ", insertTime=" + insertTime +
            ", dianyingCommentbackContent=" + dianyingCommentbackContent +
            ", commentbackTypes=" + commentbackTypes +
            ", createTime=" + createTime +
        "}";
    }
}
