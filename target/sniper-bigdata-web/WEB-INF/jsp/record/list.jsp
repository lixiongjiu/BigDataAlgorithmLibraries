<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="../common/head.jsp"/>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>用户使用记录</title>
    <%-- <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
     <script type="text/javascript" src="/resources/js/jquery-1.12.2.js"></script>
     <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>--%>
    <script type="text/javascript">
        function rm(name, pointer) {
            if (confirm("确认要删除吗？")) {
                //通过ajax异步操作删除数据库的记录
                $.ajax({
                            type: "POST",
                            data: {fileName: name},
                            url: "/io/delete",
                            dateType: "json",
                            success: function (data) {
                                var res = eval(data);
                                if (res.state == 'ok')
                                    $(pointer).parent().parent().empty();
                            }
                        }
                );
            }
        }
    </script>

</head>
<body>


<div class="func">
    <c:choose>
        <c:when test="${recordList==null}">
            <p3 class="page-header text-warning text-center">您目前没有任何记录</p3>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="table">
                    <caption style="font-size: large;font-weight: bold;"
                             class="text-center">
                        您的使用记录(总共${recordNum}条记录)
                    </caption>
                    <thead>
                    <tr>
                        <th>提交者</th>
                        <th>状态</th>
                        <th>算法包名字</th>
                        <th>算法包描述</th>
                        <th>提交时间</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${recordList}" var="record">
                        <c:choose>
                            <c:when test="${record.statusName.equals('successful')}">
                                <tr class="alert alert-success">
                            </c:when>
                            <c:when test="${record.statusName.equals('unfinished')}">
                                <tr class="alert alert-warning">
                            </c:when>
                            <c:when test="${record.statusName.equals('failed')}">
                                <tr class="alert alert-danger">
                            </c:when>
                        </c:choose>

                        <td>${record.userName}</td>
                        <td>${record.statusName}</td>
                        <td>${record.jar_name}</td>
                        <td>${record.jar_desc}</td>
                        <td>${record.commit_time}</td>
                        <td><span onclick="rm('${record.storeName}',this)">&times;</span></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>

</div>
<jsp:include page="../common/foot.jsp"/>
</body>
</html>
