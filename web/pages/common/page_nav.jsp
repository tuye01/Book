<%--
  Created by IntelliJ IDEA.
  User: 才振超
  Date: 2021/11/22
  Time: 1:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--分页开始--%>
<div id="page_nav">
    <%--        如果是首页不显示首页和上一页--%>
    <c:if test="${requestScope.page.pageNo>1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        <%--现在页数减一--%>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">${requestScope.page.pageNo-1}</a>
    </c:if>


    【${requestScope.page.pageNo}】

    <%--        如果是首页不显示末页和下一页--%>
    <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
        <%--现在页数加一--%>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">${requestScope.page.pageNo+1}</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value=${requestScope.page.pageNo} name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">

    <script type="text/javascript">
        $(function () {
            // 跳到指定的页码
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                var pageTotal =${requestScope.page.pageTotal};
                //js语言提供了一个localtion地址栏对象
                //有一个人属性href，可以获得浏览器中的地址
                //href属性用法
                if (pageNo <= pageTotal) {
                    location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
                } else {
                    location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageTotal;
                }

            })
        })
    </script>
</div>
