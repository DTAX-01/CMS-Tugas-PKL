<%-- 
    Document   : frm_cms_merchant
    Created on : Mar 11, 2019, 4:55:17 PM
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
                                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" class="collapsed" aria-expanded="false">
                                            <div class="card-header">
                                                <h4 class="card-title">
                                                    Input Merchant
                                                </h4>
                                            </div>
                                        </a>
                                        <div id="collapseOne" class="panel-collapse in collapse" style="">
                                            <form:form action="/frmAddMerchant" method="POST" enctype="multipart/form-data">
                                                <div class="card-body">
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <input type="hidden" class="form-control" name="kdmchn" id="inKdMchn">
                                                            <div class="form-group">
                                                                <label for="">Nama Merchant</label>
                                                                <input type="text" class="form-control" name="namaMerchant" id="inMerchant" placeholder="Enter Nama Merchant" required="true" />
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <label for="">Nama Pemilik</label>
                                                                <input type="text" class="form-control" name="namaPemilik" id="inPemilik" placeholder="Enter Nama Pemilik">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="">Alamat</label>
                                                                <textarea class="form-control" name="alamat" id="inAlamat" rows="3" placeholder="Alamat" style="resize: none;"></textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="">Kelurahan</label>
                                                                <input type="text" class="form-control" name="kelurahan" id="inKelurahan" placeholder="Kelurahan">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="">Kecamatan</label>
                                                                <input type="text" class="form-control" name="kecamatan" id="inKecamatan" placeholder="Kecamatan">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="">Kabupaten/Kota</label>
                                                                <input type="text" class="form-control" name="kota" id="inKota" placeholder="Kota">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="">Kode Pos</label>
                                                                <input type="number" min="1" max="99999" maxlength="5" onkeypress="return this(event)" class="form-control" name="kodePos" id="inPos" placeholder="Kode Pos">
                                                            </div>
                                                        </div>
                                                    </div>  
                                                    <div class="row">
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="">No Hp</label>
                                                                <input type="number" min="1" max="999999999999" class="form-control" name="noHp" id="inHp" placeholder="No Hp">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="">No Telepon</label>
                                                                <input type="number" min="1" max="999999999999" class="form-control" name="noTlpn" id="inTlpn" placeholder="No Telepon">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-4">
                                                            <div class="form-group">
                                                                <label for="">Email</label>
                                                                <input type="email" class="form-control" name="email" id="inEmail" placeholder="Email">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <label for="">No Rekening</label>
                                                                <input type="number" min="1" max="999999999999" class="form-control" name="noRekening" id="inNoRekening" placeholder="No Rekening">
                                                            </div>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <div class="form-group">
                                                                <label for="">Nama Rekening</label>
                                                                <input type="text" class="form-control" name="namaRekening" id="inNamaRekening" placeholder="Nama Rekening">
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-md-12">
                                                            <div class="form-group">
                                                                <label for="">Gambar</label>
                                                                <div class="input-group">
                                                                    <div class="custom-file">
                                                                        <input type="hidden" name="pic" id="pic">
                                                                        <input type="file" class="custom-file-input" name="inGambar" id="inGambar" accept="image/x-png,image/gif,image/jpeg">
                                                                        <label class="custom-file-label" for="">Choose file</label>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="card-footer">
                                                    <button type="submit" class="btn btn-primary btn-md float-right" id="btnEditMerchant">Edit</button>
                                                    <button type="button" class="btn btn-default btn-md float-right mr-2" id="btnBatalMerchant">Batal</button>
                                                    <button type="submit" class="btn btn-primary btn-md float-right" id="btnSubmitMerchant">Submit</button>
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
                                            <b class="card-title">Data Merchant</b>&nbsp;
                                            <button class="btn btn-primary" id="refresh"><i class="fa fa-refresh"></i></button>
                                        </div>
                                        <!-- /.card-header -->
                                        <div class="card-body">
                                            <div id="tblMerchant">

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
                    $("#btnBatalMerchant, #btnEditMerchant").hide();
                    $.get("/getTblMerchant", function (data) {
                        $("#tblMerchant").html("Loading...");
                    }).done(function (data) {
                        $("#tblMerchant").html(data);
                    }).fail(function (data) {
                        $("#tblMerchant").html(data);
                    });

                    $("a[data-toggle][data-parent]").trigger("click");
                });
                $("#refresh").click(function(){
                    $("#tblMerchant").empty();
                    $.get("/getTblMerchant", function (data) {
                        
                    }).done(function (data) {
                        $("#tblMerchant").html(data);
                    }).fail(function (data) {
                        $("#tblMerchant").html(data);
                    });
                });
                $("#btnBatalMerchant").click(function () {
                    $("a[data-toggle][data-parent]").trigger("click");
                    $("input, textarea").val("");
                    $("#btnBatalMerchant, #btnEditMerchant").hide();
                    $("#btnSubmitMerchant").show();
                    $("#collapseOne form").attr("action", "/frmAddMerchant");
                    $("div.card-header h4.card-title").text("Add Merchant");
                    $("#inKdMchn").attr("name", "kdmchn");
                });
                
            </script>
        </body>

    </html>
</f:view>
