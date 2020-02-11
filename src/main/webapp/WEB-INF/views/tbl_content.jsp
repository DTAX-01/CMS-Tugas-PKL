<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="msg" value="${status}"/>
<c:set var="user" value="${fn:split(msg,'_')}"/>


    <table id="dtTbl" class="table-hover table-striped">
        <thead align="center">
            <c:if test="${user[2] == 1}">
        <th>Kode Konten</th>
        </c:if>
        <th>Tipe Konten</th>
        <c:if test="${user[2] == 1}">
        <th>UserID</th>
        </c:if>
        <th>Time</th>
        <th>Konten</th>
        <th>Picture</th>
        <th style="min-width: 60px;">Action</th>
        </thead>
        <tbody align="center">
            <c:forEach items="${data}" var="data">
                <tr>
                    <c:if test="${user[2] == 1}">
                    <td>${data.getKdkonten()}</td>
                    </c:if>
                    
                    <td>
                        <c:choose>
                            <c:when test="${data.getTipekonten() == 'TK'}">Toko</c:when>
                            <c:when test="${data.getTipekonten() == 'WRNG'}">Warung</c:when>
                        </c:choose>
                    </td>
                    
                    <c:if test="${user[2] == 1}">
                    <td>${data.getUserid()}</td>
                    </c:if>
                    <td>
                        ${fn:substring(data.getTime(), 0, 2)}-${fn:substring(data.getTime(), 2, 4)}-${fn:substring(data.getTime(), 4, 8)} ${fn:substring(data.getTime(), 8, 10)}:${fn:substring(data.getTime(), 10, 12)}
                    </td>
                    <td>
                        ${data.getKonten()}
                    </td>
                    <td>${data.getPicture()}</td>
                    <td>
                        <button class="btn btn-primary btn-sm" onclick="EditContent(${data.getKdkonten()})" href="#!"><i class="fa fa-edit"></i></button>
                        <button class="btn btn-danger btn-sm" onclick="HapusContent(${data.getKdkonten()})" id="${data.getKdkonten()}" href="#!"><i class="fa fa-trash-o"></i></button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>


<!--DataTables-->


<script>
    $("#dtTbl").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false
    });
    function EditContent(id) {
        $("#collapseOne form").attr("action", "/frmEditContent");
        $("div.card-header h4.card-title").text("Edit Content");
        $("#inkdkonten").attr("name", "kdkonten");
        
        $.post("/getContentId", {"kode": id}, function (data) {

        }).done(function(data){
            var jsn = JSON.parse(data);
            $("#inkdkonten").val(jsn["kdkonten"]);
            var tpko = jsn['tpkonten'];
            $("select#tipekonten option[value='"+tpko+"']").attr("selected", true);
            $("#konten").val(jsn["konten"]);
            $("#pic").val(jsn["picture"]);
            
            $("#btnBatalContent, #btnEditContent").show();
            $("#btnSubmitContent").hide();
            
            if($("#collapseOne").attr("class") != "panel-collapse in collapse show"){
                $("a[data-toggle][data-parent]").trigger("click");
            }
            
            $("html, body").animate({ scrollTop: 0 }, "slow");
        }).fail(function(data){
            
        });
    }
    function HapusContent(id){
        $.post("/delContentId", {"kode":id}, function(){
            
        }).done(function(data){
            $("#tblContent").empty();
            $("#tblContent").html(data);
            alert("Success...");
        }).fail(function(data){
            
        });
    }
</script>