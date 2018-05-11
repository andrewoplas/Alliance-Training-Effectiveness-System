	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	<c:set var="user" scope="page" value="${sessionScope.isLoggedIn}"/>
	
	<!-- ============================================================== -->
	<!-- Left Sidebar - style you can find in sidebar.scss  -->
	<!-- ============================================================== -->
	<div class="navbar-default sidebar" role="navigation">
	    <div class="sidebar-nav slimscrollsidebar">
	        <div class="sidebar-head">
	            <h3>
	            	<p class="fa-fw open-close">
		            	<i class="ti-menu hidden-xs"></i>
		            	<i class="ti-close visible-xs"></i>
		            </p> 
		            <span class="hide-menu">Navigation</span>
				</h3> 
			</div>
	        
	        <ul class="nav" id="side-menu">
	            <li class="user-pro">
	                <a href="#" class="waves-effect">
	                	<img src="../plugins/images/users/varun.jpg" alt="user-img" class="img-circle"
	                		 onerror="if (this.src != '/plugins/images/default_user.png') this.src = '/plugins/images/default_user.png';"> 
	                	<span class="hide-menu">${user.name}<span class="fa arrow"></span></span>
	                </a>
	                <ul class="nav nav-second-level collapse" aria-expanded="false" style="height: 0px;">
	                    <li><a href="javascript:void(0)"><i class="ti-user"></i> <span class="hide-menu">My Profile</span></a></li>
	                    <li><a href="/login/logout"><i class="fa fa-power-off"></i> <span class="hide-menu">Logout</span></a></li>
	                </ul>
	            </li>	           
				
				<!-- ADMIN ONLY -->
				<c:if test = "${user.isAdmin == 1}">      
		            <li> 
		            	<a href="javascript:void(0);" class="waves-effect">
		            		<i class="mdi mdi-account-settings-variant fa-fw" data-icon="v"></i> 
		            		<span class="hide-menu"> Users <span class="fa arrow"></span></span>
						</a>
		                <ul class="nav nav-second-level">
		                    <li><a href="/ates/users/list"><i class="mdi mdi-account-multiple fa-fw"></i><span class="hide-menu">User List</span></a></li>
		                    <li><a href="/ates/users/create"><i class="mdi mdi-account-plus fa-fw"></i><span class="hide-menu">Create User</span></a></li>
		                    <li><a href="/ates/users/request"><i class="mdi mdi-account-multiple-plus fa-fw"></i><span class="hide-menu">Registration Request</span></a></li>
		                </ul>
		            </li>
		            
		            <li> 
		            	<a href="javascript:void(0);" class="waves-effect">
		            		<i class="mdi mdi-calendar-range fa-fw" data-icon="v"></i> 
		            		<span class="hide-menu"> Training Plan <span class="fa arrow"></span></span>
		            	</a>
		                <ul class="nav nav-second-level">
		                    <li><a href="/ates/training"><i class="mdi mdi-calendar-blank fa-fw"></i><span class="hide-menu">Training Plan</span></a></li>
		                    <li><a href="/ates/training/create"><i class="mdi mdi-calendar-plus fa-fw"></i><span class="hide-menu">Create Training</span></a></li>
		                </ul>
		            </li>
		            
		            <li class="devider"></li>
	            </c:if>            
	            
	            <!-- FOR GENERAL USERS -->
	            <li> 
	            	<a href="widgets.html" class="waves-effect">
	            		<i  class="mdi mdi-calendar-clock fa-fw"></i> 
	            		<span class="hide-menu">Trainings</span>
	            	</a> 
				</li>
				
				<li> 
	            	<a href="widgets.html" class="waves-effect">
	            		<i  class="mdi mdi-email fa-fw"></i> 
	            		<span class="hide-menu">Invitations</span>
	            	</a> 
				</li>
				
				
	        </ul>
	    </div>
	</div>
	<!-- ============================================================== -->
	<!-- End Left Sidebar -->
	<!-- ============================================================== -->