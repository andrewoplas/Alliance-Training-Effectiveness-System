<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<input type="hidden" class="nestable-serialized" value='${training.courseOutline}' />
<input type="hidden" class="training-id" value="${training.id}" />
<input type="hidden" class="userEvent-id" value="${userEventId}" />

<div id="page-wrapper">
	<div class="container-fluid full-height">		
		<div class="row">
			<div class="col-xs-12">
				<!-- multistep form -->
				<form id="msform">
					<!-- progressbar -->
					<ul id="eliteregister">
						<li class="active">Course Objective And Outline</li>
					</ul>
					
					<fieldset>
						<div class="floating-labels">
							<p class="training-title">${ training.title }</p>
				            
							<div class="form-group m-b-40">
								<textarea id="description" class="form-control validate-empty" required="" style="font-size: 16px;height: 130px;">${training.description}</textarea>
								<span class="highlight"></span>
				                <span class="bar"></span>
				      			<label for="description">Objective</label>
				      			<span class="help-block help-block-empty hide">
				                	<small class="text-danger"><i class="mdi mdi-close-circle-outline"></i> Don't leave this field empty</small>
				                </span>
							</div>
						</div>
						                    	
                    	<span class="course-outline-label">Course Outline</span>
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
		                
						<button type="button" name="submit" class="submit m-t-30 btn waves-effect waves-light submit action-button btn-raised btn-fixed">
							Submit
						</button>					
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
