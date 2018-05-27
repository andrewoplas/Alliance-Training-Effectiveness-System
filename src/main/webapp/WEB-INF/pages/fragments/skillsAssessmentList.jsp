<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<ul class="layer${ counter }">
	<c:forEach var="category" items="${ categories }">
		<li data-id="${ category.id }" id="${ category.id }">
			<p><i class="mdi mdi-check text-success hide"></i> ${ category.description }</p>
			
			<c:choose>
				<c:when test="${ category.saCategories.size() > 0 }">
					<c:set var="categories" value="${ category.saCategories }" scope="request" />
					<c:set var="counter" value="${ counter + 1 }" scope="request" />
					<jsp:include page="skillsAssessmentList.jsp"/>
				</c:when>
				
				<c:otherwise>
					<span class="badge pull-right"> ${ category.getSaAnswer(assignment.id).answer }</span>
				</c:otherwise>
			</c:choose>
		
		</li>
	</c:forEach>
</ul>
