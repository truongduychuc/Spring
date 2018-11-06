<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smartphone xách tay</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/myCss/style.css'/>">
	<script type="text/javascript" src='<c:url value='/bootstrap/js/bootstrap.min.js'/>'></script>
		<script type="text/javascript" src='<c:url value='/jquery/jquery.min.js'/>'></script>
</head>
<body>
	<div id="header">
		<jsp:include page="_header.jsp"></jsp:include>
	</div>
	<div id="login-container">
	<jsp:include page="login.jsp"></jsp:include>
</div>
<div id="introduce" class="container-fluid bg-1 text-center">
	<h2>Giới thiệu</h2>
	<img alt="introduction" class="img-rounded" src="<c:url value='/image/introduction.jpg'/>" style="display: inline;width:350px;height: auto; ">
	<img alt="introduction" class="img-rounded" src="<c:url value='/image/sky.jpg'/>" style="display: inline;width:350px;height: auto; ">
</div>

<div id="product-container" class="container-fluid bg-3 text-center"> 
  <h3>Sản phẩm</h3>
  <c:if test="${empty paginationProducts.list}">
				<h3>Chưa có sản phẩm nào!</h3>
				<security:authorize access="hasAnyRole('ADMIN','MANAGER')">
					<div class="addNewProduct-button">
					<a href="${pageContext.request.contextPath}/product">Thêm sản phẩm</a>
				</div>
				</security:authorize>
</c:if>
  <div class="row">
  	<c:forEach items="${paginationProducts.list}" var="productInfo">
  	   <div class="col-md-2 col-md-offset-1 product">
      <img class="img-thumbnail" style="width:auto;height: 195px" src="${pageContext.request.contextPath}/productImage?code=${productInfo.code}" alt="Image">
     	<h3>${productInfo.name}</h3>
     	<p>Mã sản phẩm: ${productInfo.code}</p>
     	<p>Gía:	${productInfo.price} VNĐ</p>
     	<div class="row">
     		<a class="btn btn-link btn-sm" href="${pageContext.request.contextPath}/detail?code=${productInfo.code}">Chi tiết</a>
     	</div>
     	
     	<security:authorize access="hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')">
     		<div class="row">
     			<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/product?code=${productInfo.code}">Chỉnh sửa</a>
     			<a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/removeProduct/${productInfo.code}">Xoá</a>	
     		</div>
			
			
		</security:authorize>
    </div>
  	</c:forEach>
  	<br/>
  	<c:if test="${paginationProducts.totalPages>1}">
				<ul class="pagination">
					<c:forEach items="${paginationProducts.navigationPages}" var="page">
						<c:if test="${page != -1}">
							<li><a href="productList?page=${page}" class="nav-item">${page}</a><li>
						</c:if>
						<c:if test="${page == -1}">
							<span class="nav-item">...</span>
						</c:if>
					</c:forEach>
				</ul>
			</c:if>
   
  </div>
</div>
<script type="text/javascript">
$(document).ready(function(){
	$("#login-link").click(function(){
		$("#login-container").slideToggle("slow");
	});
});
</script>
</body>
</html>