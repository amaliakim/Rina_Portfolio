<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	function check() {
		if (document.frm.id.value == "") {
			alert("아이디를 입력하세용");
			document.frm.id.focus();
			return false;
		} else if (document.frm.pw.value == "") {
			alert("비밀번호를 입력하세용");
			document.frm.pw.focus();
			return false;
		} else
			return true;
	}

</script>
</head>
<body>
	<center>
		<c:choose>
			<c:when test="${id == null}">
			<h3>안녕하세요 ^.^ 로그인해주세요~</h3>
				<form action="login.do" name="frm">
					<input type="text" name="id"> 
					<input type="password" name="pw"> 
					<input type="submit" value="로그인" onclick="return check()">
				</form>
				<br>
				<input type="button" value="회원가입"
					onclick="location.href='joinForm.do'">

				<input type="button" value="게시판으로" onclick="location.href='list.do'">
			</c:when>
			<c:otherwise>
				<h3>${id}님 반가워요 ^.^</h3>
				<input type="button" value="게시판으로" onclick="location.href='list.do'">
				<input type="button" value="로그아웃"
					onclick="location.href='logout.do'">

			</c:otherwise>
		</c:choose>
	</center>
</body>
</html>