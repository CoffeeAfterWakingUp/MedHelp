<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.currentLanguage}"/>
<fmt:setBundle basename="Language"/>
<div class="container-fluid bg-white pt-5">
    <div class="container">
        <div class="row">
            <div class="col">
                <h4><span style="color: crimson">Med</span><span style="color: lightskyblue">Help</span></h4>
                <br>
                <a href="#joinUs"><fmt:message key = "join.us.msg"/></a><br><br>
            </div>
        </div>

        <hr>
        <div class="row">
            <div class="col">
                &copy; 2020-2021 MedHelp
            </div>
            <div class="col text-right">
                E-mail: 180103273@stu.sdu.edu.kz
            </div>
        </div>
    </div>

</div>
