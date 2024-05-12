package com.dao;

import com.entity.JianzhiOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JianzhiOrderView;

/**
 * 兼职申请 Dao 接口
 *
 * @author 
 */
public interface JianzhiOrderDao extends BaseMapper<JianzhiOrderEntity> {

   List<JianzhiOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
