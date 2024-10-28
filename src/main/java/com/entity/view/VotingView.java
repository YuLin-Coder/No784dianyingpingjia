package com.entity.view;

import com.entity.VotingEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;

/**
 * 投票
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-04-05
 */
@TableName("voting")
public class VotingView extends VotingEntity implements Serializable {
    private static final long serialVersionUID = 1L;



		//级联表 dianying
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
				* 电影类型名称的值
				*/
				private String leixValue;
			/**
			* 项目具体内容
			*/
			private String dianyingContent;

		//级联表 yonghu
			/**
			* 姓名
			*/
			private String yonghuName;
			/**
			* 性别
			*/
			private Integer sexTypes;
				/**
				* 性别的值
				*/
				private String sexValue;
			/**
			* 身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 手机号
			*/
			private String yonghuPhone;
			/**
			* 照片
			*/
			private String yonghuPhoto;

	public VotingView() {

	}

	public VotingView(VotingEntity votingEntity) {
		try {
			BeanUtils.copyProperties(this, votingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}







				//级联表的get和set dianying
					/**
					* 获取： 电影名称
					*/
					public String getDianyingName() {
						return dianyingName;
					}
					/**
					* 设置： 电影名称
					*/
					public void setDianyingName(String dianyingName) {
						this.dianyingName = dianyingName;
					}
					/**
					* 获取： 电影主演
					*/
					public String getDianyingZhuyan() {
						return dianyingZhuyan;
					}
					/**
					* 设置： 电影主演
					*/
					public void setDianyingZhuyan(String dianyingZhuyan) {
						this.dianyingZhuyan = dianyingZhuyan;
					}
					/**
					* 获取： 电影海报
					*/
					public String getYangbanPhoto() {
						return yangbanPhoto;
					}
					/**
					* 设置： 电影海报
					*/
					public void setYangbanPhoto(String yangbanPhoto) {
						this.yangbanPhoto = yangbanPhoto;
					}
					/**
					* 获取： 电影类型名称
					*/
					public Integer getLeixTypes() {
						return leixTypes;
					}
					/**
					* 设置： 电影类型名称
					*/
					public void setLeixTypes(Integer leixTypes) {
						this.leixTypes = leixTypes;
					}


						/**
						* 获取： 电影类型名称的值
						*/
						public String getLeixValue() {
							return leixValue;
						}
						/**
						* 设置： 电影类型名称的值
						*/
						public void setLeixValue(String leixValue) {
							this.leixValue = leixValue;
						}
					/**
					* 获取： 项目具体内容
					*/
					public String getDianyingContent() {
						return dianyingContent;
					}
					/**
					* 设置： 项目具体内容
					*/
					public void setDianyingContent(String dianyingContent) {
						this.dianyingContent = dianyingContent;
					}






















				//级联表的get和set yonghu
					/**
					* 获取： 姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 性别
					*/
					public Integer getSexTypes() {
						return sexTypes;
					}
					/**
					* 设置： 性别
					*/
					public void setSexTypes(Integer sexTypes) {
						this.sexTypes = sexTypes;
					}


						/**
						* 获取： 性别的值
						*/
						public String getSexValue() {
							return sexValue;
						}
						/**
						* 设置： 性别的值
						*/
						public void setSexValue(String sexValue) {
							this.sexValue = sexValue;
						}
					/**
					* 获取： 身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 照片
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}



}
