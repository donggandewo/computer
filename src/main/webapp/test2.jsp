<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="./css/style.css" rel="stylesheet" type="text/css">
    <title>推荐页面</title>
</head>
<body>
<c:forEach items="${requestScope.list}" var="list">
<table>
    <thead>
    <tr>
        <th>名称</th>
        <th>价格</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>CPU:${list.cpu.productName}</td>
        <td> ${list.cpu.newPrice}</td>
    </tr>
    <tr>
        <td>主板:${list.zhuban.productName}</td>
        <td>${list.zhuban.newPrice}</td>
    </tr>
    <tr>
        <td>显卡:${list.xianka.productName}</td>
        <td>${list.xianka.newPrice}</td>
    </tr>
    <tr>
        <td>内存:${list.neicun.productName}</td>
        <td>${list.neicun.newPrice}</td>
    </tr>
    <tr>
        <td>硬盘:${list.yingpan.productName}</td>
        <td>${list.yingpan.newPrice}</td>
    </tr>
    <tr>
        <td>电源:${list.dianyuan.productName}</td>
        <td>${list.dianyuan.newPrice}</td>
    </tr>
    <tr>
        <td colspan="2">总价:${list.totalPrice}</td>
    </tr>
    </tbody>
</table>
    <a href="${pageContext.request.contextPath}/product/getComputer?totalPrice=${list.totalPrice}">
        <button>选择这个方案</button>
    </a></br>
</c:forEach>
</body>
</html>