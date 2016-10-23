<%--
  Created by IntelliJ IDEA.
  User: yufujia
  Date: 2016/10/22
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>饼状图</title>
</head>
<script src="<%=request.getContextPath()%>/Statistics/third-part-js/d3.v3.min.js" charset="utf-8"></script>
<style>
    .tooltip{
        font-family: simsun;
        font-size: 14px;
        width: 120;
        height: auto;
        position: absolute;
        text-align: center;
        border-style: solid;
        border-width: 1px;
        background-color: white;
        border-radius: 5px;
    }

</style>
<body>
<div class="center">
    <h2 style="font-weight:normal">学生平均成绩统计</h2>
    <form action="http://localhost:8080/Statistics" method="get">
        <select name = "rank">
            <option value ="1">等级A</option>
            <option value ="2">等级B</option>
            <option value="3">等级C</option>
            <option value="4">等级D</option>
        </select>
        <input type="submit"/>
    </form>
    <div id="chart" ></div>
</div>
<div style="">
    <input type ="text" id ="pie"  style= "display: none" value = "<% out.print(application.getAttribute("pie"));%>"/>
</div>
<script src="<%=request.getContextPath()%>/Statistics/js/pie.js" charset="utf-8"> </script>

</body>
</html>
