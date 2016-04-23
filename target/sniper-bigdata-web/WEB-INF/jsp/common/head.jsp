<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%--<title>HeadAndNav</title>--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <%--css样式--%>
    <link href="/resources/css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css" media="all"/>
    <link href="/resources/css/nav.css" rel="stylesheet" type="text/css" media="all"/>

    <%--js脚本--%>
    <script src="/resources/js/jquery-1.12.3.min.js"></script>
    <script src="/resources/js/jquery.easydropdown.js"></script>
    <script src="/resources/js/scripts.js" type="text/javascript"></script>
    <script src="/resources/js/easyResponsiveTabs.js" type="text/javascript"></script>
    <script src="/resources/js/responsiveslides.min.js"></script>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    }
    </script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#horizontalTab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true   // 100% fit in a container
            });
        });
    </script>
    <!-- start-smoth-scrolling -->
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>
    <!-- slider -->
    <script>
        $(function () {
            $("#slider").responsiveSlides({
                auto: true,
                manualControls: '#slider3-pager',
            });
        });
        function logout_check(){
            if( ${userName!=null} ){
                if(confirm("您确定要退出吗？")){
                    return true;
                }
                else
                    return false;
            }
            else {
                alert("您目前尚未登录！");
                return false;
            }
        }

    </script>
    <!-- slider -->
</head>
<body>
<%--头部--%>
<div id="home" class="header">
    <div class="header-top">
        <!-- container -->
        <div class="container">
            <div class="top-nav">
                <ul class="nav1">
                    <li><a href="/user/home"><span class="glyphicon glyphicon-home"> </span>主页</a></li>
                    <li><a href="/user/login"><span class="glyphicon glyphicon-log-in"> </span>登录</a></li>
                    <li><a href="/user/register"><span class="glyphicon glyphicon-registration-mark"> </span>注册</a></li>
                    <li><a href="/common/contact"><span class="glyphicon glyphicon-phone"> </span>联系我</a></li>


                    <c:choose>
                        <c:when test="${userName!=null}">
                            <li><a href="/user/'${userName}'"><font color="aqua">欢迎您：${userName}</font></a></li>
                            <li><a href="/user/logout" onclick="return logout_check()"><span class="glyphicon glyphicon-log-out"></span>退出</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/user/home"><font color="aqua">欢迎您：游客</font></a></li>
                        </c:otherwise>
                    </c:choose>


                </ul>
            </div>
            <div class="nav-right">
                <p><i>版权所有:&nbsp;</i><a href="#">sniper</a></p>
            </div>
            <div class="clearfix"></div>
            <!-- script-for-menu -->
        </div>
        <!-- //container -->
    </div>
    <div class="header-bottom">
        <div class="container">
            <!-- container -->

            <div class="head-logo">
                <a href="index.html"><img src="/resources/img/logo_font_2.png" class="img-head" alt=""/></a>
            </div>
            <div class="logo-right">
                <img src="/resources/img/hadoop_logo_2.png" class="img-head" alt=""/>
            </div>
            <div class="clearfix"></div>

            <!-- //container -->
        </div>
    </div>
</div>
<!-- //header -->
<!-- bg-banner -->
<div class="container">
    <div class="bg-banner">
        <div class="banner-bottom-bg">
            <div class="banner-bg">

                <!-- banner -->
                <div class="banner">
                    <div class="banner-grids">
                        <%--导航栏--%>
                        <div class="banner-top">
                            <span class="menu">
                                    <span class="glyphicon glyphicon-align-justify"></span>
                            </span>
                            <ul class="nav banner-nav">
                                <li class="active"><a href="/user/home">主页</a></li>
                                <li class="dropdown1"><a href="/user/home">快速导航</a>
                                    <ul class="dropdown2">
                                        <li><a href="/user/login">登录</a></li>
                                        <li><a href="/user/register">注册</a></li>
                                        <li><a href="/user/'${userName}'">个人主页</a></li>
                                        <li><a href="/record/list">使用记录</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown1"><a href="/user/home">功能</a>
                                    <ul class="dropdown2">
                                        <li><a href="/run/start">启动hadoop</a></li>
                                        <li><a href="/run/stop">关闭hadoop</a></li>
                                        <li><a href="/run/shell">远程shell</a></li>
                                        <%--<li><a href="404.html">Worldnews</a></li>--%>
                                    </ul>
                                </li>
                                <li class="dropdown1"><a href="/user/home">核心</a>
                                    <ul class="dropdown2">
                                        <li><a href="/io/upload">上传算法或者数据集</a></li>
                                        <li><a href="/io/#">查看上传记录</a></li>
                                        <li><a href="/io/download">下载运行结果</a></li>
                                        <%--<li><a href="404.html">Worldnews</a></li>--%>
                                    </ul>
                                </li>
                                <li class="dropdown1"><a href="/user/login">管理员入口</a>
                                    <ul class="dropdown2">
                                        <li><a href="/user/login">管理员登录</a></li>
                                        <li><a href="/user/list">用户信息</a></li>
                                    </ul>

                                </li>
                                <%-- <li class="dropdown1"><a href="sports.html">Sport</a>
                                     <ul class="dropdown2">
                                         <li><a href="lifestyle.html">Lifestyle</a></li>
                                         <li><a href="archives.html">Archives</a></li>
                                         <li><a href="fullwidth.html">Fullwidth</a></li>
                                         <li><a href="404.html">Worldnews</a></li>
                                     </ul>
                                 </li>
                                 <li class="dropdown1"><a href="travel.html">Travel</a>
                                     <ul class="dropdown2">
                                         <li><a href="lifestyle.html">Lifestyle</a></li>
                                         <li><a href="archives.html">Archives</a></li>
                                         <li><a href="fullwidth.html">Fullwidth</a></li>
                                         <li><a href="404.html">Worldnews</a></li>
                                     </ul>
                                 </li>
                                 <li class="dropdown1"><a href="celebrity.html">Celebrity</a>
                                     <ul class="dropdown2">
                                         <li><a href="lifestyle.html">Lifestyle</a></li>
                                         <li><a href="archives.html">Archives</a></li>
                                         <li><a href="fullwidth.html">Fullwidth</a></li>
                                         <li><a href="404.html">Worldnews</a></li>
                                     </ul>
                                 </li>
                                 <li class="dropdown1"><a href="fullwidth.html">Full Width </a>
                                     <ul class="dropdown2">
                                         <li><a href="lifestyle.html">Lifestyle</a></li>
                                         <li><a href="archives.html">Archives</a></li>
                                         <li><a href="fullwidth.html">Fullwidth</a></li>
                                         <li><a href="404.html">Worldnews</a></li>
                                     </ul>
                                 </li>
                                 <li class="dropdown1"><a href="404.html">World News</a>
                                     <ul class="dropdown2">
                                         <li><a href="lifestyle.html">Lifestyle</a></li>
                                         <li><a href="archives.html">Archives</a></li>
                                         <li><a href="fullwidth.html">Fullwidth</a></li>
                                         <li><a href="404.html">Worldnews</a></li>
                                     </ul>
                                 </li>
                                 <li class="dropdown1"><a href="contact.html">Contact</a></li>--%>
                            </ul>
                            <script>
                                $("span.menu").click(function () {
                                    $(" ul.nav").slideToggle("slow", function () {
                                    });
                                });
                            </script>
                        </div>
                        <%--   </div>
                       </div>
                   </div>
               </div>
           </div>--%>
                        <%--
                        </div>
                        </div>
                        </body>
                        </html>--%>
