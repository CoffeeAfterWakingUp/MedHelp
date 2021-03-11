<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLanguage}"/>
<fmt:setBundle basename="Language"/>
<html>
<head>
    <jsp:include page="head.jsp"/>
</head>
<body>
<jsp:include page="header.jsp"/>


<div class="container-fluid py-5 bg-light" style="background-color: #F2F4F8;">
    <div class="container">

        <div class="row mb-3">
            <div class="col-6 offset-3">
                <h3>
                    <fmt:message key = "card.pharmacy"/>
                    <c:if test = "${requestScope.cityOfPharmacy != null}">
                        <span>(${requestScope.cityOfPharmacy.name})</span>
                    </c:if>
                </h3>
            </div>
        </div>

        <div class="row">
            <div class="col-6 offset-3">
                <form>
                    <div class="input-group shadow-sm">
                        <input type="text" class="form-control" id="searchPharmacy" onkeyup="searchPharmacyOnKeyUp()" placeholder="<fmt:message key = "search.pharmacies.placeholder"/>">
                        <div class="input-group-append">
                            <button class="btn btn-outline-info" type="button"><i class="fas fa-search"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>


        <c:choose>

            <c:when test = "${requestScope.pharmacyByCityId == null}">
                <div class="row">
                    <div class="col-6 offset-3">
                        <p class="countOfPharmacy">Найдено ${fn:length(requestScope.allPharmacy)} аптек</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6 offset-3">
                        <c:forEach var = "pharmacy" items="${requestScope.allPharmacy}">
                            <div class="card mb-3 shadow-sm cards">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-2 align-self-center">
                                        <span style="font-size: 50px;color: blue;">
                                            <i class="fas fa-clinic-medical"></i>
                                        </span>
                                        </div>
                                        <div class="col">
                                            <h5 class="card-title"><c:out value = "${pharmacy.name}"/></h5>
                                            <h6 class="card-subtitle mb-2 text-muted"><c:out value = "${pharmacy.city.name}"/> , <c:out value = "${pharmacy.address}"/></h6>
                                            <span style="color: royalblue;"><i class="fas fa-phone"></i>  <c:out value = "${pharmacy.phone}"/></span>
                                            <form action="pharmacy" method="get">
                                                <input type="hidden" value="${pharmacy.id}" name="pharmacy_id">
                                                <button type="submit" name="showPharmacyById" value="1" class="btn"><a class="stretched-link"></a></button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
            </c:when>

            <c:otherwise>
                    <div class="row">
                        <div class="col-6 offset-3">
                            <p class="countOfPharmacy">Найдено ${fn:length(requestScope.pharmacyByCityId)} аптек</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6 offset-3">
                            <c:forEach var = "pharmacy" items="${requestScope.pharmacyByCityId}">
                                <div class="card mb-3 shadow-sm cards">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-2 align-self-center">
                                        <span style="font-size: 50px;color: blue;">
                                            <i class="fas fa-clinic-medical"></i>
                                        </span>
                                            </div>
                                            <div class="col">
                                                <h5 class="card-title"><c:out value = "${pharmacy.name}"/></h5>
                                                <h6 class="card-subtitle mb-2 text-muted"><c:out value = "${pharmacy.city.name}"/> , <c:out value = "${pharmacy.address}"/></h6>
                                                <span style="color: royalblue;"><i class="fas fa-phone"></i>  <c:out value = "${pharmacy.phone}"/></span>
                                                <form action="pharmacy" method="get">
                                                    <input type="hidden" value="${pharmacy.id}" name="pharmacy_id">
                                                    <button type="submit" name="showPharmacyById" value="1" class="btn"><a class="stretched-link"></a></button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
            </c:otherwise>
        </c:choose>



            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <form action="pharmacyByCity" method="get">
                            <div class="form-group">
                                <label for="exampleFormControlSelect1"><fmt:message key = "search.city"/></label>
                                <select class="form-control" id="exampleFormControlSelect1" name="city_id">
                                    <c:forEach var = "city" items="${requestScope.allCity}">
                                        <option value="${city.id}" <c:if test="${city.name == requestScope.cityOfPharmacy.name }"><c:out value = "selected"/></c:if>>${city.name}</option>
                                    </c:forEach>

                                </select>
                            </div>

                            <button type="submit" class="btn btn-outline-info" name="showPharmacyByCityButton" value="showPharmacyByCity"><fmt:message key = "button.show"/></button>

                        </form>
                    </div>
                </div>
            </div>

        </div>


        </div>
    </div>

</div>



<jsp:include page="footer.jsp"/>

<jsp:include page="scripts.jsp"/>
<script>
    function searchPharmacyOnKeyUp() {
        var input = document.getElementById("searchPharmacy");
        var filter = input.value.toUpperCase();
        var cards = document.getElementsByClassName("cards");
        var cardsTitle = document.getElementsByClassName("card-title");
        var countOfPharmacy = document.getElementsByClassName("countOfPharmacy");
        var count = 0;
        for (var i = 0; i < cards.length; i++) {
            if(cardsTitle[i].innerText.toUpperCase().indexOf(filter) > -1){
                count = count + 1;
                cards[i].style.display="";
            }else{
                cards[i].style.display="none";
            }
        }

        countOfPharmacy[0].innerText = "Найдено " + count + " аптек";
        countOfPharmacy[1].innerText = "Найдено " + count + " аптек";
    }
</script>

</body>
</html>



