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
        <div class="col-2 bg-info h-100" style="background-color: #1E282C;">
            <jsp:include page="adminNavbar.jsp"/>
        </div>
        <div class="col-5 pt-4 offset-2">
            <div class="container">
                <form method="post" action="changeMedicineByPharmacy">
                    <input type="hidden" value="${requestScope.medicineByPharmacy.id}" name="id">

                    <div class="form-group">
                        <label>Medicine</label>
                        <select class="form-control" name="medicineId">
                            <c:forEach var = "medicine" items="${requestScope.allMedicine}">
                                <option value="${medicine.id}">${medicine.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Pharmacy</label>
                        <select class="form-control" name="pharmacy_id">
                            <c:forEach var = "pharmacy" items="${requestScope.allPharmacy}">
                                <option value="${pharmacy.id}">${pharmacy.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Price</label>
                        <input type="text" class="form-control" value="${requestScope.medicineByPharmacy.price}" name="price">
                    </div>


                    <div class="form-group">
                        <label>Exist</label>
                        <select class="form-control" name="exist">
                            <option value="true">true</option>
                            <option value="false">false</option>
                        </select>
                    </div>

                    <br>
                    <button type="submit" class="btn btn-block btn-success" value="1" name="do">Change</button>
                </form>


                <div class="row">
                    <div class="col">
                        <form action="deleteMedicineByPharmacy" method="post">
                            <input type="hidden" value="${requestScope.medicineByPharmacy.id}" name="id">
                            <button type="submit" class="btn btn-block btn-danger" value="1" name="do">Delete</button>
                        </form>
                    </div>

                </div>
            </div>

        </div>
    </div>


</div>



<jsp:include page="scripts.jsp"/>
</body>
</html>








