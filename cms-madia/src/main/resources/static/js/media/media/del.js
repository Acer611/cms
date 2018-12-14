layui.define(['table', 'mediaApi'], function(exports) {
    var mediaApi = layui.mediaApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"mediaTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些Mediainfo?",function(){
            var ids =Common.concatBatchId(data,"mediaguid");
            mediaApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});