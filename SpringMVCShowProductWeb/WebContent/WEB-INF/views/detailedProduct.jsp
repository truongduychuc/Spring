<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thông tin chi tiết sản phẩm</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/myCss/style.css'/>">
	<script type="text/javascript" src='<c:url value='/bootstrap/js/bootstrap.min.js'/>'></script>
		<script type="text/javascript" src='<c:url value='/jquery/jquery.min.js'/>'></script>
</head>
<body>
	<div>
		<jsp:include page="_header.jsp"></jsp:include>
	</div>
	<div class="container-fluid bg-3">
	<div class="row">
	<div class="col-lg-12 col-offset-6 text-center">
		<div><h3>${productForm.name}</h3></div>
		<div><img src="${pageContext.request.contextPath}/productImage?code=${productForm.code}"/></div>
		<div><h4>Giá: ${productForm.price} VNĐ</h4></div>
		<br/>
		<div><p>${productForm.description}</p></div>
	</div>
	</div>
	</div>
</body>
</html>