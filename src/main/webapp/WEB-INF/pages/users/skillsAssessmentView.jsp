
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<div id="page-wrapper">
	<div class="container-fluid">
		<input type="hidden" name="id" value="${ assignment.id }" />
		
		<div class="row bg-title">
			<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
				<h4 class="page-title">Skills Assessment</h4>
			</div>

			<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12">
				<ol class="breadcrumb">
					<li><a href="/ates/general/dashboard">Dashboard</a></li>
					<li><a href="/ates/general/training">Training List</a></li>
					<li class="active">Skills Assessment</li>
				</ol>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<div class="white-box font-poppins p-30">
					<h3 class="text-center text-bold section-title p-b-20">
						SKILLS ASSESSMENT
						<span>SKILLS ASSESSMENT</span>
					</h3>
					
					
					<div class="outline-body">
						<h2 class="text-center text-muted m-t-10 m-b-5 user-name">
							${ assignment.userEvent2.user.name }
						</h2>
						
						<c:set var="counter" value="0" scope="request" />
						
						<ol class="layer${ counter }">
							<c:forEach var="category" items="${ categories }">
								<li data-id="${ category.id }" id="${ category.id }">
									<p><i class="mdi mdi-checkbox-marked-circle text-success hide"></i> ${ category.description }</p>
									
									<c:choose>
										<c:when test="${ category.saCategories.size() > 0 }">
											<c:set var="categories" value="${ category.saCategories }" scope="request" />
											<c:set var="counter" value="${ counter + 1 }" scope="request" />
											<jsp:include page="fragments/skillsAssessmentList.jsp"/>
										</c:when>
										
										<c:otherwise>
											<span class="badge pull-right"> ${ category.getSaAnswer(assignment.id).answer }</span>
										</c:otherwise>
									</c:choose>
									
								</li>
							</c:forEach>
						</ol>
					</div>
					
				</div>
			</div>
		</div>
		
	</div>
</div>
