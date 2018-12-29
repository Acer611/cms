package com.dragon.media.category.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.dragon.media.category.entity.*;

/**
 * Mediacategory Dao
 */
@SqlResource("category.mediacategory")
public interface MediacategoryDao extends BaseMapper<Mediacategory>{

    public PageQuery<Mediacategory> queryByCondition(PageQuery query);

    public void batchDelMediacategoryByIds( List<Long> ids);

    //查询一级分类
    PageQuery queryCategory(PageQuery page);
    /**
     * 根据code 查询下级分类
     * @param categoryCode
     * @return
     */
    List<Mediacategory> queryCategoryByCode(String categoryCode);

    /**
     * 获取当前专辑下的分类信息
     * @param mediaguid
     * @return
     */
    List<Mediacategory> queryCategoryByMediaGuid(String mediaguid);
}