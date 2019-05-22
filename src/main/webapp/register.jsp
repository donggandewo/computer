<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">

</head>
<body>
<div class="regist">
    <div class="regist_center">
        <div class="regist_top">
            <div class="left fl">用户注册</div>
            <div class="right fr"><a href="./index.html" target="_self">商城</a></div>
            <div class="clear"></div>
            <div class="xian center"></div>
        </div>

        <form action="${pageContext.request.contextPath}/user/regist">
            <div class="regist_main center">
                <div class="username">用&nbsp;&nbsp;户&nbsp;&nbsp;名:&nbsp;&nbsp;<input
                        class="shurukuang" name="username"
                        type="text"
                        value="请填写用户名"/><span>请填写用户名（必填）</span>
                </div>
                <div class="username">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:&nbsp;&nbsp;<input
                        class="shurukuang" name="mail" type="text" value="请填写邮箱"/><span>输入正确的邮箱，用以验证（必填）</span>
                </div>

                <div class="username">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:&nbsp;&nbsp;<input
                        class="shurukuang" name="password" type="password"
                        value=""/><span>6~14位，不包含空格中文字符（必填）</span></div>
                <div class="username">重复密码:&nbsp;&nbsp;<input
                        class="shurukuang" name=""
                        type="password"
                        value=""/><span>两次密码要一致（必填）</span>
                </div>
                <div class="username">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:&nbsp;&nbsp;<input
                        class="shurukuang" name="tel"
                        type="text"
                        value=""/><span>请填写您的电话（选填）</span>
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
                <input class="submit" name="submit" type="submit" value="立即注册">
            </div>
        </form>
    </div>

</div>
</body>
</html>