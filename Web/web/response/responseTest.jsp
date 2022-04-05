<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward, sendRedirect 연습</title>
</head>
<body>
<h3>forward방식과 redirect방식 연습하기</h3>
<hr>

<form action="/testWeb/responseTest01.do">
	forward 방식 이동 : <input type="text" name="userName">
	<input type="submit" value="확 인">
</form>
<br><hr><br>
<form action="/testWeb/responseTest02.do">
	redirect 방식 이동 : <input type="text" name="userName">
	<input type="submit" value="확 인">
</form>
</body>
</html>