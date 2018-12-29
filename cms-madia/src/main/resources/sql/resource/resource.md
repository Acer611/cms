
queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from MediaFileInfo t
    where 1=1 
    @//数据权限，该sql语句功能点  
    and #function("resource.query")#
    @if(!isEmpty(filetitle)){
            and  t.FileTitle LIKE #'%'+filetitle+'%'#
    @}
    @if(!isEmpty(mediaguid)){
            and  t.MediaGuid =#mediaguid#
    @}
    @if(!isEmpty(state)){
            and  t.State =#state#
    @}
    
    
    

batchDelMediafileinfoByIds
===

* 批量逻辑删除

    update MediaFileinfo set del_flag = 1 where MediaFileInfoID  in( #join(ids)#)
    


batchOnlineMediainfoByIds
===

* 批量上线
    
     update MediaFileInfo set State = 1, OnlineTime = #updateDate# ,UpdateDate=#updateDate# where MediaFileInfoID  in( #join(ids)# )
     
     
     
batchOfflineMediainfoByIds
===  

  * 批量下线
  
    update MediaFileInfo set State = 2,  UpdateDate=#updateDate# where MediaFileInfoID  in( #join(ids)# )
  
  
  
batchAddTags 
===

  * 批量添加标签
  
     update MediaFileInfo set Tags = #tags#,  UpdateDate=#updateDate# where MediaFileInfoID  in( #join(ids)# )
        
        
onLineMediaFileInfoByMediaGuid
===

* 根据MediaGuid上线资源  

   update MediaFileInfo set State = 1, OnlineTime = #updateDate# ,UpdateDate=#updateDate# where MediaGuid  in( #join(idList)# )
  
  
offLineMediaFileInfoByMediaGuid  
===   
       
* 根据MediaGuid下线资源

    update MediaFileInfo set State = 2, OnlineTime = #updateDate# ,UpdateDate=#updateDate# where MediaGuid  in( #join(idList)# )
    
    
expireMediaFileInfoByMediaGuid
===

*根据MediaGuid到期且定更音频资源

   update MediaFileInfo set State = 4, OnlineTime = #updateDate# ,UpdateDate=#updateDate# where MediaGuid  in( #join(idList)# )
   
   
updateResource
===
* 根据ID修改资源名称和标签 

    update MediaFileInfo set  UpdateDate=#updatedate# ,FileTitle=#filetitle# ,Tags=#tags# where MediaFileInfoID =#mediafileinfoid#
    