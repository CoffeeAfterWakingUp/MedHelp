<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLanguage}"/>
<fmt:setBundle basename="Language"/>
<html>
<head>
    <jsp:include page="head.jsp"/>


    <style>
        .cards:hover{
            background-color: #FBFBFB;
        }

    </style>
</head>
<body>
<jsp:include page="header.jsp"/>

<div class="container-fluid bg-white pb-5" style="padding-bottom: 30px;">
    <div class="container">

        <div class="pt-5">
            <div class="row">
                <div class="col">
                    <h3><fmt:message key = "welcome.msg"/> <span style="color: crimson">Med</span><span style="color: lightskyblue">Help</span></h3>
                </div>

            </div>

        </div>


        <div class="mt-5 pb-5">
            <div class="row row-cols-2">
                <div class="col">

                    <div class="card h-100 text-center w-80 rounded shadow-sm cards">
                        <span style="font-size: 70px;color:lightskyblue" class="pt-4">
                            <i class="fas fa-clinic-medical"></i>
                        </span>

                        <div class="card-body">
                            <h5 style="color: lightskyblue"><fmt:message key = "card.pharmacy"/></h5>
                            <form action="pharmacyAll" method="get">
                                <button type="submit" class="btn"><a class="stretched-link"></a></button>
                            </form>
                        </div>
                    </div>


                </div>
                <div class="col">

                    <div class="card h-100 text-center w-80 rounded shadow-sm cards">
                        <span style="font-size: 70px;color:lightskyblue" class="pt-4">
                            <i class="fas fa-pills"></i>
                        </span>

                        <div class="card-body">
                            <h5 style="color: lightskyblue"><fmt:message key = "card.medicines"/></h5>
                            <form action="medicineAll" method="get">
                                <button type="submit" class="btn"><a class="stretched-link"></a></button>
                            </form>
                        </div>
                    </div>

                </div>

<%--                <div class="col">--%>
<%--                    <div class="card h-100 text-center w-80 rounded shadow-sm cards">--%>
<%--                        <span style="font-size: 70px;color:lightskyblue" class="pt-4 cardIcon">--%>
<%--                            <i class="fas fa-hospital"></i>--%>
<%--                        </span>--%>

<%--                        <div class="card-body">--%>
<%--                            <h5 style="color: lightskyblue">Мед.центры</h5>--%>
<%--                            <a href="#" class="stretched-link"></a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--                <div class="col">--%>

<%--                    <div class="card h-100 text-center w-80 rounded shadow-sm cards">--%>
<%--                        <span style="font-size: 70px;color:lightskyblue" class="pt-4">--%>
<%--                            <i class="fas fa-user-md"></i>--%>
<%--                        </span>--%>

<%--                        <div class="card-body">--%>
<%--                            <h5 style="color: lightskyblue">Врачи</h5>--%>
<%--                            <a href="#" class="stretched-link"></a>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                </div>--%>
<%--                <div class="col">--%>
<%--                    <div class="card h-100 text-center w-80 rounded shadow-sm cards">--%>
<%--                        <span style="font-size: 70px;color:lightskyblue" class="pt-4 cardIcon">--%>
<%--                            <i class="fas fa-comment-medical"></i>--%>
<%--                        </span>--%>

<%--                        <div class="card-body">--%>
<%--                            <h5 style="color: lightskyblue">МедОтвет</h5>--%>
<%--                            <a href="#" class="stretched-link"></a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>




            </div>
        </div>


    </div>
</div>


<jsp:include page="joinUs.jsp"/>
<jsp:include page="footer.jsp"/>






<jsp:include page="scripts.jsp"/>
</body>
</html>


