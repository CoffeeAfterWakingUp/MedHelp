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

                        <!--User login page-->
                        <div class="tab-content" id="nav-tabContent">
                            <div class="tab-pane fade show active" id="nav-user" role="tabpanel" aria-labelledby="nav-user-tab">
                                <form class="pt-4" method="post" action="login">
                                    <div class="form-group">
                                        <label for="emailInput"><fmt:message key = "label.email"/></label>
                                        <input type="email" class="form-control" id="emailInput" aria-describedby="emailHelp" name="email" value="${requestScope.email}" required>
                                    </div>

                                    <div class="form-group">
                                        <label><fmt:message key = "label.password"/></label><br>
                                        <div class="input-group mb-3">
                                            <input type="password" class="form-control" id="passwordInput" aria-describedby="passwordHelp" name="password" required>
                                            <div class="input-group-append">
                                                <button class="btn btn-outline-info" type="button" id="showPasswordButton" onclick="showPassword()">Show</button>
                                            </div>
                                        </div>
                                    </div>

                                    <c:if test = "${requestScope.authNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.auth.not.valid"/></small>
                                        <br>
                                    </c:if>

                                    <br>
                                    <button type="submit" class="btn btn-info btn-block" name="loginAsUser" value="1"><fmt:message key = "button.login"/></button>

                                    <hr>
                                    <div class="d-flex justify-content-center">
                                        <span><fmt:message key = "span.do.not.have.account"/><a href="register"> <fmt:message key = "button.sign.up"/></a></span>
                                    </div>
                                    <hr>
                                    <div class="d-flex justify-content-start">
                                        <span><a href="index.jsp"> <- <fmt:message key = "link.back.to.main"/></a></span>
                                    </div>
                                </form>
                            </div>



                            <!--Doctor login page-->
                            <div class="tab-pane fade" id="nav-doctor" role="tabpanel" aria-labelledby="nav-doctor-tab">
                                <form class="pt-4" method="post" action="loginDoctor">
                                    <div class="form-group">
                                        <label for="emailInputDoctor"><fmt:message key = "label.email"/></label>
                                        <input type="email" class="form-control" id="emailInputDoctor" aria-describedby="emailHelp" name="doctorEmail" value="${requestScope.doctorEmail}" required>

                                    </div>

                                    <div class="form-group">
                                        <label for="passwordInputDoctor"><fmt:message key = "label.password"/></label><br>
                                        <div class="input-group mb-3">
                                            <input type="password" class="form-control" id="passwordInputDoctor" aria-describedby="doctorPasswordHelp" name="doctorPassword" required>
                                            <div class="input-group-append">
                                                <button class="btn btn-outline-info" type="button" id="showDoctorPasswordButton" onclick="showDoctorPassword()">Show</button>
                                            </div>
                                        </div>
                                    </div>

                                    <c:if test = "${requestScope.doctorAuthNotValid != null}">
                                        <small class="text-danger"><fmt:message key = "error.auth.not.valid"/></small>
                                        <br>
                                    </c:if>



                                    <br>
                                    <button type="submit" class="btn btn-info btn-block" name="loginAsDoctor" value="1"><fmt:message key = "button.login"/></button>

                                    <hr>
                                    <div class="d-flex justify-content-center">
                                        <span><fmt:message key = "span.do.not.have.account"/><a href="register"> <fmt:message key = "button.sign.up"/></a></span>
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
    </script>



<jsp:include page="scripts.jsp"/>
</body>
</html>



