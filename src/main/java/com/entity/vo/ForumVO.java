package com.entity.vo;

import com.entity.ForumEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 论坛
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-04-05
 */
@TableName("forum")
public class ForumVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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

}
