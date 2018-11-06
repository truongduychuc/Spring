<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
	<c:if test="${not empty productForm.code}">
		Chỉnh sửa sản phẩm
	</c:if>
	<c:if test="${empty productForm.code}">
		Thêm sản phẩm
	</c:if>
</title>
</head>
<body>
	<div class="add_edit_product-titlepage">
		<h2>
			<c:if test="${not empty productForm.code}">
				Chỉnh sửa sản phẩm
			</c:if>
			<c:if test="${empty productForm.code}">
				Thêm sản phẩm
			</c:if>
		</h2>
	</div>
	<c:if test="${not empty errorMessage}">
		<div class="error-message">
			${errorMessage}
		</div>
	</c:if>
	<div class="product-container">
		<form:form modelAttribute="productForm" method="POST" enctype="multipart/form-data">
			<table>
				<tr>
					<td>Mã sản phẩm*</td>
					<td>
						<c:if test="${not empty productForm.code}">
							<form:hidden path="code"/>
							${productForm.code}
						</c:if>
						<c:if test="${empty productForm.code}">
							<form:input path="code"/>
							<form:hidden path="newProduct"/>
						</c:if>
					</td>
					<td><form:errors path="code" class="error-message" /></td>
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
	               <td><form:input type="file" path="fileData"/></td>
	               <td></td>
				</tr>
				<tr>
					<td>Mô tả</td>
					<td> <form:textarea path="description" value="${productForm.description}"/></td>
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
</body>
</html>