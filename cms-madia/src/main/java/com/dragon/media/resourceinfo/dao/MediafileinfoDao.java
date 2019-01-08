package com.dragon.media.resourceinfo.dao;

import java.util.Date;
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

    /**
     * 批量上线音频资源
     * @param ids
     * @param updateDate
     * @param updateDate1
     */
    void batchOnlineMediainfoByIds(List<String> ids, Date updateDate, Date updateDate1);

    /**
     * 批量下线音频资源
     * @param ids
     * @param updateDate
     */
    void batchOfflineMediainfoByIds(List<String> ids, Date updateDate);

    /**
     * 批量添加资源标签
     * @param idList
     * @param tags
     */
    void batchAddTags(List<String> idList, String tags,Date updateDate);

    /**
     * 根据MediaGuid上线音频资源
     * @param idList
     * @param updateDate
     */
    void onLineMediaFileInfoByMediaGuid(List<String> idList, Date updateDate);

    /**
     *根据MediaGuid上线音频资源
     * @param idList
     * @param updateDate
     */
    void offLineMediaFileInfoByMediaGuid(List<String> idList, Date updateDate);

    /**
     * 根据MediaGuid到期且定更音频资源
     * @param idList
     * @param updateDate
     */
    void expireMediaFileInfoByMediaGuid(List<String> idList, Date updateDate);

    /**
     * 修改资源信息（修改名称和标签）
     * @param resource
     */
    void updateResource(Mediafileinfo resource);


    /**
     * 根据guid修改资源的状态
     * @param guid
     * @param state
     */
    void updateResourceStateByGuid(String guid, Integer state,Date updateDate);
}