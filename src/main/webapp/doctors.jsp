<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
    <jsp:include page="head.jsp"/>


</head>
<body class="bg-light">

<div class="container-fluid pb-5 bg-light h-100" style="background-color: #F2F4F8;">

    <div class="row">
        <div class="col-2 bg-info" style="background-color: #1E282C;">
            <jsp:include page="adminNavbar.jsp"/>
        </div>
        <div class="col-10 pt-4">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <h5>Table: Doctor</h5>
                        <h5>Overall Data Count: ${fn:length(requestScope.allDoctors)}</h5>
                    </div>
                </div>

                <div class="row py-4">
                    <div class="col-10">
                        <div class="input-group">
                            <input type="text" class="form-control" id="searchInput" onkeyup="search()" placeholder="Search By Email" aria-label="Recipient's username" aria-describedby="button-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-outline-info" type="button" id="button-addon2"><i class="fas fa-search"></i></button>
                            </div>
                        </div>
                    </div>
                    <div class="col-2">
                        <a href="goAddDoctor" class="btn btn-block btn-primary text-white"><i class="fas fa-user-plus"></i> Add Doctor</a>
                    </div>
                </div>

                <div class="row">
                    <div class="col pt-4">
                        <table class="table bg-white table-striped table-bordered table-responsive-sm" id="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Email</th>
                                    <th>Password</th>
                                    <th>FirstName</th>
                                    <th>LastName</th>
                                    <th>Phone</th>
                                    <th>MedCenter</th>
                                    <th>Profession</th>
                                    <th>Experience</th>
                                    <th>Approved</th>
                                    <th>Exist</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var = "doctor" items="${requestScope.allDoctors}">
                                <tr>
                                    <td>${doctor.id}</td>
                                    <td>${doctor.email}</td>
                                    <td>${doctor.password}</td>
                                    <td>${doctor.firstName}</td>
                                    <td>${doctor.lastName}</td>
                                    <td>${doctor.phone}</td>
                                    <td>${doctor.medCenter.name}</td>
                                    <td>${doctor.profession.name}</td>
                                    <td>${doctor.experience}</td>
                                    <td>${doctor.approved}</td>
                                    <td>${doctor.exist}</td>
                                    <td>
                                        <form action="editDoctor" method="get">
                                            <input type="hidden" value="${doctor.id}" name="doctorId">
                                            <button class="btn text-info" name="edit" value="1" type="submit">Edit</button>
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






