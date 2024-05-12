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
 * 兼职申请
 *
 * @author 
 * @email
 */
@TableName("jianzhi_order")
public class JianzhiOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JianzhiOrderEntity() {

	}

	public JianzhiOrderEntity(T t) {
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
     * 订单编号
     */
    @ColumnInfo(comment="订单编号",type="varchar(200)")
    @TableField(value = "jianzhi_order_uuid_number")

    private String jianzhiOrderUuidNumber;


    /**
     * 兼职
     */
    @ColumnInfo(comment="兼职",type="int(11)")
    @TableField(value = "jianzhi_id")

    private Integer jianzhiId;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 订单类型
     */
    @ColumnInfo(comment="订单类型",type="int(11)")
    @TableField(value = "jianzhi_order_types")

    private Integer jianzhiOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="订单创建时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
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
	 * 获取：订单编号
	 */
    public String getJianzhiOrderUuidNumber() {
        return jianzhiOrderUuidNumber;
    }
    /**
	 * 设置：订单编号
	 */

    public void setJianzhiOrderUuidNumber(String jianzhiOrderUuidNumber) {
        this.jianzhiOrderUuidNumber = jianzhiOrderUuidNumber;
    }
    /**
	 * 获取：兼职
	 */
    public Integer getJianzhiId() {
        return jianzhiId;
    }
    /**
	 * 设置：兼职
	 */

    public void setJianzhiId(Integer jianzhiId) {
        this.jianzhiId = jianzhiId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getJianzhiOrderTypes() {
        return jianzhiOrderTypes;
    }
    /**
	 * 设置：订单类型
	 */

    public void setJianzhiOrderTypes(Integer jianzhiOrderTypes) {
        this.jianzhiOrderTypes = jianzhiOrderTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "JianzhiOrder{" +
            ", id=" + id +
            ", jianzhiOrderUuidNumber=" + jianzhiOrderUuidNumber +
            ", jianzhiId=" + jianzhiId +
            ", yonghuId=" + yonghuId +
            ", jianzhiOrderTypes=" + jianzhiOrderTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
