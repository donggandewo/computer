<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的购物车-小米商城</title>
    <link href="./css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<!-- start header -->
<!--end header -->

<!-- start banner_x -->
<div class="banner_x center">
    <a href="./index.html" target="_blank">
        <div class="logo fl"></div>
    </a>

    <div class="wdgwc fl ml40">我的购物车</div>
    <div class="wxts fl ml20">温馨提示：产品是否购买成功，以最终下单为准哦，请尽快结算</div>
    <div class="dlzc fr">
        <ul>
            <li><a href="./login.html" target="_blank">登录</a></li>
            <li>|</li>
            <li><a href="./register.html" target="_blank">注册</a></li>
        </ul>

    </div>
    <div class="clear"></div>
</div>
<div class="xiantiao"></div>
<div class="gwcxqbj">
    <div class="gwcxd center">
        <div class="top2 center">
            <div class="sub_top fl">
                <input class="quanxuan" type="checkbox" value="quanxuan"/>全选
            </div>
            <div class="sub_top fl">商品名称</div>
            <div class="sub_top fl">单价</div>
            <div class="sub_top fl">数量</div>
            <div class="sub_top fl">小计</div>
            <div class="sub_top fr">操作</div>
            <div class="clear"></div>
        </div>

        <c:forEach var="cart" items="${requestScope.cart.map}">
            <div class="content2 center">
                <div class="sub_content fl ">
                    <input class="quanxuan" type="checkbox" value="quanxuan"/>
                </div>
                <div class="sub_content fl"><img src="./image/gwc_xiaomi6.jpg"></div>
                <div class="sub_content fl ft20">${cart.value.productName}</div>
                <div class="sub_content fl ">${cart.value.newName}元</div>
                <div class="sub_content fl">
                    <input class="shuliang" min="1" step="1" type="number" value="1">
                </div>
                <div class="sub_content fl">${cart.value.productName}*${cart.value.buyCount}元</div>
                <div class="sub_content fl"><a href="">×</a></div>
                <div class="clear"></div>
            </div>
        </c:forEach>


    </div>
    <div class="jiesuandan mt20 center">
        <%--<div class="tishi fl ml20">
            <ul>
                <li><a href="./liebiao.html">继续购物</a></li>
                <li>|</li>
                <li>共<span>2</span>件商品，已选择<span>1</span>件</li>
                <div class="clear"></div>
            </ul>
        </div>--%>
        <div class="jiesuan fr">
            <div class="jiesuanjiage fl">合计（不含运费）：<span>${requestScope.cart.price}元</span></div>
            <div class="jsanniu fr"><input class="jsan" name="jiesuan" type="submit" value="去结算"/></div>
            <div class="clear"></div>
        </div>
        <div class="clear"></div>
    </div>

</div>


<!-- footer -->
<footer class="center">

    <div class="mt20">小米商城|MIUI|米聊|多看书城|小米路由器|视频电话|小米天猫店|小米淘宝直营店|小米网盟|小米移动|隐私政策|Select Region</div>
    <div>©mi.com 京ICP证110507号 京ICP备10046444号 京公网安备11010802020134号 京网文[2014]0059-0009号</div>
    <div>违法和不良信息举报电话：185-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</div>
</footer>

</body>
</html>