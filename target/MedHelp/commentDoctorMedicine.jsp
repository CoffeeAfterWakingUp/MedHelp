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

        <div class="row">
            <div class="col">
                <h2><fmt:message key = "comment.on.medicine"/> ${requestScope.medicineName}</h2>
            </div>
        </div>

        <div class="row">
            <div class="col-8 pt-4">
                <c:if test = "${requestScope.successPostComment != null}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <p><fmt:message key = "success.post.comment"/></p>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>
            </div>
        </div>


        <div class="row">
            <div class="col-8 pt-4">
                <c:if test = "${requestScope.loginForComment != null}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <p><fmt:message key = "for.comment.login.msg"/></p>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>
            </div>
        </div>

        <div class="row">
            <div class="col-8 pt-4">
                <form method="post" action="commentMedicineAsDoctor">
                    <input type="hidden" name="medicineId" value="${requestScope.medicineId}">
                    <input type="hidden" name="doctorId" value="${requestScope.doctorId}">
                    <div class="form-group">
                        <label><fmt:message key = "medicine.pros"/></label>
                        <textarea class="form-control" rows="5" name="pros"></textarea>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key = "medicine.cons"/></label>
                        <textarea class="form-control" rows="5" name="cons"></textarea>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key = "medicine.info"/></label>
                        <textarea class="form-control" rows="5" name="info" required></textarea>
                    </div>
                    <div class="form-group w-50">
                        <label><fmt:message key = "rate.medicine.efficiency"/></label>
                        <input type="number" class="form-control" min="1" max="5" name="efficiencyRating" required>
                    </div>
                    <div class="form-group w-50">
                        <label><fmt:message key = "rate.medicine.price.and.quality"/></label>
                        <input type="number" class="form-control" min="1" max="5" name="priceAndQualityRating" required>
                    </div>
                    <div class="form-group w-50">
                        <label><fmt:message key = "rate.medicine.side.effects"/></label>
                        <input type="number" class="form-control" min="1" max="5" name="sideEffectsRating" required>
                    </div>

                    <br>
                    <button class="btn btn-block btn-info" name="postComment" value="1" type="submit"><fmt:message key = "button.leave.comment"/></button>

                </form>
            </div>

        </div>

    </div>
</div>








<jsp:include page="footer.jsp"/>

<jsp:include page="scripts.jsp"/>
</body>
</html>




