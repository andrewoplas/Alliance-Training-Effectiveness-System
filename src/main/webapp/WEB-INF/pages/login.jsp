<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="en">
<head>
	<title>ATES - Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="shortcut icon" type="image/x-icon" href="/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="/plugins/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/plugins/sweetalert/sweetalert.css">
	<link rel="stylesheet" type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="/fonts/iconic/css/material-design-iconic-font.min.css">
	<link rel="stylesheet" type="text/css" href="/css/util.css">
	<link rel="stylesheet" type="text/css" href="/css/register.css">
</head>
<body>
		
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form action="/login" method="post" class="login100-form validate-form" autocomplete=off>
					<span class="login100-form-title p-b-10">
						Login
					</span>
					
					<span class="login100-form-title p-b-20">
						<i class="zmdi zmdi-font"></i>
					</span>
					
					<div class="wrap-input100 validate-input" data-validate="Enter valid email">
						<input class="input100" type="email" name="email">
						<span class="focus-input100" data-placeholder="Email"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye-off"></i>
						</span>
						<input class="input100" type="password" name="password">
						<span class="focus-input100" data-placeholder="Password"></span>
					</div>
					
					<c:if test="${response == true}">
         				<div class="error-container text-center">
							<p class="text-danger">Invalid username / password</p>
						</div>
      				</c:if>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button type="submit" class="login100-form-btn">
								Login
							</button>
						</div>
					</div>					
				</form>
			</div>
		</div>
	</div>
	
	<!-- JS SCRIPTS -->	
	<script type="text/javascript" src="/plugins/jquery/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="/plugins/bootstrap/js/popper.min.js"></script>
	<script type="text/javascript" src="/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/plugins/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript" src="/js/login.js"></script>
</body>
</html>