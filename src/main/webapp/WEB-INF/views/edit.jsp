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
	<h1>トレーニング履歴編集</h1>
	<form action="<%= request.getContextPath() %>/update" method="post">
		<table border=1>
			<tr><th colspan=5>
				<c:out value="${date}" />
				<input type="hidden" name="date" value=${date}>
				<input type="button" onclick="location.href='<%= request.getContextPath() %>/deleteByDate?date=${date}'" value="一括削除">
			</th></tr>
			<tr><td>メニュー</td><td>ウェイト</td><td>回数</td><td>セット数</td><td></td></tr>
			<c:forEach var="trainingRecord" items="${trainingRecords}">
				<input type="hidden" name="id" value="${trainingRecord.id}">
				<tr>
					<td>
						<select name="trainingId">
							<c:forEach var="training" items="${trainingList}">
								<option value="${training.id}" <c:if test="${trainingRecord.trainingId == training.id}">selected</c:if> >
									<c:out value="${training.trainingName}" />
								</option>
							</c:forEach>
						</select>
					</td>
					<td><input type="text" name="weight" value="${trainingRecord.weight}" />kg</td>
					<td><input type="text" name="repetition" value="${trainingRecord.repetition}" /></td>
					<td><input type="text" name="setCount" value="${trainingRecord.setCount}" /></td>
					<td><input type="button" onclick="location.href='<%= request.getContextPath() %>/delete?id=${trainingRecord.id}'" value="削除"></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="更新">
		<input type="button" onclick="location.href='/toRecord'" value="キャンセル">
	</form>
</body>
</html>