<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 20.03.2017
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tataxi | Login</title>
</head>
<body>
<div>
    <div class="wrapper">
        <form action="<c:url value='/login' />" method="post" name="Login_Form" class="form-signin">
            <h3 class="form-signin-heading">Welcome Back!</h3>
            <c:if test="${param.error != null}">
                <div>
                    <div class="message-error">
                        Invalid username or password!
                    </div>
                </div>
            </c:if>
            <c:if test="${param.logout != null}">
                <div>
                    <div class="message-success">
                        You have been logged out!
                    </div>
                </div>
            </c:if>
            <br/>
            <input type="text" class="form-control" name="login" placeholder="Login" autofocus=""/>
            <input type="password" class="form-control" name="password" placeholder="Password"/>
            <div class="checkbox text-center">
                <label>
                    <input type="checkbox" name="ttx-remember-me">
                    <b>Remember me</b>
                </label>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <input type="submit" value="Sign in" class="btn btn-lg btn-primary btn-block"/>
        </form>
    </div>
</div>
</body>
</html>
