	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Dashboard</h4> 
				</div>
			</div>
			
			
			<!-- FIRST ROW -->
			<div class="row">
				<div class="col-lg-3 col-sm-6 col-xs-12">
					<div class="white-box">
						<h3 class="box-title">Users</h3>
						<ul class="list-inline two-part">
							<li><i class="icon-people text-info"></i></li>
							<li class="text-right"><span class="counter">${userCount}</span></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6 col-xs-12">
					<div class="white-box">
						<h3 class="box-title">Approval Request</h3>
						<ul class="list-inline two-part">
							<li><i class="icon-user-follow text-purple"></i></li>
							<li class="text-right"><span class="counter">${pendingUserCount}</span></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6 col-xs-12">
					<div class="white-box">
						<h3 class="box-title">Trainings</h3>
						<ul class="list-inline two-part">
							<li><i class="ti-calendar text-danger"></i></li>
							<li class="text-right"><span class="counter">${trainingCount}</span></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6 col-xs-12">
					<div class="white-box">
						<h3 class="box-title">Training Request</h3>
						<ul class="list-inline two-part">
							<li><i class="icon-bubbles text-success"></i></li>
							<li class="text-right"><span class="counter">${trainingRequestCount}</span></li>
						</ul>
					</div>
				</div>
			</div>
	
			<!-- SECOND ROW -->
			<div class="row">
				<div class="col-sm-12 col-md-6">
					<div class="white-box p-0">
						<div class="sttabs tabs-style-iconbox">
							<nav>
								<ul>
	                            	<li>
	                              		<a href="#section-iconbox-1" class="sticon mdi mdi-calendar-blank">
	                              			<span>Annual</span>
	                             		</a>
	                            	</li>
	                              	<li>
		                      			<a href="#section-iconbox-2" class="sticon mdi mdi-calendar-multiple">
		                      				<span>Quarter</span>
		                      			</a>
	                           		</li>
	                           		<li>
	                              		<a href="#section-iconbox-3" class="sticon mdi mdi-calendar-range">
	                              			<span>Month</span>
	                             		</a>
	                            	</li>
								</ul>
	                       	</nav>
						
							<div class="content-wrap">
	                         	<section id="section-iconbox-1">
	                             	<c:forEach var="question" items="${ questions }" varStatus="loop">
	                             		<c:if test="${ question.type == 'radiobutton' || question.type == 'scale' }">
						                    <div class="question-container" data-type="${ question.type }" data-id="${ question.id }">
						                    	<p class="question">
						                    		<span>${ loop.count }</span> ${ question.description }
						                   		</p>
						                    	<div class="data-container hide">
						                    	
						                    		<c:forEach var="userEvent" items="${ userEvents }">
						                    			<c:set var="assignment" value="${ userEvent.getFormAssignment(formID) }" />
						                    			<c:if test="${ assignment.formAnswers.size() > 0 && loop.index < assignment.formAnswers.size()}">
						                    				<c:set var="answer" value="${ assignment.formAnswers.get(loop.index) }"/>
							                    			<input type="hidden" class="data" value="${ answer.description }" />
						                    			</c:if>
							                    	</c:forEach>
							                    	
							                    	<c:if test="${ question.formOptions.size() > 0 }">
								                    	<div class="data-options-container">
							                    			<c:forEach var="option" items="${ question.formOptions }">
							                    				<input type="hidden" class="data-options" value="${ option.description }" value-id="${ option.id }" />
							                    			</c:forEach>
								                    	</div>
							                    	</c:if>
							                    	
						                    	</div>
						                   	</div>
						                   	<hr />
					                   	</c:if>
				                    </c:forEach>
	                         	</section>
	                         	
	                         	<section id="section-iconbox-2">
		                            <ul class="nav nav-tabs" role="tablist">
		                                <li role="presentation" class="active">
		                                	<a href="#quarter1" aria-controls="home" role="tab" data-toggle="tab" aria-expanded="true">
		                                		<span class="visible-xs"><i class="ti-home"></i></span>
		                                		<span class="hidden-xs"> First Quarter</span>
		                                	</a>
	                                	</li>
		                                <li role="presentation">
		                                	<a href="#quarter2" aria-controls="profile" role="tab" data-toggle="tab" aria-expanded="false">
			                                	<span class="visible-xs"><i class="ti-user"></i></span> 
			                                	<span class="hidden-xs"> Second Quarter</span>
		                                	</a>
	                                	</li>
		                                <li role="presentation">
		                                	<a href="#quarter3" aria-controls="messages" role="tab" data-toggle="tab" aria-expanded="false">
		                                		<span class="visible-xs"><i class="ti-email"></i></span> 
		                                		<span class="hidden-xs">Third Quarter</span>
		                                	</a>
	                                	</li>
		                                <li role="presentation">
		                                	<a href="#quarter4" aria-controls="settings" role="tab" data-toggle="tab" aria-expanded="false">
			                                	<span class="visible-xs"><i class="ti-settings"></i></span> 
			                                	<span class="hidden-xs"> Fourth Quarter</span>
		                                	</a>
	                                	</li>
		                            </ul>
                            
                            		<div class="tab-content">
		                            	<c:forEach begin="1" end="4" varStatus="counter">
											<div role="tabpanel" class="tab-pane ${ counter.index == 1? 'active' : '' }" id="quarter${ counter.index }">
												
				                            	<c:forEach var="question" items="${ questions }" varStatus="loop">
				                             		<c:if test="${ question.type == 'radiobutton' || question.type == 'scale' }">
									                    <div class="question-container" data-type="${ question.type }" data-id="quarter${ counter.index }${ question.id }">
									                    	<p class="question">
									                    		<span>${ loop.count }</span> ${ question.description }
									                   		</p>
									                    	<div class="data-container hide">
									                    	
									                    		<c:forEach var="userEvent" items="${ userEventsQuarter.get(counter.index - 1) }">
									                    			<c:set var="assignment" value="${ userEvent.getFormAssignment(formID) }" />
									                    			<c:if test="${ assignment.formAnswers.size() > 0 && loop.index < assignment.formAnswers.size()}">
									                    				<c:set var="answer" value="${ assignment.formAnswers.get(loop.index) }"/>
										                    			<input type="hidden" class="data" value="${ answer.description }" />
									                    			</c:if>
										                    	</c:forEach>
										                    	
										                    	<c:if test="${ question.formOptions.size() > 0 }">
											                    	<div class="data-options-container">
										                    			<c:forEach var="option" items="${ question.formOptions }">
										                    				<input type="hidden" class="data-options" value="${ option.description }" value-id="${ option.id }" />
										                    			</c:forEach>
											                    	</div>
										                    	</c:if>
										                    	
									                    	</div>
									                   	</div>
									                   	<hr />
								                   	</c:if>
							                    </c:forEach>
							                    
											</div>    
										</c:forEach>
                                	</div>
	                        	</section>     	
	                        	
	                        	<section id="section-iconbox-3">
	                         		<p>Test</p>
	                        	</section>
							</div>
						</div>
					</div>
				</div>
				
				<div class="col-sm-12 col-md-6 m-b-30">
					<div id="calendar"></div>
				</div>
				
			</div>
	
		</div>
	</div>