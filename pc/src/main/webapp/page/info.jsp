<%--
  Created by IntelliJ IDEA.
  User: Bruce-Jiang
  Date: 2016/10/8
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>答题说明</title>
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
<body>
<!-- Begin page content -->
<div class="container" align="center">
    <fieldset>
        <div class="page-header" align="center">
            <h1>答题说明</h1>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div class="col-xs-10" align="center">
                    <ol style="text-align: center;align:center;">
                        <li></li>
                        <li></li>
                    </ol>
                    <form id="Qform" method="post" action="../eqs" style="text-align: center; align:center;">
                        <div class="form-group">
                            <label for="rank" class="control-label col-xs-2"></label>
                            <select id="rank" name="rank" class="selectpicker show-tick form-control" >
                                <option value="">...</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">综合</option>
                            </select>
                        </div>
                        <button class="btn btn-primary" onclick="return Qsub();">同意,确认开始</button>
                    </form>
                </div> <!--/.col-sx-10-->
            </div><!-- /.col-sx-12-->
        </div><!-- /.row-->
    </fieldset>

    <script type="text/javascript">
        function Qsub(){
            //alert("OK");
            var rank  = document.getElementById("rank");
           // alert(rank.value);
            if(rank.value == null || rank.value == ""){
                alert("请选择难度等级！");
                document.getElementById("rank").focus();
                return false;
            }else {
                document.getElementById("Qform").submit();
            }
            return true;
        }
    </script>
</div>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</body>
</html>
