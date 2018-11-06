<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thông tin tài khoản</title>
</head>
<body>
	<div class="account-titlepage"><h2>Thông tin tài khoản</h2></div>
	<div class="account-container">
		<ul>
			<li>Tên tài khoản: ${pageContext.request.userPrincipal.name}</li>
			<li>Vai trò:
				<ul>
					<c:forEach items="${userDetails.authorities}" var="auth">
						<li>${auth.authority}</li>
					</c:forEach>
				</ul>
			</li>
		</ul>
	</div>
</body>
</html>