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
        级别:<select style = "height:24px" id = 'rank2'>
        <option value ="1">A</option>
        <option value ="2">B</option>
        <option value="3">C</option>
        <option value="4">D</option>
    </select>
    </div>
    <div style = "margin-left:20px;float: left; ">
        间隔:<select style = "height:24px" id = 'period2'>
        <option value ="5">5</option>
        <option value ="7">7</option>
        <option value="10">10</option>
        <option value="30">30</option>
    </select>

    </div>
    <div style = "margin-left:20px;float: left;">
        开始日期:<input type = 'date' id = 'begintime2'/>
    </div>
    <div style = "margin-left:20px;float: left;">
        结束日期:<input type = 'date' id = 'endtime2'/>
    </div>

    <div>
        &nbsp<button type = 'submit' id = 'check2' >查询</button>
    </div>
</div>

<!--   下面是图表的展示控件-->
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main2" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main2'));
    myChart.setOption({
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
    $(function(){
        $("#check2").click(function(){
            //首先获得参数
            var rank2 = $("#rank2").val();
            var period2 = $("#period2").val();
            var begintime2  = $("#begintime2").val();
            var endtime2 = $("#endtime2").val();
            if(begintime2 == ""|| endtime2 ==""){
                alert("开始日期或结束日期不能为空");
                return ;
            }
            //下面发起url请求
            var url = "<%=request.getContextPath()%>/GetScore2";
            var args = {
                "time":new Date(),
                "rank2":rank2,
                "period2":period2,
                "begintime2":begintime2,
                "endtime2":endtime2
            };
            $.get(url,args,function (data ){
                var jasonData = eval(data);
                var periodArray = new Array();
                var scoreArray = new Array();
                for(var i=0; i<jasonData.length; i++){
                    periodArray.push(jasonData[i].period);
                    scoreArray.push(jasonData[i].score);
                }
                myChart.setOption({
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
    });

</script>
</body>
</html>