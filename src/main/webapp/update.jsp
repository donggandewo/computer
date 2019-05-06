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
    <form action="${pageContext.request.contextPath}/product/update" method="post">
        <div class="regist_center">
            <div class="regist_top">
                <div class="left fl">装配推荐</div>
                <div class="right fr"><a href="./index.html" target="_self">商城</a></div>
                <div class="clear"></div>
                <div class="xian center"></div>
            </div>
            <div class="regist_main center">
                <div class="username">请填写产品名称
                    <input type="hidden" value="${requestScope.product.productId}" name="productId"/>
                    <input class="shurukuang" name="productName"
                           value="${requestScope.product.productName}"
                           type="text"/>
                </div>
                <div class="username"> 请填写产品原价
                    <input class="shurukuang" name="oldPrice"
                           value="${requestScope.product.oldPrice}"
                           type="number"/><span>请填写数字</span>
                </div>
                <div class="username"> 请填写产品现价
                    <input class="shurukuang" name="newPrice"
                           value="${requestScope.product.newPrice}"
                           type="number"/><span>请填写数字</span>
                </div>
                <div class="username">请填写产品性能
                    <input class="shurukuang" name="performance"
                           value="${requestScope.product.performance}"
                           type="number"/><span>请填写数字,没有可以不填</span>
                </div>
                <input type="hidden" value="${requestScope.product.addTime}" name="time">
                <div class="username"> 请填写产品媒体评分总分
                    <input class="shurukuang" name="media"
                           value="${requestScope.product.media}"
                           type="number"/><span>请填写数字,没有可以不填</span>
                </div>
                <div class="username">请填写评分媒体数量
                    <input class="shurukuang" name="mediaNum"
                           value="${requestScope.product.mediaNum}"
                           type="number"/><span>请填写数字,没有可以不填</span>
                </div>
                <div class="username">请填写产品销量
                    <input class="shurukuang" name="sales"
                           value="${requestScope.product.sales}"
                           type="number"/><span>请填写数字,没有可以不填</span>
                </div>
                <div class="username">
                    <input class="shurukuang"
                           value="修改"
                           type="submit"/>
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
        </div>
    </form>
</div>
</body>
</html>