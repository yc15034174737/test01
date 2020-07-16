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
            var formData = new FormData($('#fm')[0]);
            alert(formData);
            $.ajax({
                url: '<%=request.getContextPath()%>/user/addUser?token=${token}' ,
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
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
                },

            });
        }
    });


    $().ready(function(){
        $("#fm").validate({
            rules : {
                userName : {
                    required:true,
                    remote: {
                        url: "<%=request.getContextPath() %>/user/findUserByUserName?token=${token}",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            username: function() {
                                return $("#userName").val();
                            }
                        },
                        dataType : "json",
                        dataFilter:function(data,type){
                            if(data == "true"){
                                return true;
                            }else{
                                return false;
                            }
                        }
                    }
                },
                userPwd : {
                    required:true,
                },
            },
            messages:{
                userName : {
                    required:"请输入用户名",
                    remote: "用户名已存在"
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
        color: #ff0000;
    }
</style>
</head>
<body>
    <form id = "fm" method="post" enctype="multipart/form-data" >
        头像: <input type="file" name = "file" />
        用户名:<input type="text"  name="userName"   placeholder="输入用户名" /><br>
        密码:<input type="text" name="userPwd" placeholder="输入密码"/><br>
        <input type="submit" value="添加" />

    </form>
</body>
</html>
