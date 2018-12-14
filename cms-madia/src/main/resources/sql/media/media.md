queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from MediaInfo t
    where 1=1
    @//del_flag=0 
    @//数据权限，该sql语句功能点  
    and #function("media.query")#
    @if(!isEmpty(medianame)){
        and  t.MediaName =#medianame#
    @}
    @if(!isEmpty(mediatype)){
        and  t.MediaType =#mediatype#
    @}
    
    
    

batchDelMediainfoByIds
===

* 批量逻辑删除

    update mediainfo set del_flag = 1 where MediaGuid  in( #join(ids)#)
    
