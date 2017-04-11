<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 11.04.2017
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header.jsp"%>
<div class="row">

    <div class="col-lg-offset-1 col-sm-9 container general-content">
        <h2>Tataxi: Drivers who tried all cars in their departments =)</h2>
        <div class="list-group pre-scrollable">
            <c:forEach var="driver" items="${drivers}">
                <div class="list-group-item form-group list-item">
                    <div class="row">
                        <div class="col-xs-12 align-items-center vcenter"><strong>
                                ${driver.fullName}
                        </strong>
                            <span class="badge">${driver.login}</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="head_foot/footer.jsp" %>
