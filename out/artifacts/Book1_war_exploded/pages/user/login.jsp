<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员登录页面</title>
    <!--永远固定相对路径的跳转结果-->
    <%@include file="/pages/common/head.jsp" %>

</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>尚硅谷会员</h1>
                    <a href="regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
<%--                        <%=request.getAttribute("msg") == null ? "请输入用户名和密码:" : request.getAttribute("msg")%>--%>
<%--					    获取（对象）的内容--%>
                        ${empty requestScope.msg?"请输入用户名和密码:":requestScope.msg}
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="login">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                        <%--value="<%=request.getAttribute("msg")==null?"":request.getAttribute("username")%>"--%>
                               value="${requestScope.username}"
                               autocomplete="off" tabindex="1" name="username"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
<%--                               value="<%=request.getAttribute("msg")==null?"":request.getAttribute("password")%>"--%>
                               value="${requestScope.password}"

                               autocomplete="off" tabindex="1" name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<div id="bottom">
    <%--		页尾展示--%>
    <%@include file="/pages/common/footer.jsp" %>
</div>
</body>
</html>