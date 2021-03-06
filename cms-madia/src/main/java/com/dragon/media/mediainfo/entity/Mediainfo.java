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
import java.util.List;

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
	
    //1=音频、2=视频、3=课件 4=有声书
    private Integer mediatype ;
	

    private String sourceunit ;
	

    private String makingunit ;
	

    private String authunit ;
	

    private Integer authstate ;

    //专辑状态 0 未上线 1 已上线  2 已线下  3 回收站 4 到期停止更新 5 已完成更新
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

    //定价
    private Double price;
    //上线时间
    private Date onlineTime;
    //标签
    private String tags;
    //是否删除
    private Integer delFlag;
    //资源状态  更新状态 0 更新中 1 已完成更新
    private Integer resourcestate;
    //副标题
    private  String title;
    //音频总数量
    private Integer totalCount;
    //更新频率
    private String alternation;


    //-----------以下为display字段
    //分类code
    private List<String> categoryCodeList;
    //分类名称
    private List<String> categoryNameList;
    //分类名称
    private String categoryName;

    //演播者类型  1 作者 2 演播者
    private Integer authorType;
    //演播者名称
    private String author;
    //演播者简介
    private String authorNote;
    //演播者名称
    private String player;
    //演播者简介
    private String playerNote;
    //演播者名称合集
    private List<String> authorList;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public List<String> getCategoryCodeList() {
        return categoryCodeList;
    }

    public void setCategoryCodeList(List<String> categoryCodeList) {
        this.categoryCodeList = categoryCodeList;
    }

    public List<String> getCategoryNameList() {
        return categoryNameList;
    }

    public void setCategoryNameList(List<String> categoryNameList) {
        this.categoryNameList = categoryNameList;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getResourcestate() {
        return resourcestate;
    }

    public void setResourcestate(Integer resourcestate) {
        this.resourcestate = resourcestate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getAlternation() {
        return alternation;
    }

    public void setAlternation(String alternation) {
        this.alternation = alternation;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    public String getAuthorNote() {
        return authorNote;
    }

    public void setAuthorNote(String authorNote) {
        this.authorNote = authorNote;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPlayerNote() {
        return playerNote;
    }

    public void setPlayerNote(String playerNote) {
        this.playerNote = playerNote;
    }

    public Integer getAuthorType() {
        return authorType;
    }

    public void setAuthorType(Integer authorType) {
        this.authorType = authorType;
    }
}
