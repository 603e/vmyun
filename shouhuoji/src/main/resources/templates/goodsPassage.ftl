﻿<HTML>
<HEAD>
    <TITLE>vm调试</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <LINK type=text/css rel=stylesheet
          href="${base}/static/css/main.css" media="all">
    <link rel="stylesheet" type="text/css"
          href="${base}/static/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css"
          href="${base}/static/easyui/themes/icon.css">
    <script type="text/javascript" src="${base}/static/js/jquery.min.js"></script>
    <script type="text/javascript"
            src="${base}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${base}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${base}/static/easyui/jquery-submit.js"></script>
    <script type="text/javascript" src="${base}/static/easyui/jquery.json-2.3.js"></script>
    <script type="text/javascript" src="${base}/static/js/goodsPassage/goodsPassage.js"></script>
</HEAD>
<style type="text/css">
    .table-a div{
        width:100%!important;
    }
    .table-a table{
        width:100%!important;
    }
    .table-a td{
        width:11%!important;
    }
</style>
<BODY style="background: #FFFFFF" onload="aaa()">
<div class="table-a">
    <input type="hidden" name="base" value="${base}">
    <input type="hidden" id="user" value="${user}">
    <#--<input id="sbutton" style="width:70px;height:25px">
    <label class="lblname" id="authenTypeL"></label>-->
    <table class="myTable" id="myTable"  ></table>
</div>

</HTML>