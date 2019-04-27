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
            <div class="left fl">装配推荐</div>
            <div class="right fr"><a href="./index.html" target="_self">商城</a></div>
            <div class="clear"></div>
            <div class="xian center"></div>
        </div>
        <div class="regist_main center">
            <div class="username">C&nbsp;&nbsp;&nbsp;&nbsp;P&nbsp;&nbsp;&nbsp;&nbsp;U:&nbsp;&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByCondition?category.categoryName=cpu">
                <input class="shurukuang" name="username"
                       value="前往选择"
                       type="button"/></a><span>请选择CPU</span>
            </div>
            <div class="username">主&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;板:&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByCondition?category.categoryName=主板"><input
                    class="shurukuang" name="password" value="前往选择" type="button"/></a><span>请选择主板</span>
            </div>

            <div class="username">显&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卡:&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByCondition?category.categoryName=显卡"><input
                    class="shurukuang" name="repassword" value="前往选择"
                    type="button"/></a><span>请选择显卡</span></div>
            <div class="username">内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存:&nbsp;&nbsp;<input
                    class="shurukuang" name="tel"
                    value="------" readonly
                    type="button"/><span>需要先完成CPU或显卡或主板的选择</span>
            </div>
            <div class="username">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源:&nbsp;&nbsp;<input
                    class="shurukuang" name="tel"
                    value="------" readonly
                    type="button"/><span>需要先完成CPU或显卡或主板的选择</span>
            </div>
            <div class="username">机&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:&nbsp;&nbsp;<input
                    class="shurukuang" name="tel"
                    value="------" readonly
                    type="button"/><span>需要先完成CPU或显卡或主板的选择</span>
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
        <div class="regist_submit">
            <input onclick="aaaa()" class="submit" name="submit" type="submit" value="立即购买">
        </div>

    </div>
</div>
</body>
</html>