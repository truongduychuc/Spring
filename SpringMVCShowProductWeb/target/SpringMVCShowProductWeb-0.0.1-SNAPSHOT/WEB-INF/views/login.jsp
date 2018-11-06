<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập quản trị</title>
</head>
<body>
	<div class="login-pagetitle"><h2>Đăng nhập</h2></div>
	<div class="loginForm-container">
		<h3>Nhập tài khoản và mật khẩu</h3>
		<br/>
		 <!-- /login?error=true -->
		 <c:if test="${param.error =='true' }">
		 	<div style="color:red;margin:10px 0px;">
		 		Đăng nhập thất bại !!! Reason:
		 		${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }
		 	</div>
		 </c:if>
		 <form method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
		 	<table>
		 		<tr>
		 			<td>Tài khoản *</td>
		 			<td><input name="userName"/></td>
		 		</tr>
		 		<tr>
		 			<td>Mật khẩu *</td>
		 			<td><input name="password"/></td>
		 		</tr>
		 		<tr>
		 			<td>&nbsp;</td>
		 			<td>
		 			<input type="submit" value="Đăng nhập"/>
					<input type="reset" value="Reset">	
		 			</td>
		 		</tr>
		 	</table>
		 </form>
		 <span class="error-message">${error}</span>
	</div>
</body>
</html>