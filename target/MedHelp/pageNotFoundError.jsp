<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLanguage}"/>
<fmt:setBundle basename="Language"/>
<html>
<head>
    <jsp:include page="head.jsp"/>
</head>
<body class="bg-light">

    <div class="container-fluid pb-5 bg-light h-100" style="background-color: #F2F4F8;">
        <div class="row">
            <div class="col-6 offset-3 pt-5">
                <div>
                    <a href="index.jsp" class=""><span style="font-size: 13px;"><i class="fas fa-arrow-left"></i></span> <fmt:message key = "link.back.to.main"/></a>
                </div>
                <div class="text-center">
                    <h1 style="font-size: 100px;">404</h1>
                    <h2><fmt:message key = "page.not.found.error"/></h2>
                    <p><fmt:message key = "page.not.found.error.msg"/></p>
                </div>
            </div>
        </div>

    </div>


    <jsp:include page="scripts.jsp"/>
</body>
</html>






