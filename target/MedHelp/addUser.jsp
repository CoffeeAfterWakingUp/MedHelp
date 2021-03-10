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
        <div class="col-5 pt-4 offset-2">
            <div class="container">
                <form method="post" action="addUser">
                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" class="form-control" name="email">
                        <c:if test = "${requestScope.emailExists != null}">
                            <small class="text-danger">${requestScope.emailExists}</small>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="text" class="form-control" name="password">
                    </div>
                    <div class="form-group">
                        <label>First Name</label>
                        <input type="text" class="form-control" name="firstName">
                    </div>
                    <div class="form-group">
                        <label>Last Name</label>
                        <input type="text" class="form-control" name="lastName">
                    </div>
                    <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control" name="phone">
                    </div>
                    <div class="form-group">
                        <label>Role</label>
                        <select class="form-control" name="role">
                            <option value="user">user</option>
                            <option value="admin">admin</option>
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
                    <button type="submit" class="btn btn-block btn-primary" value="1" name="addUser">Add User</button>
                </form>

            </div>

        </div>
    </div>


</div>



<jsp:include page="scripts.jsp"/>
</body>
</html>






