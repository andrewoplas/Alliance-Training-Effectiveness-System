<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="en">
<head>
	<title>ATES - Register</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link type="text/css" rel="stylesheet" href="/plugins/bootstrap/dist/css/bootstrap.min.css">
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
				<form action="http://localhost:8080/register/saveRegistration" method="post" class="login100-form validate-form" autocomplete=off>
					<span class="login100-form-title p-b-10">
						Registration
					</span>
					
					<span class="login100-form-title p-b-20">
						<i class="zmdi zmdi-font"></i>
					</span>
					
					<div class="wrap-input100 validate-input" data-validate="Enter your full name">
						<input class="input100" type="text" name="name">
						<span class="focus-input100" data-placeholder="Name"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate="Enter valid email" data-email="Email already exist">
						<input class="input100" type="email" name="email">
						<span class="focus-input100" data-placeholder="Email"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate="Enter position in company">
						<input class="input100" type="text" name="position">
						<span class="focus-input100" data-placeholder="Position"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye-off"></i>
						</span>
						<input class="input100" type="password" name="password">
						<span class="focus-input100" data-placeholder="Password"></span>
					</div>
						
					<div class="wrap-input100 validate-input" data-validate="Enter confirm password" data-confirm-password="Password doesn't match">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye-off"></i>
						</span>
						<input class="input100" type="password" name="confirm_password">
						<span class="focus-input100" data-placeholder="Confirm Password"></span>
					</div>						

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button type="submit" class="login100-form-btn">
								Register
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- JS SCRIPTS -->	
	<script type="text/javascript" src="/plugins/jquery/jquery-3.2.1.min.js"></script>
		
	<script type="text/javascript" src="/plugins/bootstrap/dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/plugins/sweetalert/sweetalert.min.js"></script>
	<script type="text/javascript" src="/js/register.js"></script>
</body>
</html>