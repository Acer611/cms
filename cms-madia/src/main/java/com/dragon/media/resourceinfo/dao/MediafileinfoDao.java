package com.dragon.media.resourceinfo.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.dragon.media.resourceinfo.entity.*;

/**
 * Mediafileinfo Dao
 */
@SqlResource("resource.resource")
public interface MediafileinfoDao extends BaseMapper<Mediafileinfo>{
    public PageQuery<Mediafileinfo> queryByCondition(PageQuery query);
    public void batchDelMediafileinfoByIds( List<Long> ids);
}