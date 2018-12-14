/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateMedia:function(form,callback){
                Lib.submitForm("/media/media/edit.json",form,{},callback)
            },
            addMedia:function(form,callback){
                Lib.submitForm("/media/media/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/media/media/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('mediaApi',api);
});