
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">Skills Assessment View</h4>
			</div>
			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				<ol class="breadcrumb">
					<li><a href="/ates/dashboard">Dashboard</a></li>
					<li><a href="/ates/forms">Forms</a></li>
					<li class="active">Skills Assessment View</li>
				</ol>
			</div>
			<!-- /.col-lg-12 -->
		</div>

		<!-- /row -->
		<div class="row">
			<div class="col-sm-12">

				<div class="panel panel-info">
					<div class="panel-heading">Skills Assessment - Training</div>

					<div class="panel-wrapper collapse in" aria-expanded="true">
						<div class="panel-body">
							<div class="table-responsive">

								<table id="skills-assessment" class="table table-striped">
									<thead>
										<tr>
											<th>Name</th>
											<th>Self</th>
											<th>Supervisor</th>
											<th>Peers</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="userEvent" items="${ userEvents }">
											<c:set var="assignments" value="${ userEvent.getSaAssignments2Answered(userEvent.id) }"  />
											<tr>
												<td width="30%"> ${ userEvent.user.name } </td>
												<td width="20%">
													<c:forEach var="assignment" items="${ assignments }">
														<c:if test="${ assignment.type == 'Self' }">
															<a href="/ates/forms/skills-assessment/view/answer/${ assignment.id }">
																<span class="badge badge-success">${ assignment.userEvent1.user.name }</span>
															</a>
														</c:if>
													</c:forEach>
												</td>
												<td width="20%">
													<c:forEach var="assignment" items="${ assignments }">
														<c:if test="${ assignment.type == 'Supervisor' }">
															<a href="/ates/forms/skills-assessment/view/answer/${ assignment.id }">
																<span class="badge badge-info">${ assignment.userEvent1.user.name }</span>
															</a>
														</c:if>
													</c:forEach>
												</td>
												<td width="30%">
													<c:forEach var="assignment" items="${ assignments }">
														<c:if test="${ assignment.type == 'Peer' }">
															<a href="/ates/forms/skills-assessment/view/answer/${ assignment.id }">
																<span class="badge badge-warning">${ assignment.userEvent1.user.name }</span>
															</a>
														</c:if>
													</c:forEach>
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

	</div>
</div>


