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
        <div class="form-group" id="fg_dept_city">
            <label class="form-control-label">City: </label>
            <input type="text" class="form-control" name="city" id="dept_city">
        </div>
        <div class="form-group" id="fg_dept_address">
            <label class="form-control-label">Address: </label>
            <input type="text" class="form-control" name="address" id="dept_address">
        </div>
        <div class="form-group" id="fg_dept_price_per_km">
            <label class="form-control-label">Price per km (₴): </label>
            <input type="number" min="0.0" class="form-control" name="price_per_km" id="dept_price_per_km">
        </div>
    </div>
    <div class="col-sm-5 container additional-content">
        <h2>Map view:</h2>
        <img src="//maps.googleapis.com/maps/api/staticmap?center=Київ&zoom=13&size=680x400&maptype=roadmap
&key=${gApiKey}"class="img-responsive static_map" alt="Static map" id="dept_static_map">
        <div class="btn btn-lg btn-primary main-btn" id="btn_dept_edit">Create</div>
    </div>
</div>

<%@include file="head_foot/footer.jsp" %>