<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/jcrop/js/jquery.Jcrop.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/jcrop/css/jquery.Jcrop.css"/>
<script type="text/javascript">
	$(function(){
		$("#select img").Jcrop(
		{
			onChange: showPreview,
			onSelect: showPreview,
			aspectRatio: 49/12
		}		
		);
	});
	function showPreview(coords)
	{
		if (parseInt(coords.w) > 0)
		{
			var rx = 490 / coords.w;
			var ry = 120 / coords.h;

			jQuery('#preview').css({
				width: Math.round(rx * 555) + 'px',
				height: Math.round(ry * 278) + 'px',
				marginLeft: '-' + Math.round(rx * coords.x) + 'px',
				marginTop: '-' + Math.round(ry * coords.y) + 'px'
			});
		}
	}

</script>
</head>
<body>
		<div style="width:490px;height:120px;overflow:hidden;">
			<img src="<%=request.getContextPath() %>/resources/img/building.jpg" id="preview" />
		</div>
	<div id="select">
		<img src="<%=request.getContextPath() %>/resources/img/building.jpg">
	</div>	
</body>
</html>