layui.define(['table', 'resourceApi'], function(exports) {
    var resourceApi = layui.resourceApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"resourceTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些Mediafileinfo?",function(){
            var ids =Common.concatBatchId(data,"mediafileinfoid");
            resourceApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});