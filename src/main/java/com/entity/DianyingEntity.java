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
 * 电影
 *
 * @author 
 * @email
 * @date 2021-04-05
 */
@TableName("dianying")
public class DianyingEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DianyingEntity() {

	}

	public DianyingEntity(T t) {
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
     * 电影名称
     */
    @TableField(value = "dianying_name")

    private String dianyingName;


    /**
     * 电影主演
     */
    @TableField(value = "dianying_zhuyan")

    private String dianyingZhuyan;


    /**
     * 电影海报
     */
    @TableField(value = "yangban_photo")

    private String yangbanPhoto;


    /**
     * 电影类型名称
     */
    @TableField(value = "leix_types")

    private Integer leixTypes;


    /**
     * 电影类型名称
     */
    @TableField(value = "voting")

    private Integer voting;


    /**
     * 项目具体内容
     */
    @TableField(value = "dianying_content")

    private String dianyingContent;


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
	 * 设置：电影名称
	 */
    public String getDianyingName() {
        return dianyingName;
    }


    /**
	 * 获取：电影名称
	 */

    public void setDianyingName(String dianyingName) {
        this.dianyingName = dianyingName;
    }
    /**
	 * 设置：电影主演
	 */
    public String getDianyingZhuyan() {
        return dianyingZhuyan;
    }


    public Integer getVoting() {
        return voting;
    }

    public void setVoting(Integer voting) {
        this.voting = voting;
    }

    /**
	 * 获取：电影主演
	 */

    public void setDianyingZhuyan(String dianyingZhuyan) {
        this.dianyingZhuyan = dianyingZhuyan;
    }
    /**
	 * 设置：电影海报
	 */
    public String getYangbanPhoto() {
        return yangbanPhoto;
    }


    /**
	 * 获取：电影海报
	 */

    public void setYangbanPhoto(String yangbanPhoto) {
        this.yangbanPhoto = yangbanPhoto;
    }
    /**
	 * 设置：电影类型名称
	 */
    public Integer getLeixTypes() {
        return leixTypes;
    }


    /**
	 * 获取：电影类型名称
	 */

    public void setLeixTypes(Integer leixTypes) {
        this.leixTypes = leixTypes;
    }
    /**
	 * 设置：项目具体内容
	 */
    public String getDianyingContent() {
        return dianyingContent;
    }


    /**
	 * 获取：项目具体内容
	 */

    public void setDianyingContent(String dianyingContent) {
        this.dianyingContent = dianyingContent;
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
        return "Dianying{" +
            "id=" + id +
            ", dianyingName=" + dianyingName +
            ", dianyingZhuyan=" + dianyingZhuyan +
            ", yangbanPhoto=" + yangbanPhoto +
            ", leixTypes=" + leixTypes +
            ", dianyingContent=" + dianyingContent +
            ", createTime=" + createTime +
        "}";
    }
}
