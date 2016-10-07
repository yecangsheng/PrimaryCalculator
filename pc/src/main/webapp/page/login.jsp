<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>登陆</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link href="page/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="page/assets/css/font-awesome.min.css" />
<link rel="stylesheet"
  href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
<link rel="stylesheet" href="page/assets/css/ace.min.css" />
<link rel="stylesheet" href="page/assets/css/ace-rtl.min.css" />
<script type="text/javascript" src="page/assets/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="page/assets/js/bootstrap.min.js"></script>
</head>

<body class="login-layout">
  <div class="main-container">
    <div class="main-content">
      <div class="row">
        <div class="col-sm-10 col-sm-offset-1">
          <div class="login-container">
            <div class="center">
              <h1>
                <i class="icon-leaf green"></i> <span class="red"></span>
                <span class="white">Application</span>
              </h1>
              <h4 class="blue">&copy; Bruce-Jiang</h4>
            </div>

            <div class="space-6"></div>

            <div class="position-relative">
              <div id="login-box"
                class="login-box visible widget-box no-border">
                <div class="widget-body">
                  <div class="widget-main">
                    <h4 class="header blue lighter bigger">
                      <i class="icon-coffee green"></i> 请输入登录信息
                    </h4>

                    <div class="space-6"></div>

                    <form id="fLogin" action="UserLoginServlet" method="post">
                      <fieldset>
                        <label class="block clearfix"> <span
                          class="block input-icon input-icon-right">
                            <input type="text" class="form-control"
                            placeholder="登陆邮箱" name="uMail" id="uMail"/> <i
                            class="icon-user"></i>
                        </span>
                        </label> <label class="block clearfix"> <span
                          class="block input-icon input-icon-right">
                            <input type="password" class="form-control"
                            placeholder="登陆密码" name="uPassword" id="uPassword"/> <i
                            class="icon-lock"></i>
                        </span>
                        </label>
                        <div class="space"></div>
                        <div class="clearfix">
                          <label class="inline"> <input
                            type="checkbox" class="ace"/> <span
                            class="lbl"> 记住密码</span>
                          </label>

                          <button type="button" onclick="sub()"
                            class="width-35 pull-right btn btn-sm btn-primary">
                            <i class="icon-key"></i> 登陆
                          </button>
                          <script type="application/javascript">
                            /**
                             * 可以在这里做一些数据检查
                             */
                            function sub(){
                                  //alert("提交了吗");
                                  document.getElementById("fLogin").submit();
                            }

                          </script>
                        </div>

                        <div class="space-4"></div>
                      </fieldset>
                      <div class="social-or-login center">
                        <span class="bigger-110">身份</span>

                      </div>
                      <div> ${requestScope.get("message")}</div>
                      <div class="social-login center ">
                          <select id="uIdentity" name="uIdentity"
                            class="selectpicker show-tick form-control">
                            <option value="1" selected>学生</option>
                            <option value="2">其它</option>
                          </select>
                      </div>
                    </form>

                  </div>
                  <!-- /widget-main -->
                  <div class="toolbar clearfix">
                    <div>
                      <a href="#"
                        onclick="show_box('forgot-box'); return false;"
                        class="forgot-password-link"> <i
                        class="icon-arrow-left"></i> 忘记密码？
                      </a>
                    </div>

                    <div>
                      <a href="#"
                        onclick="show_box('signup-box'); return false;"
                        class="user-signup-link"> 注册新账号 <i
                        class="icon-arrow-right"></i>
                      </a>
                    </div>
                  </div>
                </div>
                <!-- /widget-body -->
              </div>
              <!-- /login-box -->

              <div id="forgot-box"
                class="forgot-box widget-box no-border">
                <div class="widget-body">
                  <div class="widget-main">
                    <h4 class="header red lighter bigger">
                      <i class="icon-key"></i> 找回密码
                    </h4>

                    <div class="space-6"></div>
                    <p>输入注册邮箱</p>

                    <form>
                      <fieldset>
                        <label class="block clearfix"> <span
                          class="block input-icon input-icon-right">
                            <input type="email" class="form-control"
                            placeholder="Email" /> <i
                            class="icon-envelope"></i>
                        </span>
                        </label>

                        <div class="clearfix">
                          <button type="button"
                            class="width-35 pull-right btn btn-sm btn-danger">
                            <i class="icon-lightbulb"></i> 确认
                          </button>
                        </div>
                      </fieldset>
                    </form>
                  </div>
                  <!-- /widget-main -->

                  <div class="toolbar center">
                    <a href="#"
                      onclick="show_box('login-box'); return false;"
                      class="back-to-login-link"> 返回登陆 <i
                      class="icon-arrow-right"></i>
                    </a>
                  </div>
                </div>
                <!-- /widget-body -->
              </div>
              <!-- /forgot-box -->

              <div id="signup-box"
                class="signup-box widget-box no-border">
                <div class="widget-body">
                  <div class="widget-main">
                    <h4 class="header green lighter bigger">
                      <i class="icon-group blue"></i> 新用户注册
                    </h4>

                    <div class="space-6"></div>
                    <p>输入你的注册邮箱</p>

                    <form id="register" action="" method="post">
                      <fieldset>
                        <label class="block clearfix"> <span
                          class="block input-icon input-icon-right">
                            <input type="email" class="form-control"
                            placeholder="Email" name="email" id="email"/> <i
                            class="icon-envelope"></i>
                        </span>
                        </label> <label class="block clearfix"> <span
                          class="block input-icon input-icon-right">
                            <input type="text" class="form-control"
                            placeholder="Nickname" name="nickname" id="nickname"/> <i
                            class="icon-user"></i>
                        </span>
                        </label> <label class="block clearfix"> <span
                          class="block input-icon input-icon-right">
                            <input type="password" class="form-control"
                            placeholder="Password"  name="password" id="password"/> <i
                            class="icon-lock"></i>
                        </span>
                        </label> <label class="block clearfix"> <span
                          class="block input-icon input-icon-right">
                            <input type="password" class="form-control"
                            placeholder="Repeat password" name="repassword" id="repassword"/> <i
                            class="icon-retweet"></i>
                        </span>
                        <div class="social-or-login center">
                          <span class="bigger-110">身份</span>

                        </div>
                        <div class="social-login center ">
                          <select id="identity" name="identity"
                                  class="selectpicker show-tick form-control">
                            <option value="1" selected>学生</option>
                            <option value="2">其它</option>
                          </select>
                        </div>
                        </label> <label class="block"> <input
                          type="checkbox" class="ace" /> <span
                          class="lbl"> I accept the <a href="#">User
                              Agreement</a>
                        </span>
                        </label>

                        <div class="space-24"></div>
                        <div class="clearfix">
                          <button type="reset"
                            class="width-30 pull-left btn btn-sm">
                            <i class="icon-refresh"></i> 重置
                          </button>

                          <button type="button"  onclick="register()"
                            class="width-65 pull-right btn btn-sm btn-success">
                            注册 <i class="icon-arrow-right icon-on-right"></i>
                          </button>
                          <script type="application/javascript">
                            //注册在客户端进行验证
                            function register(){
                              document.getElementById("register").submit();
                            }
                          </script>
                        </div>
                      </fieldset>
                    </form>
                  </div>

                  <div class="toolbar center">
                    <a href="#"
                      onclick="show_box('login-box'); return false;"
                      class="back-to-login-link"> <i
                      class="icon-arrow-left"></i> 返回登陆
                    </a>
                  </div>
                </div>
                <!-- /widget-body -->
              </div>
              <!-- /signup-box -->
            </div>
            <!-- /position-relative -->
          </div>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </div>
  </div>
  <!-- /.main-container -->

  <!-- basic scripts -->

  <!--[if !IE]> -->
  <script
    src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

  <!-- <![endif]-->
  <script type="text/javascript">
			window.jQuery
					|| document
							.write("<script src='page/assets/js/jquery-2.0.3.min.js'>"
									+ "<"+"/script>");
		</script>

  <!-- <![endif]-->

  <!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='page/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

  <script type="text/javascript">
			if ("ontouchend" in document)
				document
						.write("<script src='page/assets/js/jquery.mobile.custom.min.js'>"
								+ "<"+"/script>");
		</script>

  <!-- inline scripts related to this page -->

  <script type="text/javascript">
			function show_box(id) {
				jQuery('.widget-box.visible').removeClass('visible');
				jQuery('#' + id).addClass('visible');
			}
		</script>
</body>
</html>
