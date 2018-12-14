layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var mediaTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),mediaTable)
            }
        },
        initTable:function(){
            mediaTable = table.render({
                elem : '#mediaTable',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/media/media/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                    {
                        type : 'checkbox',
                        fixed:'left',
                    },
                {

                    field : 'mediainfoid', 
                        title : 'mediainfoid',
                    fixed:'left',
                        width : 60,
                },
                {

                    field : 'mediaguid', 
                        title : '唯一标识',
                },
                {

                    field : 'medianame', 
                        title : '专辑名',
                },
                {

                    field : 'mediatype', 
                        title : '专辑类型',
                },
                {

                    field : 'sourceunit', 
                        title : 'sourceunit',
                },
                {

                    field : 'makingunit', 
                        title : 'makingunit',
                },
                {

                    field : 'authunit', 
                        title : 'authunit',
                },
                {

                    field : 'authstate', 
                        title : 'authstate',
                },
                {

                    field : 'mediastate', 
                        title : 'mediastate',
                },
                {

                    field : 'icon', 
                        title : 'icon',
                },
                {

                    field : 'imageurl', 
                        title : 'imageurl',
                },
                {

                    field : 'note', 
                        title : 'note',
                },
                {

                    field : 'filecount', 
                        title : 'filecount',
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

                    field : 'localvalid', 
                        title : 'localvalid',
                },
                {

                    field : 'linkvalid', 
                        title : 'linkvalid',
                },
                {

                    field : 'createdate', 
                        title : 'createdate',
                },
                {

                    field : 'updatedate', 
                        title : 'updatedate',
                },
                {

                    field : 'deletenote', 
                        title : 'deletenote',
                },
                {

                    field : 'deletedate', 
                        title : 'deletedate',
                },
                {

                    field : 'shortcode', 
                        title : 'shortcode',
                }

        ] ]

        });

            table.on('checkbox(mediaTable)', function(obj){
                var media = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),mediaTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/media/media/add.do";
                    Common.openDlg(url,"Mediainfo管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"mediaTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/media/media/edit.do?mediaguid="+data.mediaguid;
                    Common.openDlg(url,"Mediainfo管理>"+data.mediainfoid+">编辑");
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