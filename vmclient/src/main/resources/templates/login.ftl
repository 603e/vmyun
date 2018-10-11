<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
    <title>登录</title>
</head>
<body>
<form class="layui-form" action="${base}/login/main" method="post">
    <div class="layui-form-item">
        <input class="layui-input" name="username" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">
    </div>
    <div class="layui-form-item">
        <input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">
    </div>
    <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script>
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
</script>
</body>
</html>