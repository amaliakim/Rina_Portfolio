<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PassCheck</title>
</head>
<body>
	<script type="text/javascript">
		if (window.name == 'modify') {
			window.opener.parent.location.href = "modifyForm.do?num=${param.num}&pass=${param.pass}";
		} else if (window.name == 'delete') {
			window.opener.parent.location.href = "delete.do?num=${param.num}&pass=${param.pass}";
		}
		window.close();
	</script>
</body>
</html>