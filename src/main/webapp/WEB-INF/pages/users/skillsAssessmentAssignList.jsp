	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
	
	<c:set var="userEventEmpty" value="${ assignments.isEmpty() }" />
	
	<div id="page-wrapper"
		<c:if test="${ userEventEmpty == true }">style="background: #fff;"</c:if>>
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Skills Assessment</h4>
				</div>
				<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
					<ol class="breadcrumb">
						<li><a href="/ates/dashboard">Dashboard</a></li>
						<li><a href="/ates/general/training">Training List</a></li>
						<li class="active">Skills Assessment</li>
					</ol>
				</div>
				<!-- /.col-lg-12 -->
			</div>
	
			<!-- /row -->
			<c:if test="${ userEventEmpty == true }">
				<div class="row">
					<div class="col-md-12 p-t-10 text-center">
						<i class="mdi mdi-account-off empty-icon"></i>
						<p class="empty-text">
							You are not assigned<br /> to any Skills Assessment
						</p>
					</div>
				</div>
			</c:if>
	
			<div class="row <c:if test="${assignments.size() == 0}">hide</c:if>">
				<div class="col-xs-12">
	
					<div class="panel panel-info">
						<div class="panel-heading">Skills Assessment Assignments</div>
	
						<div class="panel-wrapper collapse in" aria-expanded="true">
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped" id="assignments">
										<thead>
											<tr>
												<th>Training</th>
												<th>Name</th>
												<th class="text-center">Type</th>
												<th class="text-center">Status</th>
												<th>Action</th>
											</tr>
										</thead>
										
										<tbody>
											<c:forEach var="assignment" items="${ assignments }">
											<tr>
												<td width="25%">${ assignment.userEvent2.trainingPlan.title }</td>
												<td width="25%">${ assignment.userEvent2.user.name }</td>
												<td  width="15%" class="text-center">
													<span class="badge ${ assignment.type }">${ assignment.type }</span>
												</td>
												<td width="15%" class="text-center">
													<c:choose>
														<c:when test="${ assignment.status == 'answered' }">
															<span class="badge badge-success">Answered</span>
														</c:when>
														<c:when test="${ assignment.status == 'unanswered' }">
															<span class="badge badge-danger">Unanswered</span>
														</c:when>
													</c:choose>
												</td>
												<td width="10%">
													<c:choose>
														<c:when test="${ assignment.status == 'answered' }">
															<a href="/ates/general/training/skills-assessment/view/${ assignment.id }">
					                                        	<button type="button" class="btn btn-info btn-outline btn-circle m-r-5 p-t-0 p-b-0" data-toggle="tooltip" title="View Answered Form" data-placement="top">
					                                        		<i class="mdi mdi-account-box"></i>
				                                        		</button>
			                                        		</a>
														</c:when>
														<c:when test="${ assignment.status == 'unanswered' }">
															<a href="/ates/general/training/skills-assessment/answer/${ assignment.id }">
					                                        	<button type="button" class="btn btn-info btn-outline btn-circle m-r-5 p-t-0 p-b-0" data-toggle="tooltip" title="Answer Skills Assessment Form" data-placement="top">
					                                        		<i class="mdi mdi-account-edit"></i>
				                                        		</button>
			                                        		</a>
														</c:when>
													</c:choose>
												</td>
											</tr>
											</c:forEach>
										</tbody>
									
									</table>
	
									
								</div>
							</div> <!-- End Panel Body -->
						</div>
					</div> <!-- End Panel -->
				</div>
			</div>
		</div>
	</div>
	
