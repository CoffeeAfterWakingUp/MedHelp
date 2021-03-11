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
                <h3><fmt:message key = "search.medicines"/>
                    <c:if test = "${requestScope.medicineGroupName != null}">
                        <span>(${requestScope.medicineGroupName})</span>
                    </c:if>
                </h3>
            </div>
        </div>

        <div class="row">
            <div class="col-6 offset-3">
                <form>
                    <div class="input-group shadow-sm">
                        <input type="text" class="form-control" id="searchMedicine" onkeyup="searchMedicineOnKeyUp()" placeholder="<fmt:message key = "search.medicines.placeholder"/>">
                        <div class="input-group-append">
                            <button class="btn btn-outline-info" type="button" ><i class="fas fa-search"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>


        <c:choose>

            <c:when test = "${requestScope.medicinesOfForGroup != null}">
                <div class="row">
                    <div class="col-6 offset-3">
                        <p class="countOfMedicine">Найдено ${fn:length(requestScope.medicinesOfForGroup)} лекарств</p>
                    </div>
                </div>


                <div class="row">
                    <div class="col-6 offset-3">
                        <c:forEach var = "medicine" items="${requestScope.medicinesOfForGroup}">
                            <div class="card mb-3 shadow-sm cards">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-2 align-self-center">
                                        <span style="font-size: 50px;color: green;" class="mt-2">
                                            <i class="fas fa-prescription-bottle-alt"></i>
                                        </span>
                                        </div>
                                        <div class="col">
                                            <h5 class="card-title">${medicine.name}</h5>
                                            <c:if test = "${medicine.medicineFor != null}">
                                                <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineFor.name}</h6>
                                            </c:if>
                                            <c:if test = "${medicine.medicineHow != null}">
                                                <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineHow.name}</h6>
                                            </c:if>
                                            <c:if test = "${medicine.medicineFrom != null}">
                                                <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineFrom.name}</h6>
                                            </c:if>
                                            <form action="medicine" method="get">
                                                <input type="hidden" name="medicineId" value="${medicine.id}">
                                                <button type="submit" class="btn" name="showMedicine" value="1"><a class="stretched-link"></a></button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
            </c:when>



                    <c:when test = "${requestScope.medicinesOfHowGroup != null}">
                        <div class="row">
                            <div class="col-6 offset-3">
                                <p class="countOfMedicine">Найдено ${fn:length(requestScope.medicinesOfHowGroup)} лекарств</p>
                            </div>
                        </div>


                        <div class="row">
                            <div class="col-6 offset-3">
                                <c:forEach var = "medicine" items="${requestScope.medicinesOfHowGroup}">
                                    <div class="card mb-3 shadow-sm cards">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-2 align-self-center">
                                            <span style="font-size: 50px;color: green;" class="mt-2">
                                                <i class="fas fa-prescription-bottle-alt"></i>
                                            </span>
                                                </div>
                                                <div class="col">
                                                    <h5 class="card-title">${medicine.name}</h5>
                                                    <c:if test = "${medicine.medicineFor != null}">
                                                        <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineFor.name}</h6>
                                                    </c:if>
                                                    <c:if test = "${medicine.medicineHow != null}">
                                                        <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineHow.name}</h6>
                                                    </c:if>
                                                    <c:if test = "${medicine.medicineFrom != null}">
                                                        <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineFrom.name}</h6>
                                                    </c:if>
                                                    <form action="medicine" method="get">
                                                        <input type="hidden" name="medicineId" value="${medicine.id}">
                                                        <button type="submit" class="btn" name="showMedicine" value="1"><a class="stretched-link"></a></button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:when>

                            <c:when test = "${requestScope.medicinesOfFromGroup != null}">
                            <div class="row">
                                <div class="col-6 offset-3">
                                    <p class="countOfMedicine">Найдено ${fn:length(requestScope.medicinesOfFromGroup)} лекарств</p>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-6 offset-3">
                                    <c:forEach var = "medicine" items="${requestScope.medicinesOfFromGroup}">
                                        <div class="card mb-3 shadow-sm cards">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col-2 align-self-center">
                                            <span style="font-size: 50px;color: green;" class="mt-2">
                                                <i class="fas fa-prescription-bottle-alt"></i>
                                            </span>
                                                    </div>
                                                    <div class="col">
                                                        <h5 class="card-title">${medicine.name}</h5>
                                                        <c:if test = "${medicine.medicineFor != null}">
                                                            <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineFor.name}</h6>
                                                        </c:if>
                                                        <c:if test = "${medicine.medicineHow != null}">
                                                            <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineHow.name}</h6>
                                                        </c:if>
                                                        <c:if test = "${medicine.medicineFrom != null}">
                                                            <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineFrom.name}</h6>
                                                        </c:if>
                                                        <form action="medicine" method="get">
                                                            <input type="hidden" name="medicineId" value="${medicine.id}">
                                                            <button type="submit" class="btn" name="showMedicine" value="1"><a class="stretched-link"></a></button>
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
                            <p class="countOfMedicine">Найдено ${fn:length(requestScope.allMedicine)} лекарств</p>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-6 offset-3">
                            <c:forEach var = "medicine" items="${requestScope.allMedicine}">
                                <div class="card mb-3 shadow-sm cards">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-2 align-self-center">
                                        <span style="font-size: 50px;color: green;" class="mt-2">
                                            <i class="fas fa-prescription-bottle-alt"></i>
                                        </span>
                                            </div>
                                            <div class="col">
                                                <h5 class="card-title">${medicine.name}</h5>
                                                <c:if test = "${medicine.medicineFor != null}">
                                                    <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineFor.name}</h6>
                                                </c:if>
                                                <c:if test = "${medicine.medicineHow != null}">
                                                    <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineHow.name}</h6>
                                                </c:if>
                                                <c:if test = "${medicine.medicineFrom != null}">
                                                    <h6 class="card-subtitle mb-2 text-muted">${medicine.medicineFrom.name}</h6>
                                                </c:if>
                                                <form action="medicine" method="get">
                                                    <input type="hidden" name="medicineId" value="${medicine.id}">
                                                    <button type="submit" class="btn" name="showMedicine" value="1"><a class="stretched-link"></a></button>
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

                <div class="card mb-2">
                    <div class="card-body">
                        <form action="medicineByMedicineForId" method="get">
                            <div class="form-group">
                                <label><fmt:message key = "search.medicine.for"/></label>
                                <select class="form-control" name="medicineForId">
                                    <c:forEach var = "medicineFor" items="${requestScope.allMedicineForGroup}">
                                        <option value="${medicineFor.id}" <c:if test="${medicineFor.name == requestScope.medicineGroupName }"><c:out value = "selected"/></c:if>>${medicineFor.name}</option>
                                    </c:forEach>

                                </select>
                            </div>
                            <button type="submit" class="btn btn-outline-success" name="showMedicineFor" value="1"><fmt:message key = "button.show"/></button>
                        </form>
                    </div>
                </div>

                <div class="card mb-2">
                    <div class="card-body">
                        <form action="medicineByMedicineFromId" method="get">
                            <div class="form-group">
                                <label><fmt:message key = "search.medicine.from"/></label>
                                <select class="form-control" name="medicineFromId">
                                    <c:forEach var = "medicineFrom" items="${requestScope.allMedicineFromGroup}">
                                        <option value="${medicineFrom.id}" <c:if test="${medicineFrom.name == requestScope.medicineGroupName }"><c:out value = "selected"/></c:if>>${medicineFrom.name}</option>
                                    </c:forEach>

                                </select>
                            </div>
                            <button type="submit" class="btn btn-outline-success" name="showMedicineFrom" value="1"><fmt:message key = "button.show"/></button>
                        </form>
                    </div>
                </div>


                <div class="card mb-2">
                    <div class="card-body">
                        <form action="medicineByMedicineHowId" method="get">
                            <div class="form-group">
                                <label><fmt:message key = "search.medicine.how"/></label>
                                <select class="form-control" name="medicineHowId">
                                    <c:forEach var = "medicineHow" items="${requestScope.allMedicineHowGroup}">
                                        <option value="${medicineHow.id}" <c:if test="${medicineHow.name == requestScope.medicineGroupName }"><c:out value = "selected"/></c:if>>${medicineHow.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-outline-success" name="showMedicineHow" value="1"><fmt:message key = "button.show"/></button>
                        </form>
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
        var countOfMedicine = document.getElementsByClassName("countOfMedicine");
        var count = 0;
        for (var i = 0; i < cards.length; i++) {
            if(cardsTitle[i].innerText.toUpperCase().indexOf(filter) > -1){
                cards[i].style.display="";
                count++;
            }else{
                cards[i].style.display="none";
            }
        }
        countOfMedicine[0].innerText = "Найдено " + count + " лекарств";
        countOfMedicine[1].innerText = "Найдено " + count + " лекарств";
        countOfMedicine[2].innerText = "Найдено " + count + " лекарств";
        countOfMedicine[3].innerText = "Найдено " + count + " лекарств";
    }
</script>

<jsp:include page="scripts.jsp"/>
</body>
</html>




