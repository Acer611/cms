package com.dragon.media.mediainfo.service;

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

    public void batchDelMediainfo(List<Long> ids){
        try {
            mediaDao.batchDelMediainfoByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除Mediainfo失败", e);
        }
    }
}