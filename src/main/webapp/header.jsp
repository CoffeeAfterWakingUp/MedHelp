<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLanguage}"/>
<fmt:setBundle basename="Language"/>


<nav class="navbar navbar-expand-lg navbar-light bg-white sticky-top">
    <div class="container py-2">
        <a class="navbar-brand" href="index.jsp">
            <h4><span style="color: crimson">Med</span><span style="color: lightskyblue">Help</span></h4>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class="container border-right mr-3">
                <ul class="nav justify-content-center">
                    <li class="nav-item">
                        <form action="pharmacyAll" method="get" class="form-inline my-2 my-lg-0">
                            <button type="submit" class="btn" style="color: blue;"><fmt:message key = "card.pharmacy"/></button>
                        </form>

                    </li>
                    <li class="nav-item">
                        <form action="medicineAll" method="get" class="form-inline my-2 my-lg-0">
                            <button type="submit" class="btn" style="color: blue;"><fmt:message key = "card.medicines"/></button>
                        </form>
                    </li>

                </ul>
            </div>

            <form class="form-inline my-2 my-lg-0 mr-3" title="Select language" action="changeLanguage" method="post">
                <select class="form-control" name="language" onchange="this.form.submit()" id="select">
                    <option></option>
                    <option value="ru">RU</option>
                    <option value="en">EN</option>
                </select>
            </form>

            <c:choose>
                <c:when test = "${sessionScope.currentUser != null}">
                    <div class="dropdown">
                        <button class="btn dropdown-toggle" type="button" id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ${sessionScope.currentUser.lastName}  ${sessionScope.currentUser.firstName}
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu">
                            <form action="logout" method="get">
                                <button class="dropdown-item" type="submit">Log out</button>
                            </form>
                        </div>
                    </div>
                </c:when>

                <c:when test = "${sessionScope.currentDoctor != null}">
                    <div class="dropdown">
                        <button class="btn dropdown-toggle" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                ${sessionScope.currentDoctor.lastName}  ${sessionScope.currentDoctor.firstName}
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                            <form action="logoutDoctor" method="get">
                                <button class="dropdown-item" type="submit">Log out</button>
                            </form>
                        </div>
                    </div>
                </c:when>


                <c:otherwise>
                    <a href="login.jsp" class="nav-link"><fmt:message key = "login"/></a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>






