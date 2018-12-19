layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var categorydetailTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),categorydetailTable)
            }
        },
        initTable:function(){
            categorydetailTable = table.render({
                elem : '#categorydetailTable',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/categorydetail/categorydetail/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                    {
                        type : 'checkbox',
                        fixed:'left',
                    },
                {

                    field : 'id', 
                        title : '唯一标识',
                    fixed:'left',
                        width : 60,
                },
                {

                    field : 'mediaguid', 
                        title : '专辑的唯一标识',
                },
                {

                    field : 'categorycode', 
                        title : '分类的code',
                },
                {

                    field : 'createdate', 
                        title : '创建时间',
                },
                {

                    field : 'updatedate', 
                        title : '修改时间',
                }

        ] ]

        });

            table.on('checkbox(categorydetailTable)', function(obj){
                var categorydetail = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),categorydetailTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/categorydetail/categorydetail/add.do";
                    Common.openDlg(url,"categorydetail管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"categorydetailTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/categorydetail/categorydetail/edit.do?id="+data.id;
                    Common.openDlg(url,"categorydetail管理>"+data.id+">编辑");
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