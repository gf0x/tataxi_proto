<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 09.04.2017
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="car_driver" items="${car_drivers}">
    <div class="list-group-item list-item">
        <div class="row">
            <div class="col-sm-3 col-xs-11 align-items-center vcenter"> ${car_driver.car.brand} ${car_driver.car.model} ${car_driver.car.sign} </div>
            <div class="col-sm-4 col-xs-12 align-items-center vcenter">${car_driver.driver.fullName}</div>
            <div class="col-sm-2 col-xs-12 align-items-center vcenter">
                <c:if test="${car_driver.car.serviceable eq false}">
                    <span class="label label-danger n-service vcenter">Out of service!</span>
                </c:if>
            </div>
            <div class="col-sm-1 col-sm-offset-1 col-xs-offset-8 col-xs-3 vcenter">
                        <span class="btn btn-warning btn-sm car_driver_unsettle" car_id="${car_driver.car.id}"
                              driver_login="${car_driver.driver.login}">Unsettle</span>
            </div>
        </div>
    </div>
</c:forEach>