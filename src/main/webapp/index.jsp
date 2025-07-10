<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách thuê trọ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-4">
<h2 class="mb-4">Quản lý thuê trọ</h2>

<!-- Form tìm kiếm -->
<form class="row g-3 mb-3" method="get" action="rental">
    <div class="col-auto">
        <input type="text" name="search" class="form-control" placeholder="Tìm theo tên người thuê">
    </div>
    <div class="col-auto">
        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
    </div>
    <div class="col-auto">
        <a href="rental?action=create-form" class="btn btn-success">+ Tạo mới</a>
    </div>
</form>

<!-- Danh sách thuê trọ -->
<form method="get" action="rental">
    <input type="hidden" name="action" value="confirm">
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>Chọn</th>
            <th>Tên người thuê</th>
            <th>SĐT</th>
            <th>Ngày bắt đầu</th>
            <th>Hình thức thanh toán</th>
            <th>Ghi chú</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="r" items="${rentalRooms}">
            <tr>
                <td><input type="checkbox" name="confirm" value="${r.id}"></td>
                <td>${r.tenantName}</td>
                <td>${r.phoneNumber}</td>
                <td>${r.startDate}</td>
                <td>${r.paymentMethodName}</td>
                <td>${r.note}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <button type="submit" class="btn btn-danger">Xóa mục đã chọn</button>
</form>
</body>
</html>
