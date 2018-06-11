
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
					<div class="panel-heading">${ training.title}</div>
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
										<c:set var="unassigned" value="0" />
										<c:set var="assigned" value="0" />
										<c:set var="unanswered" value="0" />
										<c:set var="answered" value="0" />
										
										<c:forEach var="userEvent" items="${ userEvents }">												
												<c:set var="formAssignment" value="${ userEvent.getFormAssignment(form.id) }" />
											
											
												<tr data-id="${ userEvent.id }">
													<td width="30%"> 
														${ userEvent.user.name } 
														<c:if test="${ userEvent.role == 'Supervisor' }">
															<span class="badge badge-info m-l-5" data-toggle="tooltip" title="Supervisor" data-placement="top">S</span>
														</c:if>
														
														<c:if test="${ userEvent.role.contains('Facilitator') }">
															<span class="badge badge-info m-l-5" data-toggle="tooltip" title="Facilitator" data-placement="top">F</span>
														</c:if>
													</td>
													<td width="20%" class="text-center status">
														<c:choose>
															<c:when test="${ formAssignment == null }">
																<span class="badge badge-warning">Unassigned</span>
																<c:set var="unassigned" value="${ unassigned + 1 }" />
															</c:when>
															<c:otherwise>
																<span class="badge badge-success">Assigned</span>
																<c:set var="assigned" value="${ assigned + 1 }" />
															</c:otherwise>
														</c:choose>
													</td>
													<td width="20%" class="text-center">
														<c:choose>
															<c:when test="${ formAssignment!= null && formAssignment.status == 'answered' }">
																<span class="badge badge-success">Answered</span>
																<c:set var="answered" value="${ answered + 1 }" />
															</c:when>
															<c:otherwise>
																<span class="badge badge-warning">Not Yet Answered</span>
																<c:set var="unanswered" value="${ unanswered + 1 }" />
															</c:otherwise>
														</c:choose>
													</td>
													<td width="30%" class="text-left">
														<c:if test="${ formAssignment == null }">
															<button type="button" class="btn-assign btn btn-success btn-outline btn-circle p-t-0 p-b-0 m-r-5" data-toggle="tooltip" title="Assign" data-placement="top">
																<i class="mdi mdi-account-check"></i>
															</button>
														</c:if>
														
														
														<c:if test="${ formAssignment!= null && formAssignment.status == 'answered' }">
															<a href="/ates/forms/view/answer/${ formAssignment.id }">
															<button type="button" class="btn-generate btn btn-success btn-outline btn-circle p-t-0 p-b-0 m-r-5" data-toggle="tooltip" title="View Answered Form" data-placement="top">
																<i class="mdi mdi-file-find"></i>
															</button>
															</a>
															
															<a href="/ates/forms/downloadPDF/${ formAssignment.id }/${ userEvent.user.name }-${ form.description }" target="_blank">
															<button type="button" class="btn-generate btn btn-info btn-outline btn-circle p-t-0 p-b-0 m-r-5" data-toggle="tooltip" title="Generate (PDF)" data-placement="top">
																<i class="mdi mdi-account-convert"></i>
															</button>
															</a>
														</c:if>
													</td>
												</tr>
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
                    <h3 class="box-title">Assign To All</h3>
                    <hr>
                    <div class="text-center">
                    	<button class="btn-release btn btn-info btn-raised btn-block p-t-5 p-b-5 waves-effect waves-light p-t-10 p-b-10" value="${ form.id }">
                    		Release
                		</button>
                    </div>                    
                </div>
                          
                <div class="white-box">
                    <h3 class="box-title">Report</h3>
                    <hr>
                    <div>
                    	<span>
                    		<i class="mdi mdi-checkbox-blank-circle text-warning"></i> 
                    		Unassigned <span class="pull-right unassigned-count"> ${ unassigned }</span>
                   		</span> 
                   			<br/>
                    	<span>
                    		<i class="mdi mdi-checkbox-blank-circle text-success"></i> 
                    		Assigned <span class="pull-right assigned-count"> ${ assigned }</span>
                   		</span> 
                   			<br/>
                    </div>
                    <hr>  
                    <div>
                    	<span>
                    		<i class="mdi mdi-checkbox-blank-circle text-warning"></i> 
                    		Unanswered <span class="pull-right"> ${ unanswered }</span>
                   		</span> 
                   		 <br/>
                    	<span>
                    		<i class="mdi mdi-checkbox-blank-circle text-success"></i> 
                    		Answered <span class="pull-right"> ${ answered }</span>
                   		</span> 
                   		<br/>
                    </div>
                    
                    <c:if test="${ answered > 0 }">   
                    <hr>
                    <div class="text-center">
                    	<a href="/ates/forms/download/${ form.id }/${ form.description }-${ training.title }/${ training.id }" target="_blank">
                    	<button class="btn-generate-responses btn btn-info btn-raised btn-block p-t-5 p-b-5 waves-effect waves-light p-t-10 p-b-10" value="${ form.id }">
                    		Generate Responses
                		</button>
                		</a>
                    </div>   
                    </c:if>          
                </div>
			
			</div>
		</div>
		
		<!-- /row -->
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-9">
				<div class="white-box">
                    <h3 class="box-title m-b-30">Statistics 
                    	<small class="text-muted m-l-5">
                    		${ answered } ${ answered > 1 ? 'Responses' : 'Response' }
                   		</small>
                   	</h3>
                    
                    <c:forEach var="question" items="${ questions }" varStatus="loop">
                    <div class="question-container" data-type="${ question.type }" data-id="${ question.id }">
                    	<p class="question">
                    		<span>${ loop.count }</span> ${ question.description }
                   		</p>
                   		
                    	<div class="data-container hide">
                    	
                    		<c:forEach var="userEvent" items="${ userEvents }">
                    			<c:set var="assignment" value="${ userEvent.getFormAssignment(form.id) }" />
                    			<c:if test="${ assignment.formAnswers.size() > 0 && loop.index < assignment.formAnswers.size()}">
                    				<c:set var="answer" value="${ assignment.formAnswers.get(loop.index) }"/>
	                    			<input type="hidden" class="data" value="${ answer.description }" />
                    			</c:if>
	                    	</c:forEach>
	                    	
	                    	<c:if test="${ question.formOptions.size() > 0 }">
		                    	<div class="data-options-container">
	                    			<c:forEach var="option" items="${ question.formOptions }">
	                    				<input type="hidden" class="data-options" value="${ option.description }" value-id="${ option.id }" />
	                    			</c:forEach>
		                    	</div>
	                    	</c:if>
	                    	
                    	</div>
                   	</div>
                   	<hr />
                    </c:forEach>
                </div>
			</div>
		</div>
		
	</div>
</div>


