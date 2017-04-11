<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 28.03.2017
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Tataxi</title>
    <script type="text/javascript" src="<c:url value="/presentation/resources/jquery/jquery.min.js"/> "></script>
    <link rel="stylesheet" href="<c:url value="/presentation/resources/jquery-ui/css/jquery-ui.css"/> ">
    <link rel="stylesheet" href="<c:url value="/presentation/resources/bootstrap/css/bootstrap.min.css"/> ">
    <link rel="stylesheet"
    href="<c:url value="/presentation/resources/bootstrap/fonts/glyphicons-halflings-regular.woff"/> ">
    <script type="text/javascript" src="<c:url value="/presentation/resources/bootstrap/js/bootstrap.min.js"/> "></script>
    <script type="text/javascript"
            src="<c:url value="/presentation/resources/notify_js/bootstrap-notify.min.js"/>"></script>
    <!-- custom -->
    <link rel="stylesheet" href="<c:url value="/presentation/resources/css/style.css"/> ">
    <script type="text/javascript" src="<c:url value="/presentation/resources/js/main.js"/> "></script>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>

    <script>
        const GOOGLE_API_KEY='${gApiKey}';
    </script>
</head>
<body>
<div class="header">
    <div class="top-panel container-fluid">
        <div class="row">
            <div class="top-logo col-lg-2 col-sm-3 col-xs-offset-1 col-xs-10">
                Tataxi
            </div>

            <sec:authorize access="isAuthenticated()">
                <div class="login-top col-lg-2 col-lg-offset-4 col-sm-2 col-sm-offset-2 col-xs-offset-2 col-xs-4">
                    <sec:authentication property="principal.username"/>
                </div>
                <div class="loginout-btn btn col-lg-1 col-lg-offset-1 col-sm-2 col-xs-offset-1 col-xs-3" onclick="location.href = '/pre_logout';">
                        <i class="glyphicon glyphicon-log-out" id="logout-btn"> Logout</i>
                    <%--<i class="glyphicon glyphicon-log-in"> Sign in</i>--%>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>
<div class="main-container">