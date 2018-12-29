layui.define(['table', 'authorinfoApi'], function(exports) {
    var authorinfoApi = layui.authorinfoApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"authorinfoTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些authorinfo?",function(){
            var ids =Common.concatBatchId(data,"id");
            authorinfoApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});