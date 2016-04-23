<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/25
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>算法运行状态</title>
      <script type="text/javascript" src="/js/jquery-1.12.2.js"></script>
      <script type="text/javascript">
        $(document).ready(function(){
          var status=${status};
          alert(status);
          var result=${result}
          $("#test").html(status);
          $.get("/run/status",function(){
            $("#test").html(${status}+"Results:"+${result});
          });
        });
      </script>
</head>
<body>
<p id="test">
  ${status}
  ${result}
</p>
</body>
</html>
