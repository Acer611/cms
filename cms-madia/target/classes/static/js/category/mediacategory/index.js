layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var mediacategoryTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),mediacategoryTable)
            }
        },
        initTable:function(){
            mediacategoryTable = table.render({
                elem : '#mediacategoryTable',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/category/mediacategory/list.json' // 数据接口
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

                    field : 'categorycode', 
                        title : '分类的code',
                },
                {

                    field : 'categoryname', 
                        title : '分类名称',
                },
                {

                    field : 'categorylevel', 
                        title : '分类扥及',
                },
                {

                    field : 'parentcategorycode', 
                        title : '父分类',
                },
                {

                    field : 'ordernumber', 
                        title : '排序',
                },
                {

                    field : 'note', 
                        title : '描述',
                },
                {

                    field : 'createdate', 
                        title : '创建时间',
                },
                {

                    field : 'updatedate', 
                        title : '修改时间',
                },

        ] ]

        });

            mediacategoryTable = table.render({
                elem : '#mediaCategory',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/category/mediacategory/listCategory.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头

                    {

                        field : 'id',
                        title : '唯一标识',
                        fixed:'left',
                        width : 60,
                    },
                    {

                        field : 'categorycode',
                        title : '分类的code',
                    },
                    {

                        field : 'categoryname',
                        title : '分类名称',
                    },


                ] ]

            });




            table.on('checkbox(mediacategoryTable)', function(obj){
                var mediacategory = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),mediacategoryTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/category/mediacategory/add.do";
                    Common.openDlg(url,"Mediacategory管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"mediacategoryTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/category/mediacategory/edit.do?id="+data.id;
                    Common.openDlg(url,"Mediacategory管理>"+data.id+">编辑");
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