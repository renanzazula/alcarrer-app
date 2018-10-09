<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:url value="/resources/jquery-1.12.4.js" var="jquery124Js" />
<script type="text/javascript" src="${jquery124Js}"></script>

<spring:url value="/resources/external/jquery/jquery.js" var="jqueryJs" />
<script type="text/javascript" src="${jqueryJs}"></script>
	
<spring:url value="/resources/jquery-ui.js" var="jqueryUiJs" />
<script type="text/javascript" src="${jqueryUiJs}"></script>

<spring:url value="/resources/jquery.mask.js" var="jqueryMaskJs" />
<script type="text/javascript" src="${jqueryMaskJs}"></script>

<spring:url value="/resources/jquery.mask.min.js" var="jqueryMaskMinJs" />
<script type="text/javascript" src="${jqueryMaskMinJs}"></script>

<spring:url value="/resources/jquery.maskMoney.js" var="jqueryMaskMoney" />
<script type="text/javascript" src="${jqueryMaskMoney}"></script>

<spring:url value="/resources/jquery.dataTables.min.js" var="jqueryDataTablesMinJs" />
<script type="text/javascript" src="${jqueryDataTablesMinJs}"></script>

<c:url var="home" value="/" scope="request" />

<script type="text/javascript">
	$(document).ready(function(){
		
		var dominios = null;
		
		$('#datepicker').datepicker({dateFormat : 'dd/mm/yy'}).val();

	  	$('#incluirProduto').on( 'click', function () {
			$("form[name='produtoForm']").attr('action', 'incluirProduto');
			$("form[name='produtoForm']").submit();
		});		

	  	$('#alterarProduto').on( 'click', function () {
			$("form[name='produtoForm']").attr('action', 'alterarProduto')
			$("form[name='produtoForm']").submit();
		});	
		
		$('#excluirProduto').on( 'click', function () {
			$("form[name='produtoForm']").attr('action', 'excluirProduto')
			$("form[name='produtoForm']").submit();
		});	

  		$('#cancelarProduto').on( 'click', function () {
			$("form[name='produtoForm']").attr('action', 'abrirProduto');
			$("form[name='produtoForm']").submit();
		});	
  		
  		$('#abrirFornecedor').on( 'click', function () {
			$("form[name='produtoForm']").attr('action', 'abrirFornecedor');
			$("form[name='produtoForm']").submit();
		});		
			
  		$('#abrirMarca').on( 'click', function () {
			$("form[name='produtoForm']").attr('action', 'abrirMarca');
			$("form[name='produtoForm']").submit();
		});		
  		
  		$('#abrirCategoria').on( 'click', function () {
			$("form[name='produtoForm']").attr('action', 'abrirCategoria');
			$("form[name='produtoForm']").submit();
		});		
  		
  		$('#abrirSubCategoria').on( 'click', function () {
			$("form[name='produtoForm']").attr('action', 'abrirSubCategoria');
			$("form[name='produtoForm']").submit();
		});		
  		
  		$('#abrirMedida').on( 'click', function () {
			$("form[name='produtoForm']").attr('action', 'abrirMedida');
			$("form[name='produtoForm']").submit();
		});		
  		  		 
  		$.extend( true, $.fn.dataTable.defaults, {
		    "searching": false,
		    "ordering": false
		} );

	  	var table = $('#tableMedida').DataTable({
			"lengthMenu" : [ [ 20, 27, -1 ], [ 20, 27, "All" ] ],
	  		"bLengthChange": false,
	  		"bInfo" : false,
	  		"language": {
  				"decimal": ",",
            	"thousands": ".",
	  	    	"emptyTable": "No data available in table!!"
  	    }});

		// Categoria
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
  				},
  				error : function(e) {
  	  				alert("Erro" + e);
  				},
  				done : function(e) {
					alert("done!")
   				}
  			});
			 
  		});
		
		$( "#medida" ).change(function() {
			calItensMedida();	
			if(dominios == null){
				calDominios();
			}
  		});
		
		function calDominios(){
			$.ajax({
				type : "POST",                                                            
				contentType : "application/json",
				url : "${home}ajaxObterDominios",
				data : "",
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					dominios = data;
				},
			error : function(e) {
				alert("Erro" + e)
			},
				done : function(e) {}
			});
		}
			
  		function calItensMedida(){
  			var table = $('#tableMedida').DataTable();
  			table.clear().draw();
 			$('#hiddensInput').html("");
  			 
  			$("#categoria")[0].selectedIndex = 0;
  			$("#subCategoria")[0].selectedIndex = 0;
  			$("#marca")[0].selectedIndex = 0;
  			
 			$.ajax({
				type : "POST",                                                            
				contentType : "application/json",
				url : "${home}ajaxConsultarItensMedidaByMedidaCodigo",
				data : JSON.stringify( jQuery.parseJSON($("#medida").val())),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					
					//display(data);
					
					if(data.length > 0){
  						if(data[0].categoria != null){						
							if(data[0].categoria.codigo != null){
								$("#categoria option").each(function(){
	  								if($(this).val() != "{}"){
										var codigo = jQuery.parseJSON($(this).val()).codigo;
		  								if(codigo == data[0].categoria.codigo){
		  									$("#categoria")[0].selectedIndex = $(this).length;
		  								}
	  								}
								});
	  						}
						}	
  						
						if(data[0].subCategoria != null){
	  						if(data[0].subCategoria.codigo != null){
	  							$('#subCategoria').children('option:not(:first)').remove();
	  							var subCategorias = data[0].categoria.subCategorias;
	  		  					$.each(subCategorias, function(key, value) {
	  		  					     $('#subCategoria')
	  		  					         .append($("<option></option>")
	  		  					         .attr("value", JSON.stringify(value))
	  		  					         .attr('selected', 'selected')
	  		  					         .text(value.nome));
	  		  					});
	  							$("#subCategoria option").each(function(){
	  								if($(this).val() != "{}"){
		  								var codigo = jQuery.parseJSON($(this).val()).codigo;
		  								if(codigo == data[0].subCategoria.codigo){
		  									$(this).attr('selected', 'selected');
		  								}
	  								}	
								});
	  						}
						}
						
  						if(data[0].marca != null){
	  						if(data[0].marca.codigo != null){
	  							$("#marca option").each(function(){
	  								if($(this).val() != "{}"){
		  								var codigo = jQuery.parseJSON($(this).val()).codigo;
		  								if(codigo == data[0].marca.codigo){
		  									$(this).attr('selected', 'selected');
		  								}
	  								}	
								});
	  						}
  						}
  					}
					
					var preco = $("#precoVenda").val();
					var peso = $("#peso").val();

					$.each(data, function(key, value) {         
						var inputHidden = "<input type='text' name='produtoHasItensTipoMedida["+ key +"].itensTipoMedida.codigo' value='"+value.codigo+"'/>";
						$("#hiddensInput").append(inputHidden);
						var input = "<input type='text' name='produtoHasItensTipoMedida["+key +"].quantidade' value='" + 1 +" '/>";
						 
				  		
				  		var dominioStr = "";
				  		$.each(dominios, function(keyDominio, dominioValue) {
				  			var str_name = 'produtoHasItensTipoMedida['+ key +'].dominios['+ keyDominio +'].codigo';
				  			dominioStr = dominioStr + "<input type='checkbox' name='" + str_name + "' value='" + dominioValue.codigo + "' class='flagCheckBox' />"+dominioValue.nome;
				  		});
				  		table.row.add([value.valor, preco, input, peso, dominioStr]).draw(false);
					});
			},
				error : function(e) {
					alert("Erro" + e)
			},
				done : function(e) {}
			}); 
  		}

        $("#precoCusto").maskMoney({thousands:'', decimal:'.', allowZero:true});
        $("#precoVenda").maskMoney({thousands:'', decimal:'.', allowZero:true});
        $("#desconto").maskMoney({thousands:'', decimal:'.', allowZero:true});
        $("#precoOferta").maskMoney({thousands:'', decimal:'.', allowZero:true});
         
        function calculaPrecoVenda(data) {
        	var VTOTALPRODUTO = (100 - parseFloat( $("#porcentagem").val() ) );
        	var PCUSTO = parseFloat($("#precoCusto").val()).toFixed(2);
        	var PV = (PCUSTO / VTOTALPRODUTO);
        	var PVENDA = (PV * 100);
        	$("#precoVenda").val( PVENDA.toFixed(2) );
  		}
        
       function calculaDesconto(data) {
			var PCUSTO = parseFloat($("#precoCusto").val()).toFixed(2);
        	var DESCONTO = parseFloat($("#porcentagemDesconto").val());
			var PDESCONTO = parseFloat( ( PCUSTO  * DESCONTO ) / 100 );
 		    $("#desconto").val(PDESCONTO.toFixed(2));
 		    var PVENDA = parseFloat($("#precoVenda").val()).toFixed(2);
 		    $("#precoOferta").val( parseFloat(PVENDA - PDESCONTO).toFixed(2) );    
 		}
       
       	$("#porcentagem").change(function() {
       		calculaPrecoVenda();
 	  	});
       
        $("#porcentagemDesconto").change(function() {
       		calculaDesconto();  	  		 
 	  	});
        
  		function display(data) {
  			var json = "<h4>Ajax Response</h4><pre>"
  					+ JSON.stringify(data, null, 4) + "</pre>";
  			$('#feedback').html(json);
  		}
    });
</script>

<form:form method="post" modelAttribute="produtoForm" action="abrirProduto" name="produtoForm">
	<br>
	<fieldset>
		<legend>Gerenciar Produto</legend>
		<ul class="form-style-1">
			<li>
				<fieldset>
					<legend  style=" font-weight: bold;">Dados Gerais</legend>
					<ul class="form-style-1">
						<li>
							<table style="width:100%">
								<tr>
									<td width="20%">
										<form:hidden path="codigo"/>
										 
										<label>Bar Code:<span class="required">*</span></label>
										
										<c:if test="${alterar != true}">
											<form:input path="barCode" type="text" class="field-long" placeholder="Bar Code"/>
										</c:if>
										<c:if test="${alterar == true}">
											<input type="text" class="field-long" id="barCode" placeholder="Bar Code" disabled="${alterar}" value="${produtoForm.barCode}"/>
											<form:hidden path="barCode"/>	 
										</c:if>										
									</td>
									<td width="79%">
										<label>Nome:<span class="required">*</span></label> 
							 			<form:input path="nome" type="text" class="field-long"
										  id="nome" placeholder="Nome"/>
						 			</td>
						 		</tr>
								<tr>
									<td width="20%">
										<c:if test="${not empty codigoInvalido}">
										  	<span class="required">${codigoInvalido}</span>
	  								    </c:if>
										<form:errors path="codigo" cssClass="required"/>
									</td>
									<td width="79%">
										<form:errors path="nome" cssClass="required"/>
						 			</td>
						 		</tr>						 		
						 	</table>				
						</li>
						<li>
							<label>Descri��o:<span class="required">*</span></label>
					 		<form:textarea path="descricao"  class="field-long field-textarea" cssStyle="height: 60px"/>
						</li>
						<li>
							<form:errors path="nome" cssClass="required"/>
						</li>
					</ul>
				</fieldset>		
				
				<br>			
				
				<fieldset>
					<legend></legend>
					<ul class="form-style-1">	
						<li>
							<table style="width:100%">
								<tr>
									<td >
										<label>Pre�o Custo:<span class="required">*</span></label>
					 					<form:input path="precoCusto" type="text" cssClass="field-long money" 
										  id="precoCusto" placeholder="Pre�o Custo"/>
						 			</td>
						 			<td>
										<label>Porcentagem:<span class="required">*</span></label>
					 					<form:input path="porcentagem" type="text" cssClass="field-long" cssStyle="width: 97%;"
										  id="porcentagem" placeholder="Porcentagem"/>%				 
									</td>
						 			<td>
						 				<label>Preco Venda:<span class="required">*</span></label> 
					 					<form:input path="precoVenda" type="text" cssClass="field-long money"
										  id="precoVenda" placeholder="Pre�o Venda"/>
						 			</td>
					 			</tr>
								<tr>
									<td>
										<form:errors path="precoCusto" cssClass="required"/>
						 			</td>
						 			<td>
										<form:errors path="porcentagem" cssClass="required"/>
									</td>
						 			<td>
						 				<form:errors path="precoVenda" cssClass="required"/>
						 			</td>
					 			</tr>
					 			<tr>
					 				<td>
						 				<label>Porcentagem Desconto:<span class="required">*</span></label>
					 					<form:input path="porcentagemDesconto" type="text" cssClass="field-long money"  cssStyle="width: 97%;"
										  id="porcentagemDesconto" placeholder="Porcentagem Desconto"/>%
					 				</td>			 				
					 				<td>
					 					<label>Desconto:<span class="required">*</span></label> 
					 					<form:input path="desconto" type="text" cssClass="field-long money"
										  id="desconto" placeholder="Desconto"/>
					 				</td>				 				
					 				<td>
					 					<label>Pre�o Oferta:<span class="required">*</span></label> 
					 					<form:input path="precoOferta" type="text" cssClass="field-long money"
										  id="precoOferta" placeholder="Pre�o Oferta"/>
					 				</td>				 	
					 			</tr>
					 			<tr>
									<td>
										<form:errors path="porcentagemDesconto" cssClass="required"/>
									</td>
									<td>
										<form:errors path="desconto" cssClass="required"/>
						 			</td>
						 			<td>
						 				<form:errors path="precoOferta" cssClass="required"/>
						 			</td>
					 			</tr>
					 			<tr>
					 				<td colspan="3">
					 					<label>Peso:<span class="required">*</span></label> 
					 					<form:input path="peso" type="text" cssClass="field-long"
										  id="peso" placeholder="peso"/>
					 				</td>					 			
					 			</tr>
					 			<tr>
					 				<td>
						 				<form:errors path="peso" cssClass="required"/>
						 			</td>
					 			</tr>

						 	</table>
						</li> 
					</ul>
				</fieldset>
				<br>			
				<fieldset>
					<legend></legend>
					<ul class="form-style-1">						
						<li>
							<label>Fornecedor<span class="required">*</span></label> 
					 		<form:select path="fornecedor" cssClass="field-select" cssStyle="width: 60%" multiple="false">
					 			<form:option value="NONE" label="Selecione"/>
					 			<c:forEach items="${produtoForm.fornecedores}" var="item">
 									<c:if test="${item.codigo eq  produtoForm.fornecedor.codigo}">
 										<form:option value="${item}" label="${item.codigo} - ${item.nome}" selected="selected"/>
 									</c:if>
 									<c:if test="${item.codigo ne produtoForm.fornecedor.codigo}">
 										<form:option value="${item}" label="${item.codigo} - ${item.nome}"/>
 									</c:if>	
 								</c:forEach> 					 			
					 		</form:select>
					 		<input type="button" id="abrirFornecedor" value="Novo Fornecedor"  style="width: 38%" />
						</li>
						<li>
							<label>Medida<span class="required">*</span></label> 
					 		<form:select path="medida" cssClass="field-select" cssStyle="width: 60%" multiple="false">
					 			<form:option value="NONE" label="Selecione"/>
					 			<c:forEach items="${produtoForm.medidas}" var="item">
 									<c:if test="${item.codigo eq produtoForm.medida.codigo}">
 										<form:option value="${item}" label="${item.codigo} - ${item.nome}" selected="selected"/>
 									</c:if>
 									<c:if test="${item.codigo ne produtoForm.medida.codigo}">
 										<form:option value="${item}" label="${item.codigo} - ${item.nome}"/>
 									</c:if>	
 								</c:forEach> 					 			
					 		</form:select>
					 		<input type="button" id="abrirMedida" value="Nova Medida"  style="width: 38%" /> 
						</li>
						<li>
							<label>Categoria<span class="required">*</span></label> 
							<form:select path="categoria" cssClass="field-select" cssStyle="width: 60%" multiple="false">
						    	<form:option value="{}" label="Selecione"/>
					 			<c:forEach items="${produtoForm.categorias}" var="item">
 									<c:if test="${item.codigo eq produtoForm.categoria.codigo}">
 										<form:option value="${item}" label="${item.codigo} - ${item.nome}" selected="selected"/>
 									</c:if>
 									<c:if test="${item.codigo ne produtoForm.categoria.codigo}">
 										<form:option value="${item}" label="${item.codigo} - ${item.nome}"/>
 									</c:if>	
 								</c:forEach>
				        	</form:select> 
				        	<input type="button" id="abrirCategoria" value="Nova Categoria"  style="width: 38%" />
						</li>						
						<li> 
							<label>Sub Categoria<span class="required">*</span></label> 
					 		<form:select path="subCategoria" cssClass="field-select" cssStyle="width: 60%" multiple="false">
						    	<form:option value="{}" label="Selecione"/>
 								<c:forEach items="${produtoForm.subCategorias}" var="item">
 									<c:if test="${item.codigo eq produtoForm.subCategoria.codigo}">
 										<form:option value="${item}" label="${item.codigo} - ${item.nome}" selected="selected"/>
 									</c:if>
 									<c:if test="${item.codigo ne produtoForm.subCategoria.codigo}">
 										<form:option value="${item}" label="${item.codigo} - ${item.nome}"/>
 									</c:if>	
 								</c:forEach>
				        	</form:select>
 				        	<input type="button"  id="abrirSubCategoria" value="Nova Subcategoria"  style="width: 38%" />
						</li> 						
						<li>
							<label>Marca<span class="required">*</span></label> 
					 		<form:select path="marca" cssClass="field-select" cssStyle="width: 60%" multiple="false" >
						    	<form:option value="{}" label="Selecione"/>
					 			<c:forEach items="${produtoForm.marcas}" var="item">
 									<c:if test="${item.codigo eq  produtoForm.marca.codigo}">
 										<form:option value="${item}" label="${item.codigo} - ${item.nome}" selected="selected"/>
 									</c:if>
 									<c:if test="${item.codigo ne produtoForm.marca.codigo}">
 										<form:option value="${item}" label="${item.codigo} - ${item.nome}"/>
 									</c:if>	
 								</c:forEach>
				        	</form:select> 
				        	<input type="button" id="abrirMarca" value="Nova Marca"  style="width: 38%" />
						</li>
						
						<li>
							<fieldset>
								<legend>itensMedida
									<c:if test="${fn:length(produtoForm.produtoHasItensTipoMedida) > 0 }">
										-${produtoForm.produtoHasItensTipoMedida[0].itensTipoMedida.medida.nome}-
									</c:if>
								</legend>
									<ul class="form-style-1">									
										<li>
											<table id="tableMedida" class="display" style="width:98%">
												<thead>
													<tr>
														<th>Op��o</th>
														<th>Pre�o</th>
														<th>Invent�rio</th>
														<th>Peso</th>
														<th>Flag Site</th>							 
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${produtoForm.produtoHasItensTipoMedida}" var="item" varStatus="loop">
														<tr>
															<td class="valor">
															<input type="hidden" name="produtoHasItensTipoMedida[${loop.index}].itensTipoMedida.codigo" value="${item.itensTipoMedida.codigo}"/>
																${item.itensTipoMedida.codigo} - ${item.itensTipoMedida.valor}
															</td>
															
															<td class="precoVenda">&euro; ${produtoForm.precoVenda}</td>
															
															<td class="quantidade">
																<input type="text" name="produtoHasItensTipoMedida[${loop.index}].quantidade" value="${item.quantidade}"/>
															</td>
															
															<td class="peso">${produtoForm.peso} kg</td>
															
															<td class="flagSite"> 
																<c:forEach items="${item.dominios}" var="dominio" varStatus="dominioIndex">
																	<c:if test="${dominio.checked == true}">
									 									<input type="checkbox" name="produtoHasItensTipoMedida[${loop.index}].dominios[${dominioIndex.index}].codigo" value="${dominio.codigo}" class="flagCheckBox" checked> ${dominio.nome}
									 								</c:if>
									 								<c:if test="${dominio.checked == false}">
									 									<input type="checkbox" name="produtoHasItensTipoMedida[${loop.index}].dominios[${dominioIndex.index}].codigo" value="${dominio.codigo}" class="flagCheckBox"> ${dominio.nome}
									 								</c:if>
									 							</c:forEach>										 									
								 							</td>															
														</tr>
													</c:forEach>
												</tbody>
											</table>	
										</li>
									</ul>
							</fieldset>
 						</li>
					</ul>
				</fieldset>
				<fieldset>
					<legend></legend>
					<ul class="form-style-1">
						<li class="text-align-right">
							<input type="button" id="cancelarProduto" value="Cancelar" />
							<c:if test="${alterar != true}">
								<input type="button"  id="incluirProduto" value="Gravar"/>
							</c:if>
							<c:if test="${alterar == true}">
								<input type="button" id="excluirProduto" value="Excluir" />
								<input type="button" id="alterarProduto" value="Alterar" />
							</c:if>
						</li>
					</ul>
				</fieldset>
			</li>
		</ul>
	</fieldset>
	<br>
	<div id="feedback"></div>
	<div id="hiddensInput">
		<c:forEach items="${produtoForm.produtoHasItensTipoMedida}" var="item" varStatus="loop">
			<input type="hidden"  name="itensMedida[${loop.index}].codigo" value="${item.codigo}"/>
		</c:forEach>
	</div>
</form:form>