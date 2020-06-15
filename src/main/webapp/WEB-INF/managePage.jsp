<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8"%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="referrer" content="no-referrer" />
    <title>商品列表</title>
</head>

<body background="icon_app.png">
<div>
    <table width="100%" border="0"  style="margin-bottom: 40px;margin-top: 20px" >
        <tr>
            <td>用户名: ${adm_name}</td>
        </tr>
    </table>

</div>
<table width="100%" border=1>
    <tr>
        <td>商品名称</td>
        <td>商品图片</td>
        <td>商品描述</td>
        <td>商品价格</td>
        <td>上传日期</td>
        <td>商品状态</td>
        <td style="border: 0px">操作</td>
    </tr>
    <c:forEach items="${list}" var="list">
        <tr>
            <td>${list.goodsinfo_name }</td>
            <td><img src=${list.goodsinfo_thumb} width="60" height="60"  onerror="this.src='https://tse4-mm.cn.bing.net/th?id=OIP.H8H0pucFzRoDgTqBwjbhbAAAAA&pid=Api&rs=1'"></td>
            <td>${list.goodsinfo_desc }</td>
            <td>${list.goodsinfo_price }</td>
            <td>${list.goodsinfo_time }</td>
            <td>
                <c:choose>
                <c:when test="${list.goodsinfo_tag ==0}">
                  待审核
                </c:when>

                <c:when test="${list.goodsinfo_tag==1}">
                已通过
                 </c:when>
                <c:when test="${list.goodsinfo_tag==-1}">
                  已拒绝
            </c:when>

            </c:choose>
            </td>

            <td><a href="${pageContext.request.contextPath }/admin/agree?id=${list.goodsinfo_id}">通过</a></td>
            <td><a href="${pageContext.request.contextPath }/admin/refuse?id=${list.goodsinfo_id}">拒绝</a></td>
        </tr>
    </c:forEach>


</table>
</body>
</html>