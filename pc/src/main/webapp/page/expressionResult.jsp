<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>做题结果</title>
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

    <!-- fonts -->

    <link rel="stylesheet"
          href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

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
<div class="row">
    <div class="col-xs-12">
        <div class="table-header">做题记录表</div>
        <form>
            <div class="table-responsive">
                <table id="sample-table-2"
                       class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th class="center"><label> <input id="checkNum"
                                                          type="checkbox" class="ace" /> <span class="lbl"></span>
                        </label></th>
                        <th>#</th>
                        <th>计算表达式</th>
                        <th>正确答案</th>
                        <th class="hidden-480">你的答案</th>
                        <th class="hidden-480">答题状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="center"><label><input
                                type="checkbox" class="ace" /><span class="lbl"></span></label>
                        </td>
                        <td>1</td>
                        <td><a href="#">app.com</a></td>
                        <td>$45</td>
                        <td class="hidden-480">3,330</td>
                        <td class="hidden-480"><span
                                class="label label-sm label-warning">Expiring</span></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </div>
</div>
<!-- basic scripts -->

<!--[if !IE]> -->

<script
        src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
    window.jQuery
    || document
            .write("<script src='assets/js/jquery-2.0.3.min.js'>"
                    + "<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document)
        document
                .write("<script src='assets/js/jquery.mobile.custom.min.js'>"
                        + "<"+"/script>");
</script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>

<!-- ace scripts -->

<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($) {
        var oTable1 = $('#sample-table-2').dataTable({
            "aoColumns" : [ {
                "bSortable" : false
            }, null, null, null, null, {
                "bSortable" : false
            } ]
        });

        $('table th input:checkbox').on('click',function() {
            var that = this;
            $(this).closest('table').find('tr > td:first-child input:checkbox').each(function() {
                this.checked = that.checked;
                $(this).closest('tr').toggleClass('selected');
            });
        });
    });
</script>
</body>
</html>
