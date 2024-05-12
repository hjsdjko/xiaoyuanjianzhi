package com.entity.vo;

import com.entity.JianzhiOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 兼职申请
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jianzhi_order")
public class JianzhiOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "jianzhi_order_uuid_number")
    private String jianzhiOrderUuidNumber;


    /**
     * 兼职
     */

    @TableField(value = "jianzhi_id")
    private Integer jianzhiId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 订单类型
     */

    @TableField(value = "jianzhi_order_types")
    private Integer jianzhiOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：订单编号
	 */
    public String getJianzhiOrderUuidNumber() {
        return jianzhiOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setJianzhiOrderUuidNumber(String jianzhiOrderUuidNumber) {
        this.jianzhiOrderUuidNumber = jianzhiOrderUuidNumber;
    }
    /**
	 * 设置：兼职
	 */
    public Integer getJianzhiId() {
        return jianzhiId;
    }


    /**
	 * 获取：兼职
	 */

    public void setJianzhiId(Integer jianzhiId) {
        this.jianzhiId = jianzhiId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getJianzhiOrderTypes() {
        return jianzhiOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setJianzhiOrderTypes(Integer jianzhiOrderTypes) {
        this.jianzhiOrderTypes = jianzhiOrderTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
