<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLanguage}"/>
<fmt:setBundle basename="Language"/>
<div class="container-fluid py-5 bg-light" id="joinUs" style="background-color: #F2F4F8;">
    <div class="container">
        <h3><fmt:message key = "join.us.msg"/></h3>
        <br>
        <br>
        <div class="row">
            <div class="col">
                <div class="card h-100 ">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key = "card.join.pharmacy"/></h5>
                        <hr>
                        <p class="card-text"><fmt:message key = "card.join.pharmacy.msg"/></p>
                        <br>
                        <button type="button" class="btn btn-block text-white" data-toggle="modal" data-target="#modal" style="background-color: crimson;"><fmt:message key = "button.join.pharmacy"/></button>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key = "card.join.doctor"/></h5>
                        <hr>
                        <p class="card-text"><fmt:message key = "card.join.doctor.msg"/></p>
                        <a href="register" class="btn btn-block text-white" style="background-color: lightskyblue;"><fmt:message key = "button.join.doctor"/></a>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card h-100 ">
                    <div class="card-body">
                        <h5 class="card-title text-center"><fmt:message key = "card.join.med.center"/></h5>
                        <hr>
                        <p class="card-text"><fmt:message key = "card.join.med.center.msg"/></p>
                        <br>
                        <button type="button" class="btn btn-block text-white" data-toggle="modal" data-target="#modal" style="background-color: crimson;"><fmt:message key = "button.join.med.center"/></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h5><fmt:message key = "leave.request.msg"/></h5>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
