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
                <form method="post" action="changeMedCenter">
                    <input type="hidden" value="${requestScope.medCenter.id}" name="id">
                    <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control" value="${requestScope.medCenter.name}" name="name" required>
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" class="form-control" value="${requestScope.medCenter.address}" name="address" required>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control" value="${requestScope.medCenter.phone}" name="phone" required>
                    </div>
                    <div class="form-group">
                        <label>MedInstitution</label>
                        <input type="text" class="form-control" value="${requestScope.medCenter.medInstitution}" name="medInstitution" required>
                    </div>

                    <div class="form-group">
                        <label>City</label>
                        <select class="form-control" name="city_id">
                            <c:forEach var = "city" items="${requestScope.allCity}">
                                <option value="${city.id}" <c:if test="${city.name == requestScope.medCenter.city.name }"><c:out value = "selected"/></c:if>>${city.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Approved</label>
                        <select class="form-control" name="approved">
                            <option value="true" <c:if test="${\"true\" == requestScope.medCenter.approved }"><c:out value = "selected"/></c:if>>true</option>
                            <option value="false" <c:if test="${\"false\" == requestScope.medCenter.approved }"><c:out value = "selected"/></c:if>>false</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Exist</label>
                        <select class="form-control" name="exist">
                            <option value="true" <c:if test="${\"true\" == requestScope.medCenter.exist }"><c:out value = "selected"/></c:if>>true</option>
                            <option value="false" <c:if test="${\"false\" == requestScope.medCenter.exist }"><c:out value = "selected"/></c:if>>false</option>
                        </select>
                    </div>

                    <br>
                    <button type="submit" class="btn btn-block btn-success" value="1" name="do">Change</button>
                </form>


                <div class="row">
                    <div class="col">
                        <form action="deleteMedCenter" method="post">
                            <input type="hidden" value="${requestScope.medCenter.id}" name="id">
                            <button type="submit" class="btn btn-block btn-danger" value="1" name="do">Delete</button>
                        </form>
                    </div>

                    <c:choose>

                        <c:when test = "${requestScope.medCenter.approved == true}">
                            <div class="col">
                                <form action="disapproveMedCenter" method="post">
                                    <input type="hidden" value="${requestScope.medCenter.id}" name="id">
                                    <button type="submit" class="btn btn-block btn-warning" value="1" name="do">Disapprove</button>
                                </form>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="col">
                                <form action="approveMedCenter" method="post">
                                    <input type="hidden" value="${requestScope.medCenter.id}" name="id">
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







