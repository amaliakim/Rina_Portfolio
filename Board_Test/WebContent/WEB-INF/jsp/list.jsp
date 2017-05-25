<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List</title>

<style type="text/css">
a {
	color: black;
}

.a_Tg {
	color: white;
}

.a_Tl {
	color: black;
}

a:link {
	text-decoration: none;
}

a:hover {
	color: fuchsia;
}

th {
	font-size: x-large;
}
</style>

</head>
<body>

	<center>
		<input type="button" onclick="location.href='main.do'" value="메인으로">
		&nbsp;
		<c:choose>
			<c:when test="${id==null }">
				<input type="button" value="로그인 후 글쓰기 가능"
					onclick="location.href='main.do'">
			</c:when>
			<c:otherwise>
				<input type="button" value="글쓰기"
					onclick="location.href='writeForm.do?email=${id}&name=${member.name }'">&nbsp;
				<input type="button" value="로그아웃"
					onclick="location.href='logout.do'">
		<h4>${id }님 방가방가</h4>
			</c:otherwise>
		</c:choose>

		<table align="center">
			<tr>
				<td width="1200px" colspan="5" bgcolor="pink"></td>
			</tr>

			<tr align="center">
				<th width="100px">글 번호</th>
				<th width="700px">제목</th>
				<th width="200px">작성일</th>
				<th width="100px">조회수</th>
				<th width="150px">작성자</th>
			</tr>

			<tr>
				<td width="1200px" colspan="5" bgcolor="pink"></td>
			</tr>

			<c:forEach var="boardList" items="${boardList }">
				<tr align="center">
					<td>${boardList.num }</td>
					<td><a class="a_Tl" href="view.do?num=${boardList.num }">${boardList.title }</a>
					</td>
					<td><fmt:formatDate value="${boardList.writedate }"
							pattern="yyyy-MM-dd" /></td>
					<td>${boardList.readcount }</td>
					<td>${boardList.name }</td>
				</tr>
			</c:forEach>

			<tr align="center">
				<td width="1200px" colspan="5" bgcolor="pink"><c:if
						test="${start!=1 }">
						<a class="a_Tg" href="list.do?page=1">[처음]</a>
						<a class="a_Tg" href="list.do?page=${start-1 }">[이전]</a>
					</c:if> <c:forEach var="i" begin="${start}" end="${end }" step="1">
						<c:choose>
							<c:when test="${i == current }">
									[${i }]
								</c:when>
							<c:otherwise>
								<a class="a_Tg" href="list.do?page=${i }">[${i }]</a>
							</c:otherwise>
						</c:choose>
					</c:forEach> <c:if test="${end != last }">
						<a class="a_Tg" href="list.do?page=${end+1 }">[다음]</a>
						<a class="a_Tg" href="list.do?page=${last }">[끝]</a>
					</c:if></td>
			</tr>
		</table>

		<br> <br>
		<%-- 			시작: ${start } 끝: ${end } 처음: ${first } 마지막:  ${last } 현재: ${current }								 --%>

	</center>

</body>
</html>