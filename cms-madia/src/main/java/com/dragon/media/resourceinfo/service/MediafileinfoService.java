package com.dragon.media.resourceinfo.service;

import java.util.List;

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

    public PageQuery<Mediafileinfo>queryByCondition(PageQuery query){
        PageQuery ret =  resourceDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelMediafileinfo(List<Long> ids){
        try {
            resourceDao.batchDelMediafileinfoByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除Mediafileinfo失败", e);
        }
    }
}