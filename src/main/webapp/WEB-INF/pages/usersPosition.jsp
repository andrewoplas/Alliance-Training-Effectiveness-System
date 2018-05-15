	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	
	<div id="page-wrapper">
          <div class="container-fluid">
              <div class="row bg-title">
                  <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                      <h4 class="page-title">Position</h4> 
                  </div>
                  <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                      <ol class="breadcrumb">
                          <li><a href="/ates/dashboard">Dashboard</a></li>
                          <li><a href="/ates/users/list">Users List</a></li>
                          <li class="active">Position</li>
                      </ol>
                  </div>
                  <!-- /.col-lg-12 -->
              </div>
              
              <!-- /row -->
             <div class="row">
           		<div class="col-md-9 col-xs-12">
          				<div class="panel panel-info">
                            <div class="panel-heading">User Position</div>
                            
                            <div class="panel-wrapper collapse in" aria-expanded="true">
                                <div class="panel-body">
                                	<div class="table-responsive">
				                       <table id="position-table" class="table table-striped">
				                           <thead>
				                               <tr>
				                                   <th>Position</th>
				                                   <th>Action</th>
				                               </tr>
				                           </thead>
				                           <tbody>
												<c:forEach var="position" items="${positions}">
													<tr data-id="${position.id}">
														<td class="description" width="75%">${position.description}</td>
														<td width="25%">
															<button type="button" class="btn-delete btn btn-danger btn-outline btn-circle p-t-0 p-b-0"><i class="mdi mdi-delete"></i></button>
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
				
				<div class="col-md-3 col-xs-12">
					<div class="white-box p-0">
                  		<div class="sttabs tabs-style-iconbox">
                       		<nav>
                           		<ul>
                               		<li>
                               			<a href="#section-iconbox-1" class="sticon mdi mdi-plus-box-outline">
                               				<span>Add</span>
                              			</a>
                             		</li>
                               		<li>
                               			<a href="#section-iconbox-2" class="sticon mdi mdi-pencil-box-outline">
                               				<span>Edit</span>
                               			</a>
                             		</li>
                           		</ul>
                       		</nav>
	                       	<div class="content-wrap">
	                           	<section id="section-iconbox-1">
	                               	<form action="/ates/users/position" method="POST" class="floating-labels" id="add-position-form">
										<div class="form-group m-b-30 m-t-10">
							                <input type="text" id="position" name="position" class="form-control validate-empty" required="">
							                <span class="highlight"></span>
							                <span class="bar"></span>
							                <label for="position">Position Description</label>
							            </div>
							            
							            <div class="form-group m-b-0">
							                 <button type="submit" class="btn btn-raised btn-block btn-info waves-effect waves-light p-10">
	                                        	Add Position
	                                       	</button>
							            </div>
	                               	</form>
	                           	</section>
	                           	<section id="section-iconbox-2">
	                               	<form action="/ates/users/position/edit" method="POST" class="floating-labels" id="edit-position-form">
	                               		
	                               		<div class="form-group m-b-30 m-t-10">
		                               		<select id="old-position" class="form-control validate-empty p-0" name="old-position" required="">
                                   				<option value="" disabled selected></option>
                                   				<c:forEach var="position" items="${positions}">
													<option value="${position.id}">${position.description}</option>	
											    </c:forEach>
                                   			</select>
							                <span class="highlight"></span>
							                <span class="bar"></span>
							                <label for="old-position">Position</label>
						                </div>
	                               		
										<div class="form-group m-b-30">
							                <input type="text" id="new-position" name="new-position" class="form-control validate-empty" required="">
							                <span class="highlight"></span>
							                <span class="bar"></span>
							                <label for="new-position">New Position Description</label>
							            </div>
							            
							            <div class="form-group m-b-0">
							                 <button type="submit" class="btn btn-raised btn-block btn-info waves-effect waves-light p-10">
	                                        	Edit Position
	                                       	</button>
							            </div>
	                               	</form>
                       			</div>
						</div>
					</div>
				</div>
				
			</div>
			
			<!-- Toast Add -->
			<div id="alert-approve" class="myadmin-alert alert-success myadmin-alert-bottom-right"> 
				<a href="#" class="closed">&times;</a>
				<i class="mdi mdi-check-circle-outline m-r-5"></i><b class="name"></b> is successfully created.
			</div>
			
			<!-- Toast Edit -->
			<div id="alert-edit" class="myadmin-alert alert-info myadmin-alert-bottom-right"> 
				<a href="#" class="closed">&times;</a>
				<i class="mdi mdi-check-circle-outline m-r-5"></i><b class="name"></b> is successfully edited into <b class="new-name"></b> 
			</div>
			
			<!-- Toast Error -->
			<div id="alert-error" class="myadmin-alert alert-danger myadmin-alert-bottom-right"> 
				<a href="#" class="closed">&times;</a>
				<i class="mdi mdi-close-circle-outline m-r-5"></i><span class="name"></span>
			</div>
			
			
		</div>
	</div>
	
	