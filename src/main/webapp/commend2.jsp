<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户推荐</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
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
            <div>${requestScope.cpu.productName}</div>
            <div>${requestScope.cpu.newPrice}</div>
            <div class="username">C&nbsp;&nbsp;&nbsp;&nbsp;P&nbsp;&nbsp;&nbsp;&nbsp;U:&nbsp;&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByCondition2?category.categoryName=cpu&productId=${requestScope.cpu.productId}">
                <input class="shurukuang" name="username" value="前往选择" type="button"/></a><span>切换CPU</span>
            </div>
            <div>${requestScope.zhuban.productName}</div>
            <div>${requestScope.zhuban.newPrice}</div>
            <div class="username">主&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;板:&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByCondition2?category.categoryName=主板&productId=${requestScope.zhuban.productId}"><input
                    class="shurukuang" name="password" value="前往选择" type="button"/></a><span>切换主板</span>
            </div>
            <div>${requestScope.xianka.productName}</div>
            <div>${requestScope.xianka.newPrice}</div>
            <div class="username">显&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;卡:&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByCondition2?category.categoryName=显卡&productId=${requestScope.xianka.productId}"><input
                    class="shurukuang" name="repassword" value="前往选择"
                    type="button"/></a><span>切换显卡</span></div>
            <div>${requestScope.neicun.productName}</div>
            <div>${requestScope.neicun.newPrice}</div>
            <div class="username">内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存:&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/product/selectByCondition2?category.categoryName=内存&productId=${requestScope.neicun.productId}">
                    <input
                            class="shurukuang" name="tel"
                            value="前往选择"
                            type="button"/></a><span>切换内存</span>
            </div>
            <div>${requestScope.yingpan.productName}</div>
            <div>${requestScope.yingpan.newPrice}</div>
            <div class="username">硬&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;盘:&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByCondition2?category.categoryName=固态硬盘&productId=17"><input
                    class="shurukuang" name="tel"
                    value="前往选择" readonly
                    type="button"/></a><span>切换硬盘</span>
            </div>
            <div>${requestScope.dianyuan.productName}</div>
            <div>${requestScope.dianyuan.newPrice}</div>
            <div class="username">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源:&nbsp;&nbsp;<a
                    href="${pageContext.request.contextPath}/product/selectByCondition2?category.categoryName=电源&productId=${requestScope.dianyuan.productId}"><input
                    class="shurukuang" name="tel"
                    value="前往选择"
                    type="button"/></a><span>切换电源</span>
            </div>
            <div class="username">总&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价:&nbsp;&nbsp;<input
                    class="shurukuang" name="tel"
                    value="${requestScope.totalPrice}" readonly
                    type="text"/>
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