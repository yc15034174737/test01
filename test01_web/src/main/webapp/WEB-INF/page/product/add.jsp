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
            $.post("<%=request.getContextPath()%>/product/addProduct?token=${token}",
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
                        parent.window.location.href="<%=request.getContextPath()%>/product/toShow?token=${token}";
                    });

                })
        }
    });


    $().ready(function(){
        $("#fm").validate({
            rules : {
                proName : {
                    required:true,
                    remote: {
                        url: "<%=request.getContextPath() %>/product/findProductByProName?token=${token}",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            username: function() {
                                return $("#proName").val();
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
                proCount : {
                    required:true,
                },
            },
            messages:{
                proName : {
                    required:"请输入商品名",
                    remote: "商品名已存在"
                },
                proCount : {
                    required:"请输入数量",
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
        商品名:<input type="text"  name="proName"   placeholder="输入商品名" /><br>
        商品数量:<input type="text" name="proCount" placeholder="输入数量"/><br>
        <input type="submit" value="添加" />

    </form>
</body>
</html>
