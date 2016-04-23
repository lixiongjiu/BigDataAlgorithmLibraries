<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="../common/head.jsp"/>
<head>
    <title>注册</title>
    <script type="text/javascript">
        /*刷新验证码js*/
        function refrush() {
            $("#validimg").attr("src", "/valid/code?rand=" + Math.random());
        }
        function pw_check(ele) {
            if (ele.text() != document.getElementById("pw1").textContent()) {
                alert("检查不通过");
                $("#p_check").text("确认密码与原密码必须保持一致");
            }
        }
        function re_register(){
            if ( ${userName!=null} ) {
                if ( confirm('${userName}'+ ",您好！您目前已经在线，需要再次注册吗？") ) {
                    return true;
                }
                else
                    return false;
            }
            else
                return true;
        }
        $(document).ready(function () {
            /*用户名输入完毕时，进行ajax异步查询，验证用户名是否已经存在*/
            $("#userName").change(function () {
                if ($("#u_check").html()) {
                    $("#u_check").hide();
                }
                $.post("validate",
                        {userName: $(this).val()},
                        function (data) {
                            if (data.response) {
                                $("#u_check").show();
                                $("#u_check").text(data.response);
                            }
                            else {
                                $("#u_check").show();
                                $("#u_check").html('<img src="/resources/img/ok.jpg " height="20px" width="20px">用户名合法');
                            }
                        }
                );

            });

        });
    </script>
</head>
<body>

<div class="banner-middle">

    <!-- registration -->
    <div class="login-page">
        <div class="account_grid">
            <div class="col-md-6 login-left wow fadeInLeft" data-wow-delay="0.4s">
                <h3>已注册？</h3>
                <a class="acount-btn" href="/user/login">去登录</a>
                <br/>
                <br/>

                <p>注册后，您会享受更多的服务，包括上传算法，运行，远程控制Hadoop服务器集群；<br/>
                    同时您还可以拥有个人地带，可以查看使用记录......
                    还等什么，赶快注册吧！</p>

            </div>
            <div class="col-md-5 login-right wow fadeInRight" data-wow-delay="0.4s">


                <h3><%--<span class="glyphicon glyphicon-registration-mark"></span>--%>用户注册</h3>
                <sf:form modelAttribute="user" method="post" cssClass="form-horizontal" onsubmit="return re_register()">
                    <div class="form-group">
                        <sf:input path="userName" cssClass="form-control" placeholder="请输入用户名"/>
                        <p id="u_check"></p>
                        <sf:errors path="userName"/>
                    </div>
                    <div class="form-group">

                        <input id="pw1" type="password" placeholder="请输入密码" class="form-control"/>
                        <sf:errors path="password"/>

                    </div>

                    <div class="form-group">

                        <sf:password path="password" cssClass="form-control" placeholder="请再次输入密码"/>
                        <p id="p_check"></p>

                    </div>
                    <div class="form-group">

                        <div class="col-md-4">
                            <sf:input placeholder="请输入验证码" path="validCode" cssClass="form-control"/>
                            <sf:errors path="validCode"/>
                        </div>

                        <div class="col-md-4">
                                <%--在这里添加图片超链接--%>
                            <img src="/valid/code" title="看不清？换一张" height="32" width="120"
                                 id="validimg"
                                 onclick="this.src='/valid/code?rand='+Math.random();"/>
                        </div>

                        <div class="col-md-4">
                            <a href="javascript:refrush()">
                                <span class="glyphicon glyphicon-refresh">看不清？换一张</span>
                            </a>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <button type="reset" class="acount-btn">
                                    <%--<span class="glyphicon glyphicon-remove"> </span>--%>重置
                            </button>
                            <button type="submit" class="acount-btn">
                                    <%--<span class="glyphicon glyphicon-log-in"> </span>--%>注册
                            </button>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                </sf:form>
            </div>
            <div class="clearfix"></div>
        </div>

    </div>
    <!-- registration -->

</div>
<jsp:include page="../common/foot.jsp"/>
</body>
</html>
