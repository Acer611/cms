package com.dragon.media.author.web.query;

import org.apache.commons.lang3.StringUtils;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.Tool;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
/**
 *authorinfo查询
 */
public class AuthorinfoQuery extends PageParam {
    @Query(name = "authorguid", display = true)
    private String authorguid;
    @Query(name = "name", display = true)
    private String name;
    @Query(name = "note", display = true)
    private String note;
    public String getAuthorguid(){
        return  authorguid;
    }
    public void setAuthorguid(String authorguid ){
        this.authorguid = authorguid;
    }
    public String getName(){
        return  name;
    }
    public void setName(String name ){
        this.name = name;
    }
    public String getNote(){
        return  note;
    }
    public void setNote(String note ){
        this.note = note;
    }
 
}
