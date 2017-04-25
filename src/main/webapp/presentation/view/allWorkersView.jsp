<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 25.04.2017
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header.jsp"%>
<div class="row">

    <div class="col-lg-offset-1 col-lg-9 container general-content">
        <h2>All departments</h2>
        <div class="list-group pre-scrollable">
            <c:forEach var="worker" items="${workers}">
                <div class="list-group-item form-group list-item">
                    <div class="row">
                        <div class="col-xs-12 align-items-center vcenter"> <strong>Dept No: </strong>${worker.deptId}</div>
                        <div class="col-xs-12 align-items-center vcenter"> <strong>Name: </strong>${worker.fullName}</div>
                        <div class="col-xs-12 align-items-center vcenter"><strong>Position: </strong>
                            <c:choose>
                                <c:when test="${worker.isDriver eq true}">
                                    Driver
                                </c:when>
                                <c:otherwise>
                                    Dispatcher
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-xs-12 align-items-center vcenter"> <strong>Online: </strong>${worker.online}</div>
                        <div class="col-xs-offset-10 col-xs-2 vcenter">
                            <a href="/worker/edit/${worker.login}">Edit</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="head_foot/footer.jsp" %>
