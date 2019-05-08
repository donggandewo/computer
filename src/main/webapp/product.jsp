<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>列表</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<!-- start header -->
<header>
    <div class="top center">
        <div class="left fl">
            <ul>
                <li><a href="http://www.mi.com/" target="_blank">商城</a></li>
                <li>|</li>
                <li><a href="">游戏</a></li>
                <li>|</li>
                <li><a href="">多看阅读</a></li>
                <li>|</li>
                <li><a href="">云服务</a></li>
                <li>|</li>
                <li><a href="">金融</a></li>
                <li>|</li>
                <li><a href="">商城移动版</a></li>
                <li>|</li>
                <li><a href="">问题反馈</a></li>
                <li>|</li>
                <li><a href="">Select Region</a></li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="right fr">
            <div class="gouwuche fr"><a href="">购物车</a></div>
            <div class="fr">
                <ul>
                    <li><a href="./login.html" target="_blank">登录</a></li>
                    <li>|</li>
                    <li><a href="./register.html" target="_blank">注册</a></li>
                    <li>|</li>
                    <li><a href="">消息通知</a></li>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>
</header>
<!--end header -->

<!-- start banner_x -->
<div class="banner_x center">
    <a href="./index.html" target="_blank">
        <div class="logo fl"></div>
    </a>
    <a href="">
        <div class="ad_top fl"></div>
    </a>
    <div class="nav fl">
        <ul>
            <li><a href="">手机</a></li>
            <li><a href="">电脑配件</a></li>
            <li><a href="">平板·笔记本</a></li>
            <li><a href="">电视</a></li>
            <li><a href="">盒子·影音</a></li>
            <li><a href="">路由器</a></li>
            <li><a href="">智能硬件</a></li>
            <li><a href="">服务</a></li>
            <li><a href="">社区</a></li>
        </ul>
    </div>
    <div class="search fr">
        <form action="" method="post">
            <div class="text fl">
                <input class="shuru" placeholder="最新卡皇GTX2080现货" type="text">
            </div>
            <div class="submit fl">
                <input class="sousuo" type="submit" value="搜索"/>
            </div>
            <div class="clear"></div>
        </form>
        <div class="clear"></div>
    </div>
</div>
<!-- end banner_x -->

<!-- start banner_y -->
<!-- end banner -->

<!-- start danpin -->
<div class="danpin center">

    <div class="biaoti center">商品列表</div>
    <div class="main center">
        <c:forEach var="p" items="${requestScope.list}">
            <div class="mingxing fl mb20" onmousemove="this.style.border='2px solid red'"
                 onmouseout="this.style.border='2px solid #fff'"
                 style="border:2px solid #fff;width:230px;cursor:pointer;">
                <div class="sub_mingxing"><a href="./xiangqing.html" target="_blank"><img alt=""
                                                                                          src="${pageContext.request.contextPath}/image/liebiao_xiaomi6.jpg"></a>
                </div>
                <div class="pinpai"><a href="./xiangqing.html" target="_blank">${p.productName}</a></div>
                <div class="youhui">${p.description}</div>
                <div class="jiage">${p.newPrice}</div>
                <div class="xiadan ml20 mt10">
                    <a href="${pageContext.request.contextPath}/product/selectOne?productId=${p.productId}">
                        <button class="jrgwc">修改</button>
                    </a>
                </div>
            </div>
        </c:forEach>
        <div class="clear"></div>
    </div>
</div>



<!-- end danpin -->
</body>
</html>