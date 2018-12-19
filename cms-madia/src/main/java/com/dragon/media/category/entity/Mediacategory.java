package com.dragon.media.category.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;

import com.ibeetl.admin.core.util.ValidateConfig;

import org.beetl.sql.core.TailBean;
import java.math.*;

import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.admin.core.entity.BaseEntity;

import org.beetl.sql.core.annotatoin.InsertIgnore;
import org.beetl.sql.core.annotatoin.Version;
import org.beetl.sql.core.annotatoin.LogicDelete;


/* 
* 
* gen by Spring Boot2 Admin 2018-12-18
*/
public class Mediacategory extends BaseEntity{

    //唯一标识
    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
    @SeqID(name = ORACLE_CORE_SEQ_NAME)
    @AutoID	

    private Integer id ;
	
    //分类的code

    private String categorycode ;
	
    //分类名称

    private String categoryname ;
	

    private Integer categorylevel ;
	

    private String parentcategorycode ;
	

    private Integer ordernumber ;
	

    private String note ;
	

    private Date createdate ;
	

    private Date updatedate ;
	
    //是否删除标识 0 未删除 1 删除
	/*逻辑删除标志*/
	@InsertIgnore
	@LogicDelete(value = 1)

    private Integer delFlag ;
	
    public Mediacategory()
    {
    }

    /**唯一标识
    *@return 
    */
    public Integer getId(){
	    return  id;
    }
    /**唯一标识
    *@param  id
    */
    public void setId(Integer id){
        this.id = id;
    }

    /**分类的code
    *@return 
    */
    public String getCategorycode(){
	    return  categorycode;
    }
    /**分类的code
    *@param  categorycode
    */
    public void setCategorycode(String categorycode){
        this.categorycode = categorycode;
    }

    /**分类名称
    *@return 
    */
    public String getCategoryname(){
	    return  categoryname;
    }
    /**分类名称
    *@param  categoryname
    */
    public void setCategoryname(String categoryname){
        this.categoryname = categoryname;
    }

    public Integer getCategorylevel(){
	    return  categorylevel;
    }
    public void setCategorylevel(Integer categorylevel){
        this.categorylevel = categorylevel;
    }

    public String getParentcategorycode(){
	    return  parentcategorycode;
    }
    public void setParentcategorycode(String parentcategorycode){
        this.parentcategorycode = parentcategorycode;
    }

    public Integer getOrdernumber(){
	    return  ordernumber;
    }
    public void setOrdernumber(Integer ordernumber){
        this.ordernumber = ordernumber;
    }

    public String getNote(){
	    return  note;
    }
    public void setNote(String note){
        this.note = note;
    }

    public Date getCreatedate(){
	    return  createdate;
    }
    public void setCreatedate(Date createdate){
        this.createdate = createdate;
    }

    public Date getUpdatedate(){
	    return  updatedate;
    }
    public void setUpdatedate(Date updatedate){
        this.updatedate = updatedate;
    }

    /**是否删除标识 0 未删除 1 删除
    *@return 
    */
    public Integer getDelFlag(){
	    return  delFlag;
    }
    /**是否删除标识 0 未删除 1 删除
    *@param  delFlag
    */
    public void setDelFlag(Integer delFlag){
        this.delFlag = delFlag;
    }


}
