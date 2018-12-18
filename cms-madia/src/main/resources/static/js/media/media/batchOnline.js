layui.define(['table', 'mediaApi'], function(exports) {
    var mediaApi = layui.mediaApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        batchOnline:function(){
            var data = Common.getMoreDataFromTable(table,"mediaTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要上线这些Mediainfo?",function(){
            var ids =Common.concatBatchId(data,"mediaguid");
            mediaApi.batchOnline(ids,function(){
                Common.info("批量上线成功");
                    dataReload();
                })
            })
        }
    }
    exports('batchOnline',view);
	
});