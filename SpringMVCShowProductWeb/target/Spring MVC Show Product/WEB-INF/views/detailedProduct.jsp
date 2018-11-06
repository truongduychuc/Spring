<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thông tin chi tiết sản phẩm</title>
</head>
<body>
	<div>
	</div>
	<div class="detailedProduct-container">
		<div><h3>${productForm.name}</h3></div>
		<div><img src="${pageContext.request.contextPath}/productImage?code=${productForm.code}"/></div>
		<div><h4>Giá: ${productForm.price}</h4></div>
		<br/>
		<div><p>${productForm.description}</p></div>
	</div>
</body>
</html>