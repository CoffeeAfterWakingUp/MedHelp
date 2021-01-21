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
        <div class="col-2 bg-info h-100" style="background-color: #1E282C;">
            <jsp:include page="adminNavbar.jsp"/>
        </div>
        <div class="col-5 pt-4 offset-2">
            <div class="container">
                <form method="post" action="changePharmacy">
                    <input type="hidden" value="${requestScope.pharmacy.id}" name="id">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" value="${requestScope.pharmacy.name}" name="name">
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" class="form-control" value="${requestScope.pharmacy.address}" name="address">
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control" value="${requestScope.pharmacy.phone}" name="phone">
                    </div>
                    <div class="form-group">
                        <label>City</label>
                        <select class="form-control" name="city_id">
                            <c:forEach var = "city" items="${requestScope.allCity}">
                                <option value="${city.id}">${city.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Approved</label>
                        <select class="form-control" name="approved">
                            <option value="true">true</option>
                            <option value="false">false</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Exist</label>
                        <select class="form-control" name="exist">
                            <option value="true">true</option>
                            <option value="false">false</option>
                        </select>
                    </div>

                    <br>
                    <button type="submit" class="btn btn-block btn-success" value="1" name="do">Change</button>
                </form>


                <div class="row">
                    <div class="col">
                        <form action="deletePharmacy" method="post">
                            <input type="hidden" value="${requestScope.pharmacy.id}" name="id">
                            <button type="submit" class="btn btn-block btn-danger" value="1" name="do">Delete</button>
                        </form>
                    </div>

                    <c:choose>

                        <c:when test = "${requestScope.pharmacy.approved == true}">
                            <div class="col">
                                <form action="disapprovePharmacy" method="post">
                                    <input type="hidden" value="${requestScope.pharmacy.id}" name="id">
                                    <button type="submit" class="btn btn-block btn-warning" value="1" name="do">Disapprove</button>
                                </form>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="col">
                                <form action="approvePharmacy" method="post">
                                    <input type="hidden" value="${requestScope.pharmacy.id}" name="id">
                                    <button type="submit" class="btn btn-block btn-warning" value="1" name="do">Approve</button>
                                </form>
                            </div>
                        </c:otherwise>
                    </c:choose>


                </div>
            </div>

        </div>
    </div>


</div>



<jsp:include page="scripts.jsp"/>
</body>
</html>







