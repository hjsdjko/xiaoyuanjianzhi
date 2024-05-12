package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 兼职
 *
 * @author 
 * @email
 */
@TableName("jianzhi")
public class JianzhiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JianzhiEntity() {

	}

	public JianzhiEntity(T t) {
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
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 商家
     */
    @ColumnInfo(comment="商家",type="int(11)")
    @TableField(value = "shangjia_id")

    private Integer shangjiaId;


    /**
     * 兼职名称
     */
    @ColumnInfo(comment="兼职名称",type="varchar(200)")
    @TableField(value = "jianzhi_name")

    private String jianzhiName;


    /**
     * 兼职编号
     */
    @ColumnInfo(comment="兼职编号",type="varchar(200)")
    @TableField(value = "jianzhi_uuid_number")

    private String jianzhiUuidNumber;


    /**
     * 兼职照片
     */
    @ColumnInfo(comment="兼职照片",type="varchar(200)")
    @TableField(value = "jianzhi_photo")

    private String jianzhiPhoto;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 兼职类型
     */
    @ColumnInfo(comment="兼职类型",type="int(11)")
    @TableField(value = "jianzhi_types")

    private Integer jianzhiTypes;


    /**
     * 结算类型
     */
    @ColumnInfo(comment="结算类型",type="int(11)")
    @TableField(value = "jianzhi_jiesuan_types")

    private Integer jianzhiJiesuanTypes;


    /**
     * 招聘人数
     */
    @ColumnInfo(comment="招聘人数",type="int(11)")
    @TableField(value = "jianzhi_kucun_number")

    private Integer jianzhiKucunNumber;


    /**
     * 联系方式
     */
    @ColumnInfo(comment="联系方式",type="varchar(200)")
    @TableField(value = "jianzhi_phone")

    private String jianzhiPhone;


    /**
     * 工作地点
     */
    @ColumnInfo(comment="工作地点",type="varchar(200)")
    @TableField(value = "jianzhi_address")

    private String jianzhiAddress;


    /**
     * 工作时间
     */
    @ColumnInfo(comment="工作时间",type="varchar(200)")
    @TableField(value = "jianzhi_shijian")

    private String jianzhiShijian;


    /**
     * 薪资/小时
     */
    @ColumnInfo(comment="薪资/小时",type="varchar(200)")
    @TableField(value = "jianzhi_daiyu")

    private String jianzhiDaiyu;


    /**
     * 工作内容介绍
     */
    @ColumnInfo(comment="工作内容介绍",type="longtext")
    @TableField(value = "jianzhi_content")

    private String jianzhiContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "jianzhi_delete")

    private Integer jianzhiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：商家
	 */
    public Integer getShangjiaId() {
        return shangjiaId;
    }
    /**
	 * 设置：商家
	 */

    public void setShangjiaId(Integer shangjiaId) {
        this.shangjiaId = shangjiaId;
    }
    /**
	 * 获取：兼职名称
	 */
    public String getJianzhiName() {
        return jianzhiName;
    }
    /**
	 * 设置：兼职名称
	 */

    public void setJianzhiName(String jianzhiName) {
        this.jianzhiName = jianzhiName;
    }
    /**
	 * 获取：兼职编号
	 */
    public String getJianzhiUuidNumber() {
        return jianzhiUuidNumber;
    }
    /**
	 * 设置：兼职编号
	 */

    public void setJianzhiUuidNumber(String jianzhiUuidNumber) {
        this.jianzhiUuidNumber = jianzhiUuidNumber;
    }
    /**
	 * 获取：兼职照片
	 */
    public String getJianzhiPhoto() {
        return jianzhiPhoto;
    }
    /**
	 * 设置：兼职照片
	 */

    public void setJianzhiPhoto(String jianzhiPhoto) {
        this.jianzhiPhoto = jianzhiPhoto;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }
    /**
	 * 设置：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }
    /**
	 * 设置：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：兼职类型
	 */
    public Integer getJianzhiTypes() {
        return jianzhiTypes;
    }
    /**
	 * 设置：兼职类型
	 */

    public void setJianzhiTypes(Integer jianzhiTypes) {
        this.jianzhiTypes = jianzhiTypes;
    }
    /**
	 * 获取：结算类型
	 */
    public Integer getJianzhiJiesuanTypes() {
        return jianzhiJiesuanTypes;
    }
    /**
	 * 设置：结算类型
	 */

    public void setJianzhiJiesuanTypes(Integer jianzhiJiesuanTypes) {
        this.jianzhiJiesuanTypes = jianzhiJiesuanTypes;
    }
    /**
	 * 获取：招聘人数
	 */
    public Integer getJianzhiKucunNumber() {
        return jianzhiKucunNumber;
    }
    /**
	 * 设置：招聘人数
	 */

    public void setJianzhiKucunNumber(Integer jianzhiKucunNumber) {
        this.jianzhiKucunNumber = jianzhiKucunNumber;
    }
    /**
	 * 获取：联系方式
	 */
    public String getJianzhiPhone() {
        return jianzhiPhone;
    }
    /**
	 * 设置：联系方式
	 */

    public void setJianzhiPhone(String jianzhiPhone) {
        this.jianzhiPhone = jianzhiPhone;
    }
    /**
	 * 获取：工作地点
	 */
    public String getJianzhiAddress() {
        return jianzhiAddress;
    }
    /**
	 * 设置：工作地点
	 */

    public void setJianzhiAddress(String jianzhiAddress) {
        this.jianzhiAddress = jianzhiAddress;
    }
    /**
	 * 获取：工作时间
	 */
    public String getJianzhiShijian() {
        return jianzhiShijian;
    }
    /**
	 * 设置：工作时间
	 */

    public void setJianzhiShijian(String jianzhiShijian) {
        this.jianzhiShijian = jianzhiShijian;
    }
    /**
	 * 获取：薪资/小时
	 */
    public String getJianzhiDaiyu() {
        return jianzhiDaiyu;
    }
    /**
	 * 设置：薪资/小时
	 */

    public void setJianzhiDaiyu(String jianzhiDaiyu) {
        this.jianzhiDaiyu = jianzhiDaiyu;
    }
    /**
	 * 获取：工作内容介绍
	 */
    public String getJianzhiContent() {
        return jianzhiContent;
    }
    /**
	 * 设置：工作内容介绍
	 */

    public void setJianzhiContent(String jianzhiContent) {
        this.jianzhiContent = jianzhiContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getJianzhiDelete() {
        return jianzhiDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setJianzhiDelete(Integer jianzhiDelete) {
        this.jianzhiDelete = jianzhiDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Jianzhi{" +
            ", id=" + id +
            ", shangjiaId=" + shangjiaId +
            ", jianzhiName=" + jianzhiName +
            ", jianzhiUuidNumber=" + jianzhiUuidNumber +
            ", jianzhiPhoto=" + jianzhiPhoto +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", jianzhiTypes=" + jianzhiTypes +
            ", jianzhiJiesuanTypes=" + jianzhiJiesuanTypes +
            ", jianzhiKucunNumber=" + jianzhiKucunNumber +
            ", jianzhiPhone=" + jianzhiPhone +
            ", jianzhiAddress=" + jianzhiAddress +
            ", jianzhiShijian=" + jianzhiShijian +
            ", jianzhiDaiyu=" + jianzhiDaiyu +
            ", jianzhiContent=" + jianzhiContent +
            ", jianzhiDelete=" + jianzhiDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
