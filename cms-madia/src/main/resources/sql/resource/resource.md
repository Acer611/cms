
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
        and  t.FileTitle =#filetitle#I
    @}
    @if(!isEmpty(filefullname)){
        and  t.FileFullName =#filefullname#
    @}
    @if(!isEmpty(mediaguid)){
            and  t.MediaGuid =#mediaguid#
    @}
    
    
    
    

batchDelMediafileinfoByIds
===

* 批量逻辑删除

    update MediaFileinfo set del_flag = 1 where MediaFileInfoID  in( #join(ids)#)
    
