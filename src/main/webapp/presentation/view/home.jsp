<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 14.02.2017
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tataxi Home</title>
</head>
<body>
<div style="text-align: center">
    <br>
    <h1>Welcome to Tataxi!</h1>
    <h2>We're under construction now...</h2>
    <h1v>=(</h1v>
    <div class="testing-div">
        <div class="in">
            <form method="post">
                <label class="label">Input address below</label>
                <input type="text" name="address">
                <input type="submit" value="OK">
            </form>
        </div>
        <br/>
        <div class="out">
            <h3>Lat: ${lat}</h3>
            <h3>Lng: ${lng}</h3>
        </div>
    </div>
</div>
</body>
</html>
