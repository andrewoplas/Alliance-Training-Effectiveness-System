
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">${ form.description }</h4>
			</div>
			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				<ol class="breadcrumb">
					<li><a href="/ates/dashboard">Dashboard</a></li>
					<li><a href="/ates/forms/assignment">Forms</a></li>
					<li class="active">${ form.description }</li>
				</ol>
			</div>
			<!-- /.col-lg-12 -->
		</div>

		<!-- /row -->
		<div class="row">
			<div class="col-sm-7 col-md-9">

				<div class="panel panel-info">
					<div class="panel-heading">${ form.description } - Training</div>
					<input type="hidden" value="${ form.id }" id="formID" />
					<input type="hidden" value="${ training.id }" id="trainingPlan" />
					
					<div class="panel-wrapper collapse in" aria-expanded="true">
						<div class="panel-body">
							<div class="table-responsive">

								<table id="dataTable" class="table table-striped">
									<thead>
										<tr>
											<th>Name</th>
											<th class="text-center">Status</th>
											<th class="text-center">Form Status</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="userEvent" items="${ userEvents }">
											<c:if test="${ userEvent.role == 'Participant' || userEvent.role == 'Supervisor' }">
												
												<c:set var="formAssignment" value="${ userEvent.getFormAssignment(form.id) }" />
											
											
												<tr data-id="${ userEvent.id }">
													<td width="30%"> 
														${ userEvent.user.name } 
														<c:if test="${ userEvent.role == 'Supervisor' }">
															<span class="badge badge-info m-l-5" data-toggle="tooltip" title="Supervisor" data-placement="top">S</span>
														</c:if>
													</td>
													<td width="20%" class="text-center status">
														<c:choose>
															<c:when test="${ formAssignment == null }">
																<span class="badge badge-warning">Unassigned</span>
															</c:when>
															<c:otherwise>
																<span class="badge badge-success">Assigned</span>
															</c:otherwise>
														</c:choose>
													</td>
													<td width="20%" class="text-center">
														<c:choose>
															<c:when test="${ formAssignment!= null && formAssignment.status == 'answered' }">
																<span class="badge badge-success">Answered</span>
															</c:when>
															<c:otherwise>
																<span class="badge badge-warning">Not Yet Answered</span>
															</c:otherwise>
														</c:choose>
													</td>
													<td width="30%">
														<c:if test="${ formAssignment == null }">
															<button type="button" class="btn-assign btn btn-success btn-outline btn-circle p-t-0 p-b-0 m-r-5" data-toggle="tooltip" title="Assign" data-placement="top">
																<i class="mdi mdi-account-check"></i>
															</button>
														</c:if>
														
														
														<c:if test="${ formAssignment!= null && formAssignment.status == 'answered' }">
															<button type="button" class="btn-generate btn btn-info btn-outline btn-circle p-t-0 p-b-0 m-r-5" data-toggle="tooltip" title="Generate (PDF)" data-placement="top">
																<i class="mdi mdi-account-convert"></i>
															</button>
														</c:if>
													</td>
												</tr>
											</c:if>
										</c:forEach>
										
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-sm-5 col-md-3">
				<div class="white-box">
                    <h3 class="box-title">Training Name</h3>
                    <hr>
                    <div class="text-center">
                    	<p class="text-muted">${ training.title }</p>
                    </div>                    
                </div>
			
				<div class="white-box">
                    <h3 class="box-title">Assign To All</h3>
                    <hr>
                    <div class="text-center">
                    	<button class="btn-release btn btn-info btn-raised btn-block p-t-5 p-b-5 waves-effect waves-light" value="${ form.id }">
                    		Release
                		</button>
                    </div>                    
                </div>
			</div>
		</div>
	</div>
</div>


