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
	
	td.imprimir-recibo{
		background: url('resources/images/edit.png') no-repeat center center;
	}
	
</style>

<script type="text/javascript">
	$(document).ready(function() {
		 
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
	  						lines = lines +  '<td>' 							+ data.vendaHasItemProduto[key].produtoHasItensTipoMedida.produto.marca.nome 		    +'</td>';
	  						lines = lines +  '<td>' 							+ data.vendaHasItemProduto[key].produtoHasItensTipoMedida.itensTipoMedida.valor +'</td>';
	  						lines = lines +  '<td>' 							+ data.vendaHasItemProduto[key].produtoHasItensTipoMedida.produto.preco         +'</td>';		  						
 	  						lines = lines + '</tr>';	
	  					});
	  					// Open this row
	  					console.log(inicioTable + lines + finalTable);
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
	    
	    $('#tableConsulta tbody').on('click', 'td.imprimir-recibo', function () {
	    	console.log($(this).closest('tr').children('td.cod').text());
	        $("#codigo").val($(this).closest('tr').children('td.cod').text());
			$("form[name='vendaForm']").submit();
		});
	});
</script>
<form:form method="post" modelAttribute="vendaForm" action="abrirAlterarVenda" name="vendaForm">
	<form:hidden path="codigo" id="codigo"/>
	<br>
		<fieldset>
			<legend>Gerenciar Venda</legend>
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
				 	<table id="tableConsulta" class="display" style="width:100%" >
						<thead>
							<tr>
							 	<th></th>
								<th>Código</th>
								<th>Data Hora</th> 
								<th>Status</th> 
								<th>Cliente</th>
								<th>Forma De Pagamento</th>
								<th>Quantidade itens</th>
								<th>Desconto</th>   
								<th>Pagamento</th> 
								<th>Valor Pendente</th>
								<th>Valor Total</th> 
								<th></th>
							</tr>
						</thead>				
						<tbody>
							<c:forEach items="${list}" var="i">
								<tr>
									<td class="details-control"></td>				
									<td class="cod">${i.codigo}</td>
									<td>${i.dataHora}</td>
									<td>${i.status}</td>
									<td>${i.cliente.codigo}</td>
									<td>${i.formaDePagamento.nome}</td>
									<td>${i.quantidade}</td> 
									<td>${i.desconto}</td>   
									<td>${i.pagamento}</td> 
									<td>${i.valorPendente}</td>
									<td>${i.valorTotal}</td> 
									<td class="imprimir-recibo"></td>			
								</tr>
							</c:forEach>	
						</tbody>
					</table>
				</li>
			</ul>
		</fieldset>	
	<br>
</form:form>