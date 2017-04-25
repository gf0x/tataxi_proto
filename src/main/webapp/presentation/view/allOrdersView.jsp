<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 10.04.2017
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header.jsp"%>
<div class="row">

    <div class="col-lg-offset-1 col-lg-9 container general-content">
        <h2>My orders</h2>
        <div class="list-group pre-scrollable">
            <c:forEach var="order" items="${orders}">
                <div class="list-group-item form-group list-item">
                    <div class="row">
                        <div class="col-xs-12 align-items-center vcenter"><strong>No: </strong>${order.id}
                            <c:if test="${order.isFast eq true}">
                                <span class="label label-warning n-service vcenter">Fast-drive</span>
                            </c:if>
                            <c:if test="${order.extraLuggage eq true}">
                                <span class="label label-warning n-service vcenter">Extra luggage</span>
                            </c:if>
                        </div>
                        <div class="col-xs-12 align-items-center vcenter"> <strong>From: </strong>${order.from.address}</div>
                        <div class="col-xs-12 align-items-center vcenter"> <strong>To: </strong>${order.to.address}</div>
                        <div class="col-xs-12 align-items-center vcenter"> <strong>Made on: </strong>${order.startTime}</div>
                        <div class="col-xs-offset-4 col-xs-3 vcenter">
                        <a class="btn btn-default btn-sm accept_order"
                      href="/order/receipt/${order.id}">Details</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="head_foot/footer.jsp" %>
