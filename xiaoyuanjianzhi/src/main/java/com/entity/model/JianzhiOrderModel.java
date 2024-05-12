package com.entity.model;

import com.entity.JianzhiOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 兼职申请
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JianzhiOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单编号
     */
    private String jianzhiOrderUuidNumber;


    /**
     * 兼职
     */
    private Integer jianzhiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 订单类型
     */
    private Integer jianzhiOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
