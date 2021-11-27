<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<!--永远固定相对路径的跳转结果-->
	<%@include file="/pages/common/head.jsp"%>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
				<span class="wel_word"></span>
<%--静态包含manger模块的菜单--%>
			<%@include file="/pages/common/login_success_menu.jsp"%>

		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="index.html">转到主页</a></h1>
	
		</div>
		
		<div id="bottom">
			<%--		页尾展示--%>
			<%@include file="/pages/common/footer.jsp"%>

		</div>
</body>
</html>