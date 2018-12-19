package com.dragon.media.categorydetail.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.dragon.media.categorydetail.entity.*;

/**
 * categorydetail Dao
 */
@SqlResource("categorydetail.categorydetail")
public interface MediacategorydetailDao extends BaseMapper<Mediacategorydetail>{
    public PageQuery<Mediacategorydetail> queryByCondition(PageQuery query);
    public void batchDelMediacategorydetailByIds( List<Long> ids);
}