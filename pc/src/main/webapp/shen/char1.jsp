<%--
  Created by IntelliJ IDEA.
  User: shen
  Date: 2016/10/18
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>shenxingbo</title>
    <script src="<%=request.getContextPath()%>/shen/resource/echarts.min.js"></script>
</head>
<body>
<%
    request.getAttribute("result");
%>
<div id="main" style="width: 600px;height:400px;">
    <script type="text/javascript">
        //将的到的jason数据变成2个数组
        var jasonData = <%out.print(request.getAttribute("result"));%>
        var periodArray = new Array();
        var scoreArray = new Array();
        for(var i=0; i<jasonData.length; i++){
            periodArray.push(jasonData[i].period);
            scoreArray.push(jasonData[i].score);
        }
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '小学生成绩历史',
                subtext: '纯属虚构 ^_^'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['分数']
            },
            toolbox: {
                show: true,
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    dataView: {readOnly: false},
                    magicType: {type: ['line', 'bar']},
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis:  {
                type: 'category',
                boundaryGap: false,
                data:periodArray,
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    formatter: '{value} 分'
                }
            },
            series: [
                {
                    name:'分数',
                    type:'line',
                    data:scoreArray,
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'}
                        ]
                    },
                    markLine: {
                        data: [
                            {type: 'average', name: '平均值'}
                        ]
                    }
                }
            ]
        }; // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</div>
</body>
</html>
