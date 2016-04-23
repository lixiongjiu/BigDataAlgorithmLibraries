<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/head.jsp"/>
<html>
<head>
    <title>远程终端</title>
    <script type="text/javascript">
        function getStdout(cmd) {
            $("#shell").append('<font color="aqua"><<&nbsp;' + cmd + '<br/></font>');
            $.ajax({
                type: "POST",
                url: "/run/shell",
                data: {cmd: cmd},
                success: function (data) {
                    if (data == 'ok') {
                        var flag = 1;
                        while (flag == 1) {
                            $.ajax({
                                url: '/run/status',
                                type: 'GET',
                                dataType: 'json',
                                async: false,
                                success: function (data) {
                                    var result = eval(data);
                                    if (result.log == 'end') {
                                        $("#shell").append('>>&nbsp;END<br/>');
                                        flag = 0;
                                    }
                                    else if (result.log == 'err') {
                                        $("#shell").append('>>&nbsp;' + "运行出错<br/>");
                                        flag = 0;
                                    }
                                    else
                                        $("#shell").append('>>&nbsp;' + result.log + '<br/>');
                                },
                                err: function () {
                                    $("#shell").append('>>&nbsp;' + "运行出错<br/>");
                                    flag = 0;
                                }
                            });
                        }
                    } else {
                        $("#shell").append('>>' + '执行命令时出错:' + data);
                    }
                }
            });
        }

        $(document).ready(function () {
            /*让滚动条始终在底部*/

            /*清空终端*/
            $("#clean").click(function () {
                /*alert("测试清空终端");*/
                if (confirm("确认要清空shell的内容吗？"))
                    $("#shell").html('<font color="#87ceeb"><<([STDIN])表示输入的命令,&nbsp;>>([STDOUT])表示终端输出<br/>'+
                '输入命令后可以按回车确认，也可以按确认键....<br/><br/></font>');
            });
            /*回车输入命令*/
            $("#cmd").keydown(function (event) {
                if (event.keyCode == "13") {
                    var cmd = $("#cmd").val();
                    if (cmd != '')
                        getStdout(cmd);
                    else
                        alert("命令不能为空，请输入命令!");
                }
            });
            /* 按确认键输入命令*/
            $("#submit").click(function () {
                var cmd = $("#cmd").val();
                if (cmd != '') {
                    /* alert(cmd);*/
                    getStdout(cmd);
                }
                else
                    alert("命令不能为空，请输入命令!");
            });
            /*清空输入的命令*/
            $("#reset").click(function () {
                /*alert("已经触发清空按钮");*/
                $("#cmd").val('');
            });
        });
    </script>
</head>
<body>

<div class="banner-middle">
    <h2 class="page-header text-center"><font color="black">远程终端</font></h2>
    <div class="shell-window" id="shell">
        <font color="#87ceeb"><<([STDIN])表示输入的命令,&nbsp;>>([STDOUT])表示终端输出<br/>
        输入命令后可以按回车确认，也可以按确认键....<br/><br/></font>

    </div>

    <div class="row text-center">
        <input type="text" placeholder="在这里输入命令" class="form-control" id="cmd">
        <button type="button" id="submit" class="acount-btn">确认</button>
        <button type="button" id="reset" class="acount-btn">清空命令</button>
        <button type="button" id="clean" class="acount-btn">清空终端</button>
    </div>
</div>

</body>
<jsp:include page="../common/foot.jsp"/>
</html>
