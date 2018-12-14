package com.dragon.media.mediainfo.entity;

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
public class Mediainfo extends BaseEntity{


    private Integer mediainfoid ;
	
    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
    @SeqID(name = ORACLE_CORE_SEQ_NAME)
    @AutoID	

    private String mediaguid ;
	

    private String medianame ;
	

    private Integer mediatype ;
	

    private String sourceunit ;
	

    private String makingunit ;
	

    private String authunit ;
	

    private Integer authstate ;
	

    private Integer mediastate ;
	

    private String icon ;
	

    private String imageurl ;
	

    private String note ;
	

    private Integer filecount ;
	

    private Long filesize ;
	

    private Integer duration ;
	

    private Integer localvalid ;
	

    private Integer linkvalid ;
	

    private Date createdate ;
	

    private Date updatedate ;
	

    private String deletenote ;
	

    private Date deletedate ;
	

    private String shortcode ;
	
    public Mediainfo()
    {
    }

    public Integer getMediainfoid(){
	    return  mediainfoid;
    }
    public void setMediainfoid(Integer mediainfoid){
        this.mediainfoid = mediainfoid;
    }

    public String getMediaguid(){
	    return  mediaguid;
    }
    public void setMediaguid(String mediaguid){
        this.mediaguid = mediaguid;
    }

    public String getMedianame(){
	    return  medianame;
    }
    public void setMedianame(String medianame){
        this.medianame = medianame;
    }

    public Integer getMediatype(){
	    return  mediatype;
    }
    public void setMediatype(Integer mediatype){
        this.mediatype = mediatype;
    }

    public String getSourceunit(){
	    return  sourceunit;
    }
    public void setSourceunit(String sourceunit){
        this.sourceunit = sourceunit;
    }

    public String getMakingunit(){
	    return  makingunit;
    }
    public void setMakingunit(String makingunit){
        this.makingunit = makingunit;
    }

    public String getAuthunit(){
	    return  authunit;
    }
    public void setAuthunit(String authunit){
        this.authunit = authunit;
    }

    public Integer getAuthstate(){
	    return  authstate;
    }
    public void setAuthstate(Integer authstate){
        this.authstate = authstate;
    }

    public Integer getMediastate(){
	    return  mediastate;
    }
    public void setMediastate(Integer mediastate){
        this.mediastate = mediastate;
    }

    public String getIcon(){
	    return  icon;
    }
    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getImageurl(){
	    return  imageurl;
    }
    public void setImageurl(String imageurl){
        this.imageurl = imageurl;
    }

    public String getNote(){
	    return  note;
    }
    public void setNote(String note){
        this.note = note;
    }

    public Integer getFilecount(){
	    return  filecount;
    }
    public void setFilecount(Integer filecount){
        this.filecount = filecount;
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

    public String getDeletenote(){
	    return  deletenote;
    }
    public void setDeletenote(String deletenote){
        this.deletenote = deletenote;
    }

    public Date getDeletedate(){
	    return  deletedate;
    }
    public void setDeletedate(Date deletedate){
        this.deletedate = deletedate;
    }

    public String getShortcode(){
	    return  shortcode;
    }
    public void setShortcode(String shortcode){
        this.shortcode = shortcode;
    }


}
