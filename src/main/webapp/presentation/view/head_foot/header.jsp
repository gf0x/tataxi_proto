<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 29.03.2017
  Time: 22:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header_blank.jsp" %>

<nav class="navbar navbar-default sidebar" role="navigation">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-sidebar-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">MENU</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-sidebar-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <%--admin menu--%>
                <sec:authorize access="hasRole('ADMIN')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Create...<span class="caret"></span>
                            <span style="font-size:16px;"
                                  class="pull-right hidden-xs showopacity glyphicon glyphicon-pencil"></span></a>
                        <ul class="dropdown-menu forAnimate" role="menu">
                            <li><a href="/dept/create">Department</a></li>
                            <li><a href="/car/create">Car</a></li>
                            <li><a href="/worker/create">Worker</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Edit...<span class="caret"></span>
                            <span style="font-size:16px;"
                                  class="pull-right hidden-xs showopacity glyphicon glyphicon-edit"></span></a>
                        <ul class="dropdown-menu forAnimate" role="menu">
                            <li><a href="/dept/all">Department</a></li>
                            <li><a href="/car/all">Car</a></li>
                            <li><a href="/worker/all">Worker</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Interesting<span
                                class="caret"></span>
                            <span style="font-size:16px;"
                                  class="pull-right hidden-xs showopacity glyphicon glyphicon-stats"></span></a>
                        <ul class="dropdown-menu forAnimate" role="menu">
                            <li><a href="/stats/cars">Cars</a></li>
                            <li><a href="/stats/veterans">Veterans</a></li>
                        </ul>
                    </li>
                </sec:authorize>
                <%--client menu--%>
                <sec:authorize access="hasRole('CLIENT')">
                    <li><a href="/order/create">Order a cab<span style="font-size:16px;"
                                                                 class="pull-right hidden-xs showopacity glyphicon glyphicon-plus"></span></a>
                    </li>
                    <li><a href="/client/all_orders">My orders<span style="font-size:16px;"
                                                                    class="pull-right hidden-xs showopacity glyphicon glyphicon-list-alt"></span></a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Interesting<span
                                class="caret"></span>
                            <span style="font-size:16px;"
                                  class="pull-right hidden-xs showopacity glyphicon glyphicon-stats"></span></a>
                        <ul class="dropdown-menu forAnimate" role="menu">
                            <li><a href="/stats/cars">Cars</a></li>
                            <li><a href="/stats/veterans">Veterans</a></li>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('DISPATCHER')">
                    <li><a href="/staff/dispatcher/car_drivers">Manage cabs<span style="font-size:16px;"
                                                                 class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a>
                    </li>
                    <li><a href="/staff/dispatcher/orders_awaiting">Manage orders<span style="font-size:16px;"
                                                                                 class="pull-right hidden-xs showopacity glyphicon glyphicon-list-alt"></span></a>
                    </li>
                    <li><a href="/staff/dispatcher/all_orders">My orders<span style="font-size:16px;"
                                                                    class="pull-right hidden-xs showopacity glyphicon glyphicon-list"></span></a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Interesting<span
                                class="caret"></span>
                            <span style="font-size:16px;"
                                  class="pull-right hidden-xs showopacity glyphicon glyphicon-stats"></span></a>
                        <ul class="dropdown-menu forAnimate" role="menu">
                            <li><a href="/stats/cars">Cars</a></li>
                            <li><a href="/stats/veterans">Veterans</a></li>
                        </ul>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('DRIVER')">
                    <li><a href="/staff/driver/current_order">Current order<span style="font-size:16px;"
                                                                                 class="pull-right hidden-xs showopacity glyphicon glyphicon-map-marker"></span></a>
                    </li>
                    <li><a href="/staff/driver/all_orders">My orders<span style="font-size:16px;"
                                                                              class="pull-right hidden-xs showopacity glyphicon glyphicon-list"></span></a>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Interesting<span
                                class="caret"></span>
                            <span style="font-size:16px;"
                                  class="pull-right hidden-xs showopacity glyphicon glyphicon-stats"></span></a>
                        <ul class="dropdown-menu forAnimate" role="menu">
                            <li><a href="/stats/cars">Cars</a></li>
                            <li><a href="/stats/veterans">Veterans</a></li>
                        </ul>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<div class="main container">