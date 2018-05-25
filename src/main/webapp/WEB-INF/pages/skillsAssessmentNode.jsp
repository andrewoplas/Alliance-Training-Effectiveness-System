<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<ol class="dd-list">
	<c:forEach var="category" items="${ categories }">
		<li class="dd-item dd3-item" data-id="${ category.id }">
			<div class="dd-handle dd3-handle"></div>
			<div class="dd3-content" id="id-${ category.id }">${ category.description }</div>
			<button type="button" class="btn-remove-item btn btn-danger btn-outline btn-circle">
				<i class="mdi mdi-close"></i>
			</button>
			
			<c:if test="${ category.saCategories.size() > 0 }">
				<c:set var="categories" value="${ category.saCategories }" scope="request"/>
				<jsp:include page="skillsAssessmentNode.jsp"/>
			</c:if>
			
		</li>
	</c:forEach>
</ol>