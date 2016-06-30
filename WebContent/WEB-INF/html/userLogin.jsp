<%@ page language="java"
		 pageEncoding="UTF-8"
%>

<!DOCTYPE html PUBLIC "-//W3C/DTD HTML 401 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<jsp:directive.include file="header.jsp" />
	<body>
		<jsp:directive.include file="navBar.jsp" />
		<br /><br /><br /><br /><br />
		<br /><br />
		<div class="row">
			<div class="col-xs-4 col-xs-offset-4">
				<%
					if(request.getAttribute("success") != null) {
				%>
					<div class="alert alert-success" role="alert">
						<strong>User found</strong>
					</div>
				<%		
					} else {
						if(request.getAttribute("errorMessage") != null) {
				%>
					<div class="alert alert-danger" role="alert">
						${errorMessage}
					</div>
				<%		
						}
					}
				%>
				<h2>Login form</h2>
				<form action="login" method="get">
				  <div class="form-group">
				    <label for="login">Login</label>
				    <input type="text" class="form-control" name="user" id="login" placeholder="Login">
				  </div>
				  <div class="form-group">
				    <label for="password">Password</label>
				    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
				  </div>
				  <button type="submit" class="btn btn-default pull-right">Submit</button>
				</form>
			</div>
		</div>
	</body>
</html>
		 