package com.dragon.media.resourceinfo.service;

import java.util.Date;
import java.util.List;

import com.dragon.media.mediainfo.dao.MediainfoDao;
import com.dragon.media.mediainfo.entity.Mediainfo;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.dragon.media.resourceinfo.dao.MediafileinfoDao;
import com.dragon.media.resourceinfo.entity.Mediafileinfo;
import com.ibeetl.admin.core.service.BaseService;

/**
 * Mediafileinfo Service
 */

@Service
@Transactional
public class MediafileinfoService extends BaseService<Mediafileinfo>{

    @Autowired private MediafileinfoDao resourceDao;
    @Autowired private MediainfoDao mediainfoDao;

    public PageQuery<Mediafileinfo>queryByCondition(PageQuery query){
        PageQuery ret =  resourceDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelMediafileinfo(List<Long> ids){
        try {
            //TODO 判断资源是否上线

            resourceDao.batchDelMediafileinfoByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除Mediafileinfo失败", e);
        }
    }

    /**
     * 上线资源
     * @param idList
     */
    public void batchOnlineMediainfo(List<String> idList) {
        try {
            Date updateDate = new Date();
            resourceDao.batchOnlineMediainfoByIds(idList,updateDate,updateDate);
        } catch (Exception e) {
            throw new PlatformException("批量上线音频资源失败", e);
        }
    }

    /**
     * 下线资源
     * @param idList
     */
    public void batchOfflineMediainfo(List<String> idList) {
        try {
            Date updateDate = new Date();
            resourceDao.batchOfflineMediainfoByIds(idList,updateDate);
        } catch (Exception e) {
            throw new PlatformException("批量下线音频资源失败", e);
        }
    }

    /**
     * 批量添加标签
     * @param idList
     * @param tags
     */
    public void batchAddTags(List<String> idList, String tags) {
        try {
            Date updateDate = new Date();
            resourceDao.batchAddTags(idList,tags,updateDate);
        } catch (Exception e) {
            throw new PlatformException("批量添加资源标签失败", e);
        }
    }

    /**
     * 根据MediaGuid上线资源
     * @param idList
     */
    public void onlineMediaFileInfoByMediaGuid(List<String> idList) {
        try {
            Date updateDate = new Date();
            resourceDao.onLineMediaFileInfoByMediaGuid(idList,updateDate);
        } catch (Exception e) {
            throw new PlatformException("批量上线音频资源失败", e);
        }
    }

    /**
     * 根据MediaGuid下线资源
     * @param idList
     */
    public void offlineMediaFileInfoByMediaGuid(List<String> idList) {
        try {
            Date updateDate = new Date();
            resourceDao.offLineMediaFileInfoByMediaGuid(idList,updateDate);
        } catch (Exception e) {
            throw new PlatformException("批量下线音频资源失败", e);
        }
    }

    /**
     * 根据MediaGuid到期且停更资源信息
     * @param idList
     */
    public void expireMediaFileInfoByMediaGuid(List<String> idList) {
        try {
            Date updateDate = new Date();
            resourceDao.expireMediaFileInfoByMediaGuid(idList,updateDate);
        } catch (Exception e) {
            throw new PlatformException("到期且停更资源信息音频资源失败", e);
        }
    }

    /**
     * 修改资源信息
     * @param resource
     * @return
     */
    public boolean updateResource(Mediafileinfo resource) {
        resource.setUpdatedate(new Date());
        //resourceDao.updateResource(resource);
        resourceDao.updateTemplateById(resource);
        return true;

    }

    /**
     * 修改授权状态
     * @param guid
     * @param authState
     * @param state
     */
    public void changeAuthState(String guid, Integer authState,Integer state) {
        Date date = new Date();
        //修改专辑的状态
        mediainfoDao.updateMediaStateByGuid(guid,authState,state,date);
        //修改专辑下资源的状态
        resourceDao.updateResourceStateByGuid(guid,state,date);
    }
}