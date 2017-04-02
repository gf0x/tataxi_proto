<%--
  Created by IntelliJ IDEA.
  User: Alex_Frankiv
  Date: 28.03.2017
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head_foot/header_blank.jsp" %>
<div class="container login-form">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <c:if test="${param.error != null}">
                        <div class="alert alert-danger">
                            <strong>Error!</strong> Invalid log in credentials
                        </div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="alert alert-info">
                            <strong>Bye!</strong> Come back soon!
                        </div>
                    </c:if>
                    <c:if test="${registered eq true}">
                        <div class="alert alert-success">
                            <strong>Success!</strong> You're now registered and ready to start!
                        </div>
                    </c:if>
                    <c:if test="${registered eq false}">
                        <div class="alert alert-danger">
                            <strong>Registration failed!</strong> Specified username is already in use
                            <br/> Please enter another one
                        </div>
                    </c:if>
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="/login" method="post" role="form" style="display: block;" onsubmit="return ">
                                <div class="form-group">
                                    <input type="text" name="lgn" tabindex="1" class="form-control"
                                           placeholder="Username" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="pswd" tabindex="2" class="form-control"
                                           placeholder="Password">
                                </div>
                                <div class="form-group text-center">
                                    <input type="checkbox" tabindex="3" class="" name="ttx-remember-me" id="remember">
                                    <label for="remember"> Remember Me</label>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                                                   class="form-control btn btn-login" value="Log In">
                                        </div>
                                    </div>
                                </div>

                            </form>
                            <form id="register-form" action="/client/register" method="post"
                                  role="form" style="display: none;" onsubmit="checkRegistrationForm()">
                                <div class="form-group">
                                    <input type="text" name="login" id="login" tabindex="1" class="form-control"
                                           placeholder="Username" value="" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="real_name" id="real_name" tabindex="1" class="form-control"
                                           placeholder="Real Name" value="" required>
                                </div>
                                <div class="form-group">
                                    <input type="email" name="email" id="email" tabindex="1" class="form-control"
                                           placeholder="Email Address" value="" required>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="home_address" id="home_address" tabindex="1" class="form-control"
                                           placeholder="Home Address (Not obligatory)" value="">
                                </div>
                                <div class="form-group">
                                    <input type="tel" name="phone_num" id="phone_num" tabindex="1" class="form-control"
                                           placeholder="Phone number +380XXXXXXXXX" value="" pattern="\+380[0-9]{9}" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="pass" tabindex="2" class="form-control"
                                           placeholder="Password" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="pass_conf" id="pass_conf" tabindex="2" class="form-control"
                                           placeholder="Confirm Password" required>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="register-submit" id="register-submit"
                                                   tabindex="4" class="form-control btn btn-register"
                                                   value="Register Now">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="head_foot/footer.jsp" %>