<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>record</title>
</head>
<body>
	<h1>トレーニング履歴</h1>
	<c:forEach var="trainingRecordsDate" items="${trainingRecordsDateList}" varStatus="status">
		<table border=1>
			<tr><th colspan=4>
				<c:out value="${trainingRecordsDate.trainingDate}" />
				<input type="button" onclick="location.href='<%= request.getContextPath() %>/toEdit'" value="編集">
			</th></tr>
			<tr><td>メニュー</td><td>ウェイト</td><td>回数</td><td>セット数</td></tr>
			<c:forEach var="trainingRecord" items="${trainingRecordsDate.trainingRecords}">
				<tr>
					<td><c:out value="${trainingRecord.trainingName}" /></td>
					<td><c:out value="${trainingRecord.weight}" /></td>
					<td><c:out value="${trainingRecord.repetition}" /></td>
					<td><c:out value="${trainingRecord.setCount}" /></td>
				</tr>
			</c:forEach>
		</table>
		<br>
	</c:forEach>
	<input type="button" onclick="location.href='/'" value="戻る">
</body>
</html>