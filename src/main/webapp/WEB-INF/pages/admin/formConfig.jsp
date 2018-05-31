	
	<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	
	<input type="hidden" name="formType" value="${ form.id }" />
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">${ form.description }</h4>
				</div>
	
				<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
					<ol class="breadcrumb">
						<li><a href="/ates/dashboard">Dashboard</a></li>
						<li class="active">${ form.description }</li>
					</ol>
				</div>
				<!-- /.col-lg-12 -->
			</div>
	
			<!-- /row -->
			<div class="row">
				<div class="col-sm-12">
	
					<div class="panel panel-info">
						<div class="panel-heading">${ form.description }</div>
	
						<div class="panel-wrapper collapse in" aria-expanded="true">
							<div class="panel-body">
								<div class="controls-container">
	
									<c:forEach var="question" items="${ questions }"
										varStatus="loop">
	
										<div class="form-container m-b-10 p-30 p-b-0"
											style="border: 1px solid #eee">
											<input type="hidden" class="question-id"
												value=${ question.id } /> <span class="numbers">${ loop.count }</span>
											<input type="text" class="form-control m-b-5 question"
												value="${ question.description }"
												data-type="${ question.type }" />
	
											<c:choose>
												<c:when test="${ question.type == 'textbox' }">
													<input type="text" class="form-control p-10" readonly
														value="Answer Container" />
												</c:when>
	
												<c:when test="${ question.type == 'radiobutton' }">
													<div class="option-container">
														<c:forEach var="option" items="${ question.formOptions }">
															<div class="item">
																<i class="mdi mdi-radiobox-blank text-info m-l-30"></i> <input
																	type="text" class="form-control optionName"
																	value="${ option.description }" data-id="${ option.id }">
																<i class="btn-remove mdi mdi-close text-muted m-l-20"
																	data-toggle="tooltip" title="Remove"
																	data-placement="right"></i>
															</div>
														</c:forEach>
													</div>
	
													<a href="javascript:void(0)" class="btn-add-option"> <i
														class="mdi mdi-plus"></i> Add Option
													</a>
												</c:when>
	
												<c:when test="${ question.type == 'checkbox' }">
													<div class="option-container">
														<c:forEach var="option" items="${ question.formOptions }">
															<div class="item">
																<i
																	class="mdi mdi-checkbox-blank-outline text-info m-l-30"></i>
																<input type="text" class="form-control optionName"
																	value="${ option.description }" data-id="${ option.id }">
																<i class="btn-remove mdi mdi-close text-muted m-l-20"
																	data-toggle="tooltip" title="Remove"
																	data-placement="right"></i>
															</div>
														</c:forEach>
													</div>
	
													<a href="javascript:void(0)" class="btn-add-optionChkbox">
														<i class="mdi mdi-plus"></i> Add Option
													</a>
												</c:when>
	
												<c:when test="${ question.type == 'scale' }">
													<div class="option-container text-center p-10">
														<span class="scale-option"> 
															<i class="mdi mdi-radiobox-blank text-info m-l-30"></i>
															Strongly Agree
														</span> 
														
														<span class="scale-option"> 
															<i class="mdi mdi-radiobox-blank text-info m-l-30"></i> Agree
														</span> 
														
														<span class="scale-option"> 
															<i class="mdi mdi-radiobox-blank text-info m-l-30"></i>
															Neutral
														</span> 
														
														<span class="scale-option"> 
															<i class="mdi mdi-radiobox-blank text-info m-l-30"></i>
															Disagree
														</span> 
														
														<span class="scale-option"> 
															<i class="mdi mdi-radiobox-blank text-info m-l-30"></i>
															Strongly Disagree
														</span>
													</div>
												</c:when>
											</c:choose>
	
											<div class="text-right p-t-10 p-b-10">
												<i class="btn-delete mdi mdi-delete" data-toggle="tooltip"
													title="Delete " data-placement="top"></i>
											</div>
										</div>
	
									</c:forEach>
	
								</div>
	
								<div class="text-center m-t-30 m-b-30">
									<a href="javascript:void(0)" class="btn-add-textbox m-r-5 btn-adds"> 
										<i class="mdi mdi-format-paragraph"></i> Add Textbox
									</a>
									 
									<span class="m-l-5 m-r-5 text-muted custom-divider">|</span> 
									
									<a href="javascript:void(0)" class="btn-add-rbtn btn-adds">
										<i class="mdi mdi-checkbox-marked-circle-outline"></i> Add Radio Button
									</a> 
								
									<span class="m-l-5 m-r-5 text-muted custom-divider">|</span> 
									
									<a href="javascript:void(0)" class="btn-add-checkbox btn-adds">
										<i class="mdi mdi-checkbox-marked-outline"></i> Add Checkbox
									</a> 
									
									<span class="m-l-5 m-r-5 text-muted custom-divider">|</span> 
									
									<a href="javascript:void(0)" class="btn-add-scale btn-adds">
										<i class="mdi mdi-dots-horizontal"></i> Add Scale
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
				<div class="modal-body text-center">Processing your request...
				</div>
			</div>
		</div>
	</div>
