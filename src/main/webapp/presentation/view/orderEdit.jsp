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
    <div class="col-sm-5 container additional-content">
        <div id="right-panel">
            <p>Total Distance: <span id="total"></span></p>
        </div>
    </div>
</div>
<script src="https://maps.googleapis.com/maps/api/js?key=${gApiKey}&signed_in=true&callback=initMap"
        async defer></script>
<%@include file="head_foot/footer.jsp" %>