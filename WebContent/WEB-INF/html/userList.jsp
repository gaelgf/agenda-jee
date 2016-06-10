<%@ page language="java"
		 pageEncoding="UTF-8"
%>

<jsp:directive.page import="fr.esgi.model.User"/>
<jsp:directive.page import="java.util.List"/>

<!DOCTYPE html PUBLIC "-//W3C/DTD HTML 401 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<jsp:directive.include file="header.jsp" />
	<body>
		<jsp:directive.include file="navBar.jsp" />
		<br /><br /><br /><br /><br /><br /><br />
		<div class="row">
			<div class="col-xs-4 col-xs-offset-4">
				<h2>List of users</h2>
				<ul class="list-group">
					<%
						List<User> allUsers = (List<User>)request.getAttribute("userList");
						for(User u : allUsers) {
							out.write("<li class=\"list-group-item\">" + u.getLogin() + "</li>");
						}
					%>
				</ul>
			</div>
		</div>
	</body>
</html>
		 