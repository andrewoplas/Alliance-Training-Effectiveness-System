<div id="page-wrapper">
	<div class="container-fluid full-height">		
		<div class="row">
			<div class="col-xs-12">
				<!-- multistep form -->
				<form id="msform" class="floating-labels">
					<!-- progressbar -->
					<ul id="eliteregister">
						<li class="active">Schedule</li>
						<li>Course Outline</li>
						<li>Invite People</li>
					</ul>
					
					<!-- fieldsets -->
					<fieldset>
						<h2 class="fs-title">Title and Schedule</h2>
						
						<br/>
						<div class="form-group">
			                <input type="text" id="training" class="form-control" required="">
			                <span class="highlight"></span>
			                <span class="bar"></span>
			                <label for="training">Training Name <span class="text-danger">*</span></label>
			            </div>
			            
						<div class="form-group">
			                <input type="text" id="subject" class="form-control" required="">
			                <label for="subject">Subject <span class="text-danger">*</span></label>
			            </div>
						<div class="form-group">
							<textarea id="message" maxlength="300" class="form-control" required=""></textarea>
			      			<label for="message">Message <span class="text-danger">*</span></label>
						</div>
						
						<input type="button" name="next" class="next action-button" value="Next" />
					</fieldset>
					
					<fieldset>
						<h2 class="fs-title">Objectives and Course Outline</h2>
						<input type="button" id="nice" class="btn btn-primary" />                    	
                    	<div class="p-10 m-t-5 m-b-10" style="border: 1px solid #eee">
		                    <div class="myadmin-dd-empty dd" id="nestable2">
		                        <ol class="dd-list"></ol>
		                    </div>
		                    
							<a href="javascript:void(0)" class="btn-add-item btn-block text-center"> 
								<i class="mdi mdi-plus"></i> Add Item
							</a>
						</div>
						
						<input type="button" name="previous" class="previous action-button" value="Previous" />
						<input type="button" name="next" class="next action-button" value="Next" />
					</fieldset>
					
					<fieldset>
						<h2 class="fs-title">Invite People</h2>
						<input type="text" name="fname" placeholder="First Name" />
						<input type="text" name="lname" placeholder="Last Name" />
						<input type="text" name="phone" placeholder="Phone" />
						<textarea name="address" placeholder="Address"></textarea>
						<input type="button" name="previous" class="previous action-button" value="Previous" />
						<input type="submit" name="submit" class="submit action-button" value="Submit" />
					</fieldset>
				</form>
				<div class="clearfix"></div>
			</div>
		</div>		
	</div>
</div>