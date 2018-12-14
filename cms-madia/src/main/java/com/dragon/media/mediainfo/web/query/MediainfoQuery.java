package com.dragon.media.mediainfo.web.query;

import org.apache.commons.lang3.StringUtils;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.Tool;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
/**
 *Mediainfo查询
 */
public class MediainfoQuery extends PageParam {
    @Query(name = "专辑名", display = true)
    private String medianame;
    @Query(name = "专辑类型", display = true)
    private Integer mediatype;
    public String getMedianame(){
        return  medianame;
    }
    public void setMedianame(String medianame ){
        this.medianame = medianame;
    }
    public Integer getMediatype(){
        return  mediatype;
    }
    public void setMediatype(Integer mediatype ){
        this.mediatype = mediatype;
    }
 
}
