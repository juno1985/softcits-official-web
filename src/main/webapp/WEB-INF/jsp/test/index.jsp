<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/resources/css/zTree/zTreeStyle.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/tree/jquery.ztree.core-3.5.min.js"></script>
</head>

<script type="text/javascript">
	var datas = [ {
		id : 1,
		pid : 0,
		name : "父节点1"
	}, {
		id : 11,
		pid : 1,
		name : "子节点1"
	}, {
		id : 12,
		pid : 1,
		name : "子节点2"
	},{
		id : 2,
		pid: 0,
		name : "父节点2"
	} ,{
		id : 21,
		pid: 2,
		name : "子节点2"
	} ,
	{
		id : 22,
		pid: 2,
		name : "子节点2"
	} 
	];
	$(function() {

		var setting = {
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pid",
					rootPId : 0,
				}
			},
			view : {
				dblClickExpand : false,
				selectedMulti : false
			}
		};
		$.fn.zTree.init($("#treeTest"), setting, datas);

	});
</script>
<body>
	<div id="treeTest" class="ztree"></div>
</body>
</html>