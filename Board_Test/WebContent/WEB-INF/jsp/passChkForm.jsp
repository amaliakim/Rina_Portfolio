<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>delete</title>

</head>
<body>
	<center>
		<form action="passSucc.do">
				비밀번호 확인: <input type="password" name="pass" />
				<input type="hidden" name="num" value="${param.num }">
				<input type="submit" value="확인">
		</form>
	</center>
</body>
</html>