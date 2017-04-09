<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 09.04.2017
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head_foot/header.jsp"%>
<div class="row">
    <div class="col-sm-6 container general-content">
        <div id="map"></div>

    </div>
    <div class="col-sm-5 container general-content">
        <h2>Order info:</h2>
        <c:if test="${empty client_order}">
            <h3>No order for now!</h3>
        </c:if>
        <c:if test="${not empty client_order}">
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
                <div class="col-xs-12 align-items-center vcenter"> <strong>From: </strong>${client_order.order.from.address}</div>
                <div class="col-xs-12 align-items-center vcenter"> <strong>To: </strong>${client_order.order.to.address}</div>
                <div class="col-xs-12 align-items-center vcenter"><strong>Distance: </strong>${client_order.order.distance}km
                    <strong> Price: </strong>${client_order.order.price}&#x20B4;
                </div>
            </div>
            <div id="right-panel" class="pre-scrollable">
            </div>
            <script>
                var FROM_LAT = ${client_order.order.from.lat};
                var FROM_LNG = ${client_order.order.from.lng};
                var TO_LAT = ${client_order.order.to.lat};
                var TO_LNG = ${client_order.order.to.lng};
            </script>
            <div class="btn btn-lg btn-primary main-btn" order_id="${client_order.order.id}" id="btn_end_drive">Finish drive!</div>
        </c:if>
    </div>
</div>
<script src="https://maps.googleapis.com/maps/api/js?language=en&key=${gApiKey}&signed_in=true&callback=runMap"
        async defer></script>
<script type="text/javascript" src="<c:url value="/presentation/resources/js/dynamicMapDirections.js"/>"></script>
<%@include file="head_foot/footer.jsp" %>
