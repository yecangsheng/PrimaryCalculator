var height = 250,width = 250;
var rankValue = document.getElementById("rank").getAttribute("value");

var options = {
  ease: "bounce",
  height: height,
  width: width,
  start:{
    fill:'red',
    innerRatio:.7,
    outerRatio:1,
  },
  finish: {
    fill:'green',
    innerRatio:.7,
    outerRatio:1,
  },
  values: {
    classes: "init-values",
    styles: "text-anchor:middle;font-size:300%;font-weight:bolder;",
  },
};

var data = [{
  immediate: {
    angle: true
  },
  start: {
    angle: 1,
    fill: '#ddd'
  },
  finish: {
    angle: 0,
    fill: '#ddd'
  }
}, {
  values: {
    show: true,
    styles: "text-anchor:middle;font-weight:bolder",
    decorate:function (d) {
      return d3.format("%")(d/100);
    }
  },
  start: {
    angle: 0,
  },
  finish: {
    angle: 1,
  }
}];

var dt = new DashTimer('#timer');

new Promise(function(resolve, reject) {
  dt.init(options).setData(data);
}).then(d3.select(".init-values").attr('dy',height * 1/25))
  .then(dt.start(1500,0,1 - parseInt(rankValue)));

d3.select('.center')
  .style("margin","0")
  .style("width","100%")
  .style("text-align","center")
  .style("font-weight", "normal");
d3.select('#start').on('click', function(d) {
  dt.start(1500,0,1 - parseInt(rankValue));
}).style("text-align","center")
  .style("margin-bottom","130px");
