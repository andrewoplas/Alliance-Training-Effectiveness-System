	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
	
	<c:set var="userEventEmpty" value="${ userEvents.isEmpty() }" />
	
	<div id="page-wrapper"
		<c:if test="${ userEventEmpty == true }">style="background: #fff;"</c:if>>
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Trainings</h4>
				</div>
				<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
					<ol class="breadcrumb">
						<li><a href="/ates/dashboard">Dashboard</a></li>
						<li class="active">Training List</li>
					</ol>
				</div>
				<!-- /.col-lg-12 -->
			</div>
	
			<!-- /row -->
			<c:if test="${ userEventEmpty == true }">
				<div class="row">
					<div class="col-md-12 p-t-10 text-center">
						<i class="mdi mdi-calendar-remove empty-icon"></i>
						<p class="empty-text">
							Your training list<br /> is empty!
						</p>
					</div>
				</div>
			</c:if>
	
			<div class="row <c:if test="${userEvents.size() == 0}">hide</c:if>">
				<div class="col-xs-12">
	
					<div class="panel panel-info">
						<div class="panel-heading">List of Trainings</div>
	
						<div class="panel-wrapper collapse in" aria-expanded="true">
							<div class="panel-body">
								<div class="table-responsive">
									<label class="form-inline">Show <select
										id="show-entries" class="form-control input-sm">
											<option value="10">10</option>
											<option value="25">25</option>
											<option value="50">50</option>
											<option value="100">100</option>
									</select> entries
									</label> <label class="pull-right"> Search: <input type="search"
										id="table-search" class="m-l-5">
									</label>
	
	
									<table id="all-users-table" class="table m-b-0 toggle-circle"
										data-page-size="10" data-filter="#table-search"
										data-filter-minimum="1">
										<thead>
											<tr>
												<th data-toggle="true" class="p-l-30">Training</th>
												<th class="text-center">Role</th>
												<th class="text-center">Status</th>
												<th>Forms</th>
												<th>Action</th>
												<th data-hide="all">Start Date</th>
												<th data-hide="all">End Date</th>
												<th data-hide="all">Description</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="userEvent" items="${ userEvents }">	
												<tr data-id="${userEvent.id}">
													<td width="25%" class="training">${ userEvent.trainingPlan.title }</td>
													
													<td width="15%" class="text-center">
														<span class="badge ${ userEvent.role }">${ userEvent.role }</span>
													</td>
														
													<td width="15%" class="text-center status">
														<c:choose>
															<c:when test="${ userEvent.status == 'approved' }">
																<span class="badge badge-success">Approved</span>
															</c:when>
	
															<c:when test="${ userEvent.status == 'declined' }">
																<span class="badge badge-danger">Declined</span>
															</c:when>
														</c:choose>
													</td>
													
													<td width="20%">
														<c:if test="${ userEvent.getSaAssignments1(userEvent.id).size() > 0 }">
															<a href="/ates/general/training/skills-assessment/assignment/${ userEvent.id }">
					                                        	<button type="button" class="btn btn-info btn-outline btn-circle m-r-5 p-t-0 p-b-0" data-toggle="tooltip" title="View Skills Assessment" data-placement="top">
					                                        		<i class="mdi mdi-account-key"></i>
				                                        		</button>
			                                        		</a>
														</c:if>
														
		                                        		<c:if test="${ userEvent.role.contains('Supervisor') }">
		                                        			<a href="/ates/general/training/skills-assessment/${ userEvent.id }/${ userEvent.trainingPlan.id }">
					                                        	<button type="button" class="btn btn-warning btn-outline btn-circle m-r-5 p-t-0 p-b-0" data-toggle="tooltip" title="Assign Skills Assessment Form" data-placement="top">
					                                        		<i class="mdi mdi-account-switch"></i>
				                                        		</button>
			                                        		</a>
		                                        		</c:if>
		                                        		
		                                        		
													</td>
													<td width="20%">
														<a href="/ates/general/training/${ userEvent.id }/${ userEvent.trainingPlan.id }">
				                                        	<button type="button" class="btn btn-primary btn-outline btn-circle m-r-5 p-t-0 p-b-0" data-toggle="tooltip" title="View Training" data-placement="top">
				                                        		<i class="mdi mdi-eye"></i>
			                                        		</button>
		                                        		</a>
		                                        		
		                                        		<button type="button" class="btn-decline btn btn-danger btn-outline m-r-5 btn-circle p-t-0 p-b-0 ${ userEvent.status == 'declined'? 'hide' : '' }" data-toggle="tooltip" title="Decline Training Invitation" data-placement="top">
															<i class="mdi mdi-calendar-remove"></i>
														</button>
													
														<button type="button" class="btn-accept btn btn-success btn-outline m-r-5 btn-circle p-t-0 p-b-0 ${ userEvent.status == 'approved'? 'hide' : '' }" data-toggle="tooltip" title="Approve Training Invitation" data-placement="top">
															<i class="mdi mdi-calendar-check"></i>
														</button>
		                                        		
		                                        		<c:if test="${ userEvent.role.contains('Facilitator') }">
		                                        			<a href="/ates/general/training/edit/${ userEvent.id }/${ userEvent.trainingPlan.id }">
				                                        	<button type="button" class="btn btn-info btn-outline btn-circle m-r-5 p-t-0 p-b-0" data-toggle="tooltip" title="Edit" data-placement="top">
				                                        		<i class="mdi mdi-lead-pencil"></i>
			                                        		</button>
		                                        		</a>
		                                        		</c:if>		
													</td>
													
													<td><fmt:parseDate pattern="yyyy-MM-dd"
															value="${ userEvent.trainingPlan.schedules.get(0).date }"
															var="start_date" /> <fmt:formatDate value="${start_date }"
															pattern="MMM dd, yyyy" /></td>
															
													<td><fmt:parseDate pattern="yyyy-MM-dd"
															value="${ userEvent.trainingPlan.schedules.get(userEvent.trainingPlan.schedules.size()-1).date }"
															var="start_date" /> <fmt:formatDate value="${ start_date }"
															pattern="MMM dd, yyyy" /></td>
															
													<td><c:set var="description"
															value="${ fn:substring(userEvent.trainingPlan.description, 1, 100) }" />
														${ description }</td>
												</tr>
											</c:forEach>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="5">
													<div class="text-right">
														<ul class="pagination pagination-split m-t-30">
														</ul>
													</div>
												</td>
											</tr>
										</tfoot>
									</table>
								</div>
							</div> <!-- End Panel Body -->
						</div>
						
					</div> <!-- End Panel -->
	
				</div>
			</div>
		</div>
	</div>
	
