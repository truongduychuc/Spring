<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> <!-- set charset to utf-8 to display Vietnamese character correctly -->

<title>
	<c:if test="${not empty productForm.code}">
		Chỉnh sửa sản phẩm    <!-- if code value exists in the url, title will be changed to 'Chinh sua san pham -->
	</c:if>
	<c:if test="${empty productForm.code}">
		Thêm sản phẩm
	</c:if>
</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/myCss/style.css'/>">
	<script type="text/javascript" src='<c:url value='/bootstrap/js/bootstrap.min.js'/>'></script>
		<script type="text/javascript" src='<c:url value='/jquery/jquery.min.js'/>'></script>
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<div class="container-fluid bg-3">
	<div class="row">
<div class="col-lg-6 col-lg-offset-3">
		<div class="add_edit_product-titlepage"> 
		<h2>
			<c:if test="${not empty productForm.code}">  <!-- if code value exists in the url, h2 will be changed to 'Chinh sua san pham' -->
				Chỉnh sửa sản phẩm
			</c:if>
			<c:if test="${empty productForm.code}">
				Thêm sản phẩm
			</c:if>
		</h2>
	</div>
	<c:if test="${not empty errorMessage}">  <!-- response the error message of productInfo authentication -->
		<div class="error-message">
			${errorMessage}
		</div>
	</c:if>
	<div class="product-container">
		<form:form modelAttribute="productForm" method="POST" enctype="multipart/form-data"> <!-- this will make the url to method post and corresponding to the request mapping /product with post method -->
			<table class="table">
				<tr>
					<td>Mã sản phẩm*</td>
					<td>
						<c:if test="${not empty productForm.code}">
							<form:hidden path="code"/>
							${productForm.code}
						</c:if>
						<c:if test="${empty productForm.code}">
							<form:input path="code"/>
							<form:hidden path="newProduct"/> <!-- help page can determine new or existing product -->
						</c:if>
					</td>
					<td><form:errors path="code" class="error-message" /></td> <!-- show the error massage sent by product info authentication in MyDBauthenticationService -->
				</tr>
				<tr>
					<td>Tên sản phẩm*</td>
					<td><form:input path="name"/></td>
					<td><form:errors path="name"/></td>
				</tr>
				<tr>
					<td>Đơn giá*</td>
					<td><form:input path="price"/></td>
					<td><form:errors path="price"/></td>
				</tr>
				<tr>
					 <td>Hình ảnh</td>
              		 <td><img src="${pageContext.request.contextPath}/productImage?code=${productForm.code}" width="100"/></td>
		             <td></td>
				</tr>
				<tr>
					<td>Upload Image</td>
	               <td><form:input type="file" path="fileData"/></td> <!-- get image then send to fileData property of ProductInfo class. After that, it will be converted to byte[] in method ProductDAOImpl.save() -->
	               <td></td>
				</tr>
				<tr>
					<td>Mô tả</td>
					<td> <form:textarea path="description" value="${productForm.description}"/></td>  <!-- summary description or long description about the product -->
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
               		<td>
	               		<input type="submit" 
	               			value="<c:if test='${empty productForm.code}'>Thêm sản phẩm</c:if>
	               		 <c:if test='${not empty productForm.code}'>Cập nhật thông tin</c:if>" /> 
	               		<input type="reset" value="Reset" />
               		</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
</div>
</div>
</body>
</html>