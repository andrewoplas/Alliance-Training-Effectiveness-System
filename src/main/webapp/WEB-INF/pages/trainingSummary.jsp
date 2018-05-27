	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
	
	
	<input type="hidden" class="nestable-serialized" value='${training.courseOutline}' />
	<input type="hidden" name="id" value='${training.id}' />
	<div id="page-wrapper">
          <div class="container-fluid">
              <div class="row bg-title">
                  <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                      <h4 class="page-title">Training Summary</h4> </div>
                      
                  <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                      <ol class="breadcrumb">
                          <li><a href="/ates/dashboard">Dashboard</a></li>
                          <li><a href="/ates/training/list">Training List</a></li>
                          <li class="active">Summary</li>
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
						
						<div class="description-container container-width">
							<p class="text-muted">${ training.description }</p>
						</div>
						
						<div class="outline-container container-width">
							<hr/>
							<h3 class="text-center text-bold section-title">
								COURSE OUTLINE
								<span>COURSE OUTLINE</span>
							</h3>
							<div class="outline-body">
							
							</div>
						</div>
						
						<div class="schedule-container">
							<div class="container-width">
								<hr/>
								<h3 class="text-center text-bold section-title">
									SCHEDULE
									<span>SCHEDULE</span>
								</h3>
								
	                            <ul class="timeline">
	                            	<c:forEach var="schedule" items="${ schedules }" varStatus="loop">
	                                <li class="timeline-inverted">
	                                    <div class="timeline-badge info">
	                                    	<i class="mdi mdi-calendar-blank"></i> 
	                                   	</div>
	                                    <div class="timeline-panel">
	                                        <div class="timeline-heading">
	                                            <h4 class="timeline-title text-bold">
	                                            	<fmt:formatDate value="${ schedule.date }" pattern="MMMM dd, yyyy" />
	                                            </h4>
	                                            <span class="badge badge-info pull-right">
	                                            	<fmt:formatDate value="${ schedule.date }" pattern="EEEE" />
	                                            </span>
	                                            <p class="text-muted">
	                                            	<i class="fa fa-clock-o"></i>
	                                            	<small class="time">
														<fmt:formatDate value="${ schedule.timeStart }" pattern="hh:mm a"/>
														 - 
													 	<fmt:formatDate value="${ schedule.timeEnd }" pattern="hh:mm a"/>
													</small>
	                                            	 
	                                           	</p>
	                                        </div>
	                                        <div class="timeline-body">
	                                            <p></p>
	                                        </div>
	                                    </div>
	                                </li>
	                                </c:forEach>
	                        	</ul>
							</div>						
              			</div>
              		
	              		<div class="people-container container-width">
							<hr/>
							<h3 class="text-center text-bold section-title">
								PEOPLE
								<span>PEOPLE</span>
							</h3>
							
							<div class="people-body">
								
								<c:forEach var="person" items="${ internal }">
								<div class="top-item text-center">
									<i class="mdi mdi-account-location"></i> <br> 
									<span class="name">	${ person.name } </span>
									<span class="text-muted position-title">INTERNAL FACILITATOR</span>
								</div>
								</c:forEach>
								
								<c:forEach var="person" items="${ external }">
								<div class="top-item text-center">
									<i class="mdi mdi-account-location"></i> <br> 
									<span class="name"> ${ person.name } </span>
									<span class="text-muted position-title">EXTERNAL FACILITATOR</span>
								</div>
								</c:forEach>
								
								<c:forEach var="person" items="${ supervisors }">
								<div class="top-item text-center">
									<i class="mdi mdi-account-location"></i> <br> 
									<span class="name">	${ person.name } </span>
									<span class="text-muted position-title">SUPERVISOR</span>
								</div>
								</c:forEach>
								
								<div class="row m-t-20">
									<c:forEach var="person" items="${ participants }">
									<div class="col-xs-12 col-sm-4">
										<div class="white-box p-10 participant-item">
											<i class="mdi mdi-account text-info"></i>
											<span class="name text-bold">${ person.name }</span> <br />
											<span class="position-title">PARTICIPANT</span>
										</div>
									</div>	
									</c:forEach>					
								</div>
							</div>
						</div>
						
		              </div>
				</div>
			</div>
