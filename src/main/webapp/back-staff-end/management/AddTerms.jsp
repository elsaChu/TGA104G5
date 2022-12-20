<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Terms</title>
</head>
<body>
<h1>Create New Terms of Service</h1>
<form action="<%=request.getContextPath()%>/TermsServlet" method="post">
	<input type="hidden" name="doTask" value="insert">
	<label>Terms Title </label>
	<input type="text" name="termsTitle" >
	<br>
	<label>Terms Content</label>
	<br>
	<textarea id="" name="termsContent" rows="4" cols="50"></textarea>
	<br>
	<input type="submit" value="SUBMIT">
</form>

</body>
</html>