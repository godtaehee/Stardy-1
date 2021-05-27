<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/study/board/write" method="post" enctype="multipart/form-data">
	<input type="text" name="title">
	<input type="hidden" name="study_id" value="74">
	<textarea rows="20" cols="20" name="content" style="resize:none;"></textarea>
	<input type="file" name="uploadFile" multiple="multiple">
	<button>Submit</button>
</form>

</body>
</html>