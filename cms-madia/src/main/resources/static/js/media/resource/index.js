layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var resourceTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),resourceTable)
            }
        },
        initTable:function(){
            resourceTable = table.render({
                elem : '#resourceTable',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/media/resource/resourceList.json?mediaguid='+ mediaguid // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                    {
                        type : 'checkbox',
                        fixed:'left',
                    },
                {

                    field : 'mediafileinfoid', 
                        title : 'mediafileinfoid',
                    fixed:'left',
                        width : 60,
                },
                {

                    field : 'mediaguid', 
                        title : 'mediaguid',
                },
                {

                    field : 'filetitle', 
                        title : 'filetitle',
                },
                {

                    field : 'filefullname', 
                        title : 'filefullname',
                },
                {

                    field : 'linkurl', 
                        title : 'linkurl',
                },
                {

                    field : 'imageurl', 
                        title : 'imageurl',
                },
                {

                    field : 'filesize', 
                        title : 'filesize',
                },
                {

                    field : 'duration', 
                        title : 'duration',
                },
                {

                    field : 'fileextension', 
                        title : 'fileextension',
                },
                {

                    field : 'localvalid', 
                        title : 'localvalid',
                },
                {

                    field : 'linkvalid', 
                        title : 'linkvalid',
                },
                {

                    field : 'ordernumber', 
                        title : 'ordernumber',
                },
                {

                    field : 'createdate', 
                        title : 'createdate',
                },
                {

                    field : 'updatedate', 
                        title : 'updatedate',
                }

        ] ]

        });

            table.on('checkbox(resourceTable)', function(obj){
                var resource = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),resourceTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/media/resource/add.do";
                    Common.openDlg(url,"Mediafileinfo管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"resourceTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/media/resource/edit.do?mediafileinfoid="+data.mediafileinfoid;
                    Common.openDlg(url,"Mediafileinfo管理>"+data.mediafileinfoid+">编辑");
                },
                del : function() {
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
        };
            $('.ext-toolbar').on('click', function() {
                var type = $(this).data('type');
                toolbar[type] ? toolbar[type].call(this) : '';
            });
        }
    }
    exports('index',view);

});