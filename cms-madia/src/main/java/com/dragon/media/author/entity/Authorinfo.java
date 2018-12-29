package com.dragon.media.author.entity;

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
* gen by Spring Boot2 Admin 2018-12-24
*/
public class Authorinfo extends BaseEntity{

    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
    @SeqID(name = ORACLE_CORE_SEQ_NAME)
    @AutoID	

    private Integer id ;
	

    private String authorguid ;
	

    private String name ;
	

    private String note ;
	

    private String headimage ;
	

    private Date createdate ;
	

    private Date updatedate ;
	
    public Authorinfo()
    {
    }

    public Integer getId(){
	    return  id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getAuthorguid(){
	    return  authorguid;
    }
    public void setAuthorguid(String authorguid){
        this.authorguid = authorguid;
    }

    public String getName(){
	    return  name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getNote(){
	    return  note;
    }
    public void setNote(String note){
        this.note = note;
    }

    public String getHeadimage(){
	    return  headimage;
    }
    public void setHeadimage(String headimage){
        this.headimage = headimage;
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


}
