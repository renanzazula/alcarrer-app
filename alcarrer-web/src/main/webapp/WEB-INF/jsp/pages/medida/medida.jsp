<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <spring:url value="/resources/external/jquery/jquery.js" var="jqueryJs" /> --%>
<%-- <script type="text/javascript" src="${jqueryJs}"></script> --%>

<%-- <spring:url value="/resources/jquery-ui.js" var="jqueryUiJs" /> --%>
<%-- <script type="text/javascript" src="${jqueryUiJs}"></script> --%>
 
<%-- <spring:url value="/resources/jquery-1.12.4.js" var="jquery124Js" /> --%>
<%-- <script type="text/javascript" src="${jquery124Js}"></script> --%>

<%-- <spring:url value="/resources/jquery.dataTables.min.js" var="jqueryDataTablesMinJs" /> --%>
<%-- <script type="text/javascript" src="${jqueryDataTablesMinJs}"></script> --%>

<c:url var="home" value="/" scope="request" />

<script type="text/javascript">
	$(document).ready(function(){	
		
		$('#incluirMedida').on( 'click', function () {
			$("form[name='medidaForm']").attr('action', 'incluirMedida');
			var table = $('#tableMedida').DataTable();
			var data = table.rows().data();
			for (var i=0; i < data.length ;i++){
				$("form[name='medidaForm']").append('<input type="hidden" value="'+data[i][0]+'" name="itensTipoMedida['+i+'].valor"/>');
			}
			$("form[name='medidaForm']").submit();
		});		

		$('#alterarMedida').on( 'click', function () {
			$("form[name='medidaForm']").attr('action', 'alterarMedida');
			var table = $('#tableMedida').DataTable();
			var data = table.rows().data();
			for (var i=0; i < data.length ;i++){
				$("form[name='medidaForm']").append('<input type="hidden" value="'+data[i][0]+'" name="itensTipoMedida['+i+'].valor"/>');
			}
			$("form[name='medidaForm']").submit();
		});	
		
		$('#excluirMedida').on( 'click', function () {
			$("form[name='medidaForm']").attr('action', 'excluirMedida');
			$("form[name='medidaForm']").submit();
		});	

		$('#cancelarMedida').on( 'click', function () {
			$("form[name='medidaForm']").attr('action', 'abrirMedida');
			$("form[name='medidaForm']").submit();
		});

		$.extend( true, $.fn.dataTable.defaults, {
		    "searching": false,
		    "ordering": false
		} );
		
		var table = $('#tableMedida').DataTable({
			"lengthMenu" : [ [ 10, 17, -1 ], [ 10, 17, "All" ] ],
	  		"bLengthChange": false,
	  		"bInfo" : false
	  	});
		
	  	$('#tableMedida').on( 'click', 'tbody tr', function () {
  			table.row( this ).remove().draw();
	  	} );
		
	  	$("#adicionarMedida").click(function(event) {
			var data = "{'valor':'" + $("#id").val() + "'}"; 
			var table = $('#tableMedida').DataTable();
			table.row.add([ $("#id").val() ]).draw(false);
			$("#id").val("");  
		});
	
	  	$( "#categoria" ).change(function() {
  			var categoria = jQuery.parseJSON($(this).val());
  			$.ajax({
  				type : "POST",
  				contentType : "application/json",
  				url : "${home}ajaxConsultaSubCategoriaByCategoria",
  				data : JSON.stringify(categoria),
  				dataType : 'json',
  				timeout : 100000,
  				success : function(data) {
  					$('#subCategoria').children('option:not(:first)').remove();
  					$.each(data, function(key, value) {
  					     $('#subCategoria')
  					         .append($("<option></option>")
  					         .attr("value", JSON.stringify(value))
  					         .text(value.nome));
  					});

  					calItensMedida();	
  				},
  				error : function(e) {
  	  				alet("Erro" + e);
  				},
  				done : function(e) {
					// Chama itens medida por categoria	
					alert("done!")
   				}
  			});
			 
  		});
		
	});
</script>
 
<form:form method="post" modelAttribute="medidaForm" action="abrirMedida" name="medidaForm">
	<br>
	<fieldset>
		<legend>Gerenciar Medida</legend>
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
				<br>
				<label>Categoria<span class="required">*</span></label> 
				<form:select path="categoria" cssClass="field-select" cssStyle="width: 60%" multiple="false">
			    	<form:option value="NONE" label="Selecione"/>
		 			<c:forEach items="${medidaForm.categorias}" var="item">
		 					<c:if test="${item.codigo eq  medidaForm.itensTipoMedida[0].categoria.codigo}">
								<form:option value="${item}" label="${item.nome}" selected="selected"/>
							</c:if>
							<c:if test="${item.codigo ne medidaForm.itensTipoMedida[0].categoria.codigo}">
								<form:option value="${item}" label="${item.nome}"/>
							</c:if>	
					</c:forEach>
	        	</form:select> 
	        	<input type="button" id="abrirCategoria" value="Nova Categoria"  style="width: 38%" />
			</li>
			
			<li> 
				<label>Subcategoria<span class="required">*</span></label> 
		 		<form:select path="subCategoria" cssClass="field-select" cssStyle="width: 60%" multiple="false">
			    	
			    	<form:option value="NONE" label="Selecione"/>
						<c:forEach items="${medidaForm.subCategorias}" var="item">
							<c:if test="${item.codigo eq  medidaForm.itensTipoMedida[0].subCategoria.codigo}">
								<form:option value="${item}" label="${item.nome}" selected="selected"/>
							</c:if>
							<c:if test="${item.codigo ne medidaForm.itensTipoMedida[0].subCategoria.codigo}">
								<form:option value="${item}" label="${item.nome}"/>
							</c:if>	
						</c:forEach>
	        	</form:select>
		        	<input type="button"  id="abrirSubCategoria" value="Nova Subcategoria"  style="width: 38%" />
			</li> 

			<li>
				<label>Marca</label> 
		 		<form:select path="marca" cssClass="field-select" cssStyle="width: 60%" multiple="false" >
			    	<form:option value="NONE" label="Selecione"/>
			 			<c:forEach items="${medidaForm.marcas}" var="item">
							<c:if test="${item.codigo eq medidaForm.itensTipoMedida[0].marca.codigo}">
								<form:option value="${item}" label="${item.nome}" selected="selected"/>
							</c:if>
							<c:if test="${item.codigo ne medidaForm.itensTipoMedida[0].marca.codigo}">
								<form:option value="${item}" label="${item.nome}"/>
							</c:if>
						</c:forEach>
	        	</form:select> 	        	
	        	<input type="button" id="abrirMarca" value="Nova Marca"  style="width: 38%" />
			</li>
			<li>
				<br>
				<fieldset>    
					<legend>Gerenciar Medida</legend>
					<ul class="form-style-1">
						<li>
							<label>
								Valor:
								<input type="text" style="width: 24%" id="id"/>
								<input type="button" id="adicionarMedida" value="+ Adicionar Medida" style="width: 70%"/>
							</label>
						</li>
				
						<li> 
							<table id="tableMedida" class="display"  style="width:98%">
								<thead>
									<tr>
										<th>Medida</th>							 								 											
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${medidaForm.itensTipoMedida}" var="i">
										<tr>
											<td class="cod">${i.valor}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</li>	
					</ul>
				</fieldset>
			</li>
			<!-- Botões -->
			<li class="text-align-right">
				<input type="button" id="cancelarMedida" value="Cancelar" />
				
				<c:if test="${medidaForm.codigo == null}">
					<input type="button" id="incluirMedida" value="Gravar" />
				</c:if>
				<c:if test="${medidaForm.codigo != null}">
					<input type="button" id="excluirMedida" value="Excluir" />
					<input type="button" id="alterarMedida" value="Alterar" />
				</c:if>
								
			</li>
		</ul>
	</fieldset>	
	<br>
</form:form >