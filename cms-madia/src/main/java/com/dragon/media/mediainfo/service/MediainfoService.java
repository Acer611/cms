package com.dragon.media.mediainfo.service;

import java.util.Date;
import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.dragon.media.mediainfo.dao.MediainfoDao;
import com.dragon.media.mediainfo.entity.Mediainfo;
import com.ibeetl.admin.core.service.BaseService;

/**
 * Mediainfo Service
 */

@Service
@Transactional
public class MediainfoService extends BaseService<Mediainfo>{

    @Autowired private MediainfoDao mediaDao;

    public PageQuery<Mediainfo>queryByCondition(PageQuery query){
        PageQuery ret =  mediaDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelMediainfo(List<String> ids){
        try {
            mediaDao.batchDelMediainfoByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除Mediainfo失败", e);
        }
    }

    /**
     * 批量上线
     * @param ids
     */
    public void batchOnlineMediainfo(List<String> ids) {
        try {
            Date date = new Date();
            Date updateDate = new Date();
            mediaDao.batchOnlineMediainfoByIds(ids,date,updateDate);
        } catch (Exception e) {
            throw new PlatformException("批量上线Mediainfo失败", e);
        }
    }

    /**
     * 批量下线
     * @param idList
     */
    public void batchOfflineMediainfo(List<String> idList) {
        try {
            Date updateDate = new Date();
            mediaDao.batchOfflineMediainfoByIds(idList,updateDate);
        } catch (Exception e) {
            throw new PlatformException("批量下线Mediainfo失败", e);
        }
    }

    /**
     * 根据Guid修改专辑信息
     * @param mediainfo
     * @return
     */
    public boolean update(Mediainfo mediainfo){

        try {
            Date updateDate = new Date();
            mediainfo.setUpdatedate(updateDate);
            mediaDao.update(mediainfo);
        } catch (Exception e) {
            throw new PlatformException("更新Mediainfo失败", e);
        }
        return true;
    }
}