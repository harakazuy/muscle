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
	<table border=1>
		<tr><th colspan=5>
			2017/07/05(水)
			<input type="button" onclick="" value="レコード削除">
		</th></tr>
		<tr><td>メニュー</td><td>ウェイト</td><td>回数</td><td>セット数</td><td></td></tr>
		<tr>
			<td><select name="training_menu">
				<option value="abdominal" selected>腹筋</option>
				<option value="spine">背筋</option>
				<option value="push_ups">腕立て伏せ</option>
			</select></td>
			<td><input type="text" value="20">kg</td><td><input type="text" value="10"></td>
			<td><input type="text" value="5"></td><td><input type="button" onclick="" value="削除"></td>
		</tr>
		<tr>
			<td><select name="training_menu">
				<option value="abdominal">腹筋</option>
				<option value="spine" selected>背筋</option>
				<option value="push_ups">腕立て伏せ</option>
			</select></td>
			<td><input type="text" value="35">kg</td><td><input type="text" value="12"></td>
			<td><input type="text" value="4"></td><td><input type="button" onclick="" value="削除"></td>
		</tr>
		<tr>
			<td><select name="training_menu">
				<option value="abdominal">腹筋</option>
				<option value="spine">背筋</option>
				<option value="push_ups" selected>腕立て伏せ</option>
			</select></td>
			<td><input type="text" value="自重"></td><td><input type="text" value="15"></td>
			<td><input type="text" value="6"></td><td><input type="button" onclick="" value="削除"></td>
		</tr>
	</table>
	<br>
	<input type="button" onclick="location.href='/toRecord'" value="更新">
	<input type="button" onclick="location.href='/toRecord'" value="キャンセル">
</body>
</html>