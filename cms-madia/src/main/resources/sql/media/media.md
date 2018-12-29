queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from MediaInfo t
    where DelFlag=0 
    @//del_flag=0 
    @//数据权限，该sql语句功能点  
    @//and #function("media.query")#
    @if(!isEmpty(medianame)){
        and  t.MediaName LIKE #'%'+medianame+'%'#
    @}
    @if(!isEmpty(mediaguid)){
        and  t.MediaGuid LIKE #'%'+mediaguid+'%'#
    @}
    @if(!isEmpty(mediatype)){
        and  t.MediaType =#mediatype#
    @}
    @if(!isEmpty(mediastate)){
        and  t.MediaState =#mediastate#
    @}
    
    
    

batchDelMediainfoByIds
===

* 批量逻辑删除

    update MediaInfo set DelFlag = 1 where MediaGuid  in( #join(ids)# )
    

batchOnlineMediainfoByIds
===

* 批量上线

    update MediaInfo set MediaState = 1, OnlineTime = #date# ,UpdateDate=#updateDate# where MediaGuid  in( #join(ids)# )
    
    
batchOfflineMediainfoByIds
===

* 批量下线

    update MediaInfo set MediaState = 2, UpdateDate=#updateDate# where MediaGuid  in( #join(ids)# )
    
    
expireMediainfo
===
* 到期且定更专辑信息

    update MediaInfo set MediaState = 3, UpdateDate=#updateDate# where MediaGuid  in( #join(idList)# )
 
 
update
===
  * 根据ID修改专辑信息
  
      update MediaInfo set Note = #note#,  ImageUrl=#imageurl#, Tags=#tags#,UpdateDate=#updatedate# where MediaGuid =#mediaguid#
      
   
      
queryMediaById  
===         
   * 查询专辑和分类信息  
     
      SELECT
      	m.*,
      	mcd.CategoryCode ,
      	mc.CategoryName as categoryName
      FROM
      	MediaInfo AS m
      	LEFT JOIN MediaCategoryDetail AS mcd ON m.MediaGuid = mcd.MediaGuid 
      	LEFT JOIN MediaCategory AS mc  on mcd.CategoryCode=mc.CategoryCode
      WHERE
      	m.MediaGuid = #mediaguid#
      	
 
      	
queryAuthorById
===
   * 查询专辑和演播者信息  
   
       SELECT
        m.*,
        a.name as author,
        a.Note as authorNote,
        am.AuthorType as authorType 
       FROM
        MediaInfo AS m
        LEFT JOIN AuthorMedia AS am ON m.MediaGuid = am.MediaGuid
        LEFT JOIN AuthorInfo AS a ON am.AuthorGuid = a.AuthorGuid
       WHERE
        m.MediaGuid = #mediaguid#	
        

