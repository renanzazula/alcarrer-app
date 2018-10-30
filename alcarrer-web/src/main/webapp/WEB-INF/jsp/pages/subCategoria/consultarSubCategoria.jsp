<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <spring:url value="/resources/jquery-1.12.4.js" var="jquery124Js" /> --%>
<%-- <script type="text/javascript" src="${jquery124Js}"></script> --%>

<%-- <spring:url value="/resources/jquery.dataTables.min.js" var="jqueryDataTablesMinJs" /> --%>
<%-- <script type="text/javascript" src="${jqueryDataTablesMinJs}"></script> --%>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tableConsulta').DataTable();
		
		$('#tableConsulta tbody').on('click', 'tr', function() {
			$(this).toggleClass('selected');
			$("#codigo").val($(this).closest('tr').children('td.cod').text());
			$("form[name='subCategoriaForm']").submit();
		});
	});
</script>
 
<form:form method="post" modelAttribute="subCategoriaForm" action="abrirAlterarSubCategoria" name="subCategoriaForm">
	</br>
		
		<form:hidden path="codigo" id="codigo"/>
		<fieldset>
			<legend>Gerenciar SubCategoría</legend>
			<ul class="form-style-1">
			<li>
				<c:if test="${not empty mensagem}">
					<div class="ui-widget">
						<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
							<p>
								<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span> <strong>Hey!</strong>
								${mensagem} 
							</p>
						</div>
					</div>
				</c:if>
				</br>
				<table id="tableConsulta" class="display" cellspacing="0" width="98%">
					<thead>
						<tr>
							<th>Codigo</th>
							<th>Nome</th>
							<th>Descrição</th>							 
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="i">
							<tr>
								<td class="cod">${i.codigo}</td>
								<td class="nome">${i.nome}</td>
								<td class="descricao">${i.descricao}</td>								 
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</li>
		</ul>
		</fieldset>	
	</br>
</form:form>