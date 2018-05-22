	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<c:set var="user" scope="page" value="${sessionScope.isLoggedIn}"/>
	
	<!-- ============================================================== -->
	<!-- Preloader -->
	<!-- ============================================================== -->
	<div class="preloader">
	    <svg class="circular" viewBox="25 25 50 50">
	        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" />
	    </svg>
	</div>
	
	<!-- ============================================================== -->
	<!-- Wrapper -->
	<!-- ============================================================== -->
	<div id="wrapper">
	    <!-- ============================================================== -->
	    <!-- Topbar header - style you can find in pages.scss -->
	    <!-- ============================================================== -->
	    <nav class="navbar navbar-default navbar-static-top m-b-0">
	        <div class="navbar-header">
	            <div class="top-left-part">
	                <!-- Logo -->
	                <a class="logo" href="index.html">
	                 </b>
	                    <!-- Logo text image you can use text also --><span class="hidden-xs">
	                    <!--This is dark logo text--><img src="/plugins/images/admin-text.png" alt="home" class="dark-logo" /><!--This is light logo text--><img src="/plugins/images/admin-text-dark.png" alt="home" class="light-logo" />
	                 </span> </a>
	            </div>
	            <!-- /Logo -->
	            <!-- Search input and Toggle icon -->
	            <ul class="nav navbar-top-links navbar-right">
	                <li><a href="javascript:void(0)" class="open-close waves-effect waves-light visible-xs"><i class="ti-close ti-menu"></i></a></li>
	            <ul class="nav navbar-top-links navbar-right pull-right">
	                <li class="dropdown">
	                    <a class="dropdown-toggle profile-pic" data-toggle="dropdown" href="#"> 
	                    	<img src="/plugins/images/users/varun.jpg" alt="user-img" width="36" class="img-circle" 
	                    		 onerror="if (this.src != '/plugins/images/default_user.png') this.src = '/plugins/images/default_user.png';"/>
	                    	<span class="caret"></span> 
						</a>
						
	                    <ul class="dropdown-menu dropdown-user animated flipInY">
	                        <li>
	                            <div class="dw-user-box">
	                                <div class="u-img">
	                                	<img src="/plugins/images/users/varun.jpg" alt="user" 
	                                		 onerror="if (this.src != '/plugins/images/default_user.png') this.src = '/plugins/images/default_user.png';" />
									</div>
	                                <div class="u-text">
	                                    <h4>${user.name}</h4>
	                                    <p class="text-muted">${user.email}</p>
	                                    <a href="profile.html" class="btn btn-rounded btn-danger btn-sm">View Profile</a>
									</div>
	                            </div>
	                        </li>
	                        <li role="separator" class="divider"></li>
	                        <li><a href="#"><i class="ti-user"></i> My Profile</a></li>
	                        <li><a href="/login/logout"><i class="fa fa-power-off"></i> Logout</a></li>
	                    </ul>
	                    <!-- /.dropdown-user -->
	                </li>
	                <!-- /.dropdown -->
	            </ul>
	        </div>
	        <!-- /.navbar-header -->
	        <!-- /.navbar-top-links -->
	        <!-- /.navbar-static-side -->
	    </nav>
	    <!-- End Top Navigation -->