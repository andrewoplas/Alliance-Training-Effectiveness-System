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
						
						<div class="form-group m-b-40">
	                       	<input type="text" class="form-control" id="input1" required><span class="highlight"></span> <span class="bar"></span>
	                       	<label for="input1" class="text-left>Regular Input</label>
	                   	</div>
						
						
						<input type="button" name="next" class="next action-button" value="Next" />
					</fieldset>
					
					<fieldset>
						<h2 class="fs-title">Objectives and Course Outline</h2>
						<input type="text" name="twitter" placeholder="Twitter" />
						<input type="text" name="facebook" placeholder="Facebook" />
						<input type="text" name="gplus" placeholder="Google Plus" />
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
			</div>
		</div>		
	</div>
</div>