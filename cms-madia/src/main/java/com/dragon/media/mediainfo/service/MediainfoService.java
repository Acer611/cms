package com.dragon.media.mediainfo.service;

import java.util.ArrayList;
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

        List<Mediainfo> mediainfos = ret.getList();
        for (Mediainfo mediainfo:mediainfos
             ) {
            //TODO 查询分类信息
            List<String> categoryNameList = new ArrayList<>();
            String categoryName = "";
            List<Mediainfo> mediainfoList = mediaDao.queryMediaById(mediainfo.getMediaguid());
            for (Mediainfo tempmediainfo:mediainfoList
                 ) {
                categoryNameList.add(tempmediainfo.getCategoryName());
                categoryName = categoryName + tempmediainfo.getCategoryName()+",";
            }
            categoryName = categoryName.substring(0,categoryName.length()-1);
            mediainfo.setCategoryNameList(categoryNameList);
            mediainfo.setCategoryName(categoryName);
            //TODO 查询演播者信息
            String author = "";
            List<Mediainfo> mediaAuthorinfoList = mediaDao.queryAuthorById(mediainfo.getMediaguid());
            for (Mediainfo tempmediainfo:mediaAuthorinfoList
                 ) {
                author = author + tempmediainfo.getAuthor()+",";
            }
            author = author.substring(0,author.length()-1);
            mediainfo.setAuthor(author);
        }
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

    /**
     * 根据Guid查询专辑信息
     * @param mediaguid
     * @return
     */
    public Mediainfo queryMediaById(String mediaguid) {
        List<Mediainfo> mediainfoList = mediaDao.queryMediaById(mediaguid);

        System.out.println(".......");
        return null;
    }
}