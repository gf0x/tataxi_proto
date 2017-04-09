<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 08.04.2017
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header.jsp" %>

<div class="row">

    <div class="col-lg-offset-1 col-sm-9 container general-content">
        <h2>Department drivers</h2>
        <div id="for_car_driver_modal">
            <%@include file="carDriverModal.jsp"%>
        </div>


        <button class="btn btn-primary add_car_driver" data-toggle="modal" data-target=".car_driver_modal">+ Appoint driver</button>
        <div class="list-group pre-scrollable" id="car_driver_list">
            <%@ include file="carDriverList.jsp"%>

        </div>
    </div>
</div>
<%@include file="head_foot/footer.jsp" %>
