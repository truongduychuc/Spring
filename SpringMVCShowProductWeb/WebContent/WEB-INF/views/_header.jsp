<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/myCss/style.css'/>">
	<script type="text/javascript" src='<c:url value='/bootstrap/js/bootstrap.min.js'/>'></script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span> 
      </button>
      <a class="navbar-brand" href="#">Smartphone xách tay</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
   			<li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
   			
   				<li><a href="#product-container">Sản phẩm</a></li>
   			
			
			<c:if test="${pageContext.request.userPrincipal.name!=null}">
				<security:authorize access="hasAnyRole('ADMIN','MANAGER')">
					<li><a href="${pageContext.request.contextPath}/product">Thêm sản phẩm</a></li>
				</security:authorize>
				<li><a href="${pageContext.request.contextPath}/accountInfo">Xin chào, ${pageContext.request.userPrincipal.name}</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name==null}">
				<li><a id="login-link" href="#">Đăng nhập Admin</a></li>
			</c:if>
      </ul>
    </div>
  </div>
</nav>
</body>