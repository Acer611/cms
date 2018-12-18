package com.dragon.media.mediainfo.dao;

import java.util.Date;
import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.dragon.media.mediainfo.entity.*;

/**
 * Mediainfo Dao
 */
@SqlResource("media.media")
public interface MediainfoDao extends BaseMapper<Mediainfo>{
    public PageQuery<Mediainfo> queryByCondition(PageQuery query);
    public void batchDelMediainfoByIds( List<String> ids);

    //批量上线
    public void batchOnlineMediainfoByIds(List<String> ids, Date date,Date updateDate);


    //批量下线
    public void batchOfflineMediainfoByIds( List<String> ids,Date updateDate);
}