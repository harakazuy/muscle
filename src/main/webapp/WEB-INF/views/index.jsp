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
<!-- ヘッダー作る -->
<!-- ナビバー作る -->
	<h1>トレーニング記録</h1>
	<input type="button" onclick="location.href='<%= request.getContextPath() %>/toRecord'" value="履歴を見る">
	<br>
	<br>
	<form action="<%= request.getContextPath() %>/insert" method="post">
		<div>日時</div>
		<input type="date" name="date">
		<div>メニュー</div>
		<select name="trainingId">
			<c:forEach var="training" items="${trainingList}" varStatus="status">
				<option value="${training.id}">
					<c:out value="${training.trainingName}" />
				</option>
			</c:forEach>
		</select>
		<div>ウェイト</div>
		<input type="text" name="weight">
		<div>回数</div>
		<input type="text" name="repetition">
		<div>セット数</div>
		<input type="text" name="setCount">
		<br>
		<input type="submit" value="記録する">
	</form>
<!-- フッター作る -->
</body>
</html>