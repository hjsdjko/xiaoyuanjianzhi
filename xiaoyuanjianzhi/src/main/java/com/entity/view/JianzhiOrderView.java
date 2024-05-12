package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.JianzhiOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 兼职申请
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("jianzhi_order")
public class JianzhiOrderView extends JianzhiOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String jianzhiOrderValue;

	//级联表 兼职
					 
		/**
		* 兼职 的 商家
		*/
		@ColumnInfo(comment="商家",type="int(11)")
		private Integer jianzhiShangjiaId;
		/**
		* 兼职名称
		*/

		@ColumnInfo(comment="兼职名称",type="varchar(200)")
		private String jianzhiName;
		/**
		* 兼职编号
		*/

		@ColumnInfo(comment="兼职编号",type="varchar(200)")
		private String jianzhiUuidNumber;
		/**
		* 兼职照片
		*/

		@ColumnInfo(comment="兼职照片",type="varchar(200)")
		private String jianzhiPhoto;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 兼职类型
		*/
		@ColumnInfo(comment="兼职类型",type="int(11)")
		private Integer jianzhiTypes;
			/**
			* 兼职类型的值
			*/
			@ColumnInfo(comment="兼职类型的字典表值",type="varchar(200)")
			private String jianzhiValue;
		/**
		* 结算类型
		*/
		@ColumnInfo(comment="结算类型",type="int(11)")
		private Integer jianzhiJiesuanTypes;
			/**
			* 结算类型的值
			*/
			@ColumnInfo(comment="结算类型的字典表值",type="varchar(200)")
			private String jianzhiJiesuanValue;
		/**
		* 招聘人数
		*/

		@ColumnInfo(comment="招聘人数",type="int(11)")
		private Integer jianzhiKucunNumber;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String jianzhiPhone;
		/**
		* 工作地点
		*/

		@ColumnInfo(comment="工作地点",type="varchar(200)")
		private String jianzhiAddress;
		/**
		* 工作时间
		*/

		@ColumnInfo(comment="工作时间",type="varchar(200)")
		private String jianzhiShijian;
		/**
		* 薪资/小时
		*/

		@ColumnInfo(comment="薪资/小时",type="varchar(200)")
		private String jianzhiDaiyu;
		/**
		* 工作内容介绍
		*/

		@ColumnInfo(comment="工作内容介绍",type="longtext")
		private String jianzhiContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer jianzhiDelete;
	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;



	public JianzhiOrderView() {

	}

	public JianzhiOrderView(JianzhiOrderEntity jianzhiOrderEntity) {
		try {
			BeanUtils.copyProperties(this, jianzhiOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getJianzhiOrderValue() {
		return jianzhiOrderValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setJianzhiOrderValue(String jianzhiOrderValue) {
		this.jianzhiOrderValue = jianzhiOrderValue;
	}


	//级联表的get和set 兼职
		/**
		* 获取：兼职 的 商家
		*/
		public Integer getJianzhiShangjiaId() {
			return jianzhiShangjiaId;
		}
		/**
		* 设置：兼职 的 商家
		*/
		public void setJianzhiShangjiaId(Integer jianzhiShangjiaId) {
			this.jianzhiShangjiaId = jianzhiShangjiaId;
		}

		/**
		* 获取： 兼职名称
		*/
		public String getJianzhiName() {
			return jianzhiName;
		}
		/**
		* 设置： 兼职名称
		*/
		public void setJianzhiName(String jianzhiName) {
			this.jianzhiName = jianzhiName;
		}

		/**
		* 获取： 兼职编号
		*/
		public String getJianzhiUuidNumber() {
			return jianzhiUuidNumber;
		}
		/**
		* 设置： 兼职编号
		*/
		public void setJianzhiUuidNumber(String jianzhiUuidNumber) {
			this.jianzhiUuidNumber = jianzhiUuidNumber;
		}

		/**
		* 获取： 兼职照片
		*/
		public String getJianzhiPhoto() {
			return jianzhiPhoto;
		}
		/**
		* 设置： 兼职照片
		*/
		public void setJianzhiPhoto(String jianzhiPhoto) {
			this.jianzhiPhoto = jianzhiPhoto;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}
		/**
		* 获取： 兼职类型
		*/
		public Integer getJianzhiTypes() {
			return jianzhiTypes;
		}
		/**
		* 设置： 兼职类型
		*/
		public void setJianzhiTypes(Integer jianzhiTypes) {
			this.jianzhiTypes = jianzhiTypes;
		}


			/**
			* 获取： 兼职类型的值
			*/
			public String getJianzhiValue() {
				return jianzhiValue;
			}
			/**
			* 设置： 兼职类型的值
			*/
			public void setJianzhiValue(String jianzhiValue) {
				this.jianzhiValue = jianzhiValue;
			}
		/**
		* 获取： 结算类型
		*/
		public Integer getJianzhiJiesuanTypes() {
			return jianzhiJiesuanTypes;
		}
		/**
		* 设置： 结算类型
		*/
		public void setJianzhiJiesuanTypes(Integer jianzhiJiesuanTypes) {
			this.jianzhiJiesuanTypes = jianzhiJiesuanTypes;
		}


			/**
			* 获取： 结算类型的值
			*/
			public String getJianzhiJiesuanValue() {
				return jianzhiJiesuanValue;
			}
			/**
			* 设置： 结算类型的值
			*/
			public void setJianzhiJiesuanValue(String jianzhiJiesuanValue) {
				this.jianzhiJiesuanValue = jianzhiJiesuanValue;
			}

		/**
		* 获取： 招聘人数
		*/
		public Integer getJianzhiKucunNumber() {
			return jianzhiKucunNumber;
		}
		/**
		* 设置： 招聘人数
		*/
		public void setJianzhiKucunNumber(Integer jianzhiKucunNumber) {
			this.jianzhiKucunNumber = jianzhiKucunNumber;
		}

		/**
		* 获取： 联系方式
		*/
		public String getJianzhiPhone() {
			return jianzhiPhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setJianzhiPhone(String jianzhiPhone) {
			this.jianzhiPhone = jianzhiPhone;
		}

		/**
		* 获取： 工作地点
		*/
		public String getJianzhiAddress() {
			return jianzhiAddress;
		}
		/**
		* 设置： 工作地点
		*/
		public void setJianzhiAddress(String jianzhiAddress) {
			this.jianzhiAddress = jianzhiAddress;
		}

		/**
		* 获取： 工作时间
		*/
		public String getJianzhiShijian() {
			return jianzhiShijian;
		}
		/**
		* 设置： 工作时间
		*/
		public void setJianzhiShijian(String jianzhiShijian) {
			this.jianzhiShijian = jianzhiShijian;
		}

		/**
		* 获取： 薪资/小时
		*/
		public String getJianzhiDaiyu() {
			return jianzhiDaiyu;
		}
		/**
		* 设置： 薪资/小时
		*/
		public void setJianzhiDaiyu(String jianzhiDaiyu) {
			this.jianzhiDaiyu = jianzhiDaiyu;
		}

		/**
		* 获取： 工作内容介绍
		*/
		public String getJianzhiContent() {
			return jianzhiContent;
		}
		/**
		* 设置： 工作内容介绍
		*/
		public void setJianzhiContent(String jianzhiContent) {
			this.jianzhiContent = jianzhiContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getJianzhiDelete() {
			return jianzhiDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setJianzhiDelete(Integer jianzhiDelete) {
			this.jianzhiDelete = jianzhiDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 用户头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 用户头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}


	@Override
	public String toString() {
		return "JianzhiOrderView{" +
			", jianzhiOrderValue=" + jianzhiOrderValue +
			", jianzhiName=" + jianzhiName +
			", jianzhiUuidNumber=" + jianzhiUuidNumber +
			", jianzhiPhoto=" + jianzhiPhoto +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", jianzhiKucunNumber=" + jianzhiKucunNumber +
			", jianzhiPhone=" + jianzhiPhone +
			", jianzhiAddress=" + jianzhiAddress +
			", jianzhiShijian=" + jianzhiShijian +
			", jianzhiDaiyu=" + jianzhiDaiyu +
			", jianzhiContent=" + jianzhiContent +
			", jianzhiDelete=" + jianzhiDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
