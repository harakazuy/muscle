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
	<table border=1>
		<tr><th colspan=4>
			2017/07/05(水)
			<input type="button" onclick="location.href='<%= request.getContextPath() %>/toEdit'" value="編集">
		</th></tr>
		<tr><td>メニュー</td><td>ウェイト</td><td>回数</td><td>セット数</td></tr>
		<tr><td>腹筋</td><td>20kg</td><td>10</td><td>5</td></tr>
		<tr><td>背筋</td><td>35kg</td><td>12</td><td>4</td></tr>
		<tr><td>腕立て伏せ</td><td>自重</td><td>15</td><td>6</td></tr>
	</table>
	<br>
	<table border=1>
		<tr><th colspan=4>
			2017/07/04(火)
			<input type="button" onclick="location.href='<%= request.getContextPath() %>/toEdit'" value="編集">
		</th></tr>
		<tr><td>メニュー</td><td>ウェイト</td><td>回数</td><td>セット数</td></tr>
		<tr><td>腹筋</td><td>20kg</td><td>10</td><td>5</td></tr>
		<tr><td>背筋</td><td>35kg</td><td>12</td><td>4</td></tr>
		<tr><td>腕立て伏せ</td><td>自重</td><td>15</td><td>6</td></tr>
	</table>
	<br>
	<table border=1>
		<tr><th colspan=4>
			2017/07/03(月)
			<input type="button" onclick="location.href='<%= request.getContextPath() %>/toEdit'" value="編集">
		</th></tr>
		<tr><td>メニュー</td><td>ウェイト</td><td>回数</td><td>セット数</td></tr>
		<tr><td>腹筋</td><td>20kg</td><td>10</td><td>5</td></tr>
		<tr><td>背筋</td><td>35kg</td><td>12</td><td>4</td></tr>
		<tr><td>腕立て伏せ</td><td>自重</td><td>15</td><td>6</td></tr>
	</table>
	<br>
	<input type="button" onclick="location.href='/'" value="戻る">
</body>
</html>