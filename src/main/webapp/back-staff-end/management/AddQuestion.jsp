<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Common QA</title>
</head>
<body>
<h1>Create New question </h1>
<form action="<%=request.getContextPath()%>/QuestionServlet" method="post">
	<input type="hidden" name="doTask" value="insert">
	<label>Question Title </label>
	<input type="text" name="commonName" >
	<br>
	<label>Question Content</label>
	<br>
	<textarea id="" name="commonContent" rows="4" cols="50"></textarea>
	<br>
	<label>Priority?</label>
	<input type="number" name="sort" >
	<br>
	<input type="submit" value="SUBMIT">
</form>

</body>
</html>