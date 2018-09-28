<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>美味-新鲜</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="//at.alicdn.com/t/font_tnyc012u2rlwstt9.css" media="all" />
    <link rel="stylesheet" href="${base}/static/css/main.css" media="all" />
</head>
<body class="childrenBody" onload="kaishi()">
<div class="panel_box row">
    <#if (goods?size>0)>
        <#list goods as items>
            <div class="panel col <#if (!items_has_next)>max_panel</#if> ">
                <a class="img" onclick="goodsSlect" > <img src="${base}${items.href}">
                    <div class="panel_word newMessage">
                        <span>${items.price}</span>
                        <cite>${items.name}</cite>
                    </div>
                </a>
            </div>
        </#list>
    </#if>
</div>
<input id="Button1" type="button" value="开始全屏" onclick="kaishi()" />
<input id="Button2" type="button" value="关闭全屏" onclick="guanbi()" />

<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['layer','jquery','form'],function(){
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;

        $(".panel a").on("click",function(){
            window.parent.addTab($(this));
        });
    });

    window.onload = function () {
        //TODO 为什么不能全屏显示
        kaishi();
    }
    function kaishi()
    {
        var docElm = document.documentElement;
        //W3C
        if (docElm.requestFullscreen) {
            docElm.requestFullscreen();
        }
        //FireFox
        else if (docElm.mozRequestFullScreen) {
            docElm.mozRequestFullScreen();
        }
        //Chrome等
        else if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
        }
        //IE11
        else if (elem.msRequestFullscreen) {
            elem.msRequestFullscreen();
        }
    }

    function guanbi() {

        if (document.exitFullscreen) {
            document.exitFullscreen();
        }
        else if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen();
        }
        else if (document.webkitCancelFullScreen) {
            document.webkitCancelFullScreen();
        }
        else if (document.msExitFullscreen) {
            document.msExitFullscreen();
        }
    }




    document.addEventListener("fullscreenchange", function () {

        fullscreenState.innerHTML = (document.fullscreen) ? "" : "not ";
    }, false);



    document.addEventListener("mozfullscreenchange", function () {

        fullscreenState.innerHTML = (document.mozFullScreen) ? "" : "not ";
    }, false);



    document.addEventListener("webkitfullscreenchange", function () {

        fullscreenState.innerHTML = (document.webkitIsFullScreen) ? "" : "not ";
    }, false);

    document.addEventListener("msfullscreenchange", function () {

        fullscreenState.innerHTML = (document.msFullscreenElement) ? "" : "not ";
    }, false);

    document.onkeydown=function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==121){ // 按
            login()
        }
        if(e && e.keyCode==113){ // 按 F2
            login()
        }
    };
    //prompt层
    //触发事件
    function login() {
            //示范一个公告层
            layer.open({
                type: 1
                ,title: false //不显示标题栏
                ,closeBtn: false
                ,area: '300px;'
                ,shade: 0.8
                ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,btnAlign: 'c'
                ,moveType: 1 //拖拽模式，0或者1
                ,content: '<form class="layui-form" action="${base}/login/main" method="post">    <div class="layui-form-item">        <input class="layui-input" name="username" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">    </div>    <div class="layui-form-item">        <input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">    </div>    <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button></form><script type="text/javascript" src="${base}/static/layui/layui.js">'

            });
            layui.use(['layer', 'form'], function() {
                var layer = layui.layer,
                        $ = layui.jquery,
                        form = layui.form;

                form.on('submit(login)', function(data) {
                    var loadIndex = layer.load(2, {
                        shade: [0.3, '#333']
                    });
                    $.post(data.form.action, data.field, function(res) {
                        layer.close(loadIndex);
                        if(res.success){
                            location.href="${base}/"+res.data.url;
                        }else{
                            layer.msg(res.message);
                            $("#mycode").click();
                        }
                    });
                    return false;

                });

            });
    }
</script>
</body>
</html>