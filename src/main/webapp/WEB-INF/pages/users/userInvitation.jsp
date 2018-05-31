	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
	
	<c:set var="userEventEmpty" value="${userEvents.isEmpty()}" />
	
	<div id="page-wrapper"
		<c:if test="${userEventEmpty == true}">style="background: #fff;"</c:if>>
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Invitations</h4>
				</div>
				<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
					<ol class="breadcrumb">
						<li><a href="/ates/dashboard">Dashboard</a></li>
						<li class="active">Invitation List</li>
					</ol>
				</div>
				<!-- /.col-lg-12 -->
			</div>
	
			<!-- /row -->
			<div class="row empty-container <c:if test="${userEventEmpty == false}">hide</c:if>">
				<div class="col-md-12 p-t-10 text-center">
					<i class="mdi mdi-email-open-outline empty-icon"></i>
					<p class="empty-text">
						Your invitation list<br /> is empty!
					</p>
				</div>
			</div>
	
			<div class="row <c:if test="${userEvents.size() == 0}">hide</c:if>">
				<c:forEach var="userEvent" items="${userEvents}">
					<div class="col-xs-12 col-sm-4 col-md-3 parent-div">
						<div class="white-box">
							<div class="training-info">				
								<h2 class="m-b-0 font-medium text-center training-title">${userEvent.trainingPlan.title}</h2>
								<hr class="custom-hr"/>

								<div class="row m-b-5">
									<div class="col-xs-8">
										<p class="text-muted m-t-0 m-b-0 info-label">Role</p>
										<p class="m-b-0 font-medium">${userEvent.role}</p>
										
									</div>
									<div class="col-xs-4">
										<div class="text-info pull-right m-t-5">
											<i class="mdi mdi-account-box info-icon"></i>
										</div>
									</div>
								</div>
								
								<div class="row m-b-5">
									<div class="col-xs-8">
										<p class="text-muted m-t-0 m-b-0 info-label">Start Date</p>
										<p class="m-b-0 font-medium">
											<fmt:parseDate pattern="yyyy-MM-dd" value="${userEvent.trainingPlan.getStartSchedule().date}" var="start_date" />
											<fmt:formatDate value="${start_date}" pattern="MMM dd, yyyy" />
										</p>
										
									</div>
									<div class="col-xs-4">
										<div class="text-info pull-right m-t-5">
											<i class="mdi mdi-calendar-today info-icon"></i>
										</div>
									</div>
								</div>
								
								<div class="row m-b-5">
									<div class="col-xs-8">
										<p class="text-muted m-t-0 m-b-0 info-label">End Date</p>
										<p class="m-b-0 font-medium">
											<fmt:parseDate pattern="yyyy-MM-dd" value="${userEvent.trainingPlan.getEndSchedule().date}" var="end_date" />
											<fmt:formatDate value="${end_date}" pattern="MMM dd, yyyy" />
										</p>
										
									</div>
									<div class="col-xs-4">
										<div class="text-info pull-right m-t-5">
											<i class="mdi mdi-calendar info-icon"></i>
										</div>
									</div>
								</div>
								
								<div class="row m-b-5">
									<div class="col-xs-8">
										<p class="text-muted m-t-0 m-b-0 info-label">Location</p>
										<p class="m-b-0 font-medium">Somewhere</p>
										
									</div>
									<div class="col-xs-4">
										<div class="text-info pull-right m-t-5">
											<i class="material-icons info-icon">room</i>
										</div>
									</div>
								</div>
							</div>
							
							<div class="training-button text-center m-t-30">
								<button value="${userEvent.id}" class="btn-accept btn waves-effect waves-light btn-fixed btn-raised btn-success fcbtn btn-outline btn-1e m-r-5">Accept</button>
								<button value="${userEvent.id}" class="btn-decline btn waves-effect waves-light btn-fixed btn-raised btn-danger fcbtn btn-outline btn-1e m-l-5">Decline</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	
