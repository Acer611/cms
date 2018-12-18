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
    