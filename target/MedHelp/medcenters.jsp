<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
    <jsp:include page="head.jsp"/>


</head>
<body class="bg-light">

<div class="container-fluid pb-5 bg-light" style="background-color: #F2F4F8;">

    <div class="row">
        <div class="col-2 bg-info" style="background-color: #1E282C;">
            <jsp:include page="adminNavbar.jsp"/>
        </div>
        <div class="col-10 pt-4">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h5>Table: MedCenter</h5>
                        <h5>Overall Data Count: ${fn:length(requestScope.allMedCenter)}</h5>
                    </div>
                </div>

                <div class="row py-4">
                    <div class="col-10">
                        <div class="input-group">
                            <input type="text" class="form-control" id="searchInput" onkeyup="search()" placeholder="Search By Name" aria-label="Recipient's username" aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-info" type="button" id="button-addon2"><i class="fas fa-search"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="col-2">
                        <form action="goAddMedCenter" method="get">
                            <button type="submit" class="btn btn-block btn-primary text-white"><i class="fas fa-plus"></i> Add</button>
                        </form>
                    </div>
                </div>

                <div class="row">
                    <div class="col pt-4">
                        <table class="table bg-white table-striped table-bordered" id="table">
                            <thead class="">
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>City</th>
                                    <th>Phone</th>
                                    <th>Address</th>
                                    <th>MedInstitution</th>
                                    <th>Approved</th>
                                    <th>Exist</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var = "medCenter" items="${requestScope.allMedCenter}">
                                <tr>
                                    <td>${medCenter.id}</td>
                                    <td>${medCenter.name}</td>
                                    <td>${medCenter.city.name}</td>
                                    <td>${medCenter.phone}</td>
                                    <td>${medCenter.address}</td>
                                    <td>${medCenter.medInstitution}</td>
                                    <td>${medCenter.approved}</td>
                                    <td>${medCenter.exist}</td>
                                    <td>
                                        <form action="editMedCenter" method="get">
                                            <input type="hidden" value="${medCenter.id}" name="id">
                                            <button class="btn text-info" name="do" value="1" type="submit">Edit</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>


</div>


<script>
    function search() {
        var input = document.getElementById("searchInput");
        var filter = input.value.toUpperCase();
        var table = document.getElementById("table");
        var tr = table.getElementsByTagName("tr");
        for (var i = 0; i < tr.length ; i++) {
            var td = tr[i].getElementsByTagName("td")[1];
            if(td){
                var txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }

    }
</script>

<jsp:include page="scripts.jsp"/>
</body>
</html>






