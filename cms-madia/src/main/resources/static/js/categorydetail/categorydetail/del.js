layui.define(['table', 'categorydetailApi'], function(exports) {
    var categorydetailApi = layui.categorydetailApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"categorydetailTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些categorydetail?",function(){
            var ids =Common.concatBatchId(data,"id");
            categorydetailApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});