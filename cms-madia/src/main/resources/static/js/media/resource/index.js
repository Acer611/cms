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
                    templet:function(d){
                        var size = d.filesize/1000000;
                        size =size.toFixed(2)
                        return size + "M";
                    },
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
                },
                //添加本地资源
                addResource : function(){

                    //页面层
                    layer.open({
                        type: 2,
                        title: '添加本地资源',
                        shadeClose: true,
                        shade: 0.5,
                        skin:'demo-class',
                        maxmin: true, //开启最大化最小化按钮
                        area: ['400px', '300px'],
                        shift: 2,
                        content: '/media/media/addResource.do?mediaguid='+ mediaguid,

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

                },

        /*        upload : function(){
                    layui.use(['upload','jquery'], function(){
                        var $ = layui.$,
                        upload = layui.upload;

                        //音频上传片
                        var uploadInst = upload.render({
                            elem: '#table-button-upload',
                            url: '/uploadFile?mediaguid='+ mediaguid,
                            type:'post',
                            success:function(data){

                                if(data.code == 0){
                                    alert("上传成功");

                                }else{
                                    alert("上传失败");
                                }
                            }
                        });

                    });
                }*/

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

