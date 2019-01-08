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
                        width: 100,
                        fixed: 'left',
                        templet:'<div><img style=" display: inline-block;width: 100%; height: 100%;" src="{{ d.imageurl}}"></div>'
                    },
                    {
                        field : 'filecount',
                        title : '已更新数量',
                        width: 100
                    },

                    {
                        field : 'totalCount',
                        title : '音频总数',
                        width: 100,
                    },
                    {
                        field : 'resourcestate',
                        title : '更新状态',
                        width: 100,
                        templet:function(d){
                            switch (d.resourcestate) {
                                case 0 : d.resourcestate = "更新中" ; break;
                                case 1 : d.resourcestate= "完成更新" ; break;
                                default: d.resourcestate = "" ;break;
                            }
                            return d.resourcestate;
                        },
                    },
                    {
                        field : 'categoryName',
                        title : '分类',
                        width: 180,
                        templet:function(d){
                            return d.categoryName =='null' ?'':d.categoryName;
                        },

                    },
                    {
                        field : 'author',
                        title : '演播者',
                        width: 180,
                        templet:function(d){
                            return d.author =='null' ? '':d.author;
                        },

                    },
                    {
                        field : 'mediatype',
                        title : '资源类型',
                        width: 100,
                        templet:function(d){
                            switch (d.mediatype) {
                                case 1 : d.mediatype = "音频" ; break;
                                case 2 : d.mediatype= "视频" ; break;
                                default: d.mediatype = "课件" ;break;
                            }
                            return d.mediatype;
                        },

                    },
                    {
                        field : 'tags',
                        title : '标签',
                        width: 180,
                    },


                    {
                        field : 'price',
                        title : '定价',
                        width: 100,
                    },
                    {
                        field : 'duration',
                        title : '时长',
                        width: 100,
                    },
                    {
                        field : 'filesize',
                        title : '文件大小',
                        width: 100,
                        templet:function(d){
                            var size = d.filesize/1000000;
                            size =size.toFixed(2)
                            return size + "M";
                        },
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
                        templet:function(d){
                            switch (d.authstate) {
                                case 1 : d.authstate = "已授权" ; break;
                                case 2 : d.authstate= "授权到期" ; break;
                                default: d.authstate = "" ;break;
                            }
                            return d.authstate;
                        }
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
                        templet:function(d){
                            switch (d.mediastate) {
                                case 0 : d.mediastate = "未上线" ; break;
                                case 1 : d.mediastate = "已上线" ; break;
                                case 2 : d.mediastate= "已下线" ; break;
                                case 4 : d.mediastate= "到期且停更" ; break;
                                default: d.mediastate = "回收站" ;break;
                            }
                            return d.mediastate;
                        }

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
                },
                queryMedia : function() { // 获对应的音频列表
                    var data = Common.getOneFromTable(table,"mediaTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/media/resource/indexByMediaId.do?mediaId="+data.mediaguid;
                    Common.openDlg(url,"resource管理>"+data.mediainfoid+">列表");
                },
                addCategory : function(){
                    var data = Common.getOneFromTable(table,"mediaTable");
                    if(data==null){
                        return ;
                    }
                    //页面层
                    layer.open({
                        type: 2,
                        title: '挂载分类',
                        shadeClose: true,
                        shade: 0.5,
                        skin:'demo-class',
                        maxmin: true, //开启最大化最小化按钮
                        area: ['1000px', '660px'],
                        shift: 2,
                        content: '/media/media/addCategory.do?mediaguid='+ data.mediaguid,

                        success:function(layero,index){
                        },
                        end:function() {
                            var handle_status = $("#handle_status").val();
                            if (handle_status == '1') {
                                layer.msg('添加成功！', {
                                    icon: 1,
                                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                }, function () {
                                    history.go(0);
                                });
                                //dataReload();
                            } else if (handle_status == '2') {
                                layer.msg('添加失败！', {
                                    icon: 2,
                                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                }, function () {
                                    history.go(0);
                                });
                               // dataReload();
                            }
                        }
                    });

            }
        };
            $('.ext-toolbar').on('click', function() {
                var type = $(this).data('type');
                toolbar[type] ? toolbar[type].call(this) : '';


            });

            //监听行工具事件
            table.on('tool(mediaTable)', function(obj){ //注：tool 是工具条事件名，mediaTable 是 table 原始容器的属性 lay-filter="对应的值"
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
