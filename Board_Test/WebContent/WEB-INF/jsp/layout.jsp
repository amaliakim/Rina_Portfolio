<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tiles Board</title>
</head>
<body>

	<table width="100%">

		<tr height="20%">
			<td><img src="img/flower_img2.jpg"></td>
		</tr>

		<tr height="80%">
			<td><tiles:insertAttribute name="body"></tiles:insertAttribute></td>
		</tr>
		
	</table>

</body>
</html>