<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 03.04.2017
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header.jsp" %>

<div class="row">
    <div class="col-sm-6 container general-content">
        <h2>Car properties: </h2>
        <!-- choose dept-->
        <div class="form-group">
            <label for="car_dept">Example select</label>
            <c:if test="${firstEdit eq false}">
                <select class="form-control" id="car_dept" disabled>
                    <option id="${department.id}">${department.city}</option>
                </select>
            </c:if>
            <c:if test="${firstEdit eq true}">
                <select class="form-control" id="car_dept">
                    <c:forEach items="${departments}" var="dept">
                        <option id="${dept.id}">${dept.city}</option>
                    </c:forEach>
                </select>
            </c:if>
        </div>

        <input id="car_id" value="${car.id}" type="hidden">
        <div class="form-group" id="fg_car_sign">
            <label class="form-control-label">State sign: </label>
            <input class="form-control" type="text" placeholder="AA0000AA" id="car_sign" value="${car.sign}">
        </div>
        <div class="form-group" id="fg_car_brand">
            <label class="form-control-label">Brand: </label>
            <input type="text" class="form-control" id="car_brand" value="${car.brand}">
        </div>
        <div class="form-group" id="fg_car_model">
            <label class="form-control-label">Model: </label>
            <input type="text" class="form-control" id="car_model" value="${car.model}">
        </div>
        <div class="form-group" id="fg_car_category">
            <label class="form-control-label">Category: </label>
            <input type="text" class="form-control" id="car_category"
                   value="${car.category}">
        </div>
        <div class="form-group" id="fg_car_seats">
            <label class="form-control-label">Number of seats: </label>
            <input type="number" min="1" step="1" class="form-control" id="car_seats"
                   value="${car.seats}">
        </div>
        <div class="form-group" id="fg_car_maxWeight">
            <label class="form-control-label">Maximum weight: </label>
            <input type="number" min="0.0" step="0.01" class="form-control" id="car_maxWeight"
                   value="${car.maxWeight}">
        </div>
        <div class="form-group" id="fg_car_boughtOn">
            <label class="form-control-label">Bought on: </label>
            <input type="date" class="form-control" id="car_boughtOn"
                   value="${car.boughtOn}">
        </div>

    </div>
    <div class="col-sm-5 container additional-content">
        <h2>Map view:</h2>
        <img src="//maps.googleapis.com/maps/api/staticmap?center=
        <c:if test="${firstEdit eq true}">${departments[0].city}</c:if>
        <c:if test="${firstEdit eq false}">${department.city}</c:if>
        &zoom=13&size=680x400&maptype=roadmap
&key=${gApiKey}" class="img-responsive static_map" alt="Static map" id="car_static_map">
        <c:if test="${firstEdit eq true}">
            <div class="btn btn-lg btn-primary main-btn" id="btn_car_create">Create</div>
        </c:if>
        <c:if test="${firstEdit eq false}">
            <div class="btn btn-lg btn-primary main-btn" id="btn_car_edit">Edit</div>
        </c:if>

    </div>
</div>

<%@include file="head_foot/footer.jsp" %>
