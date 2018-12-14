package com.dragon.media.resourceinfo.entity;

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
* gen by Spring Boot2 Admin 2018-12-14
*/
public class Mediafileinfo extends BaseEntity{

    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
    @SeqID(name = ORACLE_CORE_SEQ_NAME)
    @AutoID	

    private Integer mediafileinfoid ;
	

    private String mediaguid ;
	

    private String filetitle ;
	

    private String filefullname ;
	

    private String linkurl ;
	

    private String imageurl ;
	

    private Long filesize ;
	

    private Integer duration ;
	

    private String fileextension ;
	

    private Integer localvalid ;
	

    private Integer linkvalid ;
	

    private Integer ordernumber ;
	

    private Date createdate ;
	

    private Date updatedate ;
	
    public Mediafileinfo()
    {
    }

    public Integer getMediafileinfoid(){
	    return  mediafileinfoid;
    }
    public void setMediafileinfoid(Integer mediafileinfoid){
        this.mediafileinfoid = mediafileinfoid;
    }

    public String getMediaguid(){
	    return  mediaguid;
    }
    public void setMediaguid(String mediaguid){
        this.mediaguid = mediaguid;
    }

    public String getFiletitle(){
	    return  filetitle;
    }
    public void setFiletitle(String filetitle){
        this.filetitle = filetitle;
    }

    public String getFilefullname(){
	    return  filefullname;
    }
    public void setFilefullname(String filefullname){
        this.filefullname = filefullname;
    }

    public String getLinkurl(){
	    return  linkurl;
    }
    public void setLinkurl(String linkurl){
        this.linkurl = linkurl;
    }

    public String getImageurl(){
	    return  imageurl;
    }
    public void setImageurl(String imageurl){
        this.imageurl = imageurl;
    }

    public Long getFilesize(){
	    return  filesize;
    }
    public void setFilesize(Long filesize){
        this.filesize = filesize;
    }

    public Integer getDuration(){
	    return  duration;
    }
    public void setDuration(Integer duration){
        this.duration = duration;
    }

    public String getFileextension(){
	    return  fileextension;
    }
    public void setFileextension(String fileextension){
        this.fileextension = fileextension;
    }

    public Integer getLocalvalid(){
	    return  localvalid;
    }
    public void setLocalvalid(Integer localvalid){
        this.localvalid = localvalid;
    }

    public Integer getLinkvalid(){
	    return  linkvalid;
    }
    public void setLinkvalid(Integer linkvalid){
        this.linkvalid = linkvalid;
    }

    public Integer getOrdernumber(){
	    return  ordernumber;
    }
    public void setOrdernumber(Integer ordernumber){
        this.ordernumber = ordernumber;
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
