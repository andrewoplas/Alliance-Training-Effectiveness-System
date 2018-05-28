<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">User Profile</h4>
			</div>
			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				<ol class="breadcrumb">
					<li><a href="/ates/dashboard">Dashboard</a></li>
					<li><a href="/ates/users/list">Users List</a></li>
					<li class="active">User Profile</li>
				</ol>
			</div>
		</div>
		<!-- /.col-lg-12 -->

		<div class="row">
			<div class="col-md-4 col-xs-12">
				<div class="white-box">
					<div class="user-bg">
					</div>
					<div class="user-btm-box">
						<div class="row text-center m-t-10">
							<div class="col-md-6 b-r">
								<strong>Name</strong>
								<p>${ user.name }</p>
							</div>
							<div class="col-md-6">
								<strong>Position</strong>
								<p>${ user.position.description }</p>
							</div>
						</div>
						
						<hr>
						
						<div class="row text-center m-t-10">
							<div class="col-md-6 b-r">
								<strong>Email </strong>
								<p>${ user.email }</p>
							</div>
							<div class="col-md-6">
								<strong>Phone</strong>
								<p>+123 456 789</p>
							</div>
						</div>
						
						<hr>
						
						<div class="row text-center m-t-10">
							<div class="col-md-12">
								<strong>Address</strong>
								<p>E104, Dharti-2, Chandlodia Ahmedabad <br /> Gujarat, India.</p>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-md-8 col-xs-12">
				<div class="white-box">
					<!-- .tabs -->
					<ul class="nav nav-tabs tabs customtab">
						<li class="active tab"><a href="#training" data-toggle="tab">
								<span class="visible-xs"><i class="mdi mdi-calendar-range"></i></span> 
								<span class="hidden-xs">Trainings</span>
						</a></li>
						
						<c:if test="${ admin == false }">
						<li class="tab"><a href="#profile" data-toggle="tab"
							aria-expanded="false"> <span class="visible-xs">
							<i class="mdi mdi-account-settings-variant"></i></span> 
							<span class="hidden-xs">Edit Detail</span>
						</a></li>
						</c:if>
					</ul>
					<!-- /.tabs -->
					<div class="tab-content">
						<!-- .tabs 1 -->
						<div class="tab-pane active" id="training">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Training Title</th>
											<th class="text-center">Role</th>
											<th class="text-center">Status</th>										
										</tr>
									</thead>
									<tbody>
									
										<c:forEach var="userEvent" items="${userEvents}">
											<tr>
												<td>${ userEvent.trainingPlan.title }</td>
												<td class="text-center">
													<span class="badge ${ userEvent.role }">${ userEvent.role }</span>
												</td>
												<td class="text-center status">
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

						<!-- .tabs2 -->
						<c:if test="${ admin == false }">
						<div class="tab-pane" id="profile">
							<form class="floating-labels">
								<input type="hidden" id="id" value="${ user.id }" />
							
								<div class="row">								
								<div class="col-md-12">
	                                <div class="form-group m-b-40">
						                <input type="text" id="name" name="name" class="form-control validate-empty" required value="${user.name}">
						                <span class="highlight"></span>
						                <span class="bar"></span>
						                <label for="name">Name</label>
						                <span class="help-block hide">
						                	<small class="text-danger"><i class="mdi mdi-close-circle-outline"></i> Don't leave this field empty</small>
						                </span>
						            </div>
					            </div>
						            
						        <div class="col-md-12">
		                            <div class="form-group m-b-40">
						                <input type="text" id="email" name="email" class="form-control" required value="${user.email}">
						                <span class="highlight"></span>
						                <span class="bar"></span>
						                <label for="email">Email</label>
						            </div>
					            </div>
					            
					            <div class="col-md-12">
		                            <div class="form-group m-b-40">
	                           			<select id="position" class="form-control validate-empty p-0" name="position" required="" >
	                           				<c:forEach var="position" items="${positions}">
	                         					<option value="${position.id}" ${ user.position.id == position.id? 'selected' : '' } >
	                         						${position.description}
                         						</option>
	                           				</c:forEach>
	                            		</select>
						                <span class="highlight"></span>
						                <span class="bar"></span>
						                <label for="position">Position</label>
						            </div>
					            </div>
					            
				            	<div class="col-md-12 m-b-20">
				            		<span class="text-info"><i class="mdi mdi-information"></i> You can change your password here. </span>
				            		<div  class="p-20 p-t-30" style="border: 1px solid #eee">
		                                <div class="form-group m-b-40">
							                <input type="text" id="password" name="password" class="form-control validate-empty" required />
							                <span class="highlight"></span>
							                <span class="bar"></span>
							                <label for="password">Password</label>
							                <span class="help-block help-block-password hide">
							                	<small class="text-danger"><i class="mdi mdi-close-circle-outline"></i> Passwords are not the same.</small>
							                </span>
							            </div>
							            <div class="form-group m-b-5">
							                <input type="text" id="confirm_password" name="confirm_password" class="form-control" required />
							                <span class="highlight"></span>
							                <span class="bar"></span>
							                <label for="confirm_password">Confirm Password</label>
							                <span class="help-block help-block-password hide">
							                	<small class="text-danger"><i class="mdi mdi-close-circle-outline"></i> Passwords are not the same.</small>
							                </span>
							            </div>
						            </div>
					            </div>
					            
	                            <div class="form-group">
	                                <div class="col-sm-12">
	                                    <button type="button" class="btn-submit btn btn-info btn-fix btn-raised waves-effect waves-light">Update Profile</button>
	                                </div>
	                            </div>
	                            </div>
	                        </form>
						</div>
						</c:if>
						
					</div>
				</div>
			</div>

		</div>
	</div>
</div>



