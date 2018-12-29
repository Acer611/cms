queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from MediaCategory t
    where del_flag=0 
    @//数据权限，该sql语句功能点  
    and #function("mediacategory.query")#
    @if(!isEmpty(id)){
        and  t.ID =#id#
    @}
    @if(!isEmpty(categorycode)){
        and  t.CategoryCode =#categorycode#
    @}
    @if(!isEmpty(categoryname)){
        and  t.CategoryName =#categoryname#
    @}
    @if(!isEmpty(parentcategorycode)){
            and  t.ParentCategoryCode =#parentcategorycode#
    @}
    
    
    

batchDelMediacategoryByIds
===

* 批量逻辑删除

    update mediacategory set del_flag = 1 where ID  in( #join(ids)#)
  
    
queryCategory
===

* 查询一级分类

    select 
        @pageTag(){
        t.*
        @}
        from MediaCategory t
        where del_flag=0 and  t.ParentCategoryCode ='00040002'
        
        
queryCategoryByCode
===

* 根据code查询下级分类

       select * from   MediaCategory  where del_flag=0   and ParentCategoryCode = #categoryCode#
   
   
   
queryCategoryByMediaGuid
===      

* 查询专辑下已有的分类信息

    SELECT
        m.* 
    FROM
        MediaCategoryDetail mc
        LEFT JOIN MediaCategory m ON mc.CategoryCode = m.CategoryCode 
    WHERE
        mc.MediaGuid = #mediaguid#   