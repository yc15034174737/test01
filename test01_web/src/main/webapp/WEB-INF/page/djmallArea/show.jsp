<%--
  Created by IntelliJ IDEA.
  User: cqj
  Date: 2020/6/29
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/zTree-zTree_v3-master/zTree_v3/css/demo.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/zTree-zTree_v3-master/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/res/jquery.min.js"></script>
    <script type="text/javascript" src= "<%=request.getContextPath() %>/res/zTree-zTree_v3-master/zTree_v3/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/res/zTree-zTree_v3-master/zTree_v3/api/apiCss/jquery.ztree.core.js"></script>
    <SCRIPT LANGUAGE="JavaScript">
        var setting = {
            async:{
                enable: true,
                url: "<%=request.getContextPath()%>/djmallArea/ztreeShow",
                autoParam: ["id"],
                dataFilter: ajaxDataFilter
            }
        }
        function ajaxDataFilter(treeId, parentNode, responseData) {
            if (responseData) {
                for(var i =0; i < responseData.length; i++) {
                    responseData[i].name = responseData[i].areaName;
                }
            }
            return responseData;
        }
        //初始化节点
        $(document).ready(function(){
            $.fn.zTree.init($("#demotree"), setting);
        });


    </SCRIPT>
</head>
<body>
<!-- treeDemo -->
<ul id="demotree" class="ztree"></ul>
<select></select>

</body>
</html>