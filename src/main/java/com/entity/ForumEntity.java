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
 * 论坛
 *
 * @author 
 * @email
 * @date 2021-04-05
 */
@TableName("forum")
public class ForumEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ForumEntity() {

	}

	public ForumEntity(T t) {
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
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "addtime")

    private Date addtime;


    /**
     * 帖子标题
     */
    @TableField(value = "title")

    private String title;


    /**
     * 帖子内容
     */
    @TableField(value = "content")

    private String content;


    /**
     * 父节点id
     */
    @TableField(value = "parentid")

    private Integer parentid;


    /**
     * 用户id
     */
    @TableField(value = "userid")

    private Integer userid;


    /**
     * 用户名
     */
    @TableField(value = "username")

    private String username;


    /**
     * 状态
     */
    @TableField(value = "isdone")

    private String isdone;


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
	 * 设置：创建时间
	 */
    public Date getAddtime() {
        return addtime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
    /**
	 * 设置：帖子标题
	 */
    public String getTitle() {
        return title;
    }


    /**
	 * 获取：帖子标题
	 */

    public void setTitle(String title) {
        this.title = title;
    }
    /**
	 * 设置：帖子内容
	 */
    public String getContent() {
        return content;
    }


    /**
	 * 获取：帖子内容
	 */

    public void setContent(String content) {
        this.content = content;
    }
    /**
	 * 设置：父节点id
	 */
    public Integer getParentid() {
        return parentid;
    }


    /**
	 * 获取：父节点id
	 */

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
    /**
	 * 设置：用户id
	 */
    public Integer getUserid() {
        return userid;
    }


    /**
	 * 获取：用户id
	 */

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    /**
	 * 设置：用户名
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：用户名
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：状态
	 */
    public String getIsdone() {
        return isdone;
    }


    /**
	 * 获取：状态
	 */

    public void setIsdone(String isdone) {
        this.isdone = isdone;
    }

    @Override
    public String toString() {
        return "Forum{" +
            "id=" + id +
            ", addtime=" + addtime +
            ", title=" + title +
            ", content=" + content +
            ", parentid=" + parentid +
            ", userid=" + userid +
            ", username=" + username +
            ", isdone=" + isdone +
        "}";
    }
}
