<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 03.04.2017
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header.jsp" %>

<div class="row">
    <div class="col-sm-6 container general-content">
        <h2>Worker profile: </h2>
        <!-- choose dept-->
        <div class="form-group">
            <label for="worker_dept">City: </label>
            <c:if test="${firstEdit eq false}">
                <select class="form-control" id="worker_dept" disabled>
                    <option id="${department.id}">${department.city}</option>
                </select>
            </c:if>
            <c:if test="${firstEdit eq true}">
                <select class="form-control" id="worker_dept">
                    <c:forEach items="${departments}" var="dept">
                        <option id="${dept.id}">${dept.city}</option>
                    </c:forEach>
                </select>
            </c:if>
        </div>
        <div class="form-group" id="fg_worker_login">
        <label class="form-control-label">Login: </label>
            <input class="form-control" type="text" id="worker_login" value="${driver.login}" <c:if test="${firstEdit eq false}">disabled</c:if>>
    </div>
        <c:if test="${firstEdit eq true}">
            <div class="form-group" id="fg_worker_pass">
                <label class="form-control-label">Password: </label>
                <input class="form-control" type="password" id="worker_pass">
            </div>
        </c:if>
        <div class="form-group" id="fg_worker_full_name">
            <label class="form-control-label">Full name: </label>
            <input type="text" class="form-control" id="worker_full_name" value="${driver.fullName}">
        </div>
        <div class="form-group" id="fg_worker_pass_data">
            <label class="form-control-label">Passport: </label>
            <input type="text" class="form-control" id="worker_pass_data" value="${driver.passportData}">
        </div>
        <div class="form-group" id="fg_worker_phone_num">
            <label class="form-control-label">Phone number: </label>
            <input type="tel" placeholder="+380XXXXXXXXX" class="form-control" id="worker_phone_num"
                   value="${driver.phoneNumber}">
        </div>
        <div class="form-group">
            <label for="worker_is_driver">Position: </label>
            <select class="form-control" id="worker_is_driver" >
                <option id="true">Driver</option>
                <option id="false">Dispatcher</option>
            </select>
        </div>
        <div class="form-check" id="worker_licenses" <c:if test="${driver.isDriver eq false}">hidden</c:if>>
            <label>Licenses: </label>
            <label class="form-check-label">
                <input type="checkbox" id="A" class="form-check-input" <c:if test="${fn:contains(driver.licenses, 'A')}">checked</c:if>>
                A
            </label>
            <label class="form-check-label">
                <input type="checkbox" id="B" class="form-check-input" <c:if test="${fn:contains(driver.licenses, 'B')}">checked</c:if>>
                B
            </label>
            <label class="form-check-label">
                <input type="checkbox" id="C" class="form-check-input" <c:if test="${fn:contains(driver.licenses, 'C')}">checked</c:if>>
                C
            </label>
            <label class="form-check-label">
                <input type="checkbox" id="D" class="form-check-input" <c:if test="${fn:contains(driver.licenses, 'D')}">checked</c:if>>
                D
            </label>
        </div>
    </div>
    <div class="col-sm-5 container additional-content">
        <h2>Map view:</h2>
        <img src="//maps.googleapis.com/maps/api/staticmap?center=
        <c:if test="${firstEdit eq true}">${departments[0].city}</c:if>
        <c:if test="${firstEdit eq false}">${department.city}</c:if>
        &zoom=13&size=680x400&maptype=roadmap
&key=${gApiKey}" class="img-responsive static_map" alt="Static map" id="worker_static_map">
        <c:if test="${firstEdit eq true}">
            <div class="btn btn-lg btn-primary main-btn" id="btn_worker_create">Create</div>
        </c:if>
        <c:if test="${firstEdit eq false}">
            <div class="btn btn-lg btn-primary main-btn" id="btn_worker_edit">Edit</div>
        </c:if>

    </div>
</div>

<%@include file="head_foot/footer.jsp" %>