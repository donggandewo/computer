<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link href="./css/login.css" rel="stylesheet" type="text/css">
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
    <script type="text/javascript" src="../js/china.js"></script>
    <script>
        function aaaa() {
            alert("请先选择商品")
        }
    </script>
</head>
<body>
<div class="regist">
    <div class="regist_center">
        <div class="regist_top">
            <div class="left fl">请选择您的装机需求</div>
            <div class="right fr"><a href="./index.html" target="_self">商城</a></div>
            <div class="clear"></div>
            <div class="xian center"></div>
        </div>
        <div class="regist_main center">
            <div class="username">方&nbsp;&nbsp;&nbsp;&nbsp;案&nbsp;&nbsp;&nbsp;&nbsp;一:&nbsp;&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByPerformance?performance=12&demand=1">
                <input class="shurukuang" name="username"
                       value="选择这个方案"
                       type="button"/></a><span>注重办公和系统流畅度（影音娱乐次要或者无）</span>
            </div>
            <div class="username">方&nbsp;&nbsp;&nbsp;&nbsp;案&nbsp;&nbsp;&nbsp;&nbsp;二:&nbsp;&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByPerformance?performance=18&demand=2">
                <input class="shurukuang" name="username"
                       value="选择这个方案"
                       type="button"/></a><span>注重办公同时依然有一定的娱乐能力（或者说有娱乐能力更好）</span>
            </div>
            <div class="username">方&nbsp;&nbsp;&nbsp;&nbsp;案&nbsp;&nbsp;&nbsp;&nbsp;三:&nbsp;&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByPerformance?performance=35&demand=3">
                <input class="shurukuang" name="username"
                       value="选择这个方案"
                       type="button"/></a><span> 既需要办公也需要娱乐</span>
            </div>
            <div class="username">方&nbsp;&nbsp;&nbsp;&nbsp;案&nbsp;&nbsp;&nbsp;&nbsp;四:&nbsp;&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByPerformance?performance=40&demand=4">
                <input class="shurukuang" name="username"
                       value="选择这个方案"
                       type="button"/></a><span>影音娱乐，流畅游戏（高性能）</span>
            </div>
            <!--<div class="username">
               <div class="left fl">验&nbsp;&nbsp;证&nbsp;&nbsp;码:&nbsp;&nbsp;<input class="yanzhengma"
                                                                                    name="username"
                                                                                    placeholder="请输入验证码"
                                                                                    type="text"/></div>
                <div class="right fl"><img src="./image/yanzhengma.jpg"></div>
                <div class="clear"></div>
            </div>-->
        </div>

    </div>
</div>
</body>
</html>