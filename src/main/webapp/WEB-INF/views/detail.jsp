<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
球団名<c:out value="${team.teamName}"/>
<br>
本拠地<c:out value="${team.headquarters}" />
<br>
発足<c:out value="${team.inauguration}" />
<br>
歴史<pre><c:out value="${team.history}" /></pre>

 <button type="button" onclick="history.back()">戻る</button>
</body>
</html>