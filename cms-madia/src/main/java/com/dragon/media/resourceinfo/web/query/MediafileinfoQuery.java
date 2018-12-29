package com.dragon.media.resourceinfo.web.query;

import org.apache.commons.lang3.StringUtils;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.Tool;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
/**
 *Mediafileinfo查询
 */
public class MediafileinfoQuery extends PageParam {
    @Query(name = "资源名称", display = true)
    private String filetitle;

    @Query(name = "资源状态", type = Query.TYPE_DICT, dict="album.status",display = true)
    private String state;
    @Query(name = "mediaguid", display = false)
    private String mediaguid;
    public String getFiletitle(){
        return  filetitle;
    }
    public void setFiletitle(String filetitle ){
        this.filetitle = filetitle;
    }

    public String getMediaguid() {
        return mediaguid;
    }

    public void setMediaguid(String mediaguid) {
        this.mediaguid = mediaguid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
