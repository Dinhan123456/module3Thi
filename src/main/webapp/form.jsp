<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Thêm mới thuê trọ</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="p-4">
<h2>Thêm mới thuê trọ</h2>
<form method="post" action="rental">
  <input type="hidden" name="action" value="create">
  <div class="mb-3">
    <label>Tên người thuê</label>
    <input type="text" name="tenantName" class="form-control" required>
  </div>
  <div class="mb-3">
    <label>Số điện thoại</label>
    <input type="text" name="phoneNumber" class="form-control" required>
  </div>
  <div class="mb-3">
    <label>Ngày bắt đầu thuê</label>
    <input type="date" name="startDate" class="form-control" required>
  </div>
  <div class="mb-3">
    <label>Hình thức thanh toán</label>
    <select name="paymentMethodId" class="form-control">
      <c:forEach var="pm" items="${paymentMethods}">
        <option value="${pm.key}">${pm.value}</option>
      </c:forEach>
    </select>
  </div>
  <div class="mb-3">
    <label>Ghi chú</label>
    <textarea name="note" class="form-control" maxlength="200"></textarea>
  </div>
  <button type="submit" class="btn btn-success">Tạo mới</button>
  <a href="rental" class="btn btn-secondary">Hủy</a>
</form>
</body>
</html>
