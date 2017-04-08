<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 08.04.2017
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade car_driver_modal" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Appoint driver</h4>
            </div>
            <div class="modal-body">
            <div class="form-group">
                <label for="cd_car_select">Car: </label>
                <select class="form-control" id="cd_car_select">
                    <option disabled>Choose car...</option>
                    <c:forEach var="car" items="${cars}">
                        <option id="${car.id}" category="${car.category}">${car.brand} ${car.model} ${car.sign}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label for="cd_driver_select">Driver: </label>
                <select class="form-control" id="cd_driver_select">
                    <option disabled>Choose driver...</option>
                    <c:forEach var="driver" items="${drivers}">
                        <option id="${driver.login}" categories="${driver.licenses}">${driver.fullName}</option>
                    </c:forEach>
                </select>
            </div>
            </div>
            <div class="modal-footer">
                <span class="text-danger" id="cd_error_small" hidden></span>
            <button class="btn btn-primary main-btn" id="btn_car_driver_appoint" onclick="onAppoint();">Set</button>
            </div>
        </div>
    </div>
</div>
