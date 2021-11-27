<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员注册页面</title>
    <!--base标签一般写在标题下面 -->
    <!--永远固定相对路径的跳转结果-->
 <%@include file="/pages/common/head.jsp"%>

    <script>
        //页面加载完成
        $(function () {
            //验证码单击事件
            $("#code_img").click(function (){
                //在事件响应的function函数中有一个this对象。这个this对象正是当前正在相响事件的dom对象
                //src属性表示验证码img标签 图片路径。 他可读 可写
                <%--alter(${basePath});--%>
                this.src="${basePath}/kaptcha.jpg"+new Date();//new Date()防止缓存每次都不一样
            })
            //给注册绑定单击事件
            $("#sub_btn").click(function () {
//验证用户名：必须由字母数字下划线组成，并且长度位5到12位
                //1.获取用户输入框内容
                var usernameTest = $("#username").val();
                //2.创建正则表达式
                var usernamePatt = /^\w{5,12}$/;
                //3.使用test方法验证
                if (!usernamePatt.test(usernameTest)) {
                    //4.提示用户结果
                    $("span.errorMsg").text("用户名不合法！");
                    return false;
                }
//验证密码：必须由字母数字下划线组成，并且长度位5到12位
                //1.获取用户输入框内容
                var passwordText = $("#password").val();
                //2.创建正则表达式
                var passwordPatt = /^\w{5,12}$/;
                //3.使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    //4.提示用户结果
                    $("span.errorMsg").text("密码不合法！");
                    return false;
                }
//验证确认密码：和密码相同
                //1.获取确认密码输入框内容
                var repwdPatt = $("#repwd").val();
                //2.使用test方法验证
                if (repwdPatt != passwordText) {
                    //3.提示用户结果
                    $("span.errorMsg").text("确认密码与原密码不一致！");
                    return false;
                }
//邮箱验证：xxxxx@xxxx.com
                //1.获取邮箱输入框内容
                var emailTest = $("#email").val();
                //2.创建正则表达式
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3.使用test方法验证
                if (!emailPatt.test(emailTest)) {
                    //4.提示用户结果
                    $("span.errorMsg").text("邮箱不合法！");
                    return false;
                }
// 验证码：现在只需要验证用户已输入
                //1.获取验证码输入框内容
                var codeTest = $("#code").val();
                //2.验证是否正确
                if (codeTest.trim() == "") {
                    $("span.errorMsg").text("验证码错误！");
                    return false;
                }
                //将错误提示消去
                $("span.errorMsg").text();

            })
        })
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册尚硅谷会员</h1>
                    <span class="errorMsg">
<%--                    <%=request.getAttribute("msg")==null?"请填写信息":request.getAttribute("msg")%>--%>
                    ${empty requestScope.msg?"请填写信息":requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet"method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
<%--                               value="<%=request.getAttribute("msg")==null?"":request.getAttribute("username")%>"--%>
                               value="${requestScope.username}"

                               autocomplete="off" tabindex="1" name="username" id="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
<%--                               value="<%=request.getAttribute("password")==null?"":request.getAttribute("password")%>"--%>
                               value="${requestScope.password}"
                        "
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
<%--                               value="<%=request.getAttribute("password")==null?"":request.getAttribute("password")%>"--%>
                               value="${requestScope.password}"

                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
<%--                               value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"--%>
                               value="${requestScope.email}"

                               name="email" id="email"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" name="code" type="text" style="width: 100px;" id="code"
<%--                        value="<%=request.getAttribute("msg")==null?"":request.getAttribute("code")%>"--%>
                               value="${requestScope.code}"
                        /> <img id="code_img" alt="" src="kaptcha.jpg" style="float: right; margin-right:30px;width:125px;height: 40px">

                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<div id="bottom">
    <%--		页尾展示--%>
    <%@include file="/pages/common/footer.jsp"%>

</div>
</body>
</html>