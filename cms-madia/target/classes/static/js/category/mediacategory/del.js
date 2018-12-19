layui.define(['table', 'mediacategoryApi'], function(exports) {
    var mediacategoryApi = layui.mediacategoryApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"mediacategoryTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些Mediacategory?",function(){
            var ids =Common.concatBatchId(data,"id");
            mediacategoryApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});