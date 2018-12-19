package com.dragon.media.categorydetail.service;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.dragon.media.categorydetail.dao.MediacategorydetailDao;
import com.dragon.media.categorydetail.entity.Mediacategorydetail;
import com.ibeetl.admin.core.service.BaseService;

/**
 * categorydetail Service
 */

@Service
@Transactional
public class MediacategorydetailService extends BaseService<Mediacategorydetail>{

    @Autowired private MediacategorydetailDao categorydetailDao;

    public PageQuery<Mediacategorydetail>queryByCondition(PageQuery query){
        PageQuery ret =  categorydetailDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelMediacategorydetail(List<Long> ids){
        try {
            categorydetailDao.batchDelMediacategorydetailByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除categorydetail失败", e);
        }
    }
}