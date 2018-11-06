<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/myCss/style.css'/>">
<title>Thông tin tài khoản</title>
</head>
<body>
	<div id="header">
		<jsp:include page="_header.jsp"></jsp:include>
	</div>
	<div class="row account-container">
		<div class="col-lg-4 text-center col-centered">
				<h2>Thông tin tài khoản</h2>
				<img src="<c:url value='/image/generic-user-purple.png'/>" class="" alt="Image">
				<ul class="account-info-ul">
					<li>Tên tài khoản:  ${pageContext.request.userPrincipal.name}</li>
					<c:forEach items="${userDetails.authorities}" var="auth">
						<li>Vai trò:${auth.authority}</li>
					</c:forEach>
				</ul>
		</div>
	</div>
</body>
</html>