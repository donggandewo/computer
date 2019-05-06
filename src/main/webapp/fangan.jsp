<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css">
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/china.js"></script>
    <script>
        function aaaa() {
            alert("请先选择商品")
        }
    </script>
</head>
<body>
<div class="regist">
    <div>${requestScope.warring}</div>
    <form>
        <c:forEach var="computer" items="${requestScope.computerList}">
            <table>
                <c:forEach items="${computer.computer}" var="com">
                    ${com.key}<br>
                    ${com.value}<br>
                </c:forEach>
            </table>
            <a href="">
                <button>前往修改</button>
            </a>
        </c:forEach>
    </form>
</div>
</body>
</html>