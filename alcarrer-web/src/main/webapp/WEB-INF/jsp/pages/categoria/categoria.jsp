<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- <spring:url value="/resources/external/jquery/jquery.js" var="jqueryJs" /> --%>
<%-- <script type="text/javascript" src="${jqueryJs}"></script> --%>

<%-- <spring:url value="/resources/jquery-ui.js" var="jqueryUiJs" /> --%>
<%-- <script type="text/javascript" src="${jqueryUiJs}"></script> --%>

<%-- <spring:url value="/resources/jquery-1.12.4.js" var="jquery124Js" /> --%>
<%-- <script type="text/javascript" src="${jquery124Js}"></script> --%>

<%-- <spring:url value="/resources/jquery.dataTables.min.js" var="jqueryDataTablesMinJs" /> --%>
<%-- <script type="text/javascript" src="${jqueryDataTablesMinJs}"></script> --%>

<script type="text/javascript">
	$(document).ready(function(){
		
		$('#incluirCategoria').on( 'click', function () {
			$("form[name='categoriaForm']").attr('action', 'incluirCategoria');

			var table = $('#tableSubCategoria').DataTable();
			var data = table.rows('.selected').data();
			for (var i=0; i < data.length ;i++){
				$("form[name='categoriaForm']").append('<input type="hidden" value="'+data[i][0]+'" name="subCategorias['+i+'].codigo"/>');
			}

			$("form[name='categoriaForm']").submit();
		});		

	  	$.extend( true, $.fn.dataTable.defaults, {
		    "searching": false,
		    "ordering": false
		} );

	  	var table = $('#tableSubCategoria').DataTable({
	  		"paging":   false,
	  		"lengthMenu" : [ [ 5, 7, -1 ], [ 5, 7, "All" ] ],
	  		"bLengthChange": false,
	  		"bInfo" : false,
	  		"language": {
  				"decimal": ",",
            	"thousands": ".",
	  	    	"emptyTable": "No data available in table!!"
	  	    }
	  	});
		
	    $('#tableSubCategoria tbody').on( 'click', 'tr', function () {
	        $(this).toggleClass('selected');
	    } );
	    
	    $('#novaSubCategoria').on( 'click', function () {
			$("form[name='categoriaForm']").attr('action', 'abrirSubCategoria')
			$("form[name='categoriaForm']").submit();
		});		

	    $('#cancelarCategoria').on( 'click', function () {
			$("form[name='categoriaForm']").attr('action', 'abrirCategoria')
			$("form[name='categoriaForm']").submit();
		});
 
		$('#alterarCategoria').on( 'click', function () {
			$("form[name='categoriaForm']").attr('action', 'alterarCategoria');

			var table = $('#tableSubCategoria').DataTable();
			var data = table.rows('.selected').data();
			for (var i=0; i < data.length ;i++){
				$("form[name='categoriaForm']").append('<input type="hidden" value="'+data[i][0]+'" name="subCategorias['+i+'].codigo"/>');
			}

			$("form[name='categoriaForm']").submit();
		});	
		
		$('#excluirCategoria').on( 'click', function () {
			$("form[name='categoriaForm']").attr('action', 'excluirCategoria');
			$("form[name='categoriaForm']").submit();
		});		
				
	    <c:if test="${not empty categoriaForm.subCategorias}">
	    	selectLineFunction();
	    </c:if>
	});
	
	function selectLineFunction(){
    	//Array com os codigos
    	var codigos = [<c:forEach items="${categoriaForm.subCategorias}" var="item" varStatus="loop">${item.codigo}<c:if test="${ (fn:length(categoriaForm.subCategorias) -1) > loop.index}">,</c:if></c:forEach>];

    	var data = $('#tableSubCategoria').DataTable().rows().data();
		for (var j=0; j < codigos.length ;j++){
			for (var i=0; i < data.length ;i++){
				var texto = $("#tableSubCategoria tbody tr:eq("+i+") th").text();
				if(texto.substring(0, codigos[j].toString().length).includes(codigos[j])){
					console.log(codigos[j] + ":" +codigos[j].toString().length);	
					$('#tableSubCategoria tbody tr:eq('+i+')').addClass('selected');						
				}
			}
		}
    } 
</script>
 
<form:form method="post" modelAttribute="categoriaForm" action="abrirCategoria" name="categoriaForm">
	</br>
	<fieldset>
		<legend>Gerenciar Categoría</legend>
		<ul class="form-style-1">
			
	 		<form:hidden path="codigo"/> 			  
			 
			<li>
				<label>Nome:<span class="required">*</span></label> 
				<form:input path="nome" type="text" class="field-long"
							  id="nome" placeholder="Nome"/>		
			</li>
			 
			<li>
				<form:errors path="nome" cssClass="required"/>	
			</li>
			
			<li>
				<label>Descrição:<span class="required">*</span></label>
				<form:textarea path="descricao"  class="field-long field-textarea"/>
			</li>
			<li>
				<form:errors path="descricao" cssClass="required"/>
			</li>
			 
			<li>
				<fieldset>
					<legend>Sub Categoría</legend>
					<ul class="form-style-1">
						<li>
							<table id="tableSubCategoria" class="display" cellspacing="0" width="98%">
								<thead>
									<tr>
										<th>Codigo</th>
										<th>Nome</th>							 										
										<th>Descrição</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${listSubCategoria}" var="i">
										<tr>
											<th>${i.codigo}</th>
											<th>${i.nome}</th>
											<th>${i.descricao}</th>								
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</li>
						<li>
			 				<input type="button" value="Nova Sub Categoria" class="field-divided" style="width: 100%" id="novaSubCategoria"/>		
						</li>
					</ul>	
				</fieldset>
 			</li>
 			
			<li class="text-align-right">
				<input type="button" id="cancelarCategoria" value="Cancelar" />
				
				<c:if test="${categoriaForm.codigo == null}">
					<input type="button" id="incluirCategoria" value="Gravar" />
				</c:if>
				<c:if test="${categoriaForm.codigo != null}">
					<input type="button" id="excluirCategoria" value="Excluir" />
					
					<input type="button" id="alterarCategoria" value="Alterar" />
				</c:if>
			</li>
		</ul>
	</fieldset>	
	</br>
</form:form>
