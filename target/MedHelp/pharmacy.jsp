<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <c:if test = "${requestScope.pharmacy != null}">
                <div class="col">
                    <h2>${requestScope.pharmacy.name}</h2>
                    <h4><small>${requestScope.pharmacy.city.name} , ${requestScope.pharmacy.address}</small></h4>
                    <span style="color: royalblue;">
                    <i class="fas fa-phone"></i>  ${requestScope.pharmacy.phone}
                </span>
                </div>
            </c:if>



        </div>

        <div class="row">
            <div class="col">
                <hr>
                <h6><fmt:message key = "pharmacy.schedule"/></h6>
                <div class="alert alert-dark" role="alert">
                    <fmt:message key = "alert.pharmacy.work.schedule"/>
                </div>
            </div>
            <div class="col">
                <br>
                <h6><fmt:message key = "search.medicine.in.pharmacy"/></h6>
                <div class="mt-2">
                    <form>
                        <div class="input-group shadow-sm">
                            <input type="text" class="form-control" id="searchMedicine" onkeyup="searchMedicineOnKeyUp()" placeholder="<fmt:message key = "card.medicines"/>">
                            <div class="input-group-append">
                                <button class="btn btn-outline-info" type="button" ><i class="fas fa-search"></i></button>
                            </div>
                        </div>
                    </form>
                </div>


                <p id="countOfMedicine">Найдено ${fn:length(requestScope.medicineOfPharmacy)} лекарств</p>



                <c:forEach var = "medicineOfPharmacy" items="${requestScope.medicineOfPharmacy}">
                    <div class="card mb-3 shadow-sm cards">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-2 align-self-center">
                                <span style="font-size: 50px;color: green;" class="mt-2">
                                    <i class="fas fa-prescription-bottle-alt"></i>
                                </span>
                                </div>
                                <div class="col">
                                    <h5 class="card-title">${medicineOfPharmacy.medicine.name}</h5>
                                    <c:if test = "${medicineOfPharmacy.medicine.medicineFor != null}">
                                        <span>${medicineOfPharmacy.medicine.medicineFor.name}</span><br>
                                    </c:if>
                                    <c:if test = "${medicineOfPharmacy.medicine.medicineFrom != null}">
                                        <span>${medicineOfPharmacy.medicine.medicineFrom.name}</span><br>
                                    </c:if>
                                    <c:if test = "${medicineOfPharmacy.medicine.medicineHow != null}">
                                        <span>${medicineOfPharmacy.medicine.medicineHow.name}</span>
                                    </c:if>
                                    <span class="font-weight-bold">${medicineOfPharmacy.price} тг</span>

                                    <form action="medicine" method="get">
                                        <input type="hidden" name="medicineId" value="${medicineOfPharmacy.medicine.id}">
                                        <button type="submit" class="btn" name="showMedicine" value="1"><a class="stretched-link"></a></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>






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



