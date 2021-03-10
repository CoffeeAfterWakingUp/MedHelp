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
                <form method="post" action="changeDoctor">
                    <input type="hidden" value="${requestScope.doctor.id}" name="id">
                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" class="form-control" value="${requestScope.doctor.email}" name="email" required>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="text" class="form-control" value="${requestScope.doctor.password}" name="password" readonly>
                    </div>
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" class="form-control" value="${requestScope.doctor.firstName}" name="firstName" required>
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" class="form-control" value="${requestScope.doctor.lastName}" name="lastName" required>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control" value="${requestScope.doctor.phone}" name="phone" required>
                    </div>

                    <div class="form-group">
                        <label>Working place</label>
                        <select class="form-control" name="medCenterId">
                            <c:forEach var = "medCenter" items="${requestScope.allMedCenter}">
                                <option value="${medCenter.id}" <c:if test="${medCenter.name == requestScope.doctor.medCenter.name}"><c:out value = "selected"/></c:if>>${medCenter.name} - ${medCenter.medInstitution}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Profession</label>
                        <select class="form-control" name="professionId">
                            <c:forEach var = "profession" items="${requestScope.allProfession}">
                                <option value="${profession.id}" <c:if test="${profession.name == requestScope.doctor.profession.name}"><c:out value = "selected"/></c:if>>${profession.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Experience</label>
                        <input type="number" class="form-control" min="1" max="60" name="experience" value="<c:out value = "${requestScope.doctor.experience}"/>" required>
                    </div>

                    <div class="form-group">
                        <label>Approved</label>
                        <select class="form-control" name="approved">
                            <option value="true" <c:if test="${\"true\" == requestScope.doctor.approved }"><c:out value = "selected"/></c:if>>true</option>
                            <option value="false" <c:if test="${\"false\" == requestScope.doctor.approved }"><c:out value = "selected"/></c:if>>false</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Exist</label>
                        <select class="form-control" name="exist">
                            <option value="true" <c:if test="${\"true\" == requestScope.doctor.exist }"><c:out value = "selected"/></c:if>>true</option>
                            <option value="false" <c:if test="${\"false\" == requestScope.doctor.exist }"><c:out value = "selected"/></c:if>>false</option>
                        </select>
                    </div>

                    <br>
                    <button type="submit" class="btn btn-block btn-success" value="1" name="do">Change Doctor</button>
                </form>


                <div class="row">
                    <div class="col">
                        <form action="deleteDoctor" method="post">
                            <input type="hidden" value="${requestScope.doctor.id}" name="id">
                            <button type="submit" class="btn btn-block btn-danger" value="1" name="do">Delete Doctor</button>
                        </form>
                    </div>

                    <c:choose>

                        <c:when test = "${requestScope.doctor.approved == true}">
                            <div class="col">
                                <form action="disapproveDoctor" method="post">
                                    <input type="hidden" value="${requestScope.doctor.id}" name="id">
                                    <button type="submit" class="btn btn-block btn-warning" value="1" name="do">Disapprove Doctor</button>
                                </form>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="col">
                                <form action="approveDoctor" method="post">
                                    <input type="hidden" value="${requestScope.doctor.id}" name="id">
                                    <button type="submit" class="btn btn-block btn-warning" value="1" name="do">Approve Doctor</button>
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






