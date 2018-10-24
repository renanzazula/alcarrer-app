<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<spring:url value="/resources/jquery-3.3.1.js" var="jquery331Js" />
<script type="text/javascript" src="${jquery331Js}"></script>
	  
<spring:url value="/resources/jquery.dataTables.min.js" var="jqueryDataTablesMinJs" />
<script type="text/javascript" src="${jqueryDataTablesMinJs}"></script>
<c:url var="home" value="/" scope="request" />
<style>
	td.details-control {
	    background: url('resources/images/details_open.png') no-repeat center center;
	    cursor: pointer;
	}
	
	tr.shown td.details-control {
	    background: url('resources/images/details_close.png') no-repeat center center;
	}
	
	td.editarVenda{
		background: url('resources/images/edit.png') no-repeat center center;
	}
	
</style>

<script type="text/javascript">
	$(document).ready(function() {
		
// 	  	$('#datepicker').datepicker({dateFormat : 'dd/mm/yyyy'}).val();
		
		$('#filtrar').on( 'click', function () {  		
			$("form[name='vendafiltro']").attr('action', 'filtrarVendas');
  			$("form[name='vendafiltro']").submit();
		});	
		 
		var table = $('#tableConsulta').DataTable();
		
	    // Add event listener for opening and closing details
	    $('#tableConsulta tbody').on('click', 'td.details-control', function () {
	        var tr = $(this).closest('tr');
	        var row = table.row( tr );
	 
	        if ( row.child.isShown() ) {
	            // This row is already open - close it
	            row.child.hide();
	            tr.removeClass('shown');
	        }
	        else {
	        	
	        	var fieldsetInicio = '<fieldset><legend></legend><ul class="form-style-1"><li>';
        		var fieldsetFim = '</li></ul></fieldset>';	
	        	var inicioTable = '<table class="display dataTable no-footer" border="0">'     		+
			    				  '<thead><tr>'													    +
			    				  	'<th style="width: 16%; text-align: center;">Codigo</th>' 		+
			    				  	'<th style="width: 16%; text-align: center;">BarCode </th>' 	+
			    				  	'<th style="width: 16%; text-align: center;">Nome</th>' 	    +
			    				  	'<th style="width: 16%; text-align: center;">Descricao</th>' 	+
			    				  	'<th style="width: 16%; text-align: center;">Marca</th>' 	    +
			    				  	'<th style="width: 16%; text-align: center;">Tamanho</th>' 		+
			    				  	'<th style="width: 16%; text-align: center;">Preço</th>' 		+
			    				  '</tr></thead><tbody style="text-align: center;">';
				var	lines = '';
				var finalTable = '</tbody></table>';				
	        	 
	        	
	        	$.ajax({
	  				type : "POST",
	  				contentType : "application/json",
	  				url : "${home}consultarItensVenda",
	  				data : JSON.stringify(jQuery.parseJSON(row.data()[1])),
	  				dataType : 'json',
	  				timeout : 100000,
	  				success : function(data) {

	  					console.log(data);
	  					
	  					$.each(data.vendaHasItemProduto, function(key, value) {
	  						
  							lines = lines +  '<tr>';
							lines = lines +  '<td style="text-align: center;">' + data.vendaHasItemProduto[key].produtoHasItensTipoMedida.produto.codigo        +'</td>';
  							lines = lines +  '<td>' 							+ data.vendaHasItemProduto[key].produtoHasItensTipoMedida.produto.barCode       +'</td>';
	  						lines = lines +  '<td>' 							+ data.vendaHasItemProduto[key].produtoHasItensTipoMedida.produto.nome          +'</td>';
	  						lines = lines +  '<td>'								+ data.vendaHasItemProduto[key].produtoHasItensTipoMedida.produto.descricao     +'</td>';
	  						lines = lines +  '<td>' 							+ data.vendaHasItemProduto[key].produtoHasItensTipoMedida.produto.marca.nome    +'</td>';
	  						lines = lines +  '<td>' 							+ data.vendaHasItemProduto[key].produtoHasItensTipoMedida.itensTipoMedida.valor +'</td>';
	  						lines = lines +  '<td>' 							+ data.vendaHasItemProduto[key].produtoHasItensTipoMedida.produto.precoVenda    +'</td>';		  						
 	  						lines = lines + '</tr>';	
	  					});
	  					// Open this row	  					
	  		            row.child(fieldsetInicio + inicioTable + lines + finalTable + fieldsetFim).show();
	  		            tr.addClass('shown');
	  				},
	  				error : function(e) {
	  	  				alert("Erro" + e);
	  				},
	  				done : function(e) {
	   				}
	  			});	        	
	        	row.child(fieldsetInicio + inicioTable + finalTable + fieldsetFim).show();
		        tr.addClass('shown');
	        }
	    } );
		
	    function flagDominios(data){
	    	var str_check = "";
	    	$.each(data, function(keyD, valueD) {
	    		str_check = str_check + "<input type='checkbox' checked disabled='disabled'>" + valueD.nome;
			});
	    	return str_check;  
	    }
	    
	    $('#tableConsulta tbody').on('click', 'td.editarVenda', function () {
// 	    	console.log($(this).closest('tr').children('td.cod').text());
	        $("#codigo").val($(this).closest('tr').children('td.cod').text());
	        $("form[name='vendafiltro']").attr('action', 'abrirAlterarVenda');
	        $("form[name='vendafiltro']").submit();
		});
	});
</script>
<form:form method="post" modelAttribute="vendafiltro" action="filtrar" name="vendafiltro">	 
	<br>
		<fieldset>
			<legend>Consultar Venda</legend>
			<ul class="form-style-1">
				<li>
					<c:if test="${not empty mensagem}">
						<div class="ui-widget">
							<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
								<p>
									<span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span> 
									<strong>Hey!</strong> ${mensagem} 
								</p>
							</div>
						</div>
					</c:if>
					<br>
					<!-- filtro -->
					<fieldset>
						<legend  style=" font-weight: bold;">Filtro</legend>
						<ul class="form-style-1">
							<li>
								<table style="width:100%">
									<tr>
										<td width="20%">
											<label>Codigo</label>
											<form:input path="codigo" type="text" class="field-long" placeholder="Código"/>
										</td>
										<td width="20%">
											<label>Data</label>
											<form:input path="data" id="datepicker" type="text" class="field-long" placeholder="dd/MM/yyyy"/>
										</td>
										<td width="20%">
											<label>Status</label>											
											<form:select path="status" cssClass="field-select">											
												<form:option value="NONE" label="Selecione"/>
												<c:forEach items="${listStatusVenda}" var="i">
													<form:option value="${i}" label="${i}"/>
												</c:forEach>
											 </form:select>		
										</td>
										<td width="20%">
											<label>Cliente</label> 
											<form:input path="cliente" type="text" class="field-long" placeholder="Cliente"/>
										</td>
										<td width="20%">
											<label>Forma Pagamento</label> 										
											<form:select path="formaDePagamento" cssClass="field-select">
												<form:option value="NONE" label="Selecione"/>				 								
				 								<c:forEach items="${listFormaDePagamento}" var="item">
			 						 				<form:option value="${item}" label="${item.nome}"/>
				 						  		</c:forEach>
				 						   </form:select> 	
										</td>
										<td width="20%">											
											<label>&nbsp;</label>
											<input type="button" id="filtrar" value="Filtrar" />
										</td>
							 		</tr>
							 	</table>				
							</li>						 
						</ul>
					</fieldset>	
					<!-- filtro -->
					<br>
					<fieldset>
						<legend  style=" font-weight: bold;"></legend>
							<ul class="form-style-1">
							<li>							
						 	<table id="tableConsulta" class="display" style="width:100%" >
								<thead>
									<tr>
									 	<th></th>
										<th>Código</th>
										<th>Data</th>
										<th>Hora</th> 
										<th>Status</th> 
										<th>Cliente</th>
										<th>Forma De Pagamento</th>
										<th>Quantidade</th>
										<th>Desconto</th>   
										<th>Pagamento</th> 
										<th>Pendente</th>
										<th>Total</th> 
										<th></th>
									</tr>
								</thead>				
								<tbody>
									<c:forEach items="${list}" var="i">
										<tr>
											<td class="details-control"></td>				
											<td class="cod">${i.codigo}</td>
											<td>${i.data}</td>
											<td>${i.hora}</td>
											<td>${i.status}</td>
											<td>${i.cliente.codigo}</td>
											<td>${i.formaDePagamento.nome}</td>
											<td>${i.quantidade}</td> 
											<td>${i.desconto}</td>   
											<td>${i.pagamento}</td> 
											<td>${i.valorPendente}</td>
											<td>${i.valorTotal}</td> 
											<c:if test="${i.status != 'Cancelado'}">
												<td class="editarVenda"></td>
											</c:if>
											<c:if test="${i.status == 'Cancelado'}">
												<td></td>
											</c:if>			
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
	<br>
</form:form>