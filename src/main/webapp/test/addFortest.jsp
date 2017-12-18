<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/uploadify/uploadify.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#attach").uploadify({
			swf:$("#ctx").val()+"/resources/uploadify/uploadify.swf",
			uploader:$("#ctx").val()+"/test/upload",
			fileObjName:"attach",
		});
		$("#uploadFile").click(function() {
			$("#attach").uploadify('upload','*');
	
		})
	});
</script>
</head>
<body>	
<input type="hidden" id="ctx" value="<%=request.getContextPath() %>"/>
	<input type="file" id="attach" name="attach"/>
	<input type="button" id="uploadFile" value="上传文件"/>
</body>
</html>