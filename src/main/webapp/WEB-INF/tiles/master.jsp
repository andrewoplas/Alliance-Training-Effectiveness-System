<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    
		<title><tiles:getAsString name="title" /></title>
		
		    <link type="text/css" rel="stylesheet" href="/plugins/bootstrap/css/bootstrap.min.css">
		    <link type="text/css" rel="stylesheet" href="/plugins/sidebar-nav/dist/sidebar-nav.min.css">
		    
		    <c:forEach var="css" items="${stylesheets}">
		        <link type="text/css" rel="stylesheet" href="<c:url value='${css}'/>">
		    </c:forEach>
		    
		    <link type="text/css" rel="stylesheet" href="/static/css/animate.css">
		    <link type="text/css" rel="stylesheet" href="css/style.css">
		    <link type="text/css" rel="stylesheet" href="css/default.css">
	    
		    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		    <!--[if lt IE 9]>
		    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
			<![endif]-->
	</head>

	<body class="fix-header">
	
		<tiles:insertAttribute name="header" />   	    
        <tiles:insertAttribute name="sidebar" />	    
        <tiles:insertAttribute name="body" />	    
        <tiles:insertAttribute name="footer" />	    
	    
	    <script type="text/javascript" src="/static/plugins/jquery/jquery-3.2.1.min.js"></script>
	    <script type="text/javascript" src="/plugins/bootstrap/css/bootstrap.min.js"></script>
	    <script type="text/javascript" src="/plugins/sidebar-nav/dist/sidebar-nav.min.js"></script>
	    <script type="text/javascript" src="/js/jquery.slimscroll.js"></script>
	    <script type="text/javascript" src="/js/waves.js"></script>
	    	    
	    <c:forEach var="script" items="${javascripts}">
	        <script type="text/javascript" src="${script}"></script>
	    </c:forEach>
	  	    
	    <!--Style Switcher -->
	    <!-- <script src="../plugins/bower_components/styleswitcher/jQuery.style.switcher.js"></script> -->	
	</body>
</html>