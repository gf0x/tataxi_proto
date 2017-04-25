<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 25.04.2017
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header.jsp"%>
<div class="row">

    <div class="col-lg-offset-1 col-sm-9 container general-content">
        <h2>All departments</h2>
        <div class="list-group pre-scrollable">
            <c:forEach var="car" items="${cars}">
                <div class="list-group-item form-group list-item">
                    <div class="row">
                        <div class="col-xs-12 align-items-center vcenter"> <strong>Car: </strong>${car.brand} ${car.model}</div>
                        <div class="col-xs-12 align-items-center vcenter"> <strong>Sign: </strong>${car.sign}</div>
                        <div class="col-xs-12 align-items-center vcenter"> <strong>Category: </strong>${car.category}</div>
                        <div class="col-xs-12 align-items-center vcenter"><strong>Dept No: </strong>${car.deptId}</div>
                        <div class="col-xs-offset-10 col-xs-2 vcenter">
                            <a href="/car/edit/${car.id}">Edit</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="head_foot/footer.jsp" %>
