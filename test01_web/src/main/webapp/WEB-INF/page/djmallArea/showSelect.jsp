<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <title>Title</title>
 <script type="text/javascript" src="<%=request.getContextPath() %>/res/jquery.min.js"></script>
 <script type="text/javascript" >
  $(document).ready(function(e){
   //三个下拉列表
   //加载显示数据
   $("#sanji").html("<select id='sheng' onchange='sheng()'></select><select id='shi' onchange='shi()' ></select><select id='qu' onchange='qu()'></select>");
   //加载省份
   FillSheng();
   //加载市
   FillShi();
   //加载区
   FillQu();
  })

  //当省份发生变化
  function sheng(){
   FillShi();
   FillQu();
  }
  function shi(){
   FillQu();
  }
  //加载省份信息
  function FillSheng()
  {
   //根据父级代号
   //取父级代号
   var pcode = "0";
   //根据父级代号查数据
   $.ajax({
    async:false,
    url:"<%=request.getContextPath()%>/djmallArea/ztreeShow",
    data:{'id':pcode},
    type:"POST",
    dataType:"JSON",
    success:function(data)
    {
     var str = "";
     for(var sj in data)
     {
      str = str+"<option value = '"+data[sj].id+"'>"+data[sj].areaName+"</optiom>";
     }
     $("#sheng").html(str);
    }
   });
  }

  //加载市
  function FillShi()
  {
   //根据父级代号
   //取父级代号
   var pcode = $("#sheng").val();
   //根据父级代号查数据
   $.ajax({
    async:false,
    //取消异步
    url:"<%=request.getContextPath()%>/djmallArea/ztreeShow",
    data:{'id':pcode},
    type:"POST",
    dataType:"JSON",
    success:function(data)
    {
     var str = "";
     for(var sj in data)
     {

      str = str+"<option value = '"+data[sj].id+"'>"+data[sj].areaName+"</optiom>";
     }
     $("#shi").html(str);
    }
   });
  }
  //加载区
  function FillQu()
  {
   //根据父级代号
   //取父级代号
   var pcode = $("#shi").val();
   //根据父级代号查数据
   $.ajax({

    url:"<%=request.getContextPath()%>/djmallArea/ztreeShow",
    data:{'id':pcode},
    type:"POST",
    dataType:"JSON",
    success:function(data)
    {
     var str = "";
     for(var sj in data)
     {

      str = str+"<option value = '"+data[sj].id+"'>"+data[sj].areaName+"</optiom>";
     }
     $("#qu").html(str);
    }
   });
  }

 </script>
</head>
<body>
<h1>三级联动</h1>
<div id="sanji"></div>
</body>
</html>