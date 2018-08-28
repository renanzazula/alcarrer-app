<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:url value="/resources/jquery-1.12.4.js" var="jquery124Js" />
<script type="text/javascript" src="${jquery124Js}"></script>

<spring:url value="/resources/jquery.dataTables.min.js" var="jqueryDataTablesMinJs" />
<script type="text/javascript" src="${jqueryDataTablesMinJs}"></script>

<style type="text/css">
	tr.group, tr.group:hover {
		background-color: #ddd !important;
		font-weight:bold;
	}
</style>

<script type="text/javascript">
	$(document).ready(function() {

		var table = $('#tableConsulta').DataTable({
	        "columnDefs": [
	            { "visible": false, "targets": 0}
	        ],
	        "ordering": false,
	        "order": [[ 0, 'asc' ]],
	        "displayLength": 25,
	        "drawCallback": function ( settings ) {
	            var api = this.api();
	            var rows = api.rows( {page:'current'} ).nodes();
	            var last=null;
	 
	            api.column(0, {page:'current'} ).data().each( function ( group, i ) {
	                if ( last !== group ) {
	                    $(rows).eq( i ).before(
	                    	'<tr class="group"><td colspan="2" id="'+ $(rows[i]).attr('id')+'" >'+group+'</td></tr>'
	                    );
	                    last = group;
	                }
	            } );
	        }
	    } );
		 
		$('#tableConsulta tbody').on('click', 'tr', function() {
			$(this).toggleClass('selected');
			$("#codigo").val($(this).closest('tr').children('td').attr('id'));
			$("form[name='categoriaForm']").submit();
		});
	});
</script>
 
<form:form method="post" modelAttribute="categoriaForm" action="abrirAlterarCategoria" name="categoriaForm">
	</br>
		
		<form:hidden path="codigo" id="codigo"/>
		<fieldset>
			<legend>Gerenciar Categoria</legend>
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
				<table id="tableConsulta" class="display" cellspacing="0"
					width="98%">
					<thead>
						<tr>
							<th>Categoria</th>
							<th>SubCategoria</th>
						</tr>
					</thead>
					<tbody>					
						<c:forEach items="${categoriaList}" var="categoria">
							<c:if test="${not empty categoria.subCategorias}">
								<c:forEach items="${categoria.subCategorias}" var="iten">
									<tr id="${categoria.codigo}">
										<td class="nomeCategoria"    id="${categoria.codigo}">${categoria.nome}</td>
										<td class="nomeSubCategoria" id="${categoria.codigo}">${iten.nome}</td>
									</tr>	
								</c:forEach>
							</c:if>
							<c:if test="${empty categoria.subCategorias}">
								<c:forEach items="${categoria.subCategorias}" var="iten">
									<tr id="${categoria.codigo}">
										<td class="nomeCategoria"    id="${categoria.codigo}">${categoria.nome}</td>
										<td class="nomeSubCategoria" id="${categoria.codigo}"></td>
									</tr>	
								</c:forEach>
							</c:if>		
						</c:forEach>
					</tbody>
				</table>
			</li>
		</ul>
		</fieldset>	
	</br>
</form:form>