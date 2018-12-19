package com.dragon.media.categorydetail.web.query;

import org.apache.commons.lang3.StringUtils;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.Tool;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
/**
 *categorydetail查询
 */
public class MediacategorydetailQuery extends PageParam {
    @Query(name = "专辑的唯一标识", display = true)
    private String mediaguid;
    @Query(name = "分类的code", display = true)
    private String categorycode;
    public String getMediaguid(){
        return  mediaguid;
    }
    public void setMediaguid(String mediaguid ){
        this.mediaguid = mediaguid;
    }
    public String getCategorycode(){
        return  categorycode;
    }
    public void setCategorycode(String categorycode ){
        this.categorycode = categorycode;
    }
 
}
