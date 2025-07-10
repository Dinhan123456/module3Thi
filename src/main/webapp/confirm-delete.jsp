<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Xác nhận xóa</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="p-4">
<h3>Bạn có muốn xóa thông tin thuê trọ:
    <c:forEach var="r" items="${roomsToConfirm}" varStatus="s">
        PT-${r.id}<c:if test="${!s.last}">, </c:if>
    </c:forEach>
    không?
</h3>
<form method="post" action="rental">
    <input type="hidden" name="action" value="delete" />
    <c:forEach var="r" items="${roomsToConfirm}">
        <input type="hidden" name="deleteIds" value="${r.id}"/>
    </c:forEach>
    <button type="submit" class="btn btn-danger">Có</button>
    <a href="rental" class="btn btn-secondary">Không</a>
</form>
</body>
</html>