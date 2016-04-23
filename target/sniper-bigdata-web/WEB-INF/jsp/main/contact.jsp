<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>联系我</title>
</head>
<body>

</body>
</html>
<jsp:include page="../common/head.jsp"></jsp:include>
<div class="banner-middle">
    <!-- contact -->
    <div class="contact">
        <h2>我的位置</h2>

        <div class="map">
            <iframe src="../../../map.html" frameborder="0" style="border:0"></iframe>
            <%--</div>--%>
            <div class="mail-info-grids">
                <div class="col-md-6 mail-info-grid">
                    <h3>联系信息</h3>

                    <p>联系我的最佳方式是留言给我
                    </p>
                    <h6>辽宁大连.
                        <span>大连理工大学开发区校区,</span>
                        开发区湾里街道.116620
                    </h6>

                    <p>Telephone: +86 18840869229
                        <span>FAX: .......</span>
                        E-mail: lixiongjiu@foxmail.com
                    </p>
                </div>
                <div class="col-md-6 contact-form">
                    <form>
                        <input type="text" placeholder="名字" required="">
                        <input type="text" placeholder="邮箱地址" required="">
                        <input type="text" placeholder="邮件主题" required="">
                        <textarea placeholder="正文" required></textarea>
                        <input type="submit" value="SEND">
                    </form>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../common/foot.jsp"></jsp:include>
