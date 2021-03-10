<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLanguage}"/>
<fmt:setBundle basename="Language"/>
<html>
<head>
    <jsp:include page="head.jsp"/>
</head>
<body class="bg-light">

<div class="container-fluid py-5" style="padding-bottom: 30px;">
    <div class="container">
        <c:if test = "${requestScope.successReg != null}">
            <div class="row">
                <div class="col-4 offset-4">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <fmt:message key = "success.reg"/> <a href="login.jsp" class="alert-link"><fmt:message key = "login"/></a>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </div>
            </div>
        </c:if>
        <div class="row">
            <div class="col-4 offset-4 bg-white">
                <div class="py-4 d-flex justify-content-center">
                    <h3><span style="color: crimson">Med</span><span style="color: lightskyblue">Help</span></h3>
                </div>
                <div>
                    <nav class="">
                        <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                            <a class="nav-link active" id="nav-user-tab" data-toggle="tab" href="#nav-user" role="tab" aria-controls="nav-user" aria-selected="true"><fmt:message key = "tab.user"/></a>
                            <a class="nav-link" id="nav-doctor-tab" data-toggle="tab" href="#nav-doctor" role="tab" aria-controls="nav-profile" aria-selected="false"><fmt:message key = "tab.doctor"/></a>
                        </div>
                    </nav>

                    <!--User signUp page-->
                    <div class="tab-content" id="nav-tabContent">
                        <div class="tab-pane fade show active" id="nav-user" role="tabpanel" aria-labelledby="nav-user-tab">
                            <form class="pt-4" method="post" action="register">
                                <div class="form-group">
                                    <label for="firstNameUser"><fmt:message key = "label.first.name"/></label>
                                    <input type="text" name="firstName" class="form-control" id="firstNameUser" value="<c:out value = "${requestScope.firstName}"/>" required>
                                    <c:if test = "${requestScope.firstNameNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.first.name.not.valid"/></small>
                                    </c:if>
                                </div>

                                <div class="form-group">
                                    <label for="lastNameUser"><fmt:message key = "label.last.name"/></label>
                                    <input type="text" name="lastName" class="form-control" id="lastNameUser" value="<c:out value = "${requestScope.lastName}"/>" required>
                                    <c:if test = "${requestScope.lastNameNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.last.name.not.valid"/></small>
                                    </c:if>
                                </div>


                                <div class="form-group">
                                    <label for="phoneUser"><fmt:message key = "label.phone"/></label>
                                    <input type="text" name="phone" class="form-control" id="phoneUser" value="<c:out value = "${requestScope.phone}"/>" placeholder="+77XXXXXXXXX" required>
                                    <c:if test = "${requestScope.phoneNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.phone.not.valid"/></small>
                                    </c:if>
                                </div>


                                <div class="form-group">
                                    <label for="emailInput"><fmt:message key = "label.email"/></label>
                                    <input type="email" name="email" class="form-control" id="emailInput" aria-describedby="emailHelp" value="<c:out value = "${requestScope.email}"/>" required>
                                    <c:if test = "${requestScope.emailExists != null}">
                                        <small class="text-danger"><fmt:message key = "error.email.exists"/></small>
                                    </c:if>
                                    <c:if test = "${requestScope.emailNotValid != null}">
                                        <br>
                                        <small class="text-danger"><fmt:message key = "error.email.not.valid"/></small>
                                    </c:if>
                                </div>

                                <div class="form-group">
                                    <label for="passwordInput"><fmt:message key = "label.password"/></label><br>
                                    <div class="input-group mb-3">
                                        <input type="password" name="password" class="form-control" id="passwordInput" aria-describedby="passwordHelp" required>
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-info" type="button" id="showPasswordButton" onclick="showPassword()">Show</button>
                                        </div>
                                    </div>
                                    <small id="passwordHelp" class="form-text text-muted"><fmt:message key = "label.password.help"/></small>
                                    <c:if test = "${requestScope.passwordNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.password.not.valid"/></small>
                                    </c:if>
                                </div>


                                <div class="form-group">
                                    <label for="confirmPasswordInput"><fmt:message key = "label.confirm.password"/></label><br>
                                    <div class="input-group mb-3">
                                        <input type="password" name="confirmPassword" class="form-control" id="confirmPasswordInput" aria-describedby="confirmPasswordHelp" required>
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-info" type="button" id="showConfirmPasswordButton" onclick="showConfirmPassword()">Show</button>
                                        </div>
                                    </div>
                                    <c:if test = "${requestScope.confirmPasswordNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.confirm.password.not.valid"/></small>
                                    </c:if>
                                </div>


                                <br>
                                <button type="submit" class="btn btn-info btn-block" name="signUp" value="registerUser"><fmt:message key = "button.sign.up"/></button>

                                <hr>
                                <div class="d-flex justify-content-center">
                                    <span><fmt:message key = "span.have.account"/><a href="login.jsp"> <fmt:message key = "button.login"/></a></span>
                                </div>
                                <hr>
                                <div class="d-flex justify-content-start">
                                    <span><a href="index.jsp"> <- <fmt:message key = "link.back.to.main"/></a></span>
                                </div>
                            </form>
                        </div>



                        <!--Doctor signUp page-->
                        <div class="tab-pane fade" id="nav-doctor" role="tabpanel" aria-labelledby="nav-doctor-tab">
                            <form class="pt-4" action="register" method="post">
                                <div class="form-group">
                                    <label><fmt:message key = "label.first.name"/></label>
                                    <input type="text" name="doctorFirstName" class="form-control" value="<c:out value = "${requestScope.doctorFirstName}"/>" required>
                                    <c:if test = "${requestScope.doctorFirstNameNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.first.name.not.valid"/></small>
                                    </c:if>
                                </div>

                                <div class="form-group">
                                    <label><fmt:message key = "label.last.name"/></label>
                                    <input type="text" name="doctorLastName" class="form-control" value="<c:out value = "${requestScope.doctorLastName}"/>" required>
                                    <c:if test = "${requestScope.doctorLastNameNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.last.name.not.valid"/></small>
                                    </c:if>
                                </div>

                                <div class="form-group">
                                    <label for="phoneUser"><fmt:message key = "label.phone"/></label>
                                    <input type="text" name="doctorPhone" class="form-control" value="<c:out value = "${requestScope.doctorPhone}"/>" placeholder="+77XXXXXXXXX" required>
                                    <c:if test = "${requestScope.doctorPhoneNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.phone.not.valid"/></small>
                                    </c:if>
                                </div>


                                <div class="form-group">
                                    <label for="emailInputDoctor"><fmt:message key = "label.email"/></label>
                                    <input type="email" class="form-control" id="emailInputDoctor" name="doctorEmail" aria-describedby="emailHelp" value="<c:out value = "${requestScope.doctorEmail}"/>" required>
                                    <c:if test = "${requestScope.doctorEmailExists != null}">
                                        <small class="text-danger"><fmt:message key = "error.email.exists"/></small>
                                    </c:if>
                                    <c:if test = "${requestScope.doctorEmailNotValid != null}">
                                        <br>
                                        <small class="text-danger"><fmt:message key = "error.email.not.valid"/></small>
                                    </c:if>
                                </div>

                                <div class="form-group">
                                    <label for="passwordInputDoctor"><fmt:message key = "label.password"/></label><br>
                                    <div class="input-group mb-3">
                                        <input type="password" class="form-control" id="passwordInputDoctor" name="doctorPassword" aria-describedby="doctorPasswordHelp" required>
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-info" type="button" id="showDoctorPasswordButton" onclick="showDoctorPassword()">Show</button>
                                        </div>
                                    </div>
                                    <small id="doctorPasswordHelp" class="form-text text-muted"><fmt:message key = "label.password.help"/></small>
                                    <c:if test = "${requestScope.doctorPasswordNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.password.not.valid"/></small>
                                    </c:if>
                                </div>

                                <div class="form-group">
                                    <label><fmt:message key = "label.confirm.password"/></label><br>
                                    <div class="input-group mb-3">
                                        <input type="password" class="form-control" id="confirmPasswordInputDoctor" name="doctorConfirmPassword" required>
                                        <div class="input-group-append">
                                            <button class="btn btn-outline-info" type="button" id="showDoctorConfirmPasswordButton" onclick="showDoctorConfirmPassword()">Show</button>
                                        </div>
                                    </div>
                                    <c:if test = "${requestScope.doctorConfirmPasswordNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.confirm.password.not.valid"/></small>
                                    </c:if>
                                </div>


                                <div class="form-group">
                                    <label><fmt:message key = "label.working.place"/></label>
                                    <select class="form-control" name="medCenterId">
                                        <c:forEach var = "medCenter" items="${sessionScope.allMedCenter}">
                                            <option value="${medCenter.id}">${medCenter.name} - ${medCenter.medInstitution}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label><fmt:message key = "label.profession"/></label>
                                    <select class="form-control" name="professionId">
                                        <c:forEach var = "profession" items="${sessionScope.allProfession}">
                                            <option value="${profession.id}">${profession.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label><fmt:message key = "label.experience"/></label>
                                    <input type="number" class="form-control" min="1" max="60" name="experience" value="<c:out value = "${requestScope.doctorExperience}"/>">
                                </div>



                                <br>
                                <button type="submit" class="btn btn-info btn-block" name="signUpDoctor" value="registerDoctor"><fmt:message key = "button.sign.up"/></button>

                                <hr>
                                <div class="d-flex justify-content-center">
                                    <span><fmt:message key = "span.have.account"/><a href=""> <fmt:message key = "button.login"/></a></span>
                                </div>
                                <hr>
                                <div class="d-flex justify-content-start">
                                    <span><a href="index.jsp"> <- <fmt:message key = "link.back.to.main"/></a></span>
                                </div>
                            </form>


                        </div>
                    </div>



                </div>
            </div>

        </div>


    </div>
</div>





<script>
    function showPassword() {
        var passwordInput = document.getElementById("passwordInput");
        var showPasswordButton = document.getElementById("showPasswordButton");
        if(passwordInput.type === "password"){
            passwordInput.type = "text";
            showPasswordButton.innerText = "Hide";
        }else{
            passwordInput.type = "password";
            showPasswordButton.innerText = "Show";
        }
    }

    function showDoctorPassword(){
        var passwordInput = document.getElementById("passwordInputDoctor");
        var showPasswordButton = document.getElementById("showDoctorPasswordButton");
        if(passwordInput.type === "password"){
            passwordInput.type = "text";
            showPasswordButton.innerText = "Hide";
        }else{
            passwordInput.type = "password";
            showPasswordButton.innerText = "Show";
        }
    }


    function showConfirmPassword(){
        var passwordInput = document.getElementById("confirmPasswordInput");
        var showPasswordButton = document.getElementById("showConfirmPasswordButton");
        if(passwordInput.type === "password"){
            passwordInput.type = "text";
            showPasswordButton.innerText = "Hide";
        }else{
            passwordInput.type = "password";
            showPasswordButton.innerText = "Show";
        }
    }

    function showDoctorConfirmPassword(){
        var passwordInput = document.getElementById("confirmPasswordInputDoctor");
        var showPasswordButton = document.getElementById("showDoctorConfirmPasswordButton");
        if(passwordInput.type === "password"){
            passwordInput.type = "text";
            showPasswordButton.innerText = "Hide";
        }else{
            passwordInput.type = "password";
            showPasswordButton.innerText = "Show";
        }
    }
</script>



<jsp:include page="scripts.jsp"/>
</body>
</html>



