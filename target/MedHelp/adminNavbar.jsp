<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>





<ul class="nav flex-column pb-3">
    <a class="nav-link align-self-center text-white" href="#">
        <h4><span style="color: crimson">Med</span><span style="color: lightskyblue">Help</span></h4>
    </a>

    <div class="pt-3">
        <p class="text-center"><small>Admin page</small></p>
    </div>
    <li class="nav-item">
        <c:choose>

            <c:when test = "${sessionScope.currentAdmin != null}">
                <div class="dropdown">
                    <button class="btn dropdown-toggle text-white" type="button" id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                       ${sessionScope.currentAdmin.lastName} ${sessionScope.currentAdmin.firstName}
                    </button>
                    <div class="dropdown-menu" aria-labelledby="dropdownMenu">

                        <a class="dropdown-item" href="logoutAdmin">Logout</a>
                    </div>
                </div>
            </c:when>

            <c:otherwise>
                <a class="nav-link text-white" href="login.jsp">Login</a>
            </c:otherwise>
        </c:choose>

    </li>
    <div class="pt-3">
        <p class="text-center"><small>Tables</small></p>
    </div>
    <li class="nav-item">
        <a class="nav-link text-white" href="users">User</a>
    </li>
    <li class="nav-item">
        <a class="nav-link text-white" href="doctors">Doctor</a>
    </li>
    <li class="nav-item">
        <a class="nav-link text-white" href="medCenters">MedCenter</a>
    </li>
    <li class="nav-item">
        <a class="nav-link text-white" href="pharmacies">Pharmacy</a>
    </li>
    <li class="nav-item">
        <a class="nav-link text-white" href="medicineByPharmacies">Medicine_by_pharmacy</a>
    </li>


</ul>





