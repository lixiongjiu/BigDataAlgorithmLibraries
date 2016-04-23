<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>上传文件</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <script type="text/javascript" src="/resources/js/jquery-1.12.2.js"></script>
    <script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var i=2;
            $("#btn-plus").click(function () {
                $("#plus").before("<div class='row'>" +
                        "<div class='col-md-4'></div> " +
                        "<div class='col-md-4'> " +
                        "<input type='file' class='form-control' "+"name='file"+i+"'/> " +
                        "</div></div>");
                i+=1;
            });

        });

    </script>
</head>
<body background="/resources/img/bg4.jpg" style="background-size:cover;">
<%--导航栏--%>
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
            </c:choose>
        </div>
        <button class="btn-default navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="glyphicon glyphicon-align-justify"></span>
        </button>
        <div class="collapse navbar-collapse">

            <div class="navbar-form navbar-right">
                <c:choose>
                    <c:when test="${userName!=null}">
                        <a href="/user/'${userName}'" class="navbar-link">
                            <span class="glyphicon glyphicon-user"></span>
                            个人主页
                        </a>
                        <div class="btn-group">
                            <button type="button" class="btn dropdown-toggle"
                                    data-toggle="dropdown">
                                <span class="caret"></span>
                                    <%-- <span class="sr-only">切换下拉菜单</span>--%>
                            </button>

                            <ul class="navbar-inverse dropdown-menu" role="menu">
                                <li><a href="" class="navbar-link">账户设置</a></li>
                                <li><a href="/record/list" class="navbar-link">运行记录</a></li>
                                <li><a href="/user/logout" class="navbar-link">退出</a></li>
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
<div class="container-fluid center-block">
    <h2 class="page-header text-center"><span class="glyphicon glyphicon-upload"></span>&nbsp;上传算法包</h2>

    <form enctype="multipart/form-data" method="post" class="form-group" id="filesUploadForm">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <input type="file" class="form-control" name="file1"/>
            </div>
            <div class="col-md-1">
                    &times;
            </div>
        </div>

        <div class="row" id="plus">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <button class="btn-xs btn-primary pull-right" id="btn-plus" type="button">
                    <span class="glyphicon glyphicon-plus"></span>&nbsp;追加文件
                </button>
            </div>
        </div>
        <div class="row text-center" style="margin: 50px;">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <button type="reset" class="btn btn-primary">
                    <span class="glyphicon glyphicon-refresh"></span>&nbsp;重置
                </button>
                <button type="submit" class="btn btn-primary">
                    <span class="glyphicon glyphicon-upload"></span>&nbsp;上传
                </button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
