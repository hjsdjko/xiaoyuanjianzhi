package com.entity.model;

import com.entity.JianzhiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 兼职
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JianzhiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 商家
     */
    private Integer shangjiaId;


    /**
     * 兼职名称
     */
    private String jianzhiName;


    /**
     * 兼职编号
     */
    private String jianzhiUuidNumber;


    /**
     * 兼职照片
     */
    private String jianzhiPhoto;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 兼职类型
     */
    private Integer jianzhiTypes;


    /**
     * 结算类型
     */
    private Integer jianzhiJiesuanTypes;


    /**
     * 招聘人数
     */
    private Integer jianzhiKucunNumber;


    /**
     * 联系方式
     */
    private String jianzhiPhone;


    /**
     * 工作地点
     */
    private String jianzhiAddress;


    /**
     * 工作时间
     */
    private String jianzhiShijian;


    /**
     * 薪资/小时
     */
    private String jianzhiDaiyu;


    /**
     * 工作内容介绍
     */
    private String jianzhiContent;


    /**
     * 逻辑删除
     */
    private Integer jianzhiDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
