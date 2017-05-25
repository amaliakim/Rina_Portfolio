<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>joinForm</title>

<script src="https://code.jquery.com/jquery-2.2.4.min.js"
	integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
	crossorigin="anonymous"></script>

<script type="text/javascript">

	$(document).ready(function() {

		var id = document.getElementById('id');
		var pass = document.getElementById('pw');
		var pass_chk = document.getElementById('pw_chk');
		var name = document.getElementById('name');

		var span_id = document.getElementById('span_id');
		var span_pw = document.getElementById('span_pw');
		var span_pw_chk = document.getElementById('span_pw_chk');
		var span_name = document.getElementById('span_name');

		var pwdCondition1 = /(?=.*[a-z])/;
		var pwdCondition2 = /(?=.*[A-Z])/;
		var pwdCondition3 = /(?=.*[0-9])/;
		var pwdCondition4 = /(?=.*[!@#$%^&*()_])/;

		var emailCondition = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

		$(id).keyup(function() {

			var inputid = $(id).val();

			$.ajax({
				url : 'idCheck.do',
				type : 'GET',
				data : 'id=' + inputid,
				datatype : 'json',
				success : function(data) {
					// 					alert(data.result);
					if (!emailCondition.test(inputid)) {
						$(span_id).html('아이디는 이메일 형식입니다.');
						document.frm.id.focus();
					} else {
						if (data.result) {
							$(span_id).html('중복된 아이디입니다.');
							document.frm.id.focus();
						} else {
							$(span_id).html('사용 가능한 아이디입니다.');
						}
					}

				},
				error : function() {
					alert("에러다..에러.....");
				}
			});
		})



		$(pass).keyup(function() {
			if ($(this).val().length < 8) {
				$(span_pw).html('비밀번호는 8글자 이상이어야 합니다.');
				document.frm.pw.focus();
			} else if (!pwdCondition1.test($(this).val())) {
				$(span_pw).html('영소문자가 포함되어야 합니다.');
				document.frm.pw.focus();
			} else if (!pwdCondition2.test($(this).val())) {
				$(span_pw).html('영대문자가 포함되어야 합니다.');
				document.frm.pw.focus();
			} else if (!pwdCondition3.test($(this).val())) {
				$(span_pw).html('숫자가 포함되어야 합니다.');
				document.frm.pw.focus();
			} else if (!pwdCondition4.test($(this).val())) {
				$(span_pw).html('특수문자가 포함되어야 합니다.');
				document.frm.pw.focus();
			} else {
				$(span_pw).html('적합한 비밀번호입니다.');
			}

		})

		$(pass_chk).keyup(function() {
			if ($(pass).val() == $(this).val()) {
				$(span_pw_chk).html('비밀번호가 일치합니다.');
				document.frm.pw_chk.focus();
			} else {
				$(span_pw_chk).html('비밀번호가 일치하지 않습니다.');
				document.frm.pw_chk.focus();
			}
		})

	});
</script>

</head>
<body>

	<center>

		<form action="joinOk.do" name="frm">

			<div>

				<input type="text" class="form-control" id="id" name="id"
					placeholder="ID를 입력하세요"> <br> <span class="span"
					id="span_id">^0^</span>
			</div>
			<br>
			<div>
				<input type="password" class="form-control" id="pw" name="pw"
					placeholder="비밀번호를 입력하세요"> <br> <span class="span"
					id="span_pw">^0^</span>
			</div>
			<br>
			<div>
				<input type="password" class="form-control" id="pw_chk"
					placeholder="위와 같은 비밀번호를 입력하세요"> <br> <span
					class="span" id="span_pw_chk">^0^</span>
			</div>
			<br>
			<div>
				<input type="text" class="form-control" id="name" name="name"
					placeholder="이름을 입력하세요"> <br> <span class="span"
					id="span_name">^0^</span>
			</div>
			<br> <br>

			<div>
					<button type="submit" id="joinOK">확인</button>
					<button type="reset" onclick="location.href='main.do'">취소</button>
			</div>

		</form>

	</center>

</body>
</html>