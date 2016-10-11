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
<body onload="startTimer()">
<div class="container center">
    <div class="row">
        <div class="col-xs-8">
            <div class="widget-box">
                <div class="widget-header">
                    <h4>答题版</h4>
                </div>
                <div class="widget-body">
                    <div class="widget-main no-padding">
                        <!-- TIMER BEGINS-->
                        <div id="timeDiv">
                            <button type="button" onclick="timer('ptime')">
                                开始计时
                            </button>
                            <p id="ptime"></p>
                        </div>
                        <script type="text/javascript">
                            var tFlag = 0;
                            var tPass = 0;
                            function timer(id) {
                                if (tFlag != 0) {
                                    var tNew = new Date().getTime();
                                    tPass = tPass + (tNew - tFlag);
                                    tFlag = tNew;

                                } else {
                                    tFlag = new Date().getTime();
                                }
                                setTimeout("timer('" + id + "')", 100);
                                var ml = tPass % 1000;
                                var sc = Math.floor((tPass / 1000) % 60);
                                var mi = Math.floor((tPass / 1000 / 60) % 60);
                                var hr = Math.floor((tPass / 1000 / 60 / 60) % 24);
                                var dy = Math.floor(tPass / 1000 / 60 / 60 / 24);
                                var info = dy + "天" + hr + "时" + mi + "分" + sc + "秒" + ml + "毫秒";
                                document.getElementById(id).innerHTML = info;
                            }
                        </script>
                        <!-- TIMER ENDS-->
                        <form class="form-horizontal" id="form" method="post" action="../rws">
                            <!-- <legend>Form</legend> -->
                            <input type="text" id="e_id" name="e_id" value="${exp.id}" hidden="true"/>
                            <fieldset>
                                <div class="form-group">
                                    <label for="exp" class="col-sm-2 control-label">题目:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="exp" name="exp" value="${exp.eExpre}" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="result" class="col-sm-2 control-label">结果:</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="result" name="result" value="" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="tip" class="col-sm-2 control-label">提示:</label>
                                    <div class="col-sm-10">
                                        <textarea id="tip" name="tip" class="form-control"></textarea>
                                    </div>
                                </div>
                                <div class="form-group" id="show" hidden="true">
                                    <label for="status" class="col-sm-2 control-label">答题状态:</label>
                                    <div class="col-sm-3">
                                        <input id="status" name="status" type="text" value="">
                                    </div>
                                    <div class="col-sm-2"></div>
                                    <label for="pResult" class="col-sm-2 control-label">预期结果:</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="pResult" name="pResult" value="${exp.geteResult()}" />
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
                                               // alert(result.value);
                                                //2.再将系统给出的答案给到这里
                                                var pResult = document.getElementById("pResult");
                                                //alert(pResult.value);
                                                //3.比较两个两个值，给出判定状态，同时将系统给出的答案输出的界面

                                                if(result.value == pResult.value){
                                                    $("#status").attr("value","正确");
                                                }else{
                                                    $("#status").attr("value","错误");
                                                }
                                            }
                                            function showStatus(){
                                                status();
                                                // alert("OK");
                                                $("#show").attr("hidden",false);
                                                // alert("Yes");
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
                        </form><!-- /.form-->
                    </div><!-- /.widget-main -->
                </div><!-- /.widget-body -->

                <script type="text/javascript">
                    //开启计时器，
                    function startTimer(){
                        alert("开始答题，计时开始");
                    }
                </script>
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
