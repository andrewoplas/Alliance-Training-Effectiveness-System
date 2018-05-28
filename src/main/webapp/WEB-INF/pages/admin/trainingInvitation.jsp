	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	
	<div id="page-wrapper">
          <div class="container-fluid">
              <div class="row bg-title">
                  <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                      <h4 class="page-title">Invitation Status</h4> </div>
                  <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                      <ol class="breadcrumb">
                          <li><a href="/ates/dashboard">Dashboard</a></li>
                          <li><a href="/ates/training/list">Training</a></li>
                          <li class="active">Invitation Status</li>
                      </ol>
                  </div>
                  <!-- /.col-lg-12 -->
              </div>
              
              <!-- /row -->
              <div class="row">
	              <div class="col-sm-7 col-md-9">
	              
	              <div class="panel panel-info">
					   <div class="panel-heading">${ training.title }</div>
                            
                       <div class="panel-wrapper collapse in" aria-expanded="true">
                       		<div class="panel-body">
                       			<div class="table-responsive">
	                   
			                       <table id="dataTable" class="table table-striped">
			                           <thead>
			                               <tr>
			                                   <th>Name</th>
			                                   <th>Role</th>
			                                   <th>Status</th>
			                               </tr>
			                           </thead>
			                           <tbody>		                           
											<c:forEach var="userEvent" items="${ userEvents }">
												<tr>
													<td class="name" width="25%">${userEvent.user.name}</td>
													<td class="name" width="25%">
														<span class="badge ${ userEvent.role }">${ userEvent.role }</span>
													</td>
													<td class="name" width="25%">
														<c:choose>
															<c:when test="${ userEvent.status == 'approved' }">
																<span class="badge badge-success">Approved</span>
															</c:when>
	
															<c:when test="${ userEvent.status == 'declined' }">
																<span class="badge badge-danger">Declined</span>
															</c:when>
															
															<c:when test="${ userEvent.status == 'pending' }">
																<span class="badge badge-warning">Pending</span>
															</c:when>
														</c:choose>
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
                    	<h3 class="box-title">Report</h3>
	                    <hr>
	                    <div>
	                    	<span>
	                    		<i class="mdi mdi-checkbox-blank-circle text-success"></i> 
	                    		Approved <span class="pull-right unassigned-count"> ${ approvedCount }</span>
	                   		</span> 
	                   			<br/>
	                    	<span>
	                    		<i class="mdi mdi-checkbox-blank-circle text-danger"></i> 
	                    		Declined <span class="pull-right assigned-count"> ${ declinedCount }</span>
	                   		</span> 
	                   			<br/>
	                		<span>
	                    		<i class="mdi mdi-checkbox-blank-circle text-warning"></i> 
	                    		Pending <span class="pull-right unassigned-count"> ${ pendingCount }</span>
	                   		</span> 
	                    </div>
                	</div>
				</div>
				
			</div>
			
		</div>
	</div>
	
	