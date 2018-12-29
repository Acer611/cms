package com.dragon.media.categorydetail.dao;

import java.util.List;

import com.dragon.media.category.entity.Mediacategory;
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

    //添加专辑和分类的关联关系
    public void save(Mediacategorydetail mediacategorydetail);

    //删除专辑和分类的关联关系
    void deleteCategoryDetailByMediaGuid(String mediaGuid);


}