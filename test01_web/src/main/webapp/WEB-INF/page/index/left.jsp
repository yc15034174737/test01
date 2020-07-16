<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body >
	<a href = "<%=request.getContextPath()%>/product/toShow?token=${token}" target = "right">商品展示</a><p>
	<a href = "<%=request.getContextPath()%>/user/toShow?token=${token}" target = "right">用户展示</a><p>
	<a href = "<%=request.getContextPath()%>/djmallArea/toShow?token=${token}" target = "right">ztree</a><p>
	<a href = "<%=request.getContextPath()%>/djmallArea/toShowSelect?token=${token}" target = "right">三级联动</a><p>

</body>
</html>