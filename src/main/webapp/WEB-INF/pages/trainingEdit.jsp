<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<c:set var="pids" value="" />
<c:set var="fids" value="" />
<c:set var="sids" value="" />

<c:forEach var="userEvent" items="${training.userEvents}">
    <c:choose>
         <c:when test = "${userEvent.role == 'Participant'}">
            <c:set var="pids" value="${pids},${userEvent.user.id}" />
         </c:when>
         
         <c:when test = "${userEvent.role == 'Supervisor'}">
            <c:set var="sids" value="${sids},${userEvent.user.id}" />
         </c:when>
         
         <c:otherwise>
            <c:set var="fids" value="${fids},${userEvent.user.id}" />
         </c:otherwise>
      </c:choose>
</c:forEach>

<c:set var="dateStart" value="" />
<c:set var="dateEnd" value="" />

<c:forEach var="schedule" items="${training.schedules}">
	<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${schedule.date} ${schedule.timeStart} " var="start_date" />
	<fmt:formatDate value="${start_date}" pattern="yyyy:MM:dd:HH:mm" var="formatted_start_date" />
	<c:set var="dateStart" value="${dateStart},${formatted_start_date}" />
	
	<fmt:parseDate pattern="yyyy-MM-dd HH:mm:ss" value="${schedule.date} ${schedule.timeEnd} " var="end_date" />
	<fmt:formatDate value="${end_date}" pattern="yyyy:MM:dd:HH:mm" var="formatted_end_date" />
	<c:set var="dateEnd" value="${dateEnd},${formatted_end_date}" />    
</c:forEach>


<input type="hidden" class="pids" value="${pids}" />
<input type="hidden" class="fids" value="${fids}" />
<input type="hidden" class="sids" value="${sids}" />

<input type="hidden" class="dateStarts" value="${dateStart}" />
<input type="hidden" class="dateEnds" value="${dateEnd}" />
<input type="hidden" class="nestable-serialized" value='${training.courseOutline}' />

<input type="hidden" class="training-id" value="${training.id}" />

<div id="page-wrapper">
	<div class="container-fluid full-height">		
		<div class="row">
			<div class="col-xs-12">
				<!-- multistep form -->
				<form id="msform">
					<!-- progressbar -->
					<ul id="eliteregister">
						<li class="active first-li">Schedule</li>
						<li class="second-li">Course Outline</li>
						<li class="third-li">Invite People</li>
						<li>Finalize</li>
					</ul>
					
					<!-- fieldsets 1 -->
					<fieldset class="first-fieldset">
						<h2 class="fs-title">Training Information and Schedule</h2>
						
						<br/>
						<div class="floating-labels">
							<div class="form-group m-b-30">
				                <input type="text" id="training" class="form-control validate-empty" required="" value="${training.title}">
				                <span class="highlight"></span>
				                <span class="bar"></span>
				                <label for="training">Training Title</label>
				                <span class="help-block help-block-empty hide">
				                	<small class="text-danger"><i class="mdi mdi-close-circle-outline"></i> Don't leave this field empty</small>
				                </span>
				                
				            </div>
				            
							<div class="form-group m-b-30">
								<textarea id="description" maxlength="300" class="form-control validate-empty" required="" style="font-size: 16px;height: 130px;">${training.description}</textarea>
								<span class="highlight"></span>
				                <span class="bar"></span>
				      			<label for="description">Description</label>
				      			<span class="help-block help-block-empty hide">
				                	<small class="text-danger"><i class="mdi mdi-close-circle-outline"></i> Don't leave this field empty</small>
				                </span>
							</div>
						</div>
						
						<!-- Calendar -->
						<div class="row m-b-30">
							<div class="col-md-12">
								<div id="calendar"></div>
								<span class="help-block help-block-schedule text-left hide">
				                	<small class="text-danger"><i class="mdi mdi-close-circle-outline"></i> You haven't set a schedule yet.</small>
				                </span>
							</div>							
						</div>					 	
						
						<button type="button" class="btn waves-effect waves-light next action-button btn-raised btn-fixed" id="first-step" name="next">
							Next
						</button>
					</fieldset>
					
					<!-- fieldsets 2 -->
					<fieldset class="second-fieldset">
						<h2 class="fs-title">Objectives and Course Outline</h2>
						                    	
						                    	
						<div class="bordered-box">
							<div class="myadmin-dd-empty dd" id="nestable2">
		                        
		                    </div>
		                    
							<a href="javascript:void(0)" class="btn-add-item btn-block text-center"> 
								<i class="mdi mdi-plus"></i> Add Item
							</a>
						</div>
                    	
						<span class="help-block help-block-outline text-left hide">
		                	<small class="text-danger"><i class="mdi mdi-close-circle-outline"></i> You haven't set a course outline yet.</small>
		                </span>                
		                
						<button type="button" name="previous" class="m-t-30 btn waves-effect waves-light previous action-button btn-raised btn-fixed">
							Previous
						</button>
						<button type="button" name="next" class="m-t-30 btn waves-effect waves-light next action-button btn-raised btn-fixed" id="second-step">
							Next
						</button>						
					</fieldset>
					
					<!-- fieldsets 3 -->
					<fieldset class="third-fieldset">
						<h2 class="fs-title">Invite People</h2>
							<div class="panel-group" role="tablist" aria-multiselectable="true">
								
								<div class="panel panel-info">
									<div class="panel-heading active" role="tab">
									    <h4 class="panel-title"> 
									    	<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse2" aria-expanded="true" aria-controls="collapse2" class="font-bold text-left"> 
									    		<i class="mdi mdi-account-check m-r-5"></i><span>Supervisors</span>
									    	</a> 
								    	</h4> 
								    </div>
									<div id="collapse2" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
									    <div class="panel-body">
									    	<select class="ui search dropdown multi-select" multiple="" id="supervisor-select">
											  	<option value="">Choose Supervisors</option>
											  	<c:forEach var="user" items="${users}">
											  		<option value="${user.id}" data-email="${user.email}">${user.name}</option>
								    			</c:forEach>
											</select>
											
											<br/>
											<span class="help-block help-block-supervisors text-left hide">
							                	<small class="text-danger">
							                		<i class="mdi mdi-close-circle-outline pull-left m-r-5"></i> 
							                		<span class="line-height-span">You haven't selected Supervisors yet.</span>
							                	</small>
							                </span>
									    </div>
									</div>
								</div>
							
								<div class="panel panel-info m-t-20">
									<div class="panel-heading active" role="tab">
									    <h4 class="panel-title"> 
									    	<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse3" aria-expanded="true" aria-controls="collapse3" class="font-bold text-left"> 
									    		<i class="mdi mdi-account m-r-5"></i><span>Facilitators</span>
									    	</a> 
								    	</h4> 
								    </div>
									<div id="collapse3" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
									    <div class="panel-body">
								    		<select class="ui search dropdown multi-select" multiple="" id="facilitator-select">
											  	<option value="">Choose Facilitators</option>
											  	<c:forEach var="user" items="${users}">
											  		<option value="${user.id}" data-email="${user.email}">${user.name}</option>
								    			</c:forEach>
											</select>
											
											<br/>
											<span class="help-block help-block-facilitators text-left hide">
							                	<small class="text-danger">
							                		<i class="mdi mdi-close-circle-outline pull-left m-r-5"></i> 
							                		<span class="line-height-span">You haven't selected Facilitators yet.</span>
							                	</small>
							                </span>
									    </div>
									</div>
								</div>
								
								<div class="panel panel-info m-t-20">
									<div class="panel-heading active" role="tab">
									    <h4 class="panel-title"> 
									    	<a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse1" aria-expanded="true" aria-controls="collapse1" class="font-bold text-left"> 
									    		<i class="mdi mdi-account-multiple m-r-5"></i><span>Participants</span>
									    	</a> 
								    	</h4> 
								    </div>
									<div id="collapse1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
									    <div class="panel-body">
									    	<select class="ui search dropdown multi-select" multiple="" id="participant-select">
											  	<option value="">Choose Participants</option>
											  	<c:forEach var="user" items="${users}">
											  		<option value="${user.id}" data-email="${user.email}">${user.name}</option>
								    			</c:forEach>
											</select>
											
											<br/>
											<span class="help-block help-block-participants text-left hide">
							                	<small class="text-danger">
							                		<i class="mdi mdi-close-circle-outline pull-left m-r-5"></i> 
							                		<span class="line-height-span">You haven't selected Participants yet.</span>
							                	</small>
							                </span>
									    </div>
									</div>
								</div>
                        	</div>
						
						<div class="clear"></div>
						
						<button type="button" name="previous" class="m-t-30 btn waves-effect waves-light previous action-button btn-raised btn-fixed">
							Previous
						</button>
						<button type="button" name="next" class="m-t-30 btn waves-effect waves-light next action-button btn-raised btn-fixed" id="third-step">
							Next
						</button>	
						
					</fieldset>
					
					<!-- fieldsets 4 -->
					<fieldset class="fourth-fieldset remove-card">
						<div class="row m-b-30">
							<div class="col-md-12 "> 
								<p class="bg-info summary-container"> SUMMARY </p>
							</div>
						</div>
						
						<div class="panel panel-default">
                            <div class="panel-heading">
                            	<div class="pull-left"> 
                            		<a href="javascript:void(0)" data-perform="panel-collapse"><i class="ti-minus"></i></a>
                            	</div>
                            	Information and Schedule
                                <div class="pull-right">
                                	<a href="javascript:void(0)" class="fieldset-goto" fieldset="first" data-toggle="tooltip" title="Edit Section" data-placement="left">
                                		<i class="ti-pencil-alt"></i>
                               		</a>
                                </div>
                            </div>
                            <div class="panel-wrapper collapse in" aria-expanded="true">
                                <div class="panel-body text-left">
                                	<div class="row m-b-20">
                                		<div class="col-md-3"><b>Training</b></div>
                                		<div class="col-md-9"><span data-value="training"></span></div>
                                	</div>
                                	
                                	<div class="row m-b-20">
                                		<div class="col-md-3"><b>Description</b></div>
                                		<div class="col-md-9"><span data-value="description"></span></div>
                                	</div>
                                    
                                    <div class="row">
                                		<div class="col-md-3"><b>Schedule</b></div>
                                		<div class="col-md-9">
                                			<div class="table-responsive">
												<table id="schedule-table" class="table table-striped m-t-0">
						                           <thead>
						                               <tr>
						                                   <th>#</th>
						                                   <th>Date</th>
						                                   <th>Start Time</th>
						                                   <th>End Time</th>
						                                   <th>Day</th>	                                   
						                               </tr>
						                           </thead>
						                           <tbody></tbody>
						                       </table>
											</div>
										</div>
                                	</div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="panel panel-default">
                            <div class="panel-heading"> 
                            	<div class="pull-left"> 
                            		<a href="javascript:void(0)" data-perform="panel-collapse"><i class="ti-minus"></i></a>
                            	</div>
                            	Course Outline
                                <div class="pull-right">
                                	<a href="javascript:void(0)" class="fieldset-goto" fieldset="second" data-toggle="tooltip" title="Edit Section" data-placement="left">
                                		<i class="ti-pencil-alt"></i>
                               		</a>
                                </div>
                            </div>
                            <div class="panel-wrapper collapse in" aria-expanded="true">
                                <div class="panel-body">
                                    <div data-value="courseOutline" class="text-left"></div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="panel panel-default">
                            <div class="panel-heading"> 
                            	<div class="pull-left"> 
                            		<a href="javascript:void(0)" data-perform="panel-collapse">
                            			<i class="ti-minus"></i>
                           			</a>
                            	</div>
                            	People
                                <div class="pull-right">
                                	<a href="javascript:void(0)" class="fieldset-goto" fieldset="third" data-toggle="tooltip" title="Edit Section" data-placement="left">
                                		<i class="ti-pencil-alt"></i>
                               		</a>
                                </div>
                            </div>
                            <div class="panel-wrapper collapse in" aria-expanded="true">
                                <div class="panel-body text-left">
                                    <div class="row m-b-20">
                                		<div class="col-md-3"><b>Supervisors</b></div>
                                		<div class="col-md-9"><div data-value="supervisors"></div></div>
                                	</div>
                                	
                                	<div class="row m-b-20">
                                		<div class="col-md-3"><b>Facilitators</b></div>
                                		<div class="col-md-9"><div data-value="facilitators"></div></div>
                                	</div>
                                	
                                	<div class="row m-b-20">
                                		<div class="col-md-3"><b>Participants</b></div>
                                		<div class="col-md-9"><div data-value="participants"></div></div>
                                	</div>
                                	
                                </div>
                            </div>
                        </div>
						
						<div class="white-box">
							<button type="button" name="previous" class="btn waves-effect waves-light previous action-button btn-raised btn-fixed">
								Previous
							</button>
							<button type="button" name="submit" class="btn waves-effect waves-light submit action-button btn-raised btn-fixed">
								Submit
							</button>
						</div>
					</fieldset>
				</form>
				
				<div class="clearfix"></div>
			</div>
		</div>		
	</div>
</div>

<div class="modal fade none-border material-design" id="ajax-process">
    <div class="modal-dialog" style="width: 30%;">
        <div class="modal-content">
            <div class="modal-body text-center">
            	Processing your request...
            </div>
        </div>
    </div>
</div>


<div class="modal fade none-border material-design" id="my-event">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title text-left">Add Event</h4>
            </div>
            <div class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-raised btn-fix btn-default waves-light btn-outline waves-effect m-r-5" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-raised btn-fix btn-info save-event waves-effect waves-light">Create</button>
                <button type="button" class="btn btn-raised btn-fix btn-danger delete-event waves-effect waves-light pull-left" data-dismiss="modal">Delete</button>
                <button type="button" class="btn btn-raised btn-fix btn-info save-event2 waves-effect waves-light">Save</button>
            </div>
        </div>
    </div>
</div>
