<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập quản trị</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-theme.min.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/myCss/style.css'/>">
	<script type="text/javascript" src='<c:url value='/bootstrap/js/bootstrap.min.js'/>'></script>
</head>
<body>	
	<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-12 text-center">
								<h2>Đăng nhập</h2>
							</div>
				
						</div>
						<!-- /login?error=true -->
						 <c:if test="${param.error =='true' }">
						 	<div class="row" style="color:red;margin:10px 0px;">
						 		Đăng nhập thất bại !!! Reason:
						 		${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }
						 	</div>
						 </c:if>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="${pageContext.request.contextPath}/j_spring_security_check" method="post" style="display: block;">
									<div class="form-group">
										<input type="text" name="userName" id="username" tabindex="1" class="form-control" placeholder="Username" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6">
												<input type="submit" tabindex="4" class="form-control btn btn-info" value="Đăng nhập">
												
											</div>
											<div class="col-sm-6">
												<input type="reset" tabindex="4" class="form-control btn btn-danger" value="Reset"/>	
											</div>
										</div>
									</div>
								</form>
								<span class="error-message">${error}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>