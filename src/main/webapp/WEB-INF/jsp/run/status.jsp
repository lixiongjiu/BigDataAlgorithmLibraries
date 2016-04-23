<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>运行状态</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script type="text/javascript" src="/resources/js/jquery-1.12.2.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            var flag = 1;
            while (flag==1) {
                $.ajax(
                        {
                            url: "/run/status",
                            type: "GET",
                            dataType: "json",
                            async:false,
                            success: function (data) {
                                var j_result = eval(data);
                                if (j_result.log == "end") {
                                    $("#logInfo").append("<font color='blue'>[STDOUT]:已经运行完毕</font><br/>");
                                    flag = 0;
                                }
                                else if (j_result.log == "err") {
                                    $("#logInfo").append("<font color='#dc143c'>[STDOUT]:运行出错</font><br/>");
                                    flag = 0;
                                }
                                else
                                    $("#logInfo").append('<font color="#00ced1">'+j_result.log + '</font><br/>');
                            },
                            err:function(){
                                $("#logInfo").append("<font color='#ffd700'>[STDOUT]:获取日志信息出错</font><br/>");
                                flag=0;
                            }
                        }
                );
            }

        });
    </script>
</head>
<body background="/resources/img/bg4.jpg" style="background-size: cover;">
<nav class="navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="/user/home" class="navbar-brand"><span class="glyphicon glyphicon-home">主页</span></a>
        </div>
        <div class="navbar-text">
            <c:choose>
                <c:when test="${userName!=null}">
                    欢迎您：${userName}&nbsp;
                    <a href="/io/upload" class="navbar-link">
                        <span class="glyphicon glyphicon-upload"></span>上传算法包
                    </a>
                    <a href="/io/download" class="navbar-link">
                        <span class="glyphicon glyphicon-download"></span>下载结果
                    </a>
                </c:when>
                <c:otherwise>
                    <strong>欢迎您：游客</strong>
                </c:otherwise>
            </c:choose>
        </div>
        <button class="btn-default navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="glyphicon glyphicon-align-justify"></span>
        </button>
        <div class="collapse navbar-collapse">

            <div class="navbar-form navbar-right">
                <span class="glyphicon glyphicon-user">个人主页</span>
                <c:choose>
                    <c:when test="${userName!=null}">
                        <div class="btn-group">
                            <button type="button" class="btn dropdown-toggle"
                                    data-toggle="dropdown">
                                <span class="caret"></span>
                                    <%-- <span class="sr-only">切换下拉菜单</span>--%>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="">账户设置</a></li>
                                <li><a href="/record/list">运行记录</a></li>
                                <li><a href="/user/logout">退出</a></li>
                            </ul>
                        </div>
                        &nbsp;&nbsp;
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${userName==null}">
                        <a href="/user/login" class="navbar-link"><span class="glyphicon glyphicon-log-in">登录</span></a>
                        <a href="/user/register" class="navbar-link"><span
                                class="glyphicon glyphicon-registration-mark">注册&nbsp;</span></a>
                    </c:when>
                </c:choose>

                <input id="kw" type="text" class="form-control" placeholder="请输入关键字">
                <button onclick="search()"><span class="glyphicon glyphicon-search"></span></button>
            </div>
        </div>
    </div>
</nav>

<div style="height: 100px;"></div>
<div class="container">
    <div class="list-group">
        <p id="logInfo" class="list-group-item" style="background: #000000">
            <font color="#00ffff">终端输出如下：</font><br/>
        </p>
    </div>

</div>
</body>
</html>
