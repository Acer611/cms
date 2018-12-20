queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from MediaCategoryDetail t
    where 1=1
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

    update MediaCategoryDetail set del_flag = 1 where ID  in( #join(ids)#)
    


save
===

* 添加分类和专辑关联关系

    insert into  MediaCategoryDetail  (MediaGuid, CategoryCode,CreateDate,UpdateDate) 
    VALUES (#mediaguid#,#categorycode#,#createdate#,#updatedate#)
    

deleteCategoryDetailByMediaGuid
===

* 添加分类和专辑关联关系
    
    delete from MediaCategoryDetail where MediaGuid =#mediaGuid#