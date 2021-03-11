<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
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

        <div class="row">
            <c:if test = "${requestScope.medicine != null}">
                <div class="col">
                    <h2>${requestScope.medicine.name} от <small>${requestScope.medicine.manufacturer}<span class="text-secondary">(${fn:length(requestScope.doctorComments)} <fmt:message key = "medicine.review"/>)</span></small></h2>
                    <c:if test = "${requestScope.medicine.medicineFor != null}">
                        <h4><small>${requestScope.medicine.medicineFor.name}</small></h4>
                    </c:if>
                    <c:if test = "${requestScope.medicine.medicineHow != null}">
                        <h4><small>${requestScope.medicine.medicineHow.name}</small></h4>
                    </c:if>
                    <c:if test = "${requestScope.medicine.medicineFrom != null}">
                        <h4><small>${requestScope.medicine.medicineFrom.name}</small></h4>
                    </c:if>
                    <span><fmt:message key = "medicine.efficiency.rating"/> ${requestScope.avgEfficiencyRating}/5</span><br>
                    <span><fmt:message key = "medicine.side.effects.rating"/> ${requestScope.avgSideEffectsRating}/5</span><br>
                    <span><fmt:message key = "medicine.price.and.quality.rating"/> ${requestScope.avgPriceAndQualityRating}/5</span>
                </div>
            </c:if>
        </div>


        <div class="row">
            <div class="col pt-4">
                <c:choose>

                    <c:when test = "${sessionScope.currentUser != null}">
                        <form action="commentAsUserPage" method="post">
                            <input type="hidden" name="medicineId" value="${requestScope.medicine.id}">
                            <input type="hidden" name="userId" value="${sessionScope.currentUser.id}">
                            <button type="submit" class="btn btn-info" name="commentAsUser" value="1"><fmt:message key = "button.leave.comment"/></button>
                        </form>
                    </c:when>

                    <c:when test = "${sessionScope.currentDoctor != null}">
                        <form action="commentAsDoctorPage" method="post">
                            <input type="hidden" name="medicineId" value="${requestScope.medicine.id}">
                            <input type="hidden" name="doctorId" value="${sessionScope.currentDoctor.id}">
                            <button type="submit" class="btn btn-info" name="commentAsDoctor" value="1"><fmt:message key = "button.leave.comment"/></button>
                        </form>
                    </c:when>

                    <c:otherwise>
                        <h5><fmt:message key = "for.comment.login.msg"/></h5>
                    </c:otherwise>
                </c:choose>


            </div>
        </div>

        <div class="row">
            <div class="col pt-4">

                <nav>
                    <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                        <a class="nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true"><fmt:message key = "medicine.tab.instruction"/></a>
                        <a class="nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false"><fmt:message key = "medicine.tab.reviews.of.doctors"/> (${fn:length(requestScope.doctorComments)})</a>
                        <a class="nav-link" id="nav-otsivi-pacientov-tab" data-toggle="tab" href="#nav-otsivi-pacientov" role="tab" aria-controls="nav-profile" aria-selected="false"><fmt:message key = "medicine.tab.reviews.of.users"/> (${fn:length(requestScope.userComments)})</a>
                        <a class="nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false"><fmt:message key = "medicine.tab.where.can.i.buy"/></a>
                    </div>
                </nav>

                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                        <div class="row">
                            <div class="col pt-4">

                                <c:if test = "${requestScope.medicine != null}">



                                    <div class="pb-3">
                                        <h5><fmt:message key = "medicine.tab.instruction.short.description"/></h5>
                                        <p>${requestScope.medicine.shortDescription}</p>
                                    </div>

                                    <div class="pb-3">
                                        <h5><fmt:message key = "medicine.tab.instruction.pharmacology"/></h5>
                                        <p>${requestScope.medicine.pharmacology}</p>
                                    </div>

                                    <div class="pb-3">
                                        <h5><fmt:message key = "medicine.tab.instruction.dosage"/></h5>
                                        <p>${requestScope.medicine.dosage}</p>
                                    </div>

                                    <div class="pb-3">
                                        <h5><fmt:message key = "medicine.tab.instruction.side.effects"/></h5>
                                        <p>${requestScope.medicine.sideEffect}</p>
                                    </div>

                                    <div class="pb-3">
                                        <h5><fmt:message key = "medicine.tab.instruction.indications"/></h5>
                                        <p>${requestScope.medicine.indication}</p>
                                    </div>

                                    <div class="pb-3">
                                        <h5><fmt:message key = "medicine.tab.instruction.contraindications"/></h5>
                                        <p>${requestScope.medicine.contraindication}</p>
                                    </div>


                                </c:if>

                            </div>
                        </div>
                    </div>


                    <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                        <div class="row">
                            <div class="col-10 pt-4 offset-1">

                                <c:forEach var = "comment" items="${requestScope.doctorComments}">
                                    <div class="card border-0 mb-4" style="">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-1 align-self-center">
                                                <span style="font-size: 50px;" class="text-info">
                                                    <i class="fas fa-comment-medical"></i>
                                                </span>
                                                </div>
                                                <div class="col">
                                                    <div class="row">
                                                        <div class="col-10">
                                                            <a href=""><h5 class="card-title">${comment.doctor.lastName} ${comment.doctor.firstName} <small class="text-muted">${comment.doctor.profession.name},стаж ${comment.doctor.experience} лет</small></h5></a>
                                                        </div>
                                                        <div class="col">
                                                            <h6 class="text-muted mt-1" style="font-size: 13px;">${comment.date}</h6>
                                                        </div>
                                                    </div>


                                                    <h6 class="card-subtitle mb-2" style=""><fmt:message key = "medicine.efficiency.rating"/>  ${comment.efficiency_rating}/5</h6>
                                                    <h6 class="card-subtitle mb-2" style=""><fmt:message key = "medicine.price.and.quality.rating"/>  ${comment.priceAndQuality_rating}/5</h6>
                                                    <h6 class="card-subtitle mb-2" style=""><fmt:message key = "medicine.side.effects.rating"/>  ${comment.sideEffects_rating}/5</h6>

                                                    <c:if test = "${comment.pros != null}">
                                                        <p class="card-text">
                                                        <span style="color:green;font-size: 15px;">
                                                            <i class="fas fa-plus"></i>
                                                        </span>
                                                            ${comment.pros}
                                                        </p>
                                                    </c:if>


                                                    <c:if test = "${comment.cons != null}">
                                                        <p class="card-text">
                                                        <span style="color:green;font-size: 15px;">
                                                            <i class="fas fa-minus"></i>
                                                        </span>
                                                                ${comment.cons}
                                                        </p>
                                                    </c:if>

                                                    <c:if test = "${comment.info != null}">
                                                        <p class="card-text">
                                                        <span style="color:green;font-size: 15px;">
                                                            <i class="fas fa-info"></i>
                                                        </span>
                                                                ${comment.info}
                                                        </p>
                                                    </c:if>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>



                            </div>
                        </div>
                    </div>





                    <div class="tab-pane fade" id="nav-otsivi-pacientov" role="tabpanel" aria-labelledby="nav-otsivi-pacientov-tab">

                        <div class="row">
                            <div class="col-10 pt-4 offset-1">


                                <c:forEach var = "comment" items="${requestScope.userComments}">
                                    <div class="card border-0 mb-4" style="">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-1 align-self-center">
                                                <span style="font-size: 50px;" class="text-info">
                                                    <i class="fas fa-comment-dots"></i>
                                                </span>
                                                </div>
                                                <div class="col">
                                                    <div class="row">
                                                        <div class="col-10">
                                                            <h5 class="card-title">${comment.user.lastName} ${comment.user.firstName}</h5>
                                                        </div>
                                                        <div class="col">
                                                            <h6 class="text-muted mt-1" style="font-size: 13px;">${comment.date}</h6>
                                                        </div>
                                                    </div>


                                                    <p class="card-text">
                                                    <span class="text-info">
                                                         <i class="fas fa-info"></i>
                                                    </span>
                                                        ${comment.info}
                                                    </p>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>



                            </div>
                        </div>

                    </div>








                    <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">

                        <div class="row">
                            <div class="col-8 pt-4 offset-1">
                                <c:choose>

                                    <c:when test = "${requestScope.pharmacyOfMedicineByCity == null}">
                                        <p>Найдено ${fn:length(requestScope.pharmacyOfMedicine)} аптек</p>
                                        <c:forEach var = "p" items="${requestScope.pharmacyOfMedicine}">
                                            <div class="card mb-3 shadow-sm cards">
                                                <div class="card-body">
                                                    <div class="row">
                                                        <div class="col-1 align-self-center">
                                                <span style="font-size: 45px;color: blue;">
                                                    <i class="fas fa-clinic-medical"></i>
                                                </span>
                                                        </div>
                                                        <div class="col">
                                                            <div class="row">
                                                                <div class="col-10">
                                                                    <h5 class="card-title">${p.pharmacy.name}</h5>
                                                                </div>
                                                                <div class="col">
                                                                    <span>${p.price} тг</span>
                                                                </div>
                                                            </div>
                                                            <h6 class="card-subtitle mb-2 text-muted">${p.pharmacy.city.name}, ${p.pharmacy.address}</h6>
                                                            <span style="color: royalblue;"><i class="fas fa-phone"></i>  ${p.pharmacy.phone}</span>
                                                            <form action="pharmacy" method="get">
                                                                <input type="hidden" value="${p.pharmacy.id}" name="pharmacy_id">
                                                                <button type="submit" class="btn" name="showPharmacyById" value="1"><a class="stretched-link"></a></button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:when>

                                    <c:otherwise>
                                        <p>Найдено ${fn:length(requestScope.pharmacyOfMedicineByCity)} аптек
                                            <c:if test = "${requestScope.cityOfPharmacy != null}">
                                                <span>(${requestScope.cityOfPharmacy.name})</span>
                                            </c:if>
                                        </p>
                                        <c:forEach var = "p" items="${requestScope.pharmacyOfMedicineByCity}">
                                            <div class="card mb-3 shadow-sm cards">
                                                <div class="card-body">
                                                    <div class="row">
                                                        <div class="col-1 align-self-center">
                                                <span style="font-size: 45px;color: blue;">
                                                    <i class="fas fa-clinic-medical"></i>
                                                </span>
                                                        </div>
                                                        <div class="col">
                                                            <div class="row">
                                                                <div class="col-10">
                                                                    <h5 class="card-title">${p.pharmacy.name}</h5>
                                                                </div>
                                                                <div class="col">
                                                                    <span>${p.price} тг</span>
                                                                </div>
                                                            </div>
                                                            <h6 class="card-subtitle mb-2 text-muted">${p.pharmacy.city.name}, ${p.pharmacy.address}</h6>
                                                            <span style="color: royalblue;"><i class="fas fa-phone"></i>  ${p.pharmacy.phone}</span>
                                                            <form action="pharmacy" method="get">
                                                                <input type="hidden" value="${p.pharmacy.id}" name="pharmacy_id">
                                                                <button type="submit" class="btn" name="showPharmacyById" value="1"><a class="stretched-link"></a></button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <div class="col-3 pt-5">
                                <p></p>
                                <div class="card">
                                    <div class="card-body">
                                        <form action="pharmacyOfMedicineByCity" method="get">
                                            <div class="form-group">
                                                <label><fmt:message key = "search.city"/> </label>
                                                <select class="form-control" name="city_id">
                                                    <c:forEach var = "city" items="${requestScope.allCity}">
                                                        <option value="${city.id}" <c:if test="${city.name == requestScope.cityOfPharmacy.name }"><c:out value = "selected"/></c:if>>${city.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <input type="hidden" name="medicineId" value="${requestScope.medicine.id}">
                                            <button type="submit" class="btn btn-outline-info" name="showPharmacyByCityButton" value="1"><fmt:message key = "button.show"/> </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>
        </div>





















    </div>
</div>








<jsp:include page="footer.jsp"/>

<script>
    function searchMedicineOnKeyUp() {
        var input = document.getElementById("searchMedicine");
        var filter = input.value.toUpperCase();
        var cards = document.getElementsByClassName("cards");
        var cardsTitle = document.getElementsByClassName("card-title");
        var countOfMedicine = document.getElementById("countOfMedicine");
        var count = 0;
        for (var i = 0; i < cards.length; i++) {
            if(cardsTitle[i].innerText.toUpperCase().indexOf(filter) > -1){
                cards[i].style.display="";
                count++;
            }else{
                cards[i].style.display="none";
            }
        }
        countOfMedicine.innerText = "Найдено " + count + " лекарств";
    }
</script>

<jsp:include page="scripts.jsp"/>
</body>
</html>



