<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${breadCrumbItens != null}">
	
	<hr align="center" width="99%" size="1" color="#a0a09f"></hr>
	
	<ul class="breadcrumb">
		<!-- Objeto BreadCrumb  -->
		<!-- link - Direcionamento  -->
		<!-- Texto - Descrição menu -->
		<!-- Last == true -->
		<c:forEach items="${breadCrumbItens}" var="itensLinkTextoLast">		
		 
			<c:if test="${itensLinkTextoLast.last == 'TRUE'}">
				<li>${itensLinkTextoLast.texto}</li>
			</c:if>
			<c:if test="${itensLinkTextoLast.last == 'FALSE'}">
				<li><a href="${itensLinkTextoLast.link}">${itensLinkTextoLast.texto}</a></li>
			</c:if>
			
		</c:forEach>	
	</ul>
	
	<hr align="center" width="99%" size="1" color="#a0a09f"></hr>
</c:if>
