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
}