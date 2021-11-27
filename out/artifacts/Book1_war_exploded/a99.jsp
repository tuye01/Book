<%--
  Created by IntelliJ IDEA.
  User: 才振超
  Date: 2021/11/16
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">九九乘法表</h1>
<table align="center" border="0px" cellspacing="0" >
    <% for (int i = 1; i < 10; i++) {%>
    <tr>
        <% for (int j = 1; j <= i; j++) {%>
        <td>
            <%=i + " X " + j + " = " + i * j + "&nbsp" + "&nbsp"%>
        </td>
        <%}%><br/>
    </tr>
    <%}%>

</table>

</body>
</html>
