<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
    <jsp:include page="head.jsp"/>


</head>
<body>

<div class="container-fluid pb-5 bg-light" style="background-color: #F2F4F8;">

        <div class="row">
            <div class="col-2 bg-info" style="background-color: #1E282C;">
                <jsp:include page="adminNavbar.jsp"/>
            </div>
            <div class="col-10 pt-4">
                <div class="container">
                    <div class="row">
                        <div class="col">
                            <h5>Table: User</h5>
                            <h5>Overall Data Count: ${fn:length(requestScope.allUsers)}</h5>
                        </div>
                    </div>

                    <div class="row py-4">
                        <div class="col-10">
                            <div class="input-group">
                                <input type="text" class="form-control" id="searchInput" onkeyup="search()" placeholder="Search User By Email" aria-label="Recipient's username" aria-describedby="button-addon2">
                                <div class="input-group-append">
                                    <button class="btn btn-outline-info" type="button" id="button-addon2"><i class="fas fa-search"></i></button>
                                </div>
                            </div>
                        </div>
                        <div class="col-2">
                            <form action="goAddUser" method="get">
                                <button type="submit" class="btn btn-block btn-primary text-white"><i class="fas fa-user-plus"></i> Add User</button>
                            </form>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col pt-4">
                            <table class="table bg-white table-striped table-bordered" id="table">
                                <thead class="">
                                    <tr>
                                        <th>ID</th>
                                        <th>Email</th>
                                        <th>Password</th>
                                        <th>FirstName</th>
                                        <th>LastName</th>
                                        <th>Phone</th>
                                        <th>Role</th>
                                        <th>Exist</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var = "user" items="${requestScope.allUsers}">
                                        <tr>
                                            <td>${user.id}</td>
                                            <td>${user.email}</td>
                                            <td>${user.password}</td>
                                            <td>${user.firstName}</td>
                                            <td>${user.lastName}</td>
                                            <td>${user.phone}</td>
                                            <td>${user.role}</td>
                                            <td>${user.exist}</td>
                                            <td>
                                                <form action="editUser" method="get">
                                                    <input type="hidden" value="${user.id}" name="userId">
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





