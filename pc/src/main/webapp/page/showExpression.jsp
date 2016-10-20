<%--
  Created by IntelliJ IDEA.
  User: Bruce-Jiang
  Date: 2016/10/5
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title></title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- basic styles -->

    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="assets/css/font-awesome.min.css" />

    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
    <![endif]-->

    <!-- page specific plugin styles -->

    <link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.custom.min.css" />
    <link rel="stylesheet" href="assets/css/chosen.css" />
    <link rel="stylesheet" href="assets/css/datepicker.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-timepicker.css" />
    <link rel="stylesheet" href="assets/css/daterangepicker.css" />
    <link rel="stylesheet" href="assets/css/colorpicker.css" />

    <!-- fonts -->

    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

    <!-- ace styles -->

    <link rel="stylesheet" href="assets/css/ace.min.css" />
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
    <link rel="stylesheet" href="assets/css/ace-skins.min.css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->

    <script src="assets/js/ace-extra.min.js"></script>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="col-sm-offset-2"></div>
            <div class="col-xs-8">

                <script type="text/javascript">
                    $(document).ready(function (){
                        $("#marquee1").marquee();
                    });
                </script>
            </div>
        </div>
        <form class="form-horizontal" id="form" method="post" action="../rws">
            <div class="col-xs-3">
                <div class="form-group">
                    <label for="timer" class="col-sm-4 control-label">剩余时间</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="timer" name="timer" value="" readonly/>
                        <input type="text"  id="time" name="time" value="" style="display: none;"/>
                     </div>
                </div>
                <div class="form-group">
                    <label for="expNum" class="col-sm-3 control-label">第 &nbsp; &nbsp;</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="expNum" name="expNum" value="${expNum}" readonly>
                    </div>
                    <label for="expNum" class="col-sm-3 control-label">题 &nbsp; &nbsp;</label>
                </div>
            </div>

            <div class="col-xs-8">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4>答题板</h4>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                                <!-- <legend>Form</legend> -->
                                <input type="text" id="e_id" name="e_id" value="${exp.id}" hidden="true"/>
                                <fieldset>
                                    <div class="form-group">
                                        <label for="exp" class="col-sm-2 control-label">题目:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="exp" name="exp" value="${exp.eExpre}" readonly/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="result" class="col-sm-2 control-label">结果:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="result" name="result" value="" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="tip" class="col-sm-2 control-label">提示:</label>
                                        <div class="col-sm-8">
                                            <textarea id="tip" name="tip" class="form-control" readonly></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group" id="show" hidden="true">
                                        <label for="status" class="col-sm-2 control-label">答题状态:</label>
                                        <div class="col-sm-3">
                                            <input id="status" name="status" type="text" value="" readonly>
                                        </div>
                                        <div class="col-sm-2"></div>
                                        <label for="pResult" class="col-sm-2 control-label">正确结果:</label>
                                        <div class="col-sm-3">
                                            <input type="text" class="form-control" id="pResult" name="pResult" value="${exp.geteResult()}" readonly/>
                                        </div>
                                    </div>
                                </fieldset>
                                <div class="row">
                                    <div class="col-xs-6">
                                        <div class="form-actions left">
                                            <button type="button" class="btn btn-sm btn-success" onclick="showStatus()">
                                                查看结果
                                                <i class="icon-arrow-up icon-on-up bigger-110"></i>
                                            </button>
                                            <script style="text/javascript">
                                            /**
                                             * 单击查看该题结果按钮，此时显示该题的结果以及正误状态
                                             * 比较用户答案和系统答案
                                             */
                                                function status(){
                                                    //操作步骤
                                                    //1.首先取得用户给定的答案
                                                    var result = document.getElementById("result");
                                                    //alert(result.value);
                                                    //2.再将系统给出的答案给到这里
                                                    var pResult = document.getElementById("pResult");
                                                    //alert(result.value!=pResult.value);
                                                    //alert(pResult.value);
                                                    //3.比较两个两个值，给出判定状态，同时将系统给出的答案输出的界面

                                                    if(parseInt(result.value)==parseInt(pResult.value)
                                                            || parseFloat(result.value)==parseFloat(pResult.value)){
                                                       // alert("OK");
                                                        $("#status").attr("value","正确");
                                                    }else{
                                                       // alert("Not");
                                                        $("#status").attr("value","错误");
                                                    }
                                                }
                                                function showStatus(){
                                                   // alert("status");
                                                    var sta = $("#show").attr("hidden");
                                                    // alert(sta);
                                                    if(sta == "hidden") {
                                                        status();
                                                        // alert("OK");
                                                        $("#show").attr("hidden", false);
                                                        // alert("Yes");
                                                    }else{
                                                        $("#show").attr("hidden", true);
                                                    }
                                                }
                                            </script>
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="form-actions right">
                                            <button type="button" class="btn btn-sm btn-success" onclick="sub();">
                                                下一题
                                                <i class="icon-arrow-right icon-on-right bigger-110"></i>
                                            </button>
                                            <script type="text/javascript">
                                                function sub(){
                                                    status();
                                                    document.getElementById("form").submit();
                                                }
                                            </script>
                                        </div>
                                    </div>
                                </div><!-- /.row -->
                    </div><!-- /.widget-main -->
                </div><!-- /.widget-body -->

                <script type="text/javascript">
                    //开启计时器，
                    start();
                    /*主函数要使用的函数，进行声明*/
                    var clock=new clock();
                    /*指向计时器的指针*/
                    var timer;
                    function start(){
                        //alert("OK");
                        /*主函数就在每50秒调用1次clock函数中的move方法即可*/
                        timer=setInterval("clock.move()",1000);
                    }
                    function clock(){
                        /*s是clock()中的变量，非var那种全局变量，代表剩余秒数*/
                        this.s=${leftTime};
                        //alert(this.s);
                        this.move=function(){
                            /*输出前先调用exchange函数进行秒到分秒的转换，因为exchange并非在主函数window.onload使用，因此不需要进行声明*/
                            document.getElementById("timer").value=exchange(this.s);
                            document.getElementById("time").value =  this.s;
                            var curExpNum = document.getElementById("expNum");
                            //alert(curExpNum.value);
                            /*如果时间耗尽，那么，弹窗，使按钮不可用，停止不停调用clock函数中的move()*/
                            if(this.s<=0){
                                //alert("Ok");
                                //弹出模态框
                                $('#resultModal').modal({keyboard: false});
                            }
                            if(parseInt(curExpNum.value)>20){
                                $('#resultModal').modal({keyboard: false});
                            }
                            /*每被调用一次，剩余秒数就自减*/
                            this.s=this.s-1;
                        }
                    }
                    function exchange(time){
                        /*javascript的除法是浮点除法，必须使用Math.floor取其整数部分*/
                        this.m=Math.floor(time/60);
                        /*存在取余运算*/
                        this.s=(time%60);
                        this.text=this.m+"分"+this.s+"秒";
                        /*传过来的形式参数time不要使用this，而其余在本函数使用的变量则必须使用this*/
                        return this.text;
                    }
                </script>
            </div>
            </div>
        </form><!-- /.form-->
        <form method="post" action="../rqs" id="rForm">
        </form>
        <!-- Modal -->
        <div class="modal fade" id="resultModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">答题结束</h4>
                    </div>
                    <div class="modal-body">
                        查看本次答题记录？？
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="showResult()">查看</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="backToIndex()">下次吧！</button>
                    </div>
                    <script type="text/javascript">
                        function showResult(){ //查询记录
                            $("#rForm").submit();
                        }
                        function backToIndex(){
                            location.href = "index.jsp";
                        }
                    </script>
                </div>
            </div>
        </div>
    </div>
    <!-- basic scripts -->

    <!--[if !IE]> -->

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

    <!-- <![endif]-->

    <!--[if IE]>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <![endif]-->

    <!--[if !IE]> -->

    <script type="text/javascript">
        window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
    </script>

    <!-- <![endif]-->

    <!--[if IE]>
    <script type="text/javascript">
        window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
    </script>
    <![endif]-->

    <script type="text/javascript">
        if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>

    <!-- page specific plugin scripts -->

    <!--[if lte IE 8]>
    <script src="assets/js/excanvas.min.js"></script>
    <![endif]-->

    <script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="assets/js/jquery.ui.touch-punch.min.js"></script>
    <script src="assets/js/chosen.jquery.min.js"></script>
    <script src="assets/js/fuelux/fuelux.spinner.min.js"></script>
    <script src="assets/js/date-time/bootstrap-datepicker.min.js"></script>
    <script src="assets/js/date-time/bootstrap-timepicker.min.js"></script>
    <script src="assets/js/date-time/moment.min.js"></script>
    <script src="assets/js/date-time/daterangepicker.min.js"></script>
    <script src="assets/js/bootstrap-colorpicker.min.js"></script>
    <script src="assets/js/jquery.knob.min.js"></script>
    <script src="assets/js/jquery.autosize.min.js"></script>
    <script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
    <script src="assets/js/jquery.maskedinput.min.js"></script>
    <script src="assets/js/bootstrap-tag.min.js"></script>

    <!-- ace scripts -->

    <script src="assets/js/ace-elements.min.js"></script>
    <script src="assets/js/ace.min.js"></script>
</div>
</body>
</html>
