layui.define(['table', 'mediaApi'], function(exports) {
    var mediaApi = layui.mediaApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        batchOffline:function(){
            var data = Common.getMoreDataFromTable(table,"mediaTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要下线这些Mediainfo?",function(){
            var ids =Common.concatBatchId(data,"mediaguid");
            mediaApi.batchOffline(ids,function(){
                Common.info("批量下线成功");
                    dataReload();
                })
            })
        }
    }
    exports('batchOffline',view);
	
});