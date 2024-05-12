package com.dao;

import com.entity.JianzhiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JianzhiView;

/**
 * 兼职 Dao 接口
 *
 * @author 
 */
public interface JianzhiDao extends BaseMapper<JianzhiEntity> {

   List<JianzhiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
