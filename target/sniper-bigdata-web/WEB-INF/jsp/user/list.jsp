<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="../common/head.jsp"/>
<head>
    <title>用户列表</title>
    <script type="text/javascript" src="/resources/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="/resources/js/tb_users.js">
       /* $(document).ready(function() {
            $("#t_user_list").dataTable({
                "processing": true,
                "serverSide": true,
                "ajax" : "load",
                "columns": [
                    {"data":"id","visible":false},
                    {"data": "name"/!*, "bSortable": false*!/},
                    {"data": "isAdmin"},
                    {"data": null , fnCreatedCell:test}
                ],
                "ColumnDefs": [
                    {
                        "targets": [3],
                        "data": "id",
                        "render": function(data, type, full) {
                            return "<a href='/user/modify?id=" + data + "'>修改</a>";
                        }
                    },
                    {
                        "targets":[4],
                        "data":"id",
                        "render":function(data){
                            return "<a href='/user/delete?id="+data+"'>删除</a>";
                        }
                    }
                ]
            });
        });*/
    </script>
</head>

<body>
<div class="func">
    <table class="table" id="tb_users">
       <caption>
            <strong>
                <span class="glyphicon glyphicon-list"></span>
                &nbsp;用户列表
            </strong>
        </caption>
       <%-- <button type="button" id="datatables_filter"><span ></span></button>--%>
        <%--
        &lt;%&ndash;<tbody>&ndash;%&gt;
        <thead>
        <tr>
            <th>用户名</th>
            &lt;%&ndash; <th>密码</th>&ndash;%&gt;
            <th>是否为管理员</th>
            <th>修改</th>
            <th>删除</th>
        </tr>
        </thead>--%>
        <%--<c:forEach items="${users}" var="user">
            <c:choose>
                <c:when test="${user.isAdmin}"><tr class="alert alert-warning"></c:when>
                <c:otherwise><tr class="alert alert-info"></c:otherwise>
            </c:choose>
            <td>${user.userId}</td>
            <td>${user.userName}</td>
            <td>${user.password}</td>
            <c:choose>
                <c:when test="${user.isAdmin}">
                    <td>管理员</td>
                </c:when>
                <c:otherwise>
                    <td>非管理员</td>
                </c:otherwise>
            </c:choose>
            <td>
                <a href="/user/modify">
                    <span class="glyphicon glyphicon-edit"></span>
                </a>
            </td>
            <td>
                <a href="/user/del">
                    <span class="glyphicon glyphicon-remove"></span>
                </a>
            </td>
            </tr>

        </c:forEach>
        </tbody>--%>
    </table>
</div>
<jsp:include page="../common/foot.jsp"/>
</body>
</html>
