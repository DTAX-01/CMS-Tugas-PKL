<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="msg" value="${status}"/>
<c:set var="user" value="${fn:split(msg,'_')}"/>




    <table id="dtTbl" class="table-hover table-striped">
        <thead align="center" style="white-space: nowrap;">
        <th>Kode</th>
        <th>Nama Merchant</th>
        <th>Nama Pemilik</th>
        <th>Alamat</th>
        <th>Kelurahan</th>
        <th>Kecamatan</th>
        <th>Kabupaten/Kota</th>
        <th>Kode POS</th>
        <th>No HP</th>
        <th>No TLPN</th>
        <th>Email</th>
        <th>No Rekening</th>
        <th>Nama Rekening</th>
        <th>Pic</th>
        <th>Status</th>
        <th style="min-width: 60px;">Action</th>
        </thead>
        <tbody align="center">
            <c:forEach  items="${data}" var="data">
                <tr>
                    <td>${data.getKdMerchant()}</td>
                    <td>${data.getNamaMerchant()}</td>
                    <td>${data.getNamaPemilik()}</td>
                    <td>${data.getAlamat()}</td>
                    <td>${data.getKelurahan()}</td>
                    <td>${data.getKecamatan()}</td>
                    <td>${data.getKota()}</td>
                    <td>${data.getKodePos()}</td>
                    <td>${data.getNoHp()}</td>
                    <td>${data.getNoTlpn()}</td>
                    <td>${data.getEmail()}</td>
                    <td>${data.getNoRekening()}</td>
                    <td>${data.getNamaRekening()}</td>
                    <td>${data.getPic()}</td>
                    <td>
                        <c:if test="${data.getStsrec() == 'A'}">
                            <small class="badge badge-success">Approved</small>
                        </c:if>
                        <c:if test="${data.getStsrec() == 'D'}">
                            <small class="badge badge-danger">Disposal</small>
                        </c:if>
                        <c:if test="${data.getStsrec() == 'N'}">
                            <small class="badge badge-info">Waiting</small>
                        </c:if>
                    </td>
                    <td>
                        <button class="btn btn-primary btn-sm" onclick="EditMerchant(${data.getKdMerchant()})" href="#!"><i class="fa fa-edit"></i></button>
                        <button class="btn btn-danger btn-sm" onclick="HapusMerchant(${data.getKdMerchant()})" id="${data.getKdMerchant()}" href="#!"><i class="fa fa-trash-o"></i></button>
                            <c:if test="${user[2] == 1}">
                            <select class="" name="" id="" style="margin-top: 10px; border-bottom-left-radius: 5px;border-bottom-right-radius: 5px;">
                                <option value="A_${data.getKdMerchant()}" <c:if test="${data.getStsrec() == 'A'}">selected="true"</c:if>>Approved</option>
                                <option value="D_${data.getKdMerchant()}" <c:if test="${data.getStsrec() == 'D'}">selected="true"</c:if>>Disposal</option>
                                <option value="N_${data.getKdMerchant()}    " <c:if test="${data.getStsrec() == 'N'}">selected="true"</c:if>>Waiting</option>
                                </select>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


<script>
    $("#dtTbl").DataTable({
        "paging": true,
        "lengthChange": true,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "scrollX": 100
    });
    function EditMerchant(id) {
        $("#collapseOne form").attr("action", "/frmEditMerchant");
        $("div.card-header h4.card-title").text("Edit Merchant");
        $("#inKdMchn").attr("name", "kdMerchant");

        $.post("/getMerchantId", {"kode": id}, function () {

        }).done(function (data) {
            var jsn = JSON.parse(data);
            $("#inKdMchn").val(jsn['kdmchn']);
            $("#inMerchant").val(jsn["nmmchn"]);
            $("#inPemilik").val(jsn["nmpemilik"]);
            $("#inAlamat").val(jsn["alamat"]);
            $("#inKelurahan").val(jsn["kelurahan"]);
            $("#inKecamatan").val(jsn["kecamatan"]);
            $("#inKota").val(jsn["kota"]);
            $("#inPos").val(jsn["pos"]);
            $("#inHp").val(jsn["hp"]);
            $("#inTlpn").val(jsn["tlpn"]);
            $("#inEmail").val(jsn["email"]);
            $("#inNoRekening").val(jsn["norek"]);
            $("#inNamaRekening").val(jsn["nmrek"]);
            $("#pic").val(jsn["gbr"]);

            $("#btnBatalMerchant, #btnEditMerchant").show();
            $("#btnSubmitMerchant").hide();

            if ($("#collapseOne").attr("class") != "panel-collapse in collapse show") {
                $("a[data-toggle][data-parent]").trigger("click");
            }

            $("html, body").animate({scrollTop: 0}, "slow");
        }).fail(function (data) {

        });
    }
    function HapusMerchant(id) {
        $.post("/delMerchantId", {"kode": id}, function () {

        }).done(function (data) {
            $("#tblMerchant").empty();
            $("#tblMerchant").html(data);
            alert("Success...");
        }).fail(function (data) {

        });
    }
    $('select').on('change', function (e) {
        var optionSelected = $("option:selected", this);
        var valueSelected = this.value;

        $.post("/changeSts", {"kode": valueSelected}, function () {

        }).done(function (data) {
            $("#tblMerchant").empty();
            $("#tblMerchant").html(data);
            alert("Success...");
        }).fail(function (data) {
            $("#tblMerchant").html(data);
        });
    });
</script>