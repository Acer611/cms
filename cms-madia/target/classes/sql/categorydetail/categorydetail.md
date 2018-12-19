queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from mediacategorydetail t
    where del_flag=0 
    @//数据权限，该sql语句功能点  
    and #function("categorydetail.query")#
    @if(!isEmpty(mediaguid)){
        and  t.MediaGuid =#mediaguid#
    @}
    @if(!isEmpty(categorycode)){
        and  t.CategoryCode =#categorycode#
    @}
    
    
    

batchDelMediacategorydetailByIds
===

* 批量逻辑删除

    update mediacategorydetail set del_flag = 1 where ID  in( #join(ids)#)
    
