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
    <link rel="stylesheet" href="${base}/static/css/common.css" media="all" />
    <!-- 引入 jQuery Mobile 样式 -->
    <#--<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">-->
    <script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
    <!-- 引入 jQuery Mobile 库 -->
    <#--<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>-->
    <script type="text/javascript" src="${base}/static/easyui/jquery-submit.js"></script>
    <script type="text/javascript" src="${base}/static/easyui/jquery.json-2.3.js"></script>
<style type="text/css">
    .Switch {
        width: 80px;
        height: 28px;
        display: inline-block;
    }
    .Switch li {
        /*	clear: both;
            line-height: 44px;
            border-bottom: 1px solid #CCC;*/
        list-style: none;
    }
    .Switch input {
        display: none
    }
    .Switch label {
        width: 80px;
        background: #CCC;
        height: 28px;
        border-radius: 14px;
        float: right;
        box-shadow: 0 1px 2px rgba(0,0,0,.1) inset;
    }
    .Switch label em {
        width: 26px;
        height: 26px;
        float: left;
        margin: 1px;
        border-radius: 13px;
        box-shadow: 2px 3px 8px rgba(0,0,0,.1);
        background: #FFF;
        cursor: pointer;
    }
    .Switch input:checked + label {
        background: #a4d714;
    }
    .Switch input:checked + label em {
        float: right;
    }
    .Switch input:disabled + label {
        opacity: 0.5
    }
    .open{
        display: none;
    }
    .open,.close{
        color:#fff;
        width: calc(100% - 30px);
        text-align: center;
        height: 28px;
        line-height: 28px;
        font-size:14px;
    }
    .Switch input:checked + label > .open{
        display: inline-block;
    }
    .Switch input:checked + label > .close{
        display: none;
    }
</style>
</head>
<body class="childrenBody" onload="kaishi()">
<div style="width: 1024px;height: 1280px;">
<div class="panel_box row" id="goodsImgList">
    <#if (goods?size>0)>
        <#list goods as items>
            <div class="panel col <#if (!items_has_next)>max_panel</#if> " style="width: 341px" >
                <a href="#" onclick="goodsSlect('${items.name}','${items.price}','${items.id}','${items.number}')" > <img src="${base}${items.href}" style="width: 300px; height: 280px;"></a>
                <div class="panel_word newMessage" style="float: none;">
                    <span>${items.name}&nbsp;&nbsp;&nbsp;&nbsp;${items.price}元</span>
                </div>
            </div>
        </#list>
    </#if>
</div>

<div class="mask_box">
    <div class="mask"></div>
    <div class="box">
        <div class="box_top">结算</div>
        <form class="layui-form">
            <table class="layui-table">
                <thead>
                <tr>
                    <th lay-data="{field:'number'}">商品编码</th>
                    <th lay-data="{field:'name'}">商品名称</th>
                    <th lay-data="{field:'price', edit: 'text'}">零售价（元）</th>
                    <th lay-data="{field:'qty', edit: 'text'}">数量</th>
                    <th lay-data="{field:'heatFlag'}">是否加热</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="myTbody">

                </tbody>
            </table>
        </form>
        <div class="bot">
            <input type="submit" value="结算">
            <input type="text" onclick="cancel()" value="取消">
        </div>
    </div>
</div>
<div class="layui-anim" id="dd" data-anim="layui-anim-fadein" style="height: 60px;width: 100%" onclick="gouwuche()"><#--<img src="/static/vmimgs/购物车2.png" style="width: 100px;height: 80px;float: right">-->
    <button class="layui-btn layui-btn-fluid"  style="height: 70px;width: 1024px; background-color: #d9d9d9; font-size: 40px">购物车</button>
</div>
<#--<input id="Button1" type="button" value="开始全屏" onclick="kaishi()" />
<input id="Button2" type="button" value="关闭全屏" onclick="guanbi()" />-->
<input type="hidden" id="gouwuche" name="gouwuche" value="" />
</div>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
    layui.use(['layer','jquery','form'],function(){
        var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;

        $(".panel a").on("click",function(){
            //window.parent.goodsSlect($(this.name),$(this.price),$(this.id),$(this.number));
        });

        //监听指定开关
        form.on('switch(switchTest)', function(data){

            layer.msg( (this.checked ? '打开' : '关闭'), {
                offset: '400px'
            });
            if(this.checked){
                $(this).val("1");
            }else{
                $(this).val("0");
            }

            //开关状态判断，是否添加或更新数据
            switchStatus($(this));
        });
    });

    function  switchStatus(heatFlag){
        alert(123);
    };

    $(document).ready(function(){
        $(".mask_box").hide();
    });

    function cancel(){
        $(".mask_box").hide();
        $('#gouwuche').val('');
    }

    //添加购物车
    function goodsSlect(name,price,id,number){
        var flag = false;
        var tableLength = $("#myTbody tr").length;
        //判断该列表中有无商品数据
        if(tableLength <= 0) {
            var addtr = '<tr class="mytr">';
            addtr += '<td class="number">' + number + '</td>';//商品编码
            addtr += '<td>' + name + '</td>';//商品名称
            addtr += '<td class="kbj danjia">' + price + '</td>';//商品价格
            addtr += '<td class="numberTd"><div class="jiajian"><span class="jian" onclick="num_sub(this)">-</span><input type="text" value="1" class="num"><span class="jia" onclick="num_add(this)">+</span></div></td>';
            addtr += '<td class="heatFlag">' +
                        '<div class="Switch">\n' +
                        '   <input type="checkbox" name="Storage2" id="blance'+tableLength+'"/>\n' +
                        '   <label for="blance'+tableLength+'"><span class="open">加热</span><span class="close">常温</span><em></em></label>\n' +
                        '</div>' +
                    ' </td>';
            addtr += '<td><a class="layui-btn layui-btn-xs layui-btn-danger delete_btn" lay-event="del">' +
                    '<i class="layui-icon layui-icon-delete"></i>删除</a></td>';
            addtr += '</tr>';
            $("#myTbody").append(addtr);
            return;
        }else{
            //如果有数据，进行商品number比对，只更改该商品数量，不再进行添加行
            $("#myTbody tr").each(function () {
                //找到商品的名称与上面获取到的商品名称进行对比
                if ($(this).children("td:eq(0)").html() == number) {
                    //找到此商品的数量
                    var count = parseInt($(this).children("td:eq(3)").find("input").val());
                    count++;
                    $(this).children("td:eq(3)").find("input").val(count); //对商品的数量进行重新赋值
                    flag = true;
                    return false;
                }else {
                    flag = false;
                }
            })
        }

        //倘若购物车中午该商品，则添加新一行
        if (flag == false) {
            var addtr = '<tr class="mytr">';
            addtr += '<td>' + number + '</td>';//商品编码
            addtr += '<td>' + name + '</td>';//商品名称
            addtr += '<td class="kbj danjia">' + price + '</td>';//商品价格
            addtr += '<td class="numberTd"><div class="jiajian"><span class="jian" onclick="num_sub(this)">-</span><input type="text" value="1" class="num"><span class="jia" onclick="num_add(this)">+</span></div></td>';
            addtr += '<td class="heatFlag">' +
                    '<div class="Switch">\n' +
                    '   <input type="checkbox" name="Storage2" id="blance'+tableLength+'" checked />\n' +
                    '   <label for="blance'+tableLength+'"><span class="open">加热</span><span class="close">常温</span><em></em></label>\n' +
                    '</div>' +
                    ' </td>';
            addtr += '<td><a class="layui-btn layui-btn-xs layui-btn-danger delete_btn" lay-event="del">' +
                    '<i class="layui-icon layui-icon-delete"></i>删除</a></td>';
            addtr += '</tr>';
            $("#myTbody").append(addtr);
            return;
        }
    }

    //删除选中商品行
    $("#myTbody").on("click",".delete_btn",function(){
        if($("#myTbody tr").length < 1){
            /*$(".totalQuantity").html("0");
            $(".totalMoney").html("0");
            $(".totalIntegral").html("0");*/
            return;
        }
        $(this).parents("#myTbody tr").remove();
    })

    //加的效果
    function num_add(on_this){

        var totalQuantity = 0;    //总数量
        var totalMoney = 0;       //总金额
        var totalIntegral = 0;    //总积分
        $("#myTbody tr").each(function(){

            //获取当前行的单价
            this_price = $(this).children(".danjia").text();
            this_price = parseFloat(this_price);

            //获取当前行的数量
            this_num = $(this).find(".num").val();
            this_num = parseInt(this_num);

            //获取当前行的总价格、总积分
            var trmoney = this_price*this_num;

            //总金额、总数量、总积分
            totalQuantity += this_num*1;   //总数量
            totalMoney += trmoney*1        //总金额
        })
        this_price = $(on_this).parents("td").siblings("td.danjia").text();//获取单价
        this_price = parseFloat(this_price);
        console.log(totalMoney);
        totalMoney += this_price;
        //$('.totalMoney').html(totalMoney.toFixed(2));
        console.log(totalMoney);

        //当前商品数量
        this_num = $(on_this).siblings('.num');
        var get_this_num = parseInt(this_num.val())+1;
        this_num.val(get_this_num);

    }

    //减的效果
    function num_sub(on_this){

        var totalQuantity = 0;    //总数量
        var totalMoney = 0;       //总金额
        $("#myTbody tr").each(function(){

            //获取当前行的单价
            this_price = $(this).children(".danjia").text();
            this_price = parseFloat(this_price);

            //获取当前行的数量
            this_num = $(this).find(".num").val();
            this_num = parseInt(this_num);

            //获取当前行的总价格、总积分
            var trmoney = this_price*this_num;

            //总金额、总数量、总积分
            totalQuantity += this_num*1;   //总数量
            totalMoney += trmoney*1        //总金额
        })
        $(".totalQuantity").html(totalQuantity);
        $(".totalMoney").html(totalMoney);

        //当前商品数量
        this_num = $(on_this).siblings('.num');
        if(this_num.val() <= 1){
            this_num.siblings('.jian').removeAttr('onclick');
            return;
        }else{
            var get_this_num = this_num.val()-1;
            this_num.val(get_this_num);

            this_price = $(on_this).parents("td").siblings("td.danjia").text();//获取单价
            totalMoney -= this_price;
            $('.totalMoney').html(totalMoney.toFixed(2));

        }
    }

    //购物车清单
    function gouwuche(){
        debugger;
        var gouwuche = $('#gouwuche').val();
        if('' == gouwuche || null == gouwuche){
            $(".mask_box").show();
            $('#gouwuche').val('1');
        }else{
            $(".mask_box").hide();
            $('#gouwuche').val('');
        }
    }

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
                ,content: '<form class="layui-form" action="${base}/login/main" method="post">    \n' +
                '            <div class="layui-form-item">        \n' +
                '                <input class="layui-input" name="username" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">    \n' +
                '                </div>    \n' +
                '                    <div class="layui-form-item">          \n' +
                '                        <input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">    </div>\n' +
                '                            <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button></form>\n' +
                '        <script type="text/javascript" src="${base}/static/layui/layui.js">'

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