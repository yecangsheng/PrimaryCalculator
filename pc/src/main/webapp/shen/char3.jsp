<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="echarts.min.js"></script>
    <script src="<%=request.getContextPath()%>/shen/resource/echarts.min.js"></script>
</head>
<body>
<div style = "margin-bottom: 20px">
    <div style = "float: left; height:8px;">
        级别:<select style = "height:24px" id = 'rank3'>
        <option value ="1">A</option>
        <option value ="2">B</option>
        <option value="3">C</option>
        <option value="4">D</option>
    </select>
    </div>
    <div style = "margin-left:20px;float: left; ">
        间隔:<select style = "height:24px" id = 'period3'>
        <option value ="5">5</option>
        <option value ="7">7</option>
        <option value="10">10</option>
        <option value="30">30</option>
    </select>

    </div>
    <div style = "margin-left:20px;float: left;">
        开始日期:<input type = 'date' id = 'begintime3'/>
    </div>
    <div style = "margin-left:20px;float: left;">
        结束日期:<input type = 'date' id = 'endtime3'/>
    </div>

    <div>
        &nbsp<button type = 'submit' id = 'check3' >查询</button>
    </div>
</div>

<!--   下面是图表的展示控件-->
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main3" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart3 = echarts.init(document.getElementById('main3'));
    myChart3.setOption({
        title: {
            text: '小学生成绩稳定性（方差）',
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
            data:[]
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
                data:[],
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
    });
    // 异步加载数据
    //$(function(){
        $("#check3").click(function(){
            var rank3 = $("#rank3").val();
            var period3 = $("#period3").val();
            var begintime3  = $("#begintime3").val();
            var endtime3 = $("#endtime3").val();
            if(begintime3 == ""|| endtime3 ==""){
                alert("开始日期或结束日期不能为空");
                return ;
            }
            var url = "<%=request.getContextPath()%>/GetScore3";
            var args = {"time":new Date(),
                "rank3":rank3,
                "period3":period3,
                "begintime3":begintime3,
                "endtime3":endtime3
            };
            $.get(url,args,function (data ){
                var jasonData = eval(data);
                var periodArray = new Array();
                var scoreArray = new Array();
                for(var i=0; i<jasonData.length; i++){
                    periodArray.push(jasonData[i].period);
                    scoreArray.push(jasonData[i].score);
                }
                myChart3.setOption({
                    xAxis: {
                        // data: data.categories
                        data:periodArray
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '分数',
                        //data: data.data
                        data:scoreArray
                    }]
                });
            });
        });
    //});

</script>
</body>
</html>