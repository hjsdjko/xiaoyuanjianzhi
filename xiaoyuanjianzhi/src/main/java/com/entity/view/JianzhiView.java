package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.JianzhiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 兼职
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("jianzhi")
public class JianzhiView extends JianzhiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 兼职类型的值
	*/
	@ColumnInfo(comment="兼职类型的字典表值",type="varchar(200)")
	private String jianzhiValue;
	/**
	* 结算类型的值
	*/
	@ColumnInfo(comment="结算类型的字典表值",type="varchar(200)")
	private String jianzhiJiesuanValue;

	//级联表 商家
		/**
		* 商家名称
		*/

		@ColumnInfo(comment="商家名称",type="varchar(200)")
		private String shangjiaName;
		/**
		* 联系方式
		*/

		@ColumnInfo(comment="联系方式",type="varchar(200)")
		private String shangjiaPhone;
		/**
		* 邮箱
		*/

		@ColumnInfo(comment="邮箱",type="varchar(200)")
		private String shangjiaEmail;
		/**
		* 商家地址
		*/

		@ColumnInfo(comment="商家地址",type="varchar(200)")
		private String shangjiaAddress;
		/**
		* 商家介绍
		*/

		@ColumnInfo(comment="商家介绍",type="longtext")
		private String shangjiaContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer shangjiaDelete;



	public JianzhiView() {

	}

	public JianzhiView(JianzhiEntity jianzhiEntity) {
		try {
			BeanUtils.copyProperties(this, jianzhiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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
	//当前表的
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


	//级联表的get和set 商家

		/**
		* 获取： 商家名称
		*/
		public String getShangjiaName() {
			return shangjiaName;
		}
		/**
		* 设置： 商家名称
		*/
		public void setShangjiaName(String shangjiaName) {
			this.shangjiaName = shangjiaName;
		}

		/**
		* 获取： 联系方式
		*/
		public String getShangjiaPhone() {
			return shangjiaPhone;
		}
		/**
		* 设置： 联系方式
		*/
		public void setShangjiaPhone(String shangjiaPhone) {
			this.shangjiaPhone = shangjiaPhone;
		}

		/**
		* 获取： 邮箱
		*/
		public String getShangjiaEmail() {
			return shangjiaEmail;
		}
		/**
		* 设置： 邮箱
		*/
		public void setShangjiaEmail(String shangjiaEmail) {
			this.shangjiaEmail = shangjiaEmail;
		}

		/**
		* 获取： 商家地址
		*/
		public String getShangjiaAddress() {
			return shangjiaAddress;
		}
		/**
		* 设置： 商家地址
		*/
		public void setShangjiaAddress(String shangjiaAddress) {
			this.shangjiaAddress = shangjiaAddress;
		}

		/**
		* 获取： 商家介绍
		*/
		public String getShangjiaContent() {
			return shangjiaContent;
		}
		/**
		* 设置： 商家介绍
		*/
		public void setShangjiaContent(String shangjiaContent) {
			this.shangjiaContent = shangjiaContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getShangjiaDelete() {
			return shangjiaDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setShangjiaDelete(Integer shangjiaDelete) {
			this.shangjiaDelete = shangjiaDelete;
		}


	@Override
	public String toString() {
		return "JianzhiView{" +
			", jianzhiValue=" + jianzhiValue +
			", jianzhiJiesuanValue=" + jianzhiJiesuanValue +
			", shangjiaName=" + shangjiaName +
			", shangjiaPhone=" + shangjiaPhone +
			", shangjiaEmail=" + shangjiaEmail +
			", shangjiaAddress=" + shangjiaAddress +
			", shangjiaContent=" + shangjiaContent +
			", shangjiaDelete=" + shangjiaDelete +
			"} " + super.toString();
	}
}
