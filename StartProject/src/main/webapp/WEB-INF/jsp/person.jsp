<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<c:forEach items="${persons}" var="person">
			<tr>
				<td>${person.phoneNumber}</td>
				<td>${person.firstName}</td>
				<td>${person.lastName}</td>
				<td>${person.eMail}</td>
			</tr>
		</c:forEach>
	</table>
	<sf:form method="POST" modelAttribute="person" action="person.html">
		<fieldset>
			<table>
				<tr>
					<th><label for="phone_number">Phone number:</label></th>
					<td><sf:input path="phoneNumber" id="phone_number" /></td>
					<br />
					<sf:errors path="phoneNumber" />
				</tr>
				<tr>
					<th><label for="first_name">First name:</label></th>
					<td><sf:input path="firstName" id="first_name" /></td>
					<br />
					<sf:errors path="firstName" />
				</tr>
				<tr>
					<th><label for="last_name">Last name:</label></th>
					<td><sf:input path="lastName" id="last_name" /></td>
					<br />
					<sf:errors path="lastName" />
				</tr>
				<tr>
					<th><label for="e_mail">E-mail:</label></th>
					<td><sf:input path="eMail" id="e_mail" /></td>
					<br />
					<sf:errors path="eMail" />
				</tr>
				<tr>
					<th></th>
					<td><input name="commit" type="submit"
						value="Ok" /></td>
				</tr>
			</table>
		</fieldset>
	</sf:form>
</body>
</html>