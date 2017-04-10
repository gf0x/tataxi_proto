<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 10.04.2017
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head_foot/header.jsp" %>
<div class="row">
    <div class="col-sm-6 container general-content">
        <div id="map"></div>

    </div>
    <div class="col-sm-5 container general-content">
        <h2>Order info:</h2>
        <div class="row">
            <div class="col-xs-12 align-items-center vcenter"><strong>No: </strong>${client_order.order.id}
                <c:if test="${client_order.order.isFast eq true}">
                    <span class="label label-warning n-service vcenter">Fast-drive</span>
                </c:if>
                <c:if test="${client_order.order.extraLuggage eq true}">
                    <span class="label label-warning n-service vcenter">Extra luggage</span>
                </c:if>
            </div>
            <div class="col-xs-12 align-items-center vcenter"> ${client_order.client.realName}
                t:${client_order.client.phoneNumber}</div>
            <div class="col-xs-12 align-items-center vcenter"><strong>From: </strong>${client_order.order.from.address}
            </div>
            <div class="col-xs-12 align-items-center vcenter"><strong>To: </strong>${client_order.order.to.address}
            </div>
            <div class="col-xs-12 align-items-center vcenter"><strong>Distance: </strong>${client_order.order.distance}km
                <strong> Price: </strong>${client_order.order.price}&#x20B4;
            </div>
            <div class="col-xs-12 align-items-center vcenter"><strong>Car: </strong>
                <c:choose>
                    <c:when test="${not empty car_driver}">
                        ${car_driver.car.brand} ${car_driver.car.model} ${car_driver.car.sign}
                    </c:when>
                    <c:otherwise>
                        n/a
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-xs-12 align-items-center vcenter"><strong>Driver: </strong>
                <c:choose>
                    <c:when test="${not empty car_driver}">
                        ${car_driver.driver.fullName}
                    </c:when>
                    <c:otherwise>
                        n/a
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="col-xs-12 align-items-center vcenter"><strong>Dispatcher: </strong>
                <c:choose>
                    <c:when test="${not empty dispatcher}">
                        ${dispatcher.fullName}
                    </c:when>
                    <c:otherwise>
                        n/a
                    </c:otherwise>
                </c:choose>
            </div>
            </div>
        <script>
            var FROM_LAT = ${client_order.order.from.lat};
            var FROM_LNG = ${client_order.order.from.lng};
            var TO_LAT = ${client_order.order.to.lat};
            var TO_LNG = ${client_order.order.to.lng};

            var RATE_MARK = ${client_order.order.feedback};
        </script>
        <sec:authorize access="hasRole('CLIENT')">
            <form id="ratingsForm" >
                <div class="stars">
                    <input type="radio" name="star" class="star-1" rate="1" id="star-1">
                    <label class="star-1" for="star-1">1</label>
                    <input type="radio" name="star" class="star-2" rate="2" id="star-2">
                    <label class="star-2" for="star-2">2</label>
                    <input type="radio" name="star" class="star-3" rate="3" id="star-3">
                    <label class="star-3" for="star-3">3</label>
                    <input type="radio" name="star" class="star-4" rate="4" id="star-4">
                    <label class="star-4" for="star-4">4</label>
                    <input type="radio" name="star" class="star-5" rate="5" id="star-5">
                    <label class="star-5" for="star-5">5</label>
                    <span></span>
                </div>
            </form>
            <div class="btn btn-lg btn-primary main-btn" order_id="${client_order.order.id}" id="btn_rate_order"
                 <c:if test="${empty client_order.order.finishTime}">disabled</c:if>
            >Rate!
            </div>
        </sec:authorize>
    </div>
</div>

<link rel="stylesheet" href="<c:url value="/presentation/resources/css/stars.css"/>"/>
<script src="https://maps.googleapis.com/maps/api/js?language=en&key=${gApiKey}&signed_in=true&callback=runMap"
        async defer></script>
<script type="text/javascript" src="<c:url value="/presentation/resources/js/dynamicMapDirections.js"/>"></script>
<%@include file="head_foot/footer.jsp" %>
