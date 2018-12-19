package com.dragon.media.category.service;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.dragon.media.category.dao.MediacategoryDao;
import com.dragon.media.category.entity.Mediacategory;
import com.ibeetl.admin.core.service.BaseService;

/**
 * Mediacategory Service
 */

@Service
@Transactional
public class MediacategoryService extends BaseService<Mediacategory>{

    @Autowired private MediacategoryDao mediacategoryDao;

    public PageQuery<Mediacategory>queryByCondition(PageQuery query){
        PageQuery ret =  mediacategoryDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelMediacategory(List<Long> ids){
        try {
            mediacategoryDao.batchDelMediacategoryByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除Mediacategory失败", e);
        }
    }

    /**
     * 查询一级分类
     * @param page
     */
    public PageQuery<Mediacategory> queryCategory(PageQuery page) {
        PageQuery ret =  mediacategoryDao.queryCategory(page);
        queryListAfter(ret.getList());
        return ret;
    }

    /**
     * 根据父cod查找分类信息
     * @param page
     * @param categoryCode
     */
    public PageQuery<Mediacategory> queryByPcode(PageQuery page, String categoryCode) {
      //  PageQuery ret =  mediacategoryDao.queryByCondition(query);
      //  queryListAfter(ret.getList());
        return null;
    }
}