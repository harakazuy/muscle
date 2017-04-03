<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>

<c:forEach var="team" items="${teamList}" varStatus="status">
	<a href = "<%= request.getContextPath() %>/toDetail?id=${team.id}">
		<c:out value="${team.teamName}"/><br>
	</a>
</c:forEach>


</body>
</html>