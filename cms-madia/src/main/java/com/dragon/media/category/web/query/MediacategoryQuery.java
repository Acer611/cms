package com.dragon.media.category.web.query;

import org.apache.commons.lang3.StringUtils;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.Tool;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
/**
 *Mediacategory查询
 */
public class MediacategoryQuery extends PageParam {
    @Query(name = "唯一标识", display = true)
    private Integer id;
    @Query(name = "分类的code", display = true)
    private String categorycode;
    @Query(name = "分类名称", display = true)
    private String categoryname;
    @Query(name = "父分类的code", display = false)
    private String parentcategorycode;

    public Integer getId(){
        return  id;
    }
    public void setId(Integer id ){
        this.id = id;
    }
    public String getCategorycode(){
        return  categorycode;
    }
    public void setCategorycode(String categorycode ){
        this.categorycode = categorycode;
    }
    public String getCategoryname(){
        return  categoryname;
    }
    public void setCategoryname(String categoryname ){
        this.categoryname = categoryname;
    }

    public String getParentcategorycode() {
        return parentcategorycode;
    }

    public void setParentcategorycode(String parentcategorycode) {
        this.parentcategorycode = parentcategorycode;
    }
}
