/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateCategorydetail:function(form,callback){
                Lib.submitForm("/categorydetail/categorydetail/edit.json",form,{},callback)
            },
            addCategorydetail:function(form,callback){
                Lib.submitForm("/categorydetail/categorydetail/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/categorydetail/categorydetail/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('categorydetailApi',api);
});