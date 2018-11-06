<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tất cả sản phẩm</title>
</head>
<body>
	<div class="allProducts-titlepage">
		<h2>Tất cả sản phẩm</h2>	
	</div>
		<div class="product-container">
			<c:forEach items="${paginationProducts.list}" var="productInfo">
				<div class="product-preview-container">
					<ul>
						<li><img class="product-image" src="${pageContext.request.contextPath}/productImage?code=${productInfo.code}"/></li>
						<li>Code: ${productInfo.code}</li>
						<li>Name: ${productInfo.name}</li>
						<li>Price:${productInfo.price} VNĐ</li>
						<li><a href="${pageContext.request.contextPath}/detail?code=${productInfo.code}">Chi tiết</a></li>
						<security:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')">
							<li><a href="${pageContext.request.contextPath}/product?code=${productInfo.code}">Chỉnh sửa</a></li>
							<li><a href="${pageContext.request.contextPath}/removeProduct/${productInfo.code}">Xoá</a></li>
						</security:authorize>
					</ul>
					
				</div>
			</c:forEach>
			<br/>
			<c:if test="${paginationProducts.totalPages>1}">
				<div class="page-navigator">
					<c:forEach items="${paginationProducts.navigationPages}" var="page">
						<c:if test="${page != -1}">
							<a href="productList?page=${page}" class="nav-item">${page}</a>
						</c:if>
						<c:if test="${page == -1}">
							<span class="nav-item">...</span>
						</c:if>
					</c:forEach>
				</div>
			</c:if>
		</div>
</body>
</html>