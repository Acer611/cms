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
    @Query(name = "filetitle", display = true)
    private String filetitle;
    @Query(name = "filefullname", display = true)
    private String filefullname;
    public String getFiletitle(){
        return  filetitle;
    }
    public void setFiletitle(String filetitle ){
        this.filetitle = filetitle;
    }
    public String getFilefullname(){
        return  filefullname;
    }
    public void setFilefullname(String filefullname ){
        this.filefullname = filefullname;
    }
 
}
