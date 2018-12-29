package com.dragon.media.author.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.dragon.media.author.entity.*;

/**
 * authorinfo Dao
 */
@SqlResource("author.authorinfo")
public interface AuthorinfoDao extends BaseMapper<Authorinfo>{
    public PageQuery<Authorinfo> queryByCondition(PageQuery query);
    public void batchDelAuthorinfoByIds( List<Long> ids);
}