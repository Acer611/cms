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
                    title : '资源ID',
                    fixed:'left',
                    width : 120,
                },

                {

                    field : 'filetitle', 
                    title : '资源名称',
                    width: 180,
                },

                {

                    field : 'linkurl', 
                    title : '资源地址',
                    width: 180,
                },

                {

                    field : 'filesize', 
                    title : '资源大小',
                    width: 100,
                },
                {

                    field : 'tags',
                    title : '资源标签',
                    width: 180,
                },
                {

                    field : 'duration', 
                    title : '资源时长',
                    width: 100,
                    sort: true,
                },
                 {

                     field : 'state',
                     title : '资源状态',
                     width: 100,
                     templet:function(d){
                         switch (d.state) {
                             case 0 : d.state = "未上线" ; break;
                             case 1 : d.state = "已上线" ; break;
                             case 2 : d.state= "已下线" ; break;
                             case 4 : d.state= "到期且停更" ; break;
                             default: d.state = "回收站" ;break;
                         }
                         return d.state;
                     }

                 },

                {

                    field : 'createdate', 
                    title : '资源添加时间',
                    width: 180,
                    sort: true,
                },
                {

                    field : 'updatedate', 
                    title : '资源修改时间',
                    width: 180,
                    sort: true,
                },
                    {fixed: 'right', title: '操作', width: 165, align: 'center', toolbar: '#barDemo'}

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

            //监听行工具事件
            table.on('tool(resourceTable)', function(obj){ //注：tool 是工具条事件名，mediaTable 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
                if(layEvent === 'online'){
                    var ids = new Array();
                    ids[0] = data.mediafileinfoid;
                    Common.post("/media/resource/online.json",{"ids":data.mediafileinfoid},function(){
                        callback();
                    })
                    alert("上线成功")
                    dataReload();

                } else if(layEvent === 'edit'){
                    var url = "/media/resource/edit.do?mediafileinfoid="+data.mediafileinfoid;
                    Common.openDlg(url,"音频资源管理>"+data.mediafileinfoid+">编辑");
                }else if(layEvent === 'offline'){
                    var ids = new Array();
                    ids[0] = data.mediafileinfoid;
                    Common.post("/media/resource/offline.json",{"ids":data.mediafileinfoid},function(){
                        callback();
                    })
                    alert("下线成功")
                    dataReload();
                }
            });


        }
    }
    exports('index',view);

});