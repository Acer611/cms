package com.dragon.media.author.service;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.dragon.media.author.dao.AuthorinfoDao;
import com.dragon.media.author.entity.Authorinfo;
import com.ibeetl.admin.core.service.BaseService;

/**
 * authorinfo Service
 */

@Service
@Transactional
public class AuthorinfoService extends BaseService<Authorinfo>{

    @Autowired private AuthorinfoDao authorinfoDao;

    public PageQuery<Authorinfo>queryByCondition(PageQuery query){
        PageQuery ret =  authorinfoDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelAuthorinfo(List<Long> ids){
        try {
            authorinfoDao.batchDelAuthorinfoByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除authorinfo失败", e);
        }
    }
}