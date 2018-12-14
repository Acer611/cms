/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateResource:function(form,callback){
                Lib.submitForm("/media/resource/edit.json",form,{},callback)
            },
            addResource:function(form,callback){
                Lib.submitForm("/media/resource/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/media/resource/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('resourceApi',api);
});