var width = 700;
var height = 500;
var dataset = [ ["0~60",30] , ["61~70",10] , ["71~80",43] , ["81~90",55] , ["91~100",13] ];

var svg = d3.select("#chart")
			.append("svg")
			.attr("width", width)
			.attr("height", height);

// svg.append("text").attr("dx",width*5/13).attr("dy", height/20).text("学生平均成绩情况");

var pie = d3.layout.pie()
					.value(function(d){
                        return d[1];
                    });
pie.padAngle(0.01);

var piedata = pie(dataset);
		
var outerRadius = 150;	//外半径
var innerRadius = 10;	//内半径，为0则中间没有空白

var arc = d3.svg.arc()	//弧生成器
			.innerRadius(innerRadius)	//设置内半径
			.outerRadius(outerRadius);	//设置外半径
		
var color = d3.scale.category10();
var drag = d3.behavior.drag()			//定义拖拽行为
				.origin(function(d) { return d; })
				.on("dragend", comeback)
				.on("drag", dragmove);

/**
* 初始化svg，设定圆心位置
*/
var arcs = svg.selectAll("g")
			  .data(piedata)
			  .enter()
			  .append("g")
			  .attr("transform","translate("+ (width/2) +","+ (height/2) +")")
			  .each(function(d){
					d.dx = width/2;
					d.dy = height/2;
				})
				.call(drag);

/**
* 绘制弧度，并填充颜色
* 鼠标悬停透明显示，移开恢复
*/				  
arcs.append("path")
	.attr("fill",function(d,i){
		return color(i);
	})
	.attr("d",function(d){
		return arc(d);
	}).on("mouseover", function(d,i) {
		d3.select(this)
			.attr("opacity", "0.7");
	}).on("mouseout", function() {
		d3.select(this)
			.attr("opacity", "1");
	});

/**
* 设定每个圆弧上的文字
*/	
arcs.append("text")
	.attr("transform",function(d){
		return "translate(" + arc.centroid(d) + ")";
	})
	.attr("text-anchor","middle")
	.style("font-size","18px").style("fill","white")
	.text(function(d){
		return Number(d.value);
	});

/**
* 初始化提示框，透明度为0
*/
var tooltip = d3.select("#chart")
					.append("div")
					.attr("class","tooltip")
					.style("opacity",0.0);

arcs.on("mouseover",function(d){
		/*
		鼠标移入时，
		（1）通过 selection.html() 来更改提示框的文字
		（2）通过更改样式 left 和 top 来设定提示框的位置
		（3）设定提示框的透明度为1.0（完全不透明）
		*/
		var percent = Number(d.value)/d3.sum(dataset,function(d){ return d[1]; }) * 100;
		tooltip.html(d.data[0] + "分的人数" + "<br />占" + percent.toFixed(1) + "%")
			.style("left", (d3.event.pageX) + "px")
			.style("top", (d3.event.pageY + 20) + "px")
			.style("opacity",1.0);
		})
		.on("mousemove",function(d){
		/* 鼠标移动时，更改样式 left 和 top 来改变提示框的位置 */

			tooltip.style("left", (d3.event.pageX) + "px")
				.style("top", (d3.event.pageY + 20) + "px");
		})
		.on("mouseout",function(d){
		/* 鼠标移出时，将透明度设定为0.0（完全透明）*/

			tooltip.style("opacity",0.0);
		});

function dragmove(d) {			//定义拖拽函数
	d.dx += d3.event.dx;
	d.dy += d3.event.dy;
	// var size = d3.select(this).select("text").style("font-size");
	d3.select(this)
		.transition()
		.duration(200)
		.ease("linear")
		.attr("transform","translate("+d.dx+","+d.dy+")")
		.select("text")
		.styleTween("font-size", function(d,i,a) {
			return d3.interpolate(a, String("32px"));
		});
}	  

function comeback(d) {			//定义拖拽完成后的动作
	d.dx = width/2;
	d.dy = height/2;

	console.log(d.dx);
	console.log(d.dy);

	d3.select(this)
		.transition()
		.duration(1000)
		.ease("elastic")
		.attr("transform","translate("+d.dx+","+d.dy+")")
		.select("text")
		.styleTween("font-size", function(d,i,a) {
			return d3.interpolate(a, String("18px"));
		});
}

d3.select('.center')
  .style("margin","0")
  .style("width","80%")
  .style("text-align","center");