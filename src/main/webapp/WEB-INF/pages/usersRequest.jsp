	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	
	<div id="page-wrapper">
          <div class="container-fluid">
              <div class="row bg-title">
                  <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                      <h4 class="page-title">Registration Request</h4> </div>
                  <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                      <ol class="breadcrumb">
                          <li><a href="/ates/dashboard">Dashboard</a></li>
                          <li><a href="/ates/users/list">Users</a></li>
                          <li class="active">Registration Request</li>
                      </ol>
                  </div>
                  <!-- /.col-lg-12 -->
              </div>
              
              <!-- /row -->
              <div class="row">
	              <div class="col-sm-12">
	              
	              <div class="panel panel-info">
					   <div class="panel-heading">Pending Users</div>
                            
                       <div class="panel-wrapper collapse in" aria-expanded="true">
                       		<div class="panel-body">
                       			<div class="table-responsive">
	                   
			                       <table id="pending-users-table" class="table table-striped">
			                           <thead>
			                               <tr>
			                                   <th>Name</th>
			                                   <th>Position</th>
			                                   <th>Email</th>
			                                   <th>Action</th>	                                   
			                               </tr>
			                           </thead>
			                           <tbody>
											<c:forEach var="user" items="${users}">
												<tr data-id="${user.id}">
													<td class="name" width="25%">${user.name}</td>
													<td width="25%">${user.position}</td>
													<td width="25%">${user.email}</td>
													<td width="25%">
														<button type="button" class="btn-accept btn btn-info btn-outline btn-circle m-r-5 p-t-0 p-b-0"><i class="mdi mdi-check"></i></button>
														<button type="button" class="btn-decline btn btn-danger btn-outline btn-circle p-t-0 p-b-0"><i class="mdi mdi-close"></i></button>
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
			</div>
			
			<!-- Toast Approve -->
			<div id="alert-approve" class="myadmin-alert alert-info myadmin-alert-top-right"> 
				<a href="#" class="closed">&times;</a>
				<i class="mdi mdi-check m-r-5"></i><b class="name"></b> was approved.
			</div>
			
			<!-- Toast Decline -->
			<div id="alert-decline" class="myadmin-alert alert-warning myadmin-alert-top-right"> 
				<a href="#" class="closed">&times;</a>
				<i class="mdi mdi-close m-r-5"></i><b class="name"></b> was declined.
			</div>
		</div>
	</div>
	
	