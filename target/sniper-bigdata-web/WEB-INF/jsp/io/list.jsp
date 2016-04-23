<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>上传文件清单</title>
    <%--<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script type="text/javascript" src="/resources/js/jquery-1.12.2.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>--%>
    <script type="text/javascript">
        function rm(name,pointer) {
            if(confirm("确认要删除吗？")){
                //通过ajax异步操作删除数据库的记录
                $.ajax({
                            type:"POST",
                            data:{fileName:name},
                            url:"/io/delete",
                            dateType:"json",
                            success:function(data){
                                var res=eval(data);
                                if(res.state=='ok')
                                    $(pointer).parent().parent().empty();
                            }
                        }
                );
            }
        }
    </script>
</head>
<body>
<%--导航栏--%>

<jsp:include page="../common/head.jsp"/>
<div  class="func">
    <table class="table">
        <caption class="text-center">
            <strong><span class="glyphicon glyphicon-list">&nbsp;
            </span>您上传的文件列表如下</strong>
        </caption>
        <tr>
            <th>文件状态</th>
            <th>文件名称</th>
            <th>存储名称</th>
            <th>上传时间</th>
            <th>操作</th>
            <th>删除</th>
        </tr>
        <c:forEach items="${file_list}" var="map">
            <c:choose>
                <c:when test="${map.status=='ok'}">
                    <tr class="alert alert-info">
                    <td>${map.status}</td>
                    <td>${map.realName}</td>
                    <td>${map.storeName}</td>
                    <td>${map.createTime}</td>
                    <td>
                        <a href="/run/commit?storeName=${map.storeName}">运行</a>
                    </td>
                    <th>
                        <span onclick="rm('${map.storeName}',this)">&times;</span>
                    </th>
                </c:when>
                <c:otherwise>
                    <tr class="alert alert-warning">
                    <td>${map.status}</td>
                    <td>${map.realName}</td>
                    <td>...</td>
                    <td>...</td>
                    <td>...</td>
                    <th><span  onclick="rm('${map.storeName}',this)">&times;</span></th>
                </c:otherwise>
            </c:choose>
            </tr>
        </c:forEach>
    </table>

</div>
<jsp:include page="../common/foot.jsp"/>
</body>
</html>
