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

    //更新
    public void update(Mediainfo mediainfo);

    /**
     * 根据ID查询专辑信息
     * @param mediaGuid
     * @return
     */
    public List<Mediainfo> queryMediaById(String mediaGuid);

    /**
     * 根据ID查询演播者信息
     * @param mediaGuid
     * @return
     */
    public List<Mediainfo> queryAuthorById(String mediaGuid);

    /**
     * 到期且定更专辑信息
     * @param idList
     * @param updateDate
     */
    void expireMediainfo(List<String> idList, Date updateDate);

    /**
     * 根据Guid修改专辑的状态
     * @param guid
     * @param authState
     * @param state
     */
    void updateMediaStateByGuid(String guid, Integer authState, Integer state,Date updateDate);
}