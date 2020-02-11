<%-- 
    Document   : dashboard
    Created on : Mar 6, 2019, 7:12:53 PM
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
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <!-- Tell the browser to be responsive to screen width -->
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Web CMS</title>
            <jsp:include page="distCss.jsp" />
            

        </head>
        <body class="hold-transition sidebar-mini">
            <div class="wrapper">
                <!-- Navbar -->
                <jsp:include page="v_headnav.jsp" />
                <!-- /.navbar -->

                <!-- Main Sidebar Container -->
                <jsp:include page="v_sidenav.jsp" />

                <!-- Content Wrapper. Contains page content -->
                <div class="content-wrapper">
                    <!-- Content Header (Page header) -->
                    <div class="content-header">
                        <div class="container-fluid">
                            <!-- /.row -->
                        </div><!-- /.container-fluid -->
                    </div>
                    <!-- Main content -->
                    <section class="content">
                        <div class="container-fluid">
                            <!-- Small boxes (Stat box) -->
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <b class="card-title">WELCOME TO MICASA</b>
                                        </div>
                                        <!-- /.card-header -->
                                        <div class="card-body">
                                           ${coba} | ${status}
                                        </div>
                                        <!-- /.card-body -->
                                        <div class="card-footer clearfix">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.row (main row) -->
                        </div><!-- /.container-fluid -->
                    </section>
                    <!-- /.content -->
                </div>
                <!-- /.content-wrapper -->

                <jsp:include page="v_footer.jsp" />

            </div>

            <jsp:include page="distJs.jsp" />

            

            <script>
                $(document).ready(function () {

                });
                $("#btnSubmitContent").click(function () {
                    var img = $("#inImage").val();
                    var jsn = {
                        "tipe": $("#inTipeContent").val(),
                        "userid": "gg",
                        "time": timeDate(),
                        "konten": $("#inContent").val(),
                        "img": img.substr(12)
                    }

                    $.post("/ajxContent", jsn, function (data) {

                    }).done(function (data) {
                        alert(data);
                    });
                });

                function timeDate() {
                    var today = new Date();
                    var dd = today.getDate();
                    var mm = today.getMonth() + 1;
                    var yyyy = today.getFullYear();
                    var hour = today.getHours();
                    var min = today.getMinutes();

                    if (dd < 10) {
                        dd = '0' + dd;
                    }
                    if (mm < 10) {
                        mm = '0' + mm;
                    }
                    if (hour < 10) {
                        hour = '0' + hour;
                    }
                    if (min < 10) {
                        min = '0' + min;
                    }
                    var today = dd + '' + mm + '' + yyyy + '' + hour + '' + min;
                    return today;
                }
            </script>
        </body>

    </html>
</f:view>
