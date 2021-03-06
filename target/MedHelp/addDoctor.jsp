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
                <form method="post" action="addDoctor">
                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" class="form-control" name="email" required>
                        <c:if test = "${requestScope.emailExists != null}">
                            <small class="text-danger">${requestScope.emailExists}</small>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="text" class="form-control" name="password" required>
                    </div>
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" class="form-control" name="firstName" required>
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" class="form-control" name="lastName" required>
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control" name="phone" required>
                    </div>

                    <div class="form-group">
                        <label>Working place</label>
                        <select class="form-control" name="medCenterId">
                            <c:forEach var = "medCenter" items="${requestScope.allMedCenter}">
                                <option value="${medCenter.id}">${medCenter.name} - ${medCenter.medInstitution}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Profession</label>
                        <select class="form-control" name="professionId">
                            <c:forEach var = "profession" items="${requestScope.allProfession}">
                                <option value="${profession.id}">${profession.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Experience</label>
                        <input type="number" class="form-control" min="1" max="60" name="experience" required>
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
                    <button type="submit" class="btn btn-block btn-success" value="1" name="do">Add Doctor</button>
                </form>



            </div>

        </div>
    </div>


</div>



<jsp:include page="scripts.jsp"/>
</body>
</html>






