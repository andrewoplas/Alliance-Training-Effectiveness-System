
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">Forms Assignment</h4>
			</div>
			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				<ol class="breadcrumb">
					<li><a href="/ates/dashboard">Dashboard</a></li>
					<li class="active">Forms Assignment</li>
				</ol>
			</div>
			<!-- /.col-lg-12 -->
		</div>

		<!-- /row -->
		<div class="row">
			<div class="col-sm-12">

				<div class="panel panel-info">
					<div class="panel-heading">Forms</div>

					<div class="panel-wrapper collapse in" aria-expanded="true">
						<div class="panel-body">
							<div class="table-responsive">

								<table id="pending-users-table" class="table table-striped">
									<thead>
										<tr>
											<th>Training</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="training" items="${ trainings }">
											<tr>
												<td width="50%">${ training.title }</td>
												<td width="50%"><a
													href="/ates/forms/skills-assessment/view/${ training.id }">
														<button type="button"
															class="btn btn-warning btn-outline btn-circle m-r-5 p-t-0 p-b-0"
															data-toggle="tooltip" title="" data-placement="top"
															data-original-title="View Skills Assessment Form">
															<i class="mdi mdi-account-switch"></i>
														</button>
												</a></td>
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

	</div>
</div>


