<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <input type="hidden" id="ctx" value="<%=request.getContextPath()%>"/>
<div id="header">
	<div id="header_con">
		<div id="logo"></div>
		<div id="main_nav">
			<ul>
				<li><a class="main_nav_link" href="http://www.ztu.edu.cn">沙龙院首页</a></li>
				<li><a class="main_nav_link" href="<%=request.getContextPath()%>/channel/5">勤耕园</a></li>
				<li><a class="main_nav_link" href="<%=request.getContextPath()%>/admin">后台管理</a></li>
				<li><a class="main_nav_link" href="mailto:juno@softcits.com">联系我们</a></li>
			</ul>
		</div>
		<div id="search">
			<input type="text" id="search_con" value="Search.." />
			<div id="search_btn"></div>
		</div>
	</div>
</div>
<div id="nav">
	<div id="nav_con">
		<ul>
			<li><span href="<%=request.getContextPath()%>/index">沙龙首页</span></li>
				<li>
					<span href="<%=request.getContextPath()%>/channel/22">沙龙简介</span>
				</li>
				<li>
					<span href="<%=request.getContextPath()%>/channel/39">沙龙是梦1</span>
				</li>
				<li>
					<span href="<%=request.getContextPath()%>/channel/24">活动回顾</span>
				</li>
				<li>
					<span href="<%=request.getContextPath()%>/channel/25">心灵引导</span>
				</li>
				<li>
					<span href="<%=request.getContextPath()%>/channel/26">企业分享</span>
				</li>
				<li>
					<span href="<%=request.getContextPath()%>/channel/27">技术学习</span>
				</li>
				<li>
					<span href="<%=request.getContextPath()%>/channel/28">团队建设</span>
				</li>
				<li>
					<span href="<%=request.getContextPath()%>/channel/29">沙龙使命</span>
				</li>
				<li>
					<span href="<%=request.getContextPath()%>/channel/23">微网站</span>
				</li>
		</ul>
	</div>
</div>