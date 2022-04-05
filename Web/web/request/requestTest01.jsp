<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request객체 연습</title>
<style type="text/css">
tr, td{
	border : 1px solid red;
}
</style>
</head>
<body>
<h2>Request 연습 Form</h2><hr>
<form action="/testWeb/requestTest01.do">
<table border="1">
<tr>
	<td>이름</td>
	<td><input type="text" name="userName" size="10"></td>
</tr>
<tr>
	<td>직업</td>
	<td>
		<select name="job">
			<option value="무직">무직</option>
			<option value="회사원">회사원</option>
			<option value="전문직">전문직</option>
			<option value="학생">학생</option>
		</select>
	</td>
</tr>
<tr>
	<td>취미</td>
	<td>
		<input type="checkbox" name="hobby" value="여행">여행
		<input type="checkbox" name="hobby" value="게임">게임
		<input type="checkbox" name="hobby" value="독서">독서
		<input type="checkbox" name="hobby" value="테니스">테니스
		<input type="checkbox" name="hobby" value="배드민턴">배드민턴		
	</td>
</tr>
<tr>
	<td colspan="2" style="text-align:center;">
		<input type="submit" value="전송">
		<input type="reset" value="취소">
	</td>
</tr>

</table>

</form>

</body>
</html>