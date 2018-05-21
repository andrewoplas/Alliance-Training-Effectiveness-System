	
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
	<jsp:useBean id="now" class="java.util.Date"/>
	<jsp:setProperty name="now" property="date" value="${now.date}" />
	
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Attendance</h4>
				</div>
				<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
					<ol class="breadcrumb">
						<li><a href="/ates/dashboard">Dashboard</a></li>
						<li><a href="/ates/training">Training List</a></li>
						<li class="active">Attendance</li>
					</ol>
				</div>
				<!-- /.col-lg-12 -->
			</div>		
			
	
			<div class="row">
	            <div class="col-md-12">
	            	<input type="hidden" name="id" value="${ training.id }"/>
	            	
	                <div class="white-box">
	                	<p class="training-title">${ training.title }</p>
                    	<section class="cd-horizontal-timeline">
                        	<div class="timeline">
	                            <div class="events-wrapper">
	                                <div class="events">
	                                    <ol>
	                                    
	                                    <c:set var="selected" value="false" />
	                                    <c:set var="selectedClass" value="" />
                                    	<c:forEach var="schedule" items="${schedules}" varStatus="loop">
											<fmt:formatDate value="${schedule.date}" pattern="dd/MM/yyyy" var="data_date" />
											<fmt:formatDate value="${schedule.date}" pattern="dd MMM" var="date_display" />
												
												<c:choose>
													<c:when test="${ (schedule.date gt now || schedule.isEquals(schedule.date, now)) && !selected }">
														<c:set var="selected" value="true" />
														<c:set var="selectedClass" value="selected" />
													</c:when>
													<c:when test="${ loop.last && !selected }">
														<c:set var="selected" value="true" />
														<c:set var="selectedClass" value="selected" />
													</c:when>
													<c:otherwise>
														<c:set var="selectedClass" value="" />
													</c:otherwise>
												</c:choose>
												
												<li>
													<a href="#0" data-date="${ data_date }" class="${ selectedClass }">
														${ date_display }
													</a>
												</li>
												
										</c:forEach>
											
	                                    </ol> <span class="filling-line" aria-hidden="true"></span> </div>
	                                <!-- .events -->
	                            </div>
	                            <!-- .events-wrapper -->
	                            <ul class="cd-timeline-navigation">
	                                <li><a href="#0" class="prev inactive">Prev</a></li>
	                                <li><a href="#0" class="next">Next</a></li>
	                            </ul>
	                            <!-- .cd-timeline-navigation -->
	                        </div>
	                        <!-- .timeline -->
	                        <div class="events-content">
	                            <ol>
	                            	<c:set var="selected" value="false" />
	                                <c:set var="selectedClass" value="" />
	                                <c:set var="count" value="1" scope="page" />
	                            	<c:forEach var="schedule" items="${ schedules }" varStatus="loop">
										<fmt:formatDate value="${ schedule.date }" pattern="MMMM dd, yyyy" var="date_display" />
										<fmt:formatDate value="${ schedule.date }" pattern="dd/MM/yyyy" var="data_date" />
										
										<c:choose>
											<c:when test="${ (schedule.date gt now || schedule.isEquals(schedule.date, now)) && !selected }">
												<c:set var="selected" value="true" />
												<c:set var="selectedClass" value="selected" />
											</c:when>
											<c:when test="${ loop.last && !selected }">
												<c:set var="selected" value="true" />
												<c:set var="selectedClass" value="selected" />
											</c:when>
											<c:otherwise>
												<c:set var="selectedClass" value="" />
											</c:otherwise>
										</c:choose>
										
										<li data-date="${ data_date }" class="${ selectedClass }">
											<input type="hidden" name="date" value="${ schedule.date }"/>
											<h2> 
												<img class="img-responsive pull-left m-r-20 m-b-10" width="60" src="/images/calendar.png">
												Day ${ count } <br />
												<small>${ date_display }</small>
											</h2>
											<hr class="m-t-40">
											<div class="m-t-40">
												<!-- Participants List -->
												<c:choose>
													<c:when test="${ schedule.date > now }">
														<div class="text-center empty-container">
															<i class="mdi mdi-calendar-question empty-icon"></i>
															<p class="empty-text">
																There are no data for <br />this day yet!
															</p>
														</div>
													</c:when>
													<c:when test="${ schedule.isEquals(schedule.date, now) || schedule.date < now }">
														<div class="button-container text-center" style="display: none">
															<button class="btn btn-fixed btn-raised btn-info m-r-10 waves-effect waves-light" data-toggle="modal" data-target="#timeIn">Set Time In</button>
															<button class="btn btn-fixed btn-raised btn-info waves-effect waves-light" data-toggle="modal" data-target="#timeOut">Set Time Out</button>
														</div>
														
														<div class="">
														<table class="table attendance-table">
															<thead>
																<tr>
																	<th class="nosort"></th>
																	<th class="nosort">
																		<div class="checkbox checkbox-circle checkbox-info m-t-0 m-b-0">
						                                                    <input value="all" class="all" type="checkbox" />
						                                                    <label for="all"> </label>
						                                                </div>
																	</th>
																	<th>Name</th>
																	<th class="text-center nosort">Time In</th>
																	<th class="text-center nosort">Time Out</th>
																	<th class="nosort">Action</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach var="participant" items="${ participants }">
																	<tr data-id="${ participant.id }" class="advance-table-row">
																		<td width="3%"> </td>
																		<td width="5%">
							                                                <div class="checkbox checkbox-circle checkbox-info">
							                                                    <input value="${ participant.id }" id="${ participant.id }" type="checkbox" class="row-checkbox">
							                                                    <label for="${ participant.id }"> </label>
							                                                </div>
							                                            </td>
																		<td width="30%"> ${ participant.name } </td>
																		<c:set var="attendanceTime" value="${ participant.getAttendanceByTrainingAndDate(training.id, schedule.date) }"/>
																		<td width="15%" class="timeIn text-center"> 
																			<c:choose>
																				<c:when test="${ attendanceTime.timeIn.getSeconds() == 1 }">
																					<span class="badge badge-danger">Absent</span>
																				</c:when>
																				<c:otherwise>
																					<fmt:formatDate value="${ attendanceTime.timeIn }" pattern="hh:mm a" />
																				</c:otherwise>
																			</c:choose>
																		</td>
																		<td width="15%" class="timeOut text-center"> 
																			<c:choose>
																				<c:when test="${ attendanceTime.timeOut.getSeconds() == 1 }">
																					<span class="badge badge-danger">Absent</span>
																				</c:when>
																				<c:otherwise>
																					<fmt:formatDate value="${ attendanceTime.timeOut }" pattern="hh:mm a"/>
																				</c:otherwise>
																			</c:choose>
																		</td>
																		<td width="30%">		
																			<span data-toggle="modal" data-target="#timeIn">																	
								                                        	<button type="button" class="btn-timeIn btn btn-info btn-outline btn-circle m-r-5 p-t-0 p-b-0" data-toggle="tooltip" title="Set Time In" data-placement="top">
								                                        		IN
							                                        		</button>
							                                        		</span>
						                                        			
						                                        			<span data-toggle="modal" data-target="#timeOut">
								                                        	<button type="button" class="btn-timeOut btn btn-info btn-outline btn-circle m-r-10 p-t-0 p-b-0" data-toggle="tooltip" title="Set Time Out" data-placement="top">
								                                        		OUT
							                                        		</button>
							                                        		</span>


								                                        	<button type="button" class="btn-absent btn btn-danger btn-outline btn-circle m-r-5 p-t-0 p-b-0" data-toggle="tooltip" title="Mark As Absent" data-placement="top">
								                                        		<i class="mdi mdi-account-remove"></i>
							                                        		</button>

																			<button type="button" class="btn-reset btn btn-success btn-outline btn-circle p-t-0 p-b-0" data-toggle="tooltip" title="Reset Attendance" data-placement="top">
																				<i class="mdi mdi-account-convert"></i>
																			</button>
																		</td>
																	</tr>	
																	
																	<td colspan="6" class="sm-pd"></td>
																</c:forEach>
															</tbody>
														</table>
														</div>
													</c:when>
													
												</c:choose>
												
												
											</div>
										</li>
										
										<c:set var="count" value="${ count + 1 }" scope="page"/>
									</c:forEach>
									
								</ol>
	                        </div>
	                        <!-- .events-content -->
	                    </section>
	                </div>
	            </div>
	        </div>
	
		</div>
	</div>
	
	
	<div class="modal fade none-border material-design" id="timeIn">
	    <div class="modal-dialog">
	        <div class="modal-content" style="width: 370px; margin: auto;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title text-center">Set Time In</h4>                
	            </div>
	            <div class="modal-body">
	            	<div class="form-group m-b-20">
	            		<input type="hidden" name="user_id" />
		                <input type="text" id="timepickerTimeIn" name="time" class="form-control" required="" placeholder="Select Time">
		            </div>
	            </div>
	            <div class="modal-footer text-center">
	                <button type="button" class="btn btn-raised btn-fix btn-default waves-light btn-outline waves-effect m-r-5" data-dismiss="modal">Close</button>
	                <button type="button" class="btn btn-raised btn-fix btn-info save-event waves-effect waves-light" id="btn-timeIn">Set</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	<div class="modal fade none-border material-design" id="timeOut">
	    <div class="modal-dialog">
	        <div class="modal-content" style="width: 370px; margin: auto;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title text-center">Set Time Out</h4>                
	            </div>
	            <div class="modal-body">
	            	<div class="form-group m-b-20">
	            		<input type="hidden" name="user_id" />
		                <input type="text" id="timepickerTimeOut" name="time" class="form-control" required="" placeholder="Select Time">
		            </div>
	            </div>
	            <div class="modal-footer text-center">
	                <button type="button" class="btn btn-raised btn-fix btn-default waves-light btn-outline waves-effect m-r-5" data-dismiss="modal">Close</button>
	                <button type="button" class="btn btn-raised btn-fix btn-info save-event waves-effect waves-light" id="btn-timeOut">Set</button>
	            </div>
	        </div>
	    </div>
	</div>
	
	