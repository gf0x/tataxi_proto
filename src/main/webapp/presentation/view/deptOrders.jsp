<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 09.04.2017
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header.jsp"%>
<div class="row">

    <div class="col-lg-offset-1 col-sm-9 container general-content">
        <h2>Department orders</h2>
        <div class="list-group pre-scrollable" id="dept_orders_list">
            <%@ include file="deptOrdersList.jsp"%>

        </div>
    </div>
</div>
<%--suto update every 30 sec --%>
<script>
    window.setInterval(function () {
        refreshAwaitingOrders();
    }, 30000);
</script>
<%@include file="head_foot/footer.jsp" %>
