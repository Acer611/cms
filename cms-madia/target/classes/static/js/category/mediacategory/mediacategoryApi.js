/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateMediacategory:function(form,callback){
                Lib.submitForm("/category/mediacategory/edit.json",form,{},callback)
            },
            addMediacategory:function(form,callback){
                Lib.submitForm("/category/mediacategory/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/category/mediacategory/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('mediacategoryApi',api);
});