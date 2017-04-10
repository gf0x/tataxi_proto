<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="order" items="${client_orders}">
    <div class="list-group-item form-group list-item">
        <div class="row">
            <div class="col-xs-12 align-items-center vcenter"><strong>No: </strong>${order.order.id}
                <c:if test="${order.order.isFast eq true}">
                    <span class="label label-warning n-service vcenter">Fast-drive</span>
                </c:if>
                <c:if test="${order.order.extraLuggage eq true}">
                    <span class="label label-warning n-service vcenter">Extra luggage</span>
                </c:if>
            </div>
            <div class="col-xs-12 align-items-center vcenter"> ${order.client.realName}
                t:${order.client.phoneNumber}</div>
            <div class="col-xs-12 align-items-center vcenter"> <strong>From: </strong>${order.order.from.address}</div>
            <div class="col-xs-12 align-items-center vcenter"> <strong>To: </strong>${order.order.to.address}</div>
            <div class="col-xs-12 align-items-center vcenter"><strong>Distance: </strong>${order.order.distance}km
                <strong> Price: </strong>${order.order.price}&#x20B4;
            </div>
            <select class="form-control driver_select" id="${order.order.id}">
                <c:forEach var="car_driver" items="${car_drivers}">
                    <c:if test="${car_driver.car.seats ge order.order.seats}">
                        <option id="${car_driver.car.id}">${car_driver.car.brand} ${car_driver.car.model} ${car_driver.car.sign}
                            Driver: ${car_driver.driver.fullName}</option>
                    </c:if>
                </c:forEach>
            </select>
            <div class="col-xs-offset-4 col-xs-3 vcenter">
                <span class="btn btn-success btn-sm accept_order"
                      order_id="${order.order.id}">Accept</span>
            </div>
            <div class="col-xs-offset-1 col-xs-3 vcenter">
                <span class="btn btn-danger btn-sm decline_order"
                      order_id="${order.order.id}">Decline</span>
            </div>
        </div>
    </div>
</c:forEach>