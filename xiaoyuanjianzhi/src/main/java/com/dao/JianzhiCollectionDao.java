package com.dao;

import com.entity.JianzhiCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JianzhiCollectionView;

/**
 * 兼职收藏 Dao 接口
 *
 * @author 
 */
public interface JianzhiCollectionDao extends BaseMapper<JianzhiCollectionEntity> {

   List<JianzhiCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
