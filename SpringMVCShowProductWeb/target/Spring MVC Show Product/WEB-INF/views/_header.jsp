<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"  %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header-container">
	<div class="logo-site"></div>
	<div class="menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>
			<li><a href="${pageContext.request.contextPath}/productList">Sản phẩm</a></li>
			<c:if test="${pageContext.request.userPrincipal.name!=null}">
				<li><a href="${pageContext.request.contextPath}/accountInfo">Xin chào, ${pageContext.request.userPrincipal.name}</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name==null}">
				<li><a href="${pageContext.request.contextPath}/login">Đăng nhập Admin</a></li>
			</c:if>
		</ul>
	</div>
</div>