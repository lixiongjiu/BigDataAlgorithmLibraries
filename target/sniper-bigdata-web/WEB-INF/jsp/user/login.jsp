<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<jsp:include page="../common/head.jsp"/>
<head>
    <title>登录</title>
    <script type="text/javascript">
        /*刷新验证码js*/
        function refrush() {
            $("#validimg").attr("src", "/valid/code?rand" + Math.random());
        }
        function search() {
            var keyWord = $("#kw").val();
            alert(keyWord);
        }
        function overlogin(){
            if( ${userName!=null} ){
                var res=confirm("您已经登录，确认要再次登录吗？");
                if(res==true)
                    return true;
                else {
                    return false;
                }
            }
        }
    </script>
</head>
<body>
<div class="banner-middle">

    <div class="login-page">
        <div class="account_grid">
            <div class="col-md-6 login-left wow fadeInLeft" data-wow-delay="0.4s">
                <h3>新用户？</h3>

                <p>注册后，您会享受更多的服务，包括上传算法，运行，远程控制Hadoop服务器集群；<br/>
                    同时您还可以拥有个人地带，可以查看使用记录......
                    还等什么，赶快注册吧！</p>
                <a class="acount-btn" href="/user/register">去注册</a>
            </div>
            <div class="col-md-4 login-right wow fadeInRight" data-wow-delay="0.4s">
                <h3>已注册账户登录</h3>

                <p>如果您已经是注册账户，请登录</p>

                <sf:form method="post" modelAttribute="user" cssClass="form-horizontal" onsubmit="return overlogin()">
                    <div class="form-group">
                        <span style="color:red;">${result}</span>
                        <sf:input path="userName" cssClass="form-control" placeholder="请输入用户名"/>
                        <sf:errors path="userName"/>
                    </div>

                    <div class="form-group">

                        <sf:password path="password" cssClass="form-control" placeholder="请输入密码"/>
                        <sf:errors path="password"/>

                    </div>

                    <div class="form-group">
                        <div class="col-md-4">
                            <sf:input placeholder="请输入验证码" path="validCode" cssClass="form-control"/>
                            <sf:errors path="validCode"/>
                        </div>
                        <div class="col-md-4">
                            <img src="/valid/code" height="32" width="120"
                                 title="看不清楚？换一张" id="validimg"
                                 onclick='this.src="/valid/code?rnd="+Math.random();'/>
                        </div>
                        <div class="col-md-4">
                            <a href="javascript:refrush()">
                                <span class="glyphicon glyphicon-refresh">看不清楚？换一张</span>
                            </a>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-3"></div>
                        <div class="col-md-6">
                            <button type="reset" class="acount-btn">
                                    <%--<span class="glyphicon glyphicon-remove"> </span>--%>重置
                            </button>
                            <button type="submit" class="acount-btn"">
                                    <%--<span class="glyphicon glyphicon-log-in"> </span>--%>登录
                            </button>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                </sf:form>
            </div>
            <div class="clearfix"></div>
        </div>

    </div>
</div>
<jsp:include page="../common/foot.jsp"/>
</body>
</html>
