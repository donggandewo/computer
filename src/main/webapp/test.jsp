<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="./css/style.css" rel="stylesheet" type="text/css">
    <title>推荐页面</title>
</head>
<body>
<table>
    <div>
        <a href="${pageContext.request.contextPath}/product/selectByCondition?category.categoryName=cpu">
            <button>请选择cpu</button>
        </a>
    </div>
    <br>
    <div>
        <a href="${pageContext.request.contextPath}/product/selectByCondition?category.categoryName=主板">
            <button>请选择主板</button>
        </a>
    </div>
    <br>
    <div>
        <a href="">
            <button>请选择显卡</button>
        </a>
    </div>
    <br>
    <div>
        <a href="">
            <button>请选择内存</button>
        </a>
    </div>
    <br>
    <div>
        <a href="">
            <button>请选择硬盘</button>
        </a>
    </div>
    <br>
    <div>
        <a href="">
            <button>请选择散热器</button>
        </a>
    </div>
    <br>
    <div>
        <button>请选择电源</button>
    </div>
    <br>
    <div>
        <a href="">
            <button>请选择机箱</button>
        </a>
    </div>
    <br>
</table>
</body>
</html>