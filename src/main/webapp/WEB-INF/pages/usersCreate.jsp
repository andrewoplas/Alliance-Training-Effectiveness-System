
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">Create User</h4>
			</div>
			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				<ol class="breadcrumb">
					<li><a href="/ates/dashboard">Dashboard</a></li>
					<li><a href="/ates/users/list">Users List</a></li>
					<li class="active">Create User</li>
				</ol>
			</div>
			<!-- /.col-lg-12 -->
		</div>

		<!-- /row -->
		<div class="row">
			<div class="col-md-6 col-md-offset-3 col-xs-12 col-sm-12">

				<div class="panel panel-info">
					<div class="panel-heading">User Creation</div>

					<div class="panel-wrapper collapse in" aria-expanded="true">
						<div class="panel-body">
							<form action="/ates/users/create" method="POST"
								class="floating-labels" id="form-create-user">
								<div class="row m-t-30">
									<div class="col-xs-12">
										<div class="form-group m-b-30">
											<input type="text" id="name" name="name"
												class="form-control validate-empty" required=""> <span
												class="highlight"></span> <span class="bar"></span> <label
												for="name">Full Name</label>
										</div>
									</div>

									<div class="col-xs-12">
										<div class="form-group m-b-30">
											<select id="position" class="form-control validate-empty p-0"
												name="position" required="">
												<option value="" disabled selected></option>
												<c:forEach var="position" items="${positions}">
													<option value="${position.id}">${position.description}</option>
												</c:forEach>
											</select> <span class="highlight"></span> <span class="bar"></span> <label
												for="position">Position</label>
										</div>
									</div>

									<div class="col-xs-12">
										<div class="form-group m-b-30">
											<input type="email" id="email" name="email"
												class="form-control validate-empty" required=""> <span
												class="highlight"></span> <span class="bar"></span> <label
												for="email">Email Address</label> <span
												class="help-block hide"> <small class="text-danger"><i
													class="mdi mdi-close-circle-outline"></i> <span>Email
														already exists.</span></small>
											</span>
										</div>
									</div>

									<div class="col-xs-12">
										<div class="form-group m-b-30">
											<div class="notification-create">
												<i class="m-r-5 mdi mdi-alert-circle-outline"></i> <span>Password
													is generated and will be sent to user's email.</span>
											</div>
										</div>
									</div>

									<div class="col-xs-12">
										<div class="form-group m-b-30">
											<button type="submit"
												class="btn btn-raised btn-fix btn-info waves-effect waves-light pull-right">
												Submit</button>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade none-border material-design" id="ajax-process">
	<div class="modal-dialog" style="width: 30%;">
		<div class="modal-content">
			<div class="modal-body text-center">Processing your request...
			</div>
		</div>
	</div>
</div>

