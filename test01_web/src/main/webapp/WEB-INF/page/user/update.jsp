<%--
  Created by IntelliJ IDEA.
  User: cqj
  Date: 2020/6/23
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<script type="text/javascript" src ="<%=request.getContextPath() %>/res/js/jquery-1.12.4.js"  ></script>
    <script  type="text/javascript" src="<%=request.getContextPath() %>/res/jquery-validation-1.14.0/lib/jquery.js"></script>
    <script  type="text/javascript" src="<%=request.getContextPath() %>/res/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
    <script type="text/javascript" src ="<%=request.getContextPath()%>/res/layer/layer.js" ></script>
<script type="text/javascript">
    $.validator.setDefaults({
        submitHandler: function() {
            var index = layer.load(1,{shade:0.3});
            $.post("<%=request.getContextPath()%>/user/updateUser?token=${token}",
                $("#fm").serialize(),
                function(data){
                    if(data.code != 200){
                        layer.msg(data.msg, {icon: 5});
                        layer.close(index);
                        return;
                    }
                    layer.msg(data.msg, {
                        icon: 6,
                        time: 2000 //2秒关闭（如果不配置，默认是3秒）
                    }, function(){
                        layer.close(index);
                        parent.window.location.href="<%=request.getContextPath()%>/user/toShow?token=${token}";
                    });

                })
        }
    });


    $().ready(function(){
        $("#fm").validate({
            rules : {
                userName : {
                    required:true,
                },
                userPwd : {
                    required:true,
                },
            },
            messages:{
                userName : {
                    required:"请输入用户名",
                },
                userPwd : {
                    required:"请输入密码",
                },
            }
        })
    })
</script>
    <style>
        .error{
            color:red;
        }
    </style>
</head>
<body>
    <form id = "fm">
        <input type="hidden"  name="id"  value="${user.id}" /><br>
        用户名:<input type="text"  name="userName"  value="${user.userName}" /><br>
        密码:<input type="text" name="userPwd" value="${user.userPwd}" /><br>
        <input type="submit" value="修改" />

    </form>
</body>
</html>
