<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List,com.neuralnoise.map.model.map.Artisan" %>
<html>
<head>
<script language="javascript">
function update(id){
    var f=document.form;
    f.method="post";
    f.action='entities.jsp?id=' + id;
    f.submit();
}
function remove(id){
    var f=document.form;
    f.method="post";
    f.action='entities.jsp?id=' + id;
    f.submit();
}
</script>
</head>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
	
	<form method="get">
		<table border="1">
			<tr>
				<th>id</th>
				<th>nome</th>
				<th>descrizione</th>
				<th>indirizzo</th>
				<th colspan="2" align="center">azione</th>
			</tr>
		<%
			List<Artisan> artisans = (List<Artisan>) request.getAttribute("artisans");
			for (Artisan artisan : artisans) {
				final String description = (artisan.getDescription() != null ? artisan.getDescription() : "");
				final String address = (artisan.getLocation() != null ? artisan.getLocation().getAddress() : "");
		%>
			<tr>
				<td><%= artisan.getId() %></td>
				<td><input type="text" value="<%= artisan.getName() %>"></td>
				<td><input type="text" value="<%= description %>"></td>
				<td><input type="text" value="<%= address %>"></td>
				<td><input type="button" name="UPDATE" value="UPDATE"></td>
				<td><input type="button" name="REMOVE" value="REMOVE"></td>
			</tr>
		<%
			}
		%>
		</table>
	</form>

	<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
		
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
		</c:if>


	</sec:authorize>
</body>
</html>