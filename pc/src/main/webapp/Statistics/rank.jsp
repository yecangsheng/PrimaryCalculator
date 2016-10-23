<%--
  Created by IntelliJ IDEA.
  User: yufujia
  Date: 2016/10/22
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>D3.js circular progress timer</title>
</head>
<link href="<%=request.getContextPath()%>/Statistics/third-part-css/mui.min.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath()%>/Statistics/third-part-js/es6-promise.min.js"></script>
<script src="<%=request.getContextPath()%>/Statistics/third-part-js/d3.v3.min.js"></script>
<script src="<%=request.getContextPath()%>/Statistics/third-part-js/mui.min.js"></script>
<script src="<%=request.getContextPath()%>/Statistics/third-part-js/dashtimer.js"></script>
<body>

<div class="center">
    <h2 style="padding-top:19px;margin-bottom:10px">你的综合排名为：</h2>
    <button id="start" >Refresh</button>
    <div id="timer" ></div>
</div>
<input type ="text" id ="rank"  style= "display: none" value = "<% out.print(application.getAttribute("rank"));%>"/>
<script src="<%=request.getContextPath()%>/Statistics/js/rank.js"></script>
</body>
</html>
