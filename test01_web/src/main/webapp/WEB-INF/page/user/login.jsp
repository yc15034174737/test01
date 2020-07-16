<%--
  Created by IntelliJ IDEA.
  User: cqj
  Date: 2020/6/23
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<script type="text/javascript" src = "<%=request.getContextPath()%>/res/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath()%>/res/layer/layer.js"></script>
<script type="text/javascript">
    //判断当前窗口路径与加载路径是否一致。
    if(window.top.document.URL != document.URL){
        //将窗口路径与加载路径同步
        window.top.location = document.URL;
    }
    function login(){
        var index = layer.load(1,{shade:0.3});
        $.post("<%=request.getContextPath()%>/user/login?token=${token}",
            $("#fm").serialize(),
            function(data){
                if (data.code != 200) {
                    layer.close(index);
                    layer.msg(data.msg, {icon: 5});
                    return;
                }
                layer.close(index);
                layer.msg('登陆成功，欢迎！', {icon: 6, time: 2000},
                    function(){
                        parent.location.href = "<%=request.getContextPath()%>/index/toIndex?token="+data.data.token;
                    });
            })
    }

</script>
</head>
<body>

    <form id="fm">
        用户名:<input type="text"  name="userName"   placeholder="输入用户名" />
        密&码:<input type="text" name="userPwd" placeholder="输入密码"/><br>
        <input type="button" value="登陆" onclick="login()"/>

    </form>

</body>
</html>
