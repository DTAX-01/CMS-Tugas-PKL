<%-- 
    Document   : frm_cms_content
    Created on : Mar 11, 2019, 5:04:25 PM
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
                            <div class="row mb-2">
                                <div class="col-sm-12">
                                    <div class="card card-primary">
                                        <div class="card-header">
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="collapsed" aria-expanded="false">
                                                <h4 class="card-title">
                                                    Input Content
                                                </h4>
                                            </a>
                                        </div>
                                        <div id="collapseOne" class="panel-collapse in collapse" style="">
                                            <form:form role="form" action="/frmAddContent" method="POST" enctype="multipart/form-data">
                                                <div class="card-body">
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <input type="hidden" class="form-control" id="inkdkonten" name="kdkontenn" placeholder="Nama Rekening">
                                                                    <label for="tipekonten">Tipe Konten</label>
                                                                    <select name="tipekonten" id="tipekonten" class="form-control">
                                                                        <option value="TK">Toko</option>
                                                                        <option value="WRNG">Warung</option>
                                                                    </select>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-6">
                                                                <div class="form-group">
                                                                    <label for="inGambar">Gambar</label>
                                                                    <div class="input-group">
                                                                        <div class="custom-file">
                                                                            <input type="hidden" name="picture" id="pic">
                                                                            <input type="file" name="gbr" class="custom-file-input" id="picture" accept="image/x-png,image/gif,image/jpeg">
                                                                            <label class="custom-file-label" for="">Choose file</label>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-md-12">
                                                                <div class="form-group">
                                                                    <label for="konten">Konten</label>
                                                                    <textarea name="konten" class="form-control" id="konten" rows="3" placeholder="Enter Konten..." style="height: 150px;"></textarea>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-footer">
                                                    <button type="submit" class="btn btn-primary btn-md float-right" id="btnEditContent">Edit</button>
                                                    <button type="button" class="btn btn-default btn-md float-right mr-2" id="btnBatalContent">Batal</button>
                                                    <button type="submit" class="btn btn-primary btn-md float-right" id="btnSubmitContent">Submit</button>
                                                </div>
                                            </form:form>
                                        </div>
                                    </div>
                                </div><!-- /.col -->
                            </div><!-- /.row -->
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
                                            <b class="card-title">Data Content</b>
                                            <button class="btn btn-primary" id="refresh"><i class="fa fa-refresh"></i></button>
                                        </div>
                                        <!-- /.card-header -->
                                        <div class="card-body">
                                            <div id="tblContent">

                                            </div>
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
                    $.get("/getTblContent", function (data) {

                    }).done(function (data) {
                        $("#tblContent").html(data);
                    }).fail(function (data) {
                        $("#tblContent").html(data);
                    });

                    $("#btnBatalContent, #btnEditContent").hide();

                    $("a[data-toggle][data-parent]").trigger("click");
                });
                $("#refresh").click(function () {
                    $("#tblContent").empty();
                    $("#tblContent").html("Loading...");
                    setTimeout(function () {
                        $.get("/getTblContent", function (data) {

                        }).done(function (data) {
                            $("#tblContent").html(data);
                        }).fail(function (data) {
                            $("#tblContent").html(data);
                        });
                    }, 2000);

                });
                $("#btnBatalContent").click(function () {
                    $("input, textarea").val("");
                    $("select option:eq(0)").attr("selected", true);
                    $("#btnBatalContent, #btnEditContent").hide();
                    $("#btnSubmitContent").show();

                    $("a[data-toggle][data-parent]").trigger("click");
                    $("#collapseOne form").attr("action", "/frmAddContent");
                    $("div.card-header h4.card-title").text("Add Content");
                    $("#inkdkonten").attr("name", "kdcntn");
                    
                });
            </script>
        </body>

    </html>
</f:view>
