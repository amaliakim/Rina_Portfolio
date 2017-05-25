<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view</title>

<style type="text/css">
a {
	color: black;
}

a:link {
	text-decoration: none;
}

a:hover {
	color: fuchsia;
}
</style>

<script type="text/javascript">

	function loadChk(url, name) {
		window.open(url, name, "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=450, height=200");
	}

	/**
	 * 
	 */
	function commentsInput(cm_idx, cm_order, cm_depth) {
		hideDiv("updateComments");
		var form = document.reForm;
		var div = document.getElementById("commentsDiv" + cm_idx);
		var div2 = document.getElementById("reComments");
		div2.style.display = "";
// 		alert(cm_idx + "," + cm_depth);

		form.cm_content.value = "";
		form.cm_order.value = cm_order;
		form.cm_parent.value = cm_idx;
		form.cm_depth.value = cm_depth;
		div.appendChild(div2);
	}

	//		창사라지기
	function hideDiv(id) {
		var div = document.getElementById(id);
		div.style.display = "none";
		document.body.appendChild(div);
	}
	function commentsCancel() {
		hideDiv("reComments");
	}

	//		댓글 수정
	function commentsUpdate(cm_idx) {
		hideDiv("reComments");

		var form = document.updateForm;
		var div = document.getElementById("commentsDiv" + cm_idx);
		var div2 = document.getElementById("updateComments");
		div2.style.display = "";

		form.cm_idx.value = cm_idx;
		form.cm_content.value = "";
		div.appendChild(div2);
	}
	function commentsUpdateCancel() {
		hideDiv("updateComments");
	}
</script>
</head>
<body>

	<center>
		<form name="frm" action="list.do">
			<table align="center">

				<tr>
					<td width="100%" colspan="7" bgcolor="pink"></td>
				</tr>

				<tr align="center">
					<th width="100px">글 번호</th>
					<th width="700px">제목</th>
					<th width="200px">작성일</th>
					<th width="100px">조회수</th>
					<th width="150px">작성자</th>
					<th width="100px">이메일</th>
					<th width="100px">파일</th>
				</tr>

				<tr>
					<td width="100%" colspan="7" bgcolor="pink"></td>
				</tr>

				<tr align="center" bgcolor="white">
					<td>${board.num }</td>
					<td>${board.title}</td>
					<td><fmt:formatDate value="${board.writedate }"
							pattern="yyyy-MM-dd" /></td>
					<td>${board.readcount }</td>
					<td>${board.name }</td>
					<td>${board.email }</td>
					<td><a href="download.do?f_id=${boardFile.f_id }">
							${boardFile.originFileName }</a> (${boardFile.size / 1000}KB)</td>
				</tr>

				<tr>
					<td style="color: white;" bgcolor="pink" align="center" colspan="7">내용</td>
				</tr>

				<tr>
					<td><img src="fileView.do?f_id=${boardFile.f_id }"
						style="width: 500px"></td>
					<td align="center" colspan="5" height="500">${board.content }</td>
				</tr>

				<tr>
					<td width="100%" colspan="7" bgcolor="pink"></td>
				</tr>

				<tr align="right">
					<td colspan="7"><input type="button" value="수정"
						onclick="loadChk('passChkForm.do?num=${board.num}','modify')">
						<input type="submit" value="목록" /> <input type="button"
						value="삭제"
						onclick="loadChk('passChkForm.do?num=${board.num}','delete')">
					</td>
				</tr>

			</table>
		</form>



		<!-- 		Comments Form -->
		<div class="well">
			<form role="form" action="commentsWrite.do" method="post">
				<div class="form-group">
					<input type="hidden" name="b_idx" value="${board.num }"> <input
						type="hidden" name="cm_writer" value="${id }"> <input
						type="hidden" name="cm_parent" value="0">
					<c:choose>
						<c:when test="${id == null }">
							<textarea style="width: 1000px; resize: none;" name="cm_content"
								rows="3" readonly></textarea>
						</c:when>
						<c:otherwise>
							<textarea style="width: 1000px; resize: none;" name="cm_content"
								rows="3"></textarea>
							<input type="submit" value="등록">
						</c:otherwise>
					</c:choose>
				</div>
			</form>
		</div>

		<!--         reComments input, nondisplay -->
		<div class="well" style="display: none;" id="reComments">
			<form role="form" action="commentsWrite.do" method="post"
				name="reForm">
				<div class="form-group">
					<input type="hidden" name="b_idx" value="${board.num }"> <input
						type="hidden" name="cm_writer" value="${id }"> <input
						type="hidden" name="cm_parent">
					<!-- 					<input type="hidden" name="parent_cm">  -->
					<input type="hidden" name="cm_depth"> <input type="hidden"
						name="cm_order">
					<textarea style="width: 1000px; resize: none;" name="cm_content"
						rows="3"></textarea>
					<input type="submit" value="등록">
				</div>
			</form>
		</div>

		<!--          comments update form -->
		<div class="well" style="display: none;" id="updateComments">
			<form role="form" action="commentsUpdate.do" method="post"
				name="updateForm">
				<div class="form-group">
					<input type="hidden" name="b_idx" value="${board.num }"> <input
						type="hidden" name="cm_idx">
					<textarea style="width: 1000px; resize: none;" name="cm_content"
						rows="3"></textarea>
				</div>
				<input type="submit" value="수정"> <input type="button"
					value="취소" onclick="commentsUpdateCancel()">
			</form>
		</div>


		<!-- Comment List -->
		<c:choose>
			<c:when test="${comments == null }">
        		댓글이 없습니다.
        	</c:when>
			<c:otherwise>
				<c:forEach var="comments" items="${comments}" varStatus="status">
					<div style="margin-left : ${50*comments.cm_depth}px">
						<table>
							<tr>
								<td width="180px">
									<h4 class="media-heading">${comments.cm_writer }
										<input type="hidden" name="cm_idx" value="${comments.cm_idx}">
									</h4>
								</td>
								<td width="700px" id="commentsDiv${comments.cm_idx}">${comments.cm_content }</td>
								<td width="120px"><c:if
										test="${comments.cm_delete != 'Y' }">
										<c:if test="${comments.cm_writer == id }">
											<a
												onclick="location.href='commentsDelete.do?cm_idx=${comments.cm_idx}&b_idx=${comments.b_idx }&site=notice'">
												삭제</a>
											<a onclick="commentsUpdate(${comments.cm_idx})"> 수정</a>
										</c:if>
										<c:if test="${id != null }">
											<a
												onclick="commentsInput(${comments.cm_idx},${comments.cm_order },${comments.cm_depth })">
												댓글</a>
										</c:if>
									</c:if></td>
							</tr>
							<tr>
								<td td width="100%" colspan="3" bgcolor="pink"></td>
							</tr>
						</table>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

	</center>
</body>
</html>