layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var authorinfoTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),authorinfoTable)
            }
        },
        initTable:function(){
            authorinfoTable = table.render({
                elem : '#authorinfoTable',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/author/authorinfo/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                    {
                        type : 'checkbox',
                        fixed:'left',
                    },
                {

                    field : 'id', 
                        title : 'id',
                    fixed:'left',
                        width : 60,
                },
                {

                    field : 'authorguid', 
                        title : 'authorguid',
                },
                {

                    field : 'name', 
                        title : 'name',
                },
                {

                    field : 'note', 
                        title : 'note',
                },
                {

                    field : 'headimage', 
                        title : 'headimage',
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

            table.on('checkbox(authorinfoTable)', function(obj){
                var authorinfo = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),authorinfoTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/author/authorinfo/add.do";
                    Common.openDlg(url,"authorinfo管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"authorinfoTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/author/authorinfo/edit.do?id="+data.id;
                    Common.openDlg(url,"authorinfo管理>"+data.id+">编辑");
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