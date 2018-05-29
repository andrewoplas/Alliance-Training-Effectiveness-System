<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">${ form.description }</h4>
			</div>
			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				<ol class="breadcrumb">
					<li><a href="/ates/dashboard">Dashboard</a></li>
					<li><a href="/ates/forms/assignment">Form Assignment</a></li>
					<li class="active">Form Answer</li>
					
				</ol>
			</div>
			<!-- /.col-lg-12 -->
		</div>

		<!-- /row -->
		<div class="row">
			<div class="col-xs-12">

				<div class="panel panel-info">
					<div class="panel-heading">
						<span>BY: ${ assignment.userEvent.user.name }</span>
					</div>

					<div class="panel-wrapper collapse in" aria-expanded="true">
						<div class="panel-body">
							
							<c:forEach var="answer" items="${ answers }" varStatus="loop">
								<c:set var="question" value="${ answer.formQuestion }" />
								<div class="question-container" data-type="${ question.type }">
									<p class="question m-b-0">${loop.count}. ${ question.description }</p>
								<c:choose>
									
									<%-- Textbox --%>
               						<c:when test="${ question.type == 'textbox' }">
								        <div class="input-field m-t-5">
							          		 <textarea class="materialize-textarea" readonly>${ answer.description }</textarea>
								        </div>
               						</c:when>
               						
               						<%-- RadioButton --%>
               						<c:when test="${ question.type == 'radiobutton' }">
					        			<div class="option-container">
					        				<c:forEach var="option" items="${ question.formOptions }">
						        				<div class="option-item m-b-5">
						        					<input type="radio" class="with-gap" name="question${ question.id }" id="option${ option.id }" ${ answer.description == option.id? 'checked' : '' } />
								              		<label for="option${ option.id }">${ option.description }</label>
						        				</div>
								    		</c:forEach>
					        			</div>	      				
               						</c:when>
                						
                					<%-- Checkbox --%>
               						<c:when test="${ question.type == 'checkbox' }">
					        			<div class="option-container">
					        				<c:set var="checkboxAnswers" value="${fn:split(answer.description, ',')}" />
					        				<c:forEach var="option" items="${ question.formOptions }">
					        					<c:forEach var="checkboxAnswer" items="${ checkboxAnswers }">	
					        						<c:if test="${ checkboxAnswer == option.id }">
													    <c:set var="checked" value="checked" />
													</c:if>
					        					</c:forEach>
						        				<div class="option-item m-b-5">
						        					<input type="checkbox" name="question${ question.id }" id="option${ option.id }" ${ checked } />
								              		<label for="option${ option.id }">${ option.description }</label>
						        				</div>
								    		</c:forEach>
					        			</div>	            				
               						</c:when>
                						
               						<%-- Scale --%>
               						<c:when test="${ question.type == 'scale' }">		        							
		        							<div class="row radio-labels text-center scale">
		          								<div class="col s3">
			            							<div class="row radio-label">&nbsp;</div>
			           								<div class="row radio-button text-right m-r-5">Strongly Agree</div>
		          								</div>
		          								
									          	<div class="col s1 center-align p-0">
									            	<div class="row radio-label p-0">1</div>
									            	<div class="row grey lighten-4 radio-button">
										              	<input value="1" class="with-gap" name="question${ question.id }" type="radio" id="option${ question.id }1" 
										              		${ answer.description == 1? 'checked' : '' }
										              	/>
										              	<label for="option${ question.id }1"></label>
									            	</div>
									          	</div>
									          	
									          	<div class="col s1 center-align p-0">
									            	<div class="row radio-label p-0">2</div>
									            	<div class="row grey lighten-4 radio-button">
									              		<input value="2" class="with-gap" name="question${ question.id }" type="radio" id="option${ question.id }2" 
									              			${ answer.description == 2? 'checked' : '' }
									              		/>
									              		<label for="option${ question.id }2"></label>
									            	</div>
									          	</div>
									          	
		         								<div class="col s1 center-align p-0">
		            								<div class="row radio-label p-0">3</div>
								            		<div class="row grey lighten-4 radio-button">
								              			<input value="3" class="with-gap" name="question${ question.id }" type="radio" id="option${ question.id }3" 
								              				${ answer.description == 3? 'checked' : '' }
								              			/>
									              		<label for="option${ question.id }3"></label>
									            	</div>
									          	</div>
									          	
									          	<div class="col s1 center-align p-0">
									            	<div class="row radio-label p-0">4</div>
								            		<div class="row grey lighten-4 radio-button">
									              		<input value="4" class="with-gap" name="question${ question.id }" type="radio" id="option${ question.id }4" 
									              			${ answer.description == 4? 'checked' : '' }
									              		/>
									              		<label for="option${ question.id }4"></label>
								            		</div>
									          	</div>
									          	
		          								<div class="col s1 center-align p-0">
		            								<div class="row radio-label p-0">5</div>
		            								<div class="row grey lighten-4 radio-button">
		              									<input value="5" class="with-gap" name="question${ question.id }" type="radio" id="option${ question.id }5" 
		              										${ answer.description == 5? 'checked' : '' }
		              									/>
		              									<label for="option${ question.id }5"></label>
		            								</div>
		          								</div>
		          								
									          	<div class="col s3">
			            							<div class="row radio-label">&nbsp;</div>
			           								<div class="row radio-button text-left m-l-5">Strongly Disagree</div>
		          								</div>
		        							</div>
               						</c:when>                					
               					</c:choose>
               						</div>
               						
               				</c:forEach>
						
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

