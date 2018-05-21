	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
		
	<div id="page-wrapper">
          <div class="container-fluid">
              <div class="row bg-title">
                  <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                      <h4 class="page-title">Training Plan</h4> </div>
                  <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                      <ol class="breadcrumb">
                          <li><a href="/ates/dashboard">Dashboard</a></li>
                          <li class="active">Training List</li>
                      </ol>
                  </div>
                  <!-- /.col-lg-12 -->
              </div>
              
              <!-- /row -->
              <div class="row">
	              <div class="col-xs-12">
	              
	              	<div class="panel panel-info">
						<div class="panel-heading">List of Trainings</div>
                            
						<div class="panel-wrapper collapse in" aria-expanded="true">
							<div class="panel-body">
								<div class="table-responsive">
		                            <label class="form-inline">Show
		                                <select id="show-entries" class="form-control input-sm">
		                                    <option value="10">10</option>
		                                    <option value="25">25</option>
		                                    <option value="50">50</option>
		                                    <option value="100">100</option>
		                                </select> entries 
									</label>
		                                
		                           	
		                           	<label class="pull-right">
		                           		Search:
		                           		<input type="search" id="table-search" class="m-l-5">
									</label>
		                             
		                                
		                            <table id="all-users-table" class="table m-b-0 toggle-circle" data-page-size="10"
		                            		data-filter="#table-search" data-filter-minimum="1">
		                                <thead>
		                                    <tr>
		                                        <th data-toggle="true" class="p-l-30">Training</th>
		                                        <th class="text-center">Status</th>
		                                        <th>Start Date</th>
		                                        <th>End Date</th>
		                                        <th>Action</th>
		                                        <th data-hide="all"> Facilitator </th>
		                                        <th data-hide="all"> Supervisor </th>
		                                        <th data-hide="all"> Description </th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                	<c:forEach var="training" items="${trainings}">
<%-- 		                                		<c:set var="count" value="0"/> --%>
<%-- 	                                			<c:forEach var="userEvent" items="${training.userEvents}"> --%>
<%-- 	                                				<c:if test="${userEvent.role == 'Participant'}"> --%>
<%-- 	                                					<c:set var="count" value="${count + 1}" /> --%>
<%-- 	                                				</c:if> --%>
<%-- 	                                			</c:forEach> --%>
		                                			
		                                		<tr data-id="${training.id}">
			                                        <td width="30%">${training.title}</td>
			                                        <td width="20%" class="text-center">
			                                        	<c:set var="status" value="${training.getStatus()}" />
			                                        	<c:choose>
			                                        		<c:when test="${status == 'Accomplished'}">
			                                        			<span class="badge badge-success">Accomplished</span>
			                                        		</c:when>
			                                        		<c:when test="${status == 'Incoming'}">
			                                        			<span class="badge badge-info">Incoming</span>
			                                        		</c:when>
			                                        		<c:when test="${status == 'Ongoing'}">
			                                        			<span class="badge badge-warning">Ongoing</span>
			                                        		</c:when>
			                                        	</c:choose>
			                                        </td>
			                                        <td width="15%">
			                                        	<fmt:parseDate pattern="yyyy-MM-dd" value="${training.schedules.get(0).date}" var="start_date" />
														<fmt:formatDate value="${start_date}" pattern="MMM dd, yyyy" />
		                                        	</td>
			                                        <td width="15%">
			                                        	<fmt:parseDate pattern="yyyy-MM-dd" value="${training.schedules.get(training.schedules.size()-1).date}" var="end_date" />
														<fmt:formatDate value="${end_date}" pattern="MMM dd, yyyy" />	
													</td>
			                                        <td width="20%">
			                                        	<a href="/ates/training/${training.id}">
				                                        	<button type="button" class="btn btn-success btn-outline btn-circle m-r-5 p-t-0 p-b-0" data-toggle="tooltip" title="View Training" data-placement="top">
				                                        		<i class="mdi mdi-eye"></i>
			                                        		</button>
		                                        		</a>
		                                        		<a href="/ates/training/attendance/${training.id}">
				                                        	<button type="button" class="btn btn-primary btn-outline btn-circle m-r-10 p-t-0 p-b-0" data-toggle="tooltip" title="View Attendance" data-placement="top">
				                                        		<i class="mdi mdi-account-check"></i>
			                                        		</button>
		                                        		</a>
			                                        	<a href="/ates/training/edit/${training.id}">
				                                        	<button type="button" class="btn btn-info btn-outline btn-circle m-r-5 p-t-0 p-b-0" data-toggle="tooltip" title="Edit" data-placement="top">
				                                        		<i class="mdi mdi-lead-pencil"></i>
			                                        		</button>
		                                        		</a>
														<button type="button" class="btn btn-danger btn-outline btn-circle p-t-0 p-b-0" data-toggle="tooltip" title="Delete" data-placement="top">
															<i class="mdi mdi-delete"></i>
														</button>
			                                        </td>
			                                        
			                                        <td>
			                                        	<c:forEach var="userEvent" items="${training.userEvents}">
			                                				<c:if test="${userEvent.role.contains('Facilitator')}">
			                                					<p class="m-b-0">${userEvent.user.name}</p>
			                                				</c:if>
			                                			</c:forEach>
			                                			<br />
			                                        </td>
			                                        <td>
		                                        		<c:forEach var="userEvent" items="${training.userEvents}">
			                                				<c:if test="${userEvent.role == 'Supervisor'}">
			                                					<p class="m-b-0">${userEvent.user.name}</p>
			                                				</c:if>
			                                			</c:forEach>
			                                			<br />
			                                        </td>
			                                        <td>
			                                        	<c:set var = "description" value = "${fn:substring(training.description, 1, 100)}" />
			                                        	${description}
			                                        </td>
			                                    </tr>
		                                	</c:forEach>                                    
		                                </tbody>
		                                <tfoot>
		                                    <tr>
		                                        <td colspan="5">
		                                            <div class="text-right">
		                                                <ul class="pagination pagination-split m-t-30"> </ul>
		                                            </div>
		                                        </td>
		                                    </tr>
		                                </tfoot>
		                            </table>
			                   </div>
                            </div>
						</div>
					</div>	              
	              
				</div>
	  		</div>
		</div>
	</div>
	
	