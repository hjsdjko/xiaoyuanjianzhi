package com.dao;

import com.entity.JianzhiLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JianzhiLiuyanView;

/**
 * 兼职留言 Dao 接口
 *
 * @author 
 */
public interface JianzhiLiuyanDao extends BaseMapper<JianzhiLiuyanEntity> {

   List<JianzhiLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
