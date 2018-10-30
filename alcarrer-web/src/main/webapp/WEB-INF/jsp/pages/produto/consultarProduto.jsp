<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <spring:url value="/resources/jquery-3.3.1.js" var="jquery331Js" /> --%>
<%-- <script type="text/javascript" src="${jquery331Js}"></script> --%>
	  
<%-- <spring:url value="/resources/jquery.dataTables.min.js" var="jqueryDataTablesMinJs" /> --%>
<%-- <script type="text/javascript" src="${jqueryDataTablesMinJs}"></script> --%>

<style>
	td.details-control {
	    background: url('resources/images/details_open.png') no-repeat center center;
	    cursor: pointer;
	}
	
	tr.shown td.details-control {
	    background: url('resources/images/details_close.png') no-repeat center center;
	}
	
	td.edit-control{
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
			    				  	'<th style="width: 25%;text-align: center;">Tamanho</th>' 		+
			    				    '<th style="width: 25%;text-align: center;">Quantidade</th>'    +
			    				    '<th style="width: 50%;text-align: center;">Dominio</th>'       +
			    				  '</tr></thead><tbody style="text-align: center;">';
				var	lines = '';
				var finalTable = '</tbody></table>';
				
	        	var codigo = '{"codigo":'+ row.data()[1] +'}';
	        	$.ajax({
	  				type : "POST",
	  				contentType : "application/json",
	  				url : "${home}ajaxConsultarItensMedidaByProdutoCodigo",
	  				data : JSON.stringify(jQuery.parseJSON(codigo)),
	  				dataType : 'json',
	  				timeout : 100000,
	  				success : function(data) {
	  					console.log(data);
	  					$.each(data, function(key, value) {
  							lines = lines +  '<tr>';
	  							lines = lines +  '<td style="text-align: center;">' + data[key].itensTipoMedida.valor   +'</td>';
		  						lines = lines +  '<td>' + data[key].quantidade              +'</td>';
		  						lines = lines +  '<td>' + flagDominios(data[key].dominios)  +'</td>';
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
	        }
	    } );
		
	    function flagDominios(data){
	    	var str_check = "";
	    	$.each(data, function(keyD, valueD) {
	    		str_check = str_check + "<input type='checkbox' checked disabled='disabled'>" + valueD.nome;
			});
	    	return str_check;  
	    }
	    
	    $('#tableConsulta tbody').on('click', 'td.edit-control', function () {
	    	console.log($(this).closest('tr').children('td.cod').text());
	        $("#codigo").val($(this).closest('tr').children('td.cod').text());
			$("form[name='produtoForm']").submit();
		});
	});
</script>
<form:form method="post" modelAttribute="produtoForm" action="abrirAlterarProduto" name="produtoForm">
	<form:hidden path="codigo" id="codigo"/>
	<br>
		<fieldset>
			<legend>Gerenciar Produto</legend>
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
								<th>Bar Code</th>
								<th>Nome</th>
								<th>Descrição</th>
								<th>Porcentagem</th>
								<th>Preço Custo</th>
								<th>Preco Venda</th>
								<th>Desconto</th>
								<th>Preço Oferta</th>
								<th>QTD Em Estoque</th>
								<th>Marca</th>
								<th>Categoría</th>
								<th>Subcategoría</th>
								<th>Fornecedor</th>
								<th></th>
							</tr>
						</thead>				
						<tbody>
							<c:forEach items="${list}" var="i">
								<tr>
									<td class="details-control"></td>				
									<td class="cod">${i.codigo}</td>
									<td>${i.barCode}</td>
									<td>${i.nome}</td>					 
									<td>${i.descricao}</td>					 
									<td>${i.porcentagem}%</td>									
									<td>${i.precoCusto}</td>		
									<td>${i.precoVenda}</td>					 
									<td>${i.desconto}</td>			 
									<td>${i.precoOferta}</td>					 
									<td>${i.quantidadeTotalEstoque}</td>					 
									<td>${i.marca.nome}</td>					 
									<td>${i.categoria.nome}</td>
									<td>${i.subCategoria.nome}</td>					 
									<td>${i.fornecedor.nome}</td>
									<td class="edit-control"></td>			
								</tr>
							</c:forEach>	
						</tbody>
					</table>
				</li>
			</ul>
		</fieldset>	
	<br>
</form:form>