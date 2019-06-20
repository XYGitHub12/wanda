<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>商品列表-后台管理系统-Admin 1.0</title>
    <meta name="Description" content="基于layUI数据表格操作"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/weadmin.css">
    <%--<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/itemlist.js" charset="utf-8"></script>--%>

    <!--<script type="text/javascript" src="../../static/js/admin.js"></script>-->
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .layui-form-switch {
            width: 55px;
        }

        .layui-form-switch em {
            width: 40px;
        }

        .layui-form-onswitch i {
            left: 45px;
        }

        body {
            overflow-y: scroll;
        }
    </style>
</head>

<body>
<div class="weadmin-nav">
			<span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">商品管理</a>
        <a>
          <cite>商品列表</cite></a>
      </span>
    <a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">&#x1002;</i></a>
</div>
<div class="weadmin-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 we-search" onsubmit="return false;">
            商品搜索：
            <div class="layui-inline">
                <input type="text" id="title" name="title" placeholder="请输入商品名称关键字" autocomplete="off"
                       class="layui-input">
            </div>
            <button class="layui-btn" lay-submit="" lay-filter="search" data-type="reload"><i class="layui-icon">&#xe615;</i>
            </button>
        </form>
    </div>
    <div class="weadmin-block demoTable">
        <button class="layui-btn layui-btn-danger" data-type="getCheckData"><i class="layui-icon">&#xe640;</i>批量删除
        </button>
        <button class="layui-btn" onclick="WeAdminShow('添加商品','./add',600,500)"><i class="layui-icon">&#xe61f;</i>添加
        </button>
    </div>
    <table class="layui-hide" id="articleList"></table>


    <script type="text/html" id="operateTpl">
        <a title="编辑" onclick="WeAdminEdit('编辑','./edit', 2, 600, 400)" href="javascript:;">
            <i class="layui-icon">&#xe642;</i>
        </a>
        <a title="删除" href="javascript:;">
            <i class="layui-icon">&#xe640;</i>
        </a>
    </script>
    <%--<script type="text/html" id="shelfTpl">--%>
    <%--<form class="layui-form">--%>
    <%--<input type="checkbox" name="itemstatus" lay-filter="itemstatus" lay-skin="switch" lay-text="正常|下架" {{1==d.status?'checked':''}}/>--%>
    <%--</form>--%>
    <%--</script>--%>
    <script type="text/html" id="myTpl">
        <div class="layui-form">
            <input type="checkbox" name="itemStatus" id="itemStatus" lay-skin="switch" lay-text="正常|下架"
                   {{1==d.status?'checked':''}}/>
        </div>
    </script>

</div>
<script type="text/javascript">
    layui.extend({
        admin: '{/}../../static/js/admin'
    });
    //可以引入admin.js，两步走：extend use
    layui.use(['table', 'admin', 'jquery'], function () {
        var table = layui.table,
            $ = layui.jquery;
        table.render({
            //表格属性
            //要渲染的容器
            page: true,
            elem: '#articleList',
            //异步请求
            url: '../../items',
            //列
            cols: [[
                //field title 列属性
                {type: 'checkbox'},
                {field: 'id', title: '商品编号'},
                {field: 'title', title: '商品名称'},
                {field: 'sellPoint', title: '商品卖点'},
                {field: 'catName', title: '分类名称'},
                {field: 'statusName', title: '商品状态'}
            ]],
//            done:function(res,curr,count){
//                console.log($("[data-field='status']").children());
//                $("[data-field='status']").children().each(function(){
//                    if($(this).text() == '1'){
//                        $(this).text('正常');
//                    }
//                    if($(this).text() == '2'){
//                        $(this).text('下架');
//                    }
//                    if($(this).text() == '3'){
//                        $(this).text('删除');
//                    }
//                });
            //$.ajax({});
            //$.get();
            //$.post();
//                $.post(
//                    //url:string,异步请求提交给谁处理
//                    'abc',
//                    //data:object,提交什么后台处理
//                    {},
//                    //success:function,成功后的回调函数
//                    function(data){},
//                    //dataType:string
//                    'json'
//                );
//            }
        });

        //批量删除按钮的点击事件
        $('.demoTable .layui-btn-danger').click(function () {
            //获取按钮的data-type属性
            var type = $(this).data('type');
            //用三元运算符判断active[type]为真值就调用，为假值就什么都不做
            active[type] ? active[type].call(this) : '';
        });

        //搜索按钮的点击事件
        $('.weadmin-body,.layui-btn').click(function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
        var active = {

            reload: function () {
                //第一步获取选择的文本框的内容
                var title = $('#title').val();
                //第二步判断内容是否为空
                if ($.trim(title).length > 0) {
                    //文本框中有内容，表格需要重载，另外发送一个请求 items
                    table.reload('articleList', {
                        page: {curr: 1},
                        //第一个title作为表单数据传递出去的key，第二个就是这里定义的js变量
                        where: {title: title}
                    });
                }
            },
            getCheckData: function () {
                //获取选中行
                var data = table.checkStatus('articleList').data;
                //判断长度（大于零至少选中了一行）
                if (data.length > 0) {
                    //目的：遍历data数组，取出其中的id，放入另一个数组中
                    var ids = []; //定义一个空数组
                    for (var i = 0; i < data.length; i++){
                        ids.push(data[i].id);
                    }
                    //目的：发送异步请求，将ids数组传递到后端
                    $.post(
                        '../../item/batch',   //url
                        {'ids[]':ids},       //data
                        function (data) {
                            //至少删除一条记录
                            if(data>0){
                                //停留在原页面并刷新
                                $('.layui-laypage-btn').click();
                                layer.msg("恭喜您，删除成功！",{icon:1});
                            }
                        }
                    );
                }else {
                    //没有选中
                    layer.msg('请至少选中一行');
                }
            }
        };
    });

</script>

</body>

</html>