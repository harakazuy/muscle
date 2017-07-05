<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<h1>トレーニング記録</h1>
	<input type="button" onclick="location.href='<%= request.getContextPath() %>/toRecord'" value="履歴を見る">
	<br>
	<br>
	<form action="<%= request.getContextPath() %>/toRecord" method="post">
		<div>日時</div>
		<input type="datetime-local">
		<div>メニュー</div>
		<select name="training_menu">
			<option value="abdominal">腹筋</option>
			<option value="spine">背筋</option>
			<option value="push_ups">腕立て伏せ</option>
		</select>
		<div>ウェイト</div>
		<input type="text">
		<div>回数</div>
		<input type="text">
		<div>セット数</div>
		<input type="text">
		<br>
		<input type="submit" value="記録する">
	</form>
<%-- 	<c:forEach var="team" items="${teamList}" varStatus="status">
		<a href="<%= request.getContextPath() %>/toDetail?id=${team.id}">
			<c:out value="${team.teamName}" /><br>
		</a>
	</c:forEach> --%>
</body>
</html>