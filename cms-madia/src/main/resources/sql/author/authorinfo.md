queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from authorinfo t
    where del_flag=0 
    @//数据权限，该sql语句功能点  
    and #function("authorinfo.query")#
    @if(!isEmpty(authorguid)){
        and  t.AuthorGuid =#authorguid#
    @}
    @if(!isEmpty(name)){
        and  t.Name =#name#
    @}
    @if(!isEmpty(note)){
        and  t.Note =#note#
    @}
    
    
    

batchDelAuthorinfoByIds
===

* 批量逻辑删除

    update authorinfo set del_flag = 1 where ID  in( #join(ids)#)
    
