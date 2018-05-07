	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	
	<div id="page-wrapper">
          <div class="container-fluid">
              <div class="row bg-title">
                  <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                      <h4 class="page-title">User List</h4> </div>
                  <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                      <ol class="breadcrumb">
                          <li><a href="/ates/dashboard">Dashboard</a></li>
                          <li class="active">Users List</li>
                      </ol>
                  </div>
                  <!-- /.col-lg-12 -->
              </div>
              
              <!-- /row -->
              <div class="row">
	              <div class="col-md-9 col-lg-9 col-sm-7">
	               <div class="white-box">
	                   <h3 class="box-title m-b-20">Users and Information</h3>
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
                             
                                
                            <table id="all-users-table" class="table m-b-0 toggle-arrow-tiny" data-page-size="10"
                            		data-filter="#table-search" data-filter-minimum="1">
                                <thead>
                                    <tr>
                                        <th data-toggle="true">Name</th>
                                        <th>Position</th>
                                        <th>Email</th>
                                        <th>Action</th>
                                        <th data-hide="all"> Trainings </th>
                                        <th data-hide="all"> Information </th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="user" items="${users}">
                                		<tr data-id="${user.id}">
	                                        <td width="25%">${user.name}</td>
	                                        <td width="25%">${user.position}</td>
	                                        <td width="25%">${user.email}</td>
	                                        <td width="25%">
	                                        	<button type="button" class="btn-edit btn btn-info btn-outline btn-circle m-r-5 p-t-0 p-b-0"><i class="mdi mdi-account-edit"></i></button>
												<button type="button" class="btn-remove btn btn-danger btn-outline btn-circle p-t-0 p-b-0"><i class="mdi mdi-delete"></i></button>
	                                        </td>
	                                        <td>
	                                        	<ul class="list-group m-b-10">
	                                        		<c:set var="length" value="${user.training.size()}"/>
	                                        		
	                                        		<c:if test="${length != 0}">
		                                        		<c:forEach var = "i" begin = "0" end = "${length-1}">
			                                        		<c:if test="${i < 3}">
																<li class="list-group-item">
																	${user.training.get(i)}
																	
																	<span class="label label-table ${user.role.get(i)} m-l-5">
																		${user.role.get(i)}
																	</span>
																</li>
															</c:if>
														</c:forEach>
													</c:if>
													
													<c:if test="${length == 0}">
														<li class="list-group-item">
															NO TRAININGS ATTENDED YET
														</li>
													</c:if>
												</ul>                                        
	                                        </td>
	                                        <td><a href="/ates/users/view/${user.id}">View Profile</a></td>
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
				
				<div class="col-md-3 col-lg-3 col-sm-5">
                    <div class="white-box">
                        <h3 class="box-title">Cart Summary</h3>
                        <hr> <small>Total Price</small>
                        <h2>$612</h2>
                        <hr>
                        <button class="btn btn-success">Checkout</button>
                        <button class="btn btn-default btn-outline">Cancel</button>
                    </div>
                </div>
			</div>
		</div>
	</div>
	
	