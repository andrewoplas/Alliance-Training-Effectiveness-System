
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<input type="hidden" class="nestable-serialized"
	value='${training.courseOutline}' />
<input type="hidden" name="id" value='${training.id}' />
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">Skills Assessment Assignment</h4>
			</div>

			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				<ol class="breadcrumb">
					<li><a href="/ates/dashboard">Dashboard</a></li>
					<li><a href="/ates/training">Training List</a></li>
					<li class="active">Skills Assessment</li>
				</ol>
			</div>
			<!-- /.col-lg-12 -->
		</div>

		<div class="row">
			<div class="col-md-12">
				<div class="white-box p-0 font-poppins">
					<div class="title-container container-width">
						<p>${ training.title }</p>
					</div>

					<hr class="custom-hr" />

					<div class="outline-container container-width">
						<h3 class="text-center text-bold section-title">
							ASSIGNMENT<span>ASSIGNMENT</span>
						</h3>
						<div class="assessmet-body p-b-20">
							<table class="table attendance-table">
								<thead>
									<tr>
										<th class="nosort"></th>
										<th>Name</th>
										<th class="text-center">Peers</th>
										<th class="text-center">Supervisor</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="participant" items="${ participants }">
										<tr data-id="${ participant.userEventID }" class="advance-table-row">
											<td width="3%"></td>
											<td width="30%">${ participant.name }</td>
											<td width="50%">
												<select class="ui search dropdown multi-select" multiple="" id="${ participant.userEventID }-peer">
												  	<option value="" disabled>Choose Peer</option>
												  	<c:forEach var="innerParticipant" items="${ participants }">
												  		<c:if test="${ innerParticipant.userEventID != participant.userEventID }">
												  			<option 
												  				<c:if test="${ assignments.containsKey(participant.userEventID) && 
												  					assignments.get(participant.userEventID).contains(innerParticipant.userEventID)
											  						}">
											  					selected="selected"
											  					</c:if>
												  				value="${innerParticipant.userEventID}">${innerParticipant.name}</option>
												  		</c:if>
									    			</c:forEach>
												</select>
											</td>
											<td width="25%">
												<select class="ui search dropdown multi-select" multiple="" id="${ participant.userEventID }-supervisor">
												  	<option value="" disabled>Choose Supervisor</option>
												  	<c:forEach var="supervisor" items="${ supervisors }">
												  		<option 
												  			<c:if test="${ assignments.containsKey(participant.userEventID) && 
											  					assignments.get(participant.userEventID).contains(supervisor.userEventID)
										  						}">
										  					selected="selected"
										  					</c:if>
												  		value="${supervisor.userEventID}">${supervisor.name}</option>
									    			</c:forEach>
												</select>
											</td>
										</tr>

										<td colspan="3" class="sm-pd"></td>
									</c:forEach>
								</tbody>
							</table>
							
							<div class="text-center p-20">
								<button class="btn btn-info btn-fix btn-raised waves-effect waves-light" id="submit">Submit</button>
							</div>
							
						</div>
					</div>

				</div>
			</div>
		</div>

	</div>
</div>
