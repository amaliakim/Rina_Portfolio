<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modify</title>

<style type="text/css">

th{
	background-color: pink;
}

</style>

</head>
<body>

	<center>
		<form action="modify.do">
			<table>
				<tr>
					<th colspan="2">제목</th>
					<td colspan="4">
						<textarea rows="1" cols="65" style="resize: none" name="title">${board.title }</textarea>
					</td>
				</tr>
	
				<tr>
					<th>작성자</th>
					<td>${board.name }</td>
					<th>아이디</th>
					<td><input type="text" name="email" value="${board.email }"></td>
					<th>비밀번호</th>
					<td><input type="password" name="pass" value="${board.pass }"></td>
				</tr>
				
				<tr>
					<th colspan="2">첨부파일</th>
					<td colspan="4">${fileuri }</td>
				</tr>
				
				<tr><th colspan="6">내 용</th></tr>
				
				<tr>
					<td colspan="6">
						<textarea rows="20" cols="100" style="resize: none" name="content">${board.content }</textarea>
					</td>
				</tr>
				
				<tr align="right">
					<td colspan="6">
						<input type="hidden" value="${board.num }" name="num">
						<input type="submit" value="수정" />
						<input type="button" value="뒤로" onclick="history.back()"/>
					</td>
				</tr>
				
			</table>
		</form>
	</center>
	
</body>
</html>