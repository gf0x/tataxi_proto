<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 30.03.2017
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header.jsp" %>

<div class="row">
    <div class="col-sm-6 container general-content">
        <h2>Department properties:</h2>
        <input id="dept_id" value="${department.id}" type="hidden">
        <div class="form-group" id="fg_dept_city">
            <label class="form-control-label">City: </label>
            <input type="text" class="form-control" name="city" id="dept_city" value="${department.city}"
            <c:if test="${firstEdit eq false}">disabled</c:if>>
        </div>
        <div class="form-group" id="fg_dept_address">
            <label class="form-control-label">Address: </label>
            <input type="text" class="form-control" name="address" id="dept_address" value="${department.address}">
        </div>
        <div class="form-group" id="fg_dept_price_per_km">
            <label class="form-control-label">Price per km (₴): </label>
            <input type="number" min="0.0" class="form-control" name="price_per_km" id="dept_price_per_km"
                   value="${department.pricePerKm}">
        </div>
        <div class="form-group" id="fg_dept_phone_1">
            <label class="form-control-label">Phone number #1: </label>
            <input type="text" placeholder="+380XXXXXXXXX" class="form-control" id="dept_phone_1"
                   value="${department.phoneNums[0]}">
        </div>
        <div class="form-group" id="fg_dept_phone_2">
            <label class="form-control-label">Phone number #2: </label>
            <input type="text" placeholder="+380XXXXXXXXX" class="form-control" id="dept_phone_2"
                   value="${department.phoneNums[1]}">
        </div>
        <div class="form-group" id="fg_dept_phone_3">
            <label class="form-control-label">Phone number #3: </label>
            <input type="text" placeholder="+380XXXXXXXXX" class="form-control" id="dept_phone_3"
                   value="${department.phoneNums[2]}">
        </div>

    </div>
    <div class="col-sm-5 container additional-content">
        <h2>Map view:</h2>
        <img src="//maps.googleapis.com/maps/api/staticmap?center=
        <c:if test="${firstEdit eq true}">Київ</c:if>
        <c:if test="${firstEdit eq false}">${department.city}</c:if>
        &zoom=13&size=680x400&maptype=roadmap
&key=${gApiKey}" class="img-responsive static_map" alt="Static map" id="dept_static_map">
        <c:if test="${firstEdit eq true}">
            <div class="btn btn-lg btn-primary main-btn" id="btn_dept_create">Create</div>
        </c:if>
        <c:if test="${firstEdit eq false}">
            <div class="btn btn-lg btn-primary main-btn" id="btn_dept_edit">Edit</div>
        </c:if>

    </div>
</div>
<script src="https://maps.googleapis.com/maps/api/js?language=en&key=${gApiKey}&signed_in=true&callback=initMap"
        async defer></script>
<%@include file="head_foot/footer.jsp" %>