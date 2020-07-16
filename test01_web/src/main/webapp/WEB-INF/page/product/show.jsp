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
            $.post("<%=request.getContextPath() %>/product/show?token=${token}",
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
                        html += "<td>"+p.proName+"</td>"
                        html += "<td>"+p.userId+"</td>"
                        html += "<td>"+p.proCount+"</td>"
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

        function addProduct(){
            layer.open({
                type: 2,
                title: '添加商品',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath() %>/product/toAdd?token=${token}'//iframe的url
            });
        }

        function upd(id) {
            layer.open({
                type: 2,
                title: '修改商品',
                shadeClose: true,
                shade: 0.8,
                area: ['380px', '90%'],
                content: '<%=request.getContextPath() %>/product/findProductById?token=${token}&id=' + id
            });
        }

        function del(id) {
            $.post("<%=request.getContextPath()%>/product/updateProductIsDel?token=${token}",
                {"id" : id},
                function(data){
                    if (data.code != 200) {
                        layer.msg(data.msg, {icon: 5});
                        return;
                    }
                    layer.msg(data.msg, {icon: 6, time: 2000},
                        function(){
                            location.href = "<%=request.getContextPath()%>/product/toShow?token=${token}";
                        });
                })
        }

    </script>
</head>
<body>
    <form id="fm">
        <input type = "hidden" id = "pageNo" value = "1"/>
        <input type = "button" value = "添加" onclick = "addProduct()"/>
    </form>
<table >
    <tr>
        <th>id</th>
        <th>商品名</th>
        <th>用户id</th>
        <th>商品数量</th>
    </tr>
    <tbody id = "tbd">

    </tbody>
</table>
<div id = "pageDiv"></div>
</body>
</html>