<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 动感张哥
  Date: 2019/4/8
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<c:forEach items="${requestScope.list}" var="p">
    编号:${p.productId}</br>
    书名:${p.productName}</br>
    作者:${p.oldPrice}</br>
</c:forEach>
</body>
</html>

