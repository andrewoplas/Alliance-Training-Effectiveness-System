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
											<th>Forms</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="training" items="${ trainings }">
											<tr>
												<td>${ training.title }</td>
												<td width="40%"><a
													href="/ates/forms/skills-assessment/view/${ training.id }">
														<span type="span" class="badge badge-success m-r-5"
														data-toggle="tooltip" title="" data-placement="top"
														data-original-title="Skills Assessment Form"> SA </span>
												</a> <a href="/ates/forms/course-feedback/view/${ training.id }">
														<span type="span" class="badge badge-info m-r-5"
														data-toggle="tooltip" title="" data-placement="top"
														data-original-title="Course Feedback Form"> CF </span>
												</a> <a
													href="/ates/forms/facilitator-feedback/view/${ training.id }">
														<span type="span" class="badge badge-purple m-r-5"
														data-toggle="tooltip" title="" data-placement="top"
														data-original-title="Facilitator's Feedback Form">
															FF </span>
												</a> <a
													href="/ates/forms/training-effectiveness-assessment/view/${ training.id }">
														<span type="span" class="badge badge-warning m-r-5"
														data-toggle="tooltip" title="" data-placement="top"
														data-original-title="Training Effectiveness Assessment Form">
															TEA </span>
												</a> <a
													href="/ates/forms/training-needs-assessment/view/${ training.id }">
														<span type="span" class="badge badge-danger m-r-5"
														data-toggle="tooltip" title="" data-placement="top"
														data-original-title="Training Need Analysis Form">
															TNA </span>
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


