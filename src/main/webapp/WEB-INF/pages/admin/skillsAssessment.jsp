	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
	
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row bg-title">
                  <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                      <h4 class="page-title">Skills Assessment</h4> </div>
                      
                  <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
                      <ol class="breadcrumb">
                          <li><a href="/ates/dashboard">Dashboard</a></li>
                          <li class="active">Skills Assessment</li>
                      </ol>
                  </div>
                  <!-- /.col-lg-12 -->
              </div>
			
			  <!-- /row -->
              <div class="row">
	              <div class="col-sm-12">
	              
	              <div class="panel panel-info">
					   <div class="panel-heading">Skills Assessment</div>
                            
                       <div class="panel-wrapper collapse in" aria-expanded="true">
                       		<div class="panel-body">
                       			<div class="text-right">
									<button class="btn-clear btn btn-fix btn-raised btn-danger btn-outline waves-light waves-effect m-b-5">Clear Items</button>
								</div>
                       			<div class="bordered-box">
									<div class="myadmin-dd-empty dd" id="nestable2">
										<jsp:include page="fragments/skillsAssessmentNode.jsp"/>
				                    </div>
				                    
									<a href="javascript:void(0)" class="btn-add-item btn-block text-center"> 
										<i class="mdi mdi-plus"></i> Add Item
									</a>
								</div>
								
								<div class="text-center">
									<button id="save" class="btn btn-fix btn-raised btn-info waves-light waves-effect m-t-20">Save</button>
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