<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>
</head>

<body style="color: red" bgcolor="black">
<a href = "<%=request.getContextPath()%>/user/out">退出登录</a>
	<center>
	<h1>欢迎${user.userName}登录 BUG酒店</h1>
	<h3>不是写BUG，就是在写BUG的路上</h3>
	</center>
	<div id="datetime" align="right" style="color:red">
	</div>
	<canvas id="canvas"></canvas>
	
	 
</body>
 <script src="<%=request.getContextPath()%>/res/time/digit.js"></script>
<script src="<%=request.getContextPath()%>/res/time/time.js"></script>
</html>