<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<jsp:include page="../common/head.jsp"/>
<div class="ban-top">
    <div class="col-md-6 bann-left">
        <div class="slider">
            <!-- Slideshow 3 -->
            <ul class="rslides" id="slider">
                <li><img src="/resources/img/1.png" class="img-responsive" alt=""></li>
                <li><img src="/resources/img/2.png" class="img-responsive" alt=""></li>
                <li><img src="/resources/img/3.png" class="img-responsive" alt=""></li>
                <li><img src="/resources/img/4.png" class="img-responsive" alt=""></li>
                <!--<li><img src="/resources/img/5.jpg" class="img-responsive" alt=""></li>-->

            </ul>
            <!-- Slideshow 3 Pager -->
            <ul id="slider3-pager">
                <li><a href="#"><img src="/resources/img/1c.png" class="img-responsive" alt=""></a></li>
                <li><a href="#"><img src="/resources/img/2c.png" class="img-responsive" alt=""></a></li>
                <li><a href="#"><img src="/resources/img/3c.png" class="img-responsive" alt=""></a></li>
                <li><a href="#"><img src="/resources/img/4c.png" class="img-responsive" alt=""></a></li>
                <!--<li><a href="#"><img src="/resources/img/5c.jpg" class="img-responsive" alt=""></a></li>-->

            </ul>
        </div>
    </div>
    <div class="col-md-6 bann-right">
        <p class="comments">// 李雄赳 // May 4 2016 //<a href="#"></a></p>

        <h2><a href="single.html">Hadoop算法库平台</a></h2>

        <p class="text">基于Hadoop分布式系统搭建的算法库平台，用户可以上传算法，数据集，运行
            算法，得到可视化结果</p>
    </div>
    <div class="clearfix"></div>
</div>
<div class="func">
    <ul id="flexiselDemo3">
        <li>
            <div class="team1">
                <img src="/resources/img/upload_logo.jpg" class="img-func" alt="" />
                <h4><a href="/io/upload">算法包、数据集上传 </a></h4>
                <%--<p class="comments">August 4 2010, <a href="#">8 Comments</a></p>--%>
            </div>
        </li>
        <li>
            <div class="team1">
                <img src="/resources/img/download_logo.jpg" class="img-func" alt=""/>
                <h4><a href="/io/download">结果集下载</a> </h4>
            </div>
        </li>
        <li>
            <div class="team1">
                <img src="/resources/img/record.jpg" class="img-func" alt=""/>
                <h4><a href="/io/download">使用记录查看</a> </h4>
            </div>
        </li>
        <li>
            <div class="team1">
                <img src="/resources/img/remote_control_logo.jpg" class="img-func" alt=""/>
                <h4><a href="/io/download">远程控制</a> </h4>
            </div>
        </li>

    </ul>
    <script type="text/javascript">
        $(window).load(function() {

            $("#flexiselDemo3").flexisel({
                visibleItems: 4,
                animationSpeed: 1000,
                autoPlay:false,
                autoPlaySpeed: 3000,
                pauseOnHover: true,
                enableResponsiveBreakpoints: true,
                responsiveBreakpoints: {
                    portrait: {
                        changePoint:480,
                        visibleItems: 1
                    },
                    landscape: {
                        changePoint:640,
                        visibleItems: 2
                    },
                    tablet: {
                        changePoint:768,
                        visibleItems: 3
                    }
                }
            });

        });
    </script>
    <script type="text/javascript" src="/resources/js/jquery.flexisel.js"></script>
</div>
<%--<div class="recent">
    <ul id="flexiselDemo3">
        <li>
            <div class="team1">
                <img src="/resources/img/pic1.jpg" class="img-responsive" alt=""/>
                <h4><a href="single.html">The point of using Lorem Ipsum is that it has </a></h4>

                <p class="comments">August 4 2010, <a href="#">8 Comments</a></p>
            </div>
        </li>
        <li>
            <div class="team1">
                <img src="/resources/img/pic6.jpg" class="img-responsive" alt=""/>
                <h4><a href="single.html">Many desktop publishing packages and web </a></h4>

                <p class="comments">August 4 2010, <a href="#">8 Comments</a></p>
            </div>
        </li>
        <li>
            <div class="team1">
                <img src="/resources/img/pic4.jpg" class="img-responsive" alt=""/>
                <h4><a href="single.html">There are many variations of passages of Lorem</a></h4>

                <p class="comments">August 4 2010, <a href="#">8 Comments</a></p>
            </div>
        </li>
        <li>
            <div class="team1">
                <img src="/resources/img/30.jpg" class="img-responsive" alt=""/>
                <h4><a href="single.html">Latin professor at Hampden-Sydney College Virginia</a></h4>

                <p class="comments">August 4 2010, <a href="#">8 Comments</a></p>
            </div>
        </li>
        <li>
            <div class="team1">
                <img src="/resources/img/10.jpg" class="img-responsive" alt=""/>
                <h4><a href="single.html">The generated Lorem Ipsum is therefore always free</a></h4>

                <p class="comments">August 4 2010, <a href="#">8 Comments</a></p>
            </div>
        </li>
    </ul>
    <script type="text/javascript">
        $(window).load(function () {

            $("#flexiselDemo3").flexisel({
                visibleItems: 4,
                animationSpeed: 1000,
                autoPlay: false,
                autoPlaySpeed: 3000,
                pauseOnHover: true,
                enableResponsiveBreakpoints: true,
                responsiveBreakpoints: {
                    portrait: {
                        changePoint: 480,
                        visibleItems: 1
                    },
                    landscape: {
                        changePoint: 640,
                        visibleItems: 2
                    },
                    tablet: {
                        changePoint: 768,
                        visibleItems: 3
                    }
                }
            });

        });
    </script>
    <script type="text/javascript" src="js/jquery.flexisel.js"></script>
</div>--%>
<jsp:include page="../common/foot.jsp"/>
</body>
</html>
