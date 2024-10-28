package com.entity.model;

import com.entity.DianyingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 电影
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-04-05
 */
public class DianyingModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 电影名称
     */
    private String dianyingName;


    /**
     * 电影主演
     */
    private String dianyingZhuyan;


    /**
     * 电影海报
     */
    private String yangbanPhoto;


    /**
     * 电影类型名称
     */
    private Integer leixTypes;


    /**
     * 项目具体内容
     */
    private String dianyingContent;


    /**
     * 创建时间 show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：电影名称
	 */
    public String getDianyingName() {
        return dianyingName;
    }


    /**
	 * 设置：电影名称
	 */
    public void setDianyingName(String dianyingName) {
        this.dianyingName = dianyingName;
    }
    /**
	 * 获取：电影主演
	 */
    public String getDianyingZhuyan() {
        return dianyingZhuyan;
    }


    /**
	 * 设置：电影主演
	 */
    public void setDianyingZhuyan(String dianyingZhuyan) {
        this.dianyingZhuyan = dianyingZhuyan;
    }
    /**
	 * 获取：电影海报
	 */
    public String getYangbanPhoto() {
        return yangbanPhoto;
    }


    /**
	 * 设置：电影海报
	 */
    public void setYangbanPhoto(String yangbanPhoto) {
        this.yangbanPhoto = yangbanPhoto;
    }
    /**
	 * 获取：电影类型名称
	 */
    public Integer getLeixTypes() {
        return leixTypes;
    }


    /**
	 * 设置：电影类型名称
	 */
    public void setLeixTypes(Integer leixTypes) {
        this.leixTypes = leixTypes;
    }
    /**
	 * 获取：项目具体内容
	 */
    public String getDianyingContent() {
        return dianyingContent;
    }


    /**
	 * 设置：项目具体内容
	 */
    public void setDianyingContent(String dianyingContent) {
        this.dianyingContent = dianyingContent;
    }
    /**
	 * 获取：创建时间 show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
