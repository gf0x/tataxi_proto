<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 04.04.2017
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header.jsp" %>

<div class="row">
    <div class="col-sm-6 container general-content">
        <div id="map"></div>

    </div>
    <div class="col-sm-5 container general-content">
        <h2>Order info:</h2>
        <label>Total Distance: <span id="order_dist"></span></label>
        <br>
        <label>Total Price: <span id="order_price"></span></label>
        <div class="form-group">
            <label for="order_dept">City: </label>
            <select class="form-control" id="order_dept">
                    <c:forEach items="${departments}" var="dept">
                        <option price_per_km="${dept.pricePerKm}" id="${dept.id}">${dept.city}</option>
                    </c:forEach>
                </select>
        </div>
        <div class="form-group">
            <label class="form-control-label" id="fg_order_from">From (address): </label>
            <input type="text" class="form-control" id="order_from_address">
        </div>
        <div class="form-group">
            <label class="form-control-label" id="fg_order_to">To (address): </label>
            <input type="text" class="form-control" id="order_to_address">
        </div>
        <div class="form-group" id="fg_order_passangers">
            <label class="form-control-label">Passengers: </label>
            <input type="number" min="1" step="1" class="form-control" id="order_seats" value="1">
        </div>
        <div class="form-group">
            <label for="order_is_fast">Drive: </label>
            <select class="form-control" id="order_is_fast" >
                <option id="1">Normal</option>
                <option id="2">Fast</option>
            </select>
        </div>
        <div class="form-group">
            <label for="order_is_fast">Luggage: </label>
            <select class="form-control" id="order_extra_luggage" >
                <option id="false">No extra luggage</option>
                <option id="true">Extra luggage</option>
            </select>
        </div>
        <div class="btn btn-lg btn-primary main-btn" id="btn_order_create">Order now!</div>
    </div>
</div>
<script src="https://maps.googleapis.com/maps/api/js?language=en&key=${gApiKey}&signed_in=true&callback=initMap"
        async defer></script>
<script type="text/javascript" src="<c:url value="/presentation/resources/js/dynamicMapNoDirectionsAndOrder.js"/> "></script>

<%@include file="head_foot/footer.jsp" %>