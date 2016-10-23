<%--
  Created by IntelliJ IDEA.
  User: yufujia
  Date: 2016/10/22
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Title</title>
</head>
<body style="TEXT-ALIGN: center;">
    <div style = "">
        <h1 style="font-family:sans-serif;margin:20px;font-size:150%;letter-spacing:3px;">
            学生成绩总体信息
        </h1>
    </div>
    <div style = "margin-top:10px;float:left">
        <div >
            <%@include file="pie.jsp" %>
        </div>
        <div >
            <%@include file="rank.jsp" %>
        </div>
    </div>

</body>
</html>
