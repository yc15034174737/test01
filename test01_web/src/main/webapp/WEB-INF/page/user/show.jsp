<%--
  Created by IntelliJ IDEA.
  User: cqj
  Date: 2020/6/24
  Time: 2:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src = "<%=request.getContextPath()%>/res/layer/layer.js"></script>
    <script type="text/javascript">
        $(function(){
            show();
        })

        function show(){
            var index = layer.load(1,{shade:0.3});
            $.post("<%=request.getContextPath() %>/user/show?token=${token}",
                {"pageNo":$("#pageNo").val()},
                function (data){
                    if(data.code != 200){
                        layer.msg(data.msg, {icon: 5});
                        layer.close(index);
                        return;
                    }
                    var html = "";
                    var pageHtml = ""
                    for(var i = 0; i <data.data.list.length; i++){
                        var p = data.data.list[i]
                        html += "<tr>"
                        html += "<td>"+p.id+"</td>"
                        html += "<td><img src='http://qcix2kqta.bkt.clouddn.com/"+p.fileName+"' width='30px' height='30px'></td>"
                        html += "<td>"+p.userName+"</td>"
                        html += "<td>"+p.userPwd+"</td>"
                        html += "<td>"+p.userId+"</td>"
                        html += "<td><input type='button' value='修改' onclick='upd("+p.id+")' /></td>"
                        html += "<td><input type='button' value='删除' onclick='del("+p.id+")' /></td>"
                        html += "</tr>"
                    }
                    $("#tbd").append(html)
                    pageHtml += "<input type = 'button' value = '加载更多' onclick = 'page("+data.data.pages+")'/>";
                    $("#pageDiv").html(pageHtml);
                    layer.close(index);
                })
        }


        function page(pages){
            var page = $("#pageNo").val();
            if(parseInt(page) +1 > pages){
                $("#pageDiv").html("--我是有底线的--");
                return;
            }
            $("#pageNo").val(parseInt(page) +1);
            show();
        }

        function addUser(){
            layer.open({
                type: 2,
                title: '添加用户',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath() %>/user/toAdd?token=${token}'//iframe的url
            });
        }

        function upd(id) {
            layer.open({
                type: 2,
                title: '修改用户',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath() %>/user/findUserById?token=${token}&id=' + id
            });
        }

        function del(id) {
            $.post("<%=request.getContextPath()%>/user/updateUserIsDel?token=${token}",
                {"id" : id},
                function(data){
                    if (data.code != 200) {
                        layer.msg(data.msg, {icon: 5});
                        return;
                    }
                    layer.msg(data.msg, {icon: 6, time: 2000},
                        function(){
                            location.href = "<%=request.getContextPath()%>/user/toShow?token=${token}";
                        });
                })
        }

    </script>
</head>
<body>
    <form id="fm">
        <input type = "hidden" id = "pageNo" value = "1"/>
        <input type = "button" value = "添加" onclick = "addUser()"/>
    </form>
<table >
    <tr>
        <th>id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>最后一次操作人id</th>
    </tr>
    <tbody id = "tbd">

    </tbody>
</table>
<div id = "pageDiv"></div>
</body>
</html>