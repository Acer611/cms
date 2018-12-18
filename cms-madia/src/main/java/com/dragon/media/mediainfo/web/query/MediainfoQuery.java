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
    @Query(name = "专辑类型",type = Query.TYPE_DICT, dict="resource.state" ,display = true)
    private Integer mediatype;
    @Query(name = "专辑状态",type = Query.TYPE_DICT, dict="album.status",display = true)
    private Integer mediastate;
    @Query(name = "专辑ID", display = false)
    private String ids;

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

    public Integer getMediastate() {
        return mediastate;
    }

    public void setMediastate(Integer mediastate) {
        this.mediastate = mediastate;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
