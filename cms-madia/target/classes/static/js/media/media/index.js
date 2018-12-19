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
                        field : 'medianame',
                        title : '专辑名',
                        width: 150,
                        fixed: 'left'

                     },

                    {
                        field : 'imageurl',
                        title : '封面图',
                        width: 150,
                        height: '100%',
                        fixed: 'left',
                        templet:'<div><img src="{{ d.imageurl}}"></div>'
                    },
                    {
                        field : 'filecount',
                        title : '音频数量',
                        width: 100
                    },

                    {
                        field : 'filecount',
                        title : '分类',
                        width: 60
                    },
                    {
                        field : 'mediatype',
                        title : '资源类型',
                        width: 100,
                    },
                    {
                        field : 'mediatype',
                        title : '演播者',
                        width: 100,
                    },
                    {
                        field : 'price',
                        title : '定价',
                        width: 60,
                    },
                    {
                        field : 'duration',
                        title : '时长',
                        width: 60,
                    },
                    {
                        field : 'filesize',
                        title : '文件大小',
                        width: 100,
                    },
                    {
                        field : 'mediaguid',
                        title : '专辑ID',
                        width: 200,

                    },
                    {
                        field : 'authstate',
                        title : '授权状态',
                        width: 100,
                    },
                    {
                        field : 'createdate',
                        title : '同步时间',
                        width: 180,
                        sort: true,
                    },
                    {
                        field : 'updatedate',
                        title : '更新时间',
                        width: 180,
                        sort: true,
                    },
                    {
                        field : 'onlineTime',
                        title : '上线时间',
                        width: 180,
                        sort: true,
                    },
                    {
                        field : 'mediastate',
                        title : '专辑状态',
                        width: 100,
                    },

                    {fixed: 'right',title : '操作', width: 165, align:'center', toolbar: '#barDemo'}

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
                },
                batchOnline : function () {
                    layui.use(['batchOnline'], function(){
                        var batchOnlineView = layui.batchOnline
                        batchOnlineView.batchOnline();
                    });
                },
                batchOffline : function () {
                    layui.use(['batchOffline'], function(){
                        var batchOfflineView = layui.batchOffline
                        batchOfflineView.batchOffline();
                    });
                }
        };
            $('.ext-toolbar').on('click', function() {
                var type = $(this).data('type');
                toolbar[type] ? toolbar[type].call(this) : '';


            });

            //监听行工具事件
            table.on('tool(mediaTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
                var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
                if(layEvent === 'online'){
                    var ids = new Array();
                    ids[0] = data.mediaguid;
                    Common.post("/media/media/online.json",{"ids":data.mediaguid},function(){
                        callback();
                    })
                    alert("上线成功")
                    dataReload();

                } else if(layEvent === 'edit'){
                    var url = "/media/media/edit.do?mediaguid="+data.mediaguid;
                    Common.openDlg(url,"Mediainfo管理>"+data.mediainfoid+">编辑");
                }else if(layEvent === 'offline'){
                    var ids = new Array();
                    ids[0] = data.mediaguid;
                    Common.post("/media/media/offline.json",{"ids":data.mediaguid},function(){
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
