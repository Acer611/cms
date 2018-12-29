/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateAuthorinfo:function(form,callback){
                Lib.submitForm("/author/authorinfo/edit.json",form,{},callback)
            },
            addAuthorinfo:function(form,callback){
                Lib.submitForm("/author/authorinfo/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/author/authorinfo/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('authorinfoApi',api);
});