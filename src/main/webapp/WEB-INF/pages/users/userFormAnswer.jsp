<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<input type="hidden" name="formType" value="${ form.id }" />
<div id="page-wrapper">
	<div class="container-fluid">
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">Form</h4>
			</div>
			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				<ol class="breadcrumb">
					<li><a href="/ates/general/dashboard">Dashboard</a></li>
					<li><a href="/ates/general/training">Training List</a></li>
					<li class="active">Form</li>
				</ol>
			</div>
			<!-- /.col-lg-12 -->
		</div>		
	
		<!-- /row -->
		<div class="row">
			<div class="col-sm-12">
				<input type="hidden" value="${ assignment.id }" id="assignment" />
				
				<div class="panel panel-info" style="margin-left: auto; margin-right: auto; width: 60%;">
					<div class="panel-heading text-center">${ form.description }</div>

					<div class="panel-wrapper collapse in" aria-expanded="true">
						<div class="panel-body">
							
							<c:forEach var="question" items="${ questions }" varStatus="loop">
								<div class="question-container" data-id="${ question.id }" data-type="${ question.type }">
									<p class="question m-b-0">${loop.count}. ${ question.description }</p>
								<c:choose>
									
									<%-- Textbox --%>
               						<c:when test="${ question.type == 'textbox' }">
								        <div class="input-field m-t-5">
							          		 <textarea class="materialize-textarea" placeholder="Your Answer"></textarea>
								        </div>
               						</c:when>
               						
               						<%-- RadioButton --%>
               						<c:when test="${ question.type == 'radiobutton' }">
					        			<div class="option-container">
					        				<c:forEach var="option" items="${ question.formOptions }">
						        				<div class="option-item m-b-5">
						        					<input type="radio" class="with-gap" name="question${ question.id }" id="option${ option.id }" value="${ option.id }" />
								              		<label for="option${ option.id }">${ option.description }</label>
						        				</div>
								    		</c:forEach>
					        			</div>	      				
               						</c:when>
                						
                					<%-- Checkbox --%>
               						<c:when test="${ question.type == 'checkbox' }">
					        			<div class="option-container">
					        				<c:forEach var="option" items="${ question.formOptions }">
						        				<div class="option-item m-b-5">
						        					<input type="checkbox" name="question${ question.id }" id="option${ option.id }" value="${ option.id }" />
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
										              	<input value="1" class="with-gap" name="question${ question.id }" type="radio" id="option${ question.id }1" />
										              	<label for="option${ question.id }1"></label>
									            	</div>
									          	</div>
									          	
									          	<div class="col s1 center-align p-0">
									            	<div class="row radio-label p-0">2</div>
									            	<div class="row grey lighten-4 radio-button">
									              		<input value="2" class="with-gap" name="question${ question.id }" type="radio" id="option${ question.id }2" />
									              		<label for="option${ question.id }2"></label>
									            	</div>
									          	</div>
									          	
		         								<div class="col s1 center-align p-0">
		            								<div class="row radio-label p-0">3</div>
								            		<div class="row grey lighten-4 radio-button">
								              			<input value="3" class="with-gap" name="question${ question.id }" type="radio" id="option${ question.id }3" />
									              		<label for="option${ question.id }3"></label>
									            	</div>
									          	</div>
									          	
									          	<div class="col s1 center-align p-0">
									            	<div class="row radio-label p-0">4</div>
								            		<div class="row grey lighten-4 radio-button">
									              		<input value="4" class="with-gap" name="question${ question.id }" type="radio" id="option${ question.id }4" />
									              		<label for="option${ question.id }4"></label>
								            		</div>
									          	</div>
									          	
		          								<div class="col s1 center-align p-0">
		            								<div class="row radio-label p-0">5</div>
		            								<div class="row grey lighten-4 radio-button">
		              									<input value="5" class="with-gap" name="question${ question.id }" type="radio" id="option${ question.id }5" />
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
               				
               				<div class="text-center">
               					<button class="btn btn-info btn-submit btn-raised waves-effect waves-light">
	               					Submit
	               				</button>
               				</div>
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
            <div class="modal-body text-center">
            	Processing your request...
            </div>
        </div>
    </div>
</div>