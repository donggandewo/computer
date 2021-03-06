<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员登录</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">

</head>
<body>
<!-- login -->
<div class="top center">
    <div class="logo center">
        <a href="./index.html" target="_blank"><img alt="" src="./image/mistore_logo.png"></a>
    </div>
</div>
<form action="${pageContext.request.contextPath}/user/login" class="form center" method="post">
    <div class="login">
        <div class="login_center">
            <div class="login_top">
                <div class="left fl">会员登录</div>
                <div class="right fr">您还不是我们的会员？<a href="./register.html" target="_self">立即注册</a></div>
                <div class="clear"></div>
                <div class="xian center"></div>
            </div>
            <div class="login_main center">
                <div class="username">用户名:&nbsp;<input class="shurukuang" name="mail" placeholder="请输入你的邮箱"
                                                       type="text"/></div>
                <div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;<input class="shurukuang" name="password"
                                                                              placeholder="请输入你的密码" type="password"/>
                </div>
                <div class="username">
                    <div class="left fl">验证码:&nbsp;<input class="yanzhengma" name="" placeholder="请输入验证码"
                                                          type="text"/></div>
                    <div class="right fl"><img src="./image/yanzhengma.jpg"></div>
                    <div class="clear"></div>
                </div>
            </div>
            <div class="login_submit">
                <input class="submit" name="submit" type="submit" value="立即登录">
            </div>

        </div>
    </div>
</form>
<footer>
    <div class="copyright">简体 | 繁体 | English | 常见问题</div>
    <div class="copyright">小米公司版权所有-京ICP备10046444-<img alt="" src="./image/ghs.png">京公网安备11010802020134号-京ICP证110507号
    </div>

</footer>
</body>
</html>