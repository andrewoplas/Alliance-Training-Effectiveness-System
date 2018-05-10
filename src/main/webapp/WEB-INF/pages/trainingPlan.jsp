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
				                <input type="text" id="training" class="form-control validate-empty" required="">
				                <span class="highlight"></span>
				                <span class="bar"></span>
				                <label for="training">Training Title</label>
				                <span class="help-block help-block-empty hide">
				                	<small class="text-danger"><i class="mdi mdi-close-circle-outline"></i> Don't leave this field empty</small>
				                </span>
				            </div>
				            
							<div class="form-group m-b-30">
								<textarea id="message" maxlength="300" class="form-control validate-empty" required="" style="font-size: 16px;height: 130px;"></textarea>
								<span class="highlight"></span>
				                <span class="bar"></span>
				      			<label for="message">Description</label>
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
		                                <button type="button" class="btn btn-raised btn-fix btn-info waves-light save-event waves-effect waves-light">Create</button>
		                                <button type="button" class="btn btn-raised btn-fix btn-danger waves-light delete-event waves-effect waves-light pull-left" data-dismiss="modal">Delete</button>
		                                <button type="button" class="btn btn-raised btn-fix btn-info waves-light save-event2 waves-effect waves-light">Save</button>
		                            </div>
		                        </div>
		                    </div>
		                </div>
						
						<button type="button" class="btn waves-effect waves-light next action-button btn-raised btn-fixed" id="first-step" name="next">
							Next
						</button>
					</fieldset>
					
					<!-- fieldsets 2 -->
					<fieldset class="second-fieldset">
						<h2 class="fs-title">Objectives and Course Outline</h2>
						                    	
                    	<div class="p-10 m-t-5" style="border: 1px solid #eee">
		                    <div class="myadmin-dd-empty dd" id="nestable2">
		                        <ol class="dd-list">
		                        	<li class="dd-item dd3-item" data-id="0">
			                        	<div class="dd-handle dd3-handle"></div>
								       	<div class="dd3-content" id="id-0">Item</div>
								        <button type="button" class="btn-remove-item btn btn-danger btn-outline btn-circle">
								        	<i class="mdi mdi-close"></i>
								        </button>
						        	</li>
		                        </ol>
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
						<button type="button" name="previous" class="m-t-30 btn waves-effect waves-light previous action-button btn-raised btn-fixed">
							Previous
						</button>
						<button type="button" name="next" class="m-t-30 btn waves-effect waves-light next action-button btn-raised btn-fixed" id="third-step">
							Next
						</button>	
						
					</fieldset>
					
					<!-- fieldsets 4 -->
					<fieldset class="fourth-fieldset">
						<h2 class="fs-title">Finalize</h2>
						
						<button type="button" class="btn btn-info fieldset-goto" fieldset='first'>First</button>
						<button type="button" class="btn btn-info fieldset-goto" fieldset='second'>Second</button>
						<button type="button" class="btn btn-info fieldset-goto" fieldset='third'>Third</button>
						
						<br/>
						
						<button type="button" name="previous" class="btn waves-effect waves-light previous action-button btn-raised btn-fixed">
							Previous
						</button>
						<button type="button" name="submit" class="btn waves-effect waves-light submit action-button btn-raised btn-fixed">
							Submit
						</button>
					</fieldset>
				</form>
				<div class="clearfix"></div>
			</div>
		</div>		
	</div>
</div>