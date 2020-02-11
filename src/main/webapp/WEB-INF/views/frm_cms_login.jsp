<%-- 
    Document   : index
    Created on : Mar 6, 2019, 11:07:55 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <title>CMS Web</title>
        </head>
        <spring:url value="dist" var="dist" />
        <link rel="stylesheet" href="${dist}/css/adminlte.min.css" />
        <link rel="stylesheet" href="${dist}/fontawesome/css/all.css" />
        <script src="${dist}/js/jquery.js"></script>
        <script src="${dist}/js/client_captcha.js"></script>
        <script src="${dist}/js/adminlte.min.js"></script>
        <script src="${dist}/fontawesome/js/all.js"></script>
        <style>
            .login-box{
                border: 0px solid lightgrey;
                box-shadow: 0px 0px 22px #B0BEC5;
            }
            .form-control{
                border-bottom-left-radius: 0px;
                border-bottom-right-radius: 0px;
                border-top-left-radius: 0px;
                border-top-right-radius: 0px;
            }
            body{
                overflow: hidden;
                background-color: #ECEFF1;
                user-select: none;
            }

        </style>

        <body>
            <div class="login-box">
                <!-- /.login-logo -->
                <div class="login-box-body">
                    <p class="login-box-msg" style="font-weight: bold;">Welcome To Micasa</p>
                    <c:if test="${not empty gagal}">
                        <div class="small-box bg-danger">
                            <div class="inner">
                                <p>${gagal}</p>
                            </div>

                            <!--<a href="#" class="small-box-footer"> <i class="fa fa-arrow-circle-right"></i></a>-->
                        </div>
                    </c:if>
                    <form action="/" method="post">
                        <div class="form-group has-feedback">
                            <input type="text" name="kdbank" class="form-control" placeholder="Kode Bank" autofocus="true">
                            <span id="warnBank" class="text-danger text-sm" style="display: none;">Kode Bank Tidak Boleh Kosong</span>
                        </div>
                        <div class="form-group has-feedback">
                            <input type="text" name="userid" class="form-control" placeholder="Username">
                            <span id="warnUser" class="text-danger text-sm" style="display: none;">Userid Tidak Boleh Kosong</span>
                        </div>
                        <div class="form-group has-feedback">
                            <input type="password" name="pass" class="form-control" placeholder="Password">
                            <span id="warnPass" class="text-danger text-sm" style="display: none;">Password Tidak Boleh Kosong</span>
                        </div>
                        <div class="row mb-2">
                            <div class="col-md-12">
                                <div class="form-group has-feedback">
                                    <div id="captcha">
                                        <div class="controls">
                                            <input class="user-text btn-common" placeholder="Type here" type="text" style="width: 275px;" />
                                            <button class="btn btn-danger btn-md float-right" type="button" id="refresh"><i class="fas fa-sync"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <button id="btnSubmit" type="submit" class="btn btn-primary btn-block btn-flat float-right" style="width: 100%;" disabled="true">Sign In</button>
                            </div>
                        </div>
                    </form>                    
                </div>
                <!-- /.login-box-body -->
            </div>
           
            <script>
                $(document).ready(function () {
                    $("#refresh").click(function () {
                        $(".user-text").val("");
                    });
                    $("#btnSubmit").attr("disabled", true);

                <c:if test="${not empty gagal}">
                    var jsn = '${data}'
                    var jj = JSON.parse(jsn);

                    $("input[name=kdbank]").val(jj["bank"]);
                    $("input[name=userid]").val(jj["usr"]);
                    $("input[name=pass]").val(jj["pass"]);

                </c:if>
                });
                document.addEventListener("DOMContentLoaded", function () {
                    document.body.scrollTop;

                    var captcha = new $.Captcha({
                        onFailure: function () {
                            $("#btnSubmit").attr("disabled", true);
                        },

                        onSuccess: function () {
                            $("#btnSubmit").removeAttr("disabled");
                        }
                    });

                    captcha.generate();
                });
                
                $("form").submit(function(e){
                    var kd = $("input[name=kdbank]").val();
                    var us = $("input[name=userid]").val();
                    var pw = $("input[name=pass]").val();
                    $("#warnBank, #warnUser, #warnPass").hide();
                    if(kd == null || kd == ""){ $("#warnBank").show(); }
                    if(us == null || us == ""){ $("#warnUser").show(); }
                    if(pw == null || pw == ""){ $("#warnPass").show(); }
                    if(kd == "" || us == "" || pw == ""){
                        e.preventDefault(e);
                        $("#btnSubmit").attr("disabled", true);
                        $("#refresh").trigger("click");
                        $(".user-text").val("");
                    }
                });
            </script>
        </body>
    </html>
</f:view>
