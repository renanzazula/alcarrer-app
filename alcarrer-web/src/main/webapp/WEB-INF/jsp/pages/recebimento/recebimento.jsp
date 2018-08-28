<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<spring:url value="/resources/external/jquery/jquery.js" var="jqueryJs" />
<script type="text/javascript" src="${jqueryJs}"></script>

<spring:url value="/resources/jquery-ui.js" var="jqueryUiJs" />
<script type="text/javascript" src="${jqueryUiJs}"></script>

<spring:url value="/resources/jquery.mask.js" var="jqueryMaskJs" />
<script type="text/javascript" src="${jqueryMaskJs}"></script>

<spring:url value="/resources/jquery.mask.min.js" var="jqueryMaskMinJs" />
<script type="text/javascript" src="${jqueryMaskMinJs}"></script>

<spring:url value="/resources/jquery-1.12.4.js" var="jquery124Js" />
<script type="text/javascript" src="${jquery124Js}"></script>

<spring:url value="/resources/jquery.dataTables.min.js"
	var="jqueryDataTablesMinJs" />
<script type="text/javascript" src="${jqueryDataTablesMinJs}"></script>

<script type="text/javascript">
	$.extend(true, $.fn.dataTable.defaults, {
		"searching" : false,
		"ordering" : false
	});

	$(document).ready(function() {

		var table = $('#tableVenda').DataTable({
			"lengthMenu" : [ [ 5, 7, -1 ], [ 5, 7, "All" ] ],
			"bLengthChange" : false,
			"bInfo" : false,
			"language" : {
				"decimal" : ",",
				"thousands" : ".",
				"emptyTable" : "No data available in table!!"
			}
		});

		$('#tableVenda').on('click', 'tbody tr', function() {
			table.row(this).remove().draw();
		});

		tableRecebimentos
		var table = $('#tableRecebimentos').DataTable({
			"lengthMenu" : [ [ 5, 7, -1 ], [ 5, 7, "All" ] ],
			"bLengthChange" : false,
			"bInfo" : false,
			"language" : {
				"decimal" : ",",
				"thousands" : ".",
				"emptyTable" : "No data available in table!!"
			}
		});

		$('#tableRecebimentos').on('click', 'tbody tr', function() {
			table.row(this).remove().draw();
		});
	});
</script>
<form>
	</br>
		<fieldset>
			<legend>Gerenciar Recebimentos</legend>
			<ul class="form-style-1" >
				<li>
		
					<fieldset>
						<legend>Dados</legend>
						<ul class="form-style-1" >
							<li><label> 
									Código: 
									<input type="text" style="width: 10%"> 
									Funcionario: 
									<input type="text" style="width: 38%"> 
									Data: 
									<input type="text" style="width: 15%"> 
									Hora: 
									<input type="text" style="width: 15%">
							</label></li>
						</ul>
					</fieldset>
	
		</br>
	
					<fieldset>
						<legend>Vendas Pendentes</legend>
						<ul class="form-style-1" >
							<li>
								<label>Cliente: 
								<input type="text" style="width: 20%;"> 
								<input type="button" id="abrirStatus" value="Buscar Cliente" style="width: 73%" />
							</label></li>
						</ul>
				
						<fieldset>
							<legend>Vendas Pendentes</legend>
							<ul class="form-style-1" >
								<li>
									<table id="tableVenda" class="display" cellspacing="0" width="100%">
										<thead>
											<tr>
												<th>Data</th>
												<th>Valor Pendentes</th>
												<th>Valor Pago</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach begin="1" end="20" var="i">
												<tr>
													<th>${i}</th>
													<th>${i}</th>
													<th>${i}</th>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</li>
							</ul>
						</fieldset>
					</fieldset>
	
		</br>
	
					<fieldset>
						<legend>Recebimentos</legend>
				
						<ul class="form-style-1" >
							<li>
								<label>
									Valor: 
									<input type="text" style="width: 20%;"> 
									<input type="button" id="registrarRecebimento" value="Registrar Recebimento" style="width: 74%" />
							</label></li>
						</ul>
				
						<fieldset>
							<legend>Recebimentos</legend>
							<ul class="form-style-1" >
								<li>
									<table id="tableRecebimentos" class="display" cellspacing="0"
										width="100%">
										<thead>
											<tr>
												<th>Data Hora Ultimo Recebimento</th>
												<th>Valor Recebimento</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach begin="1" end="20" var="i">
												<tr>
													<th>${i}</th>
													<th>${i}</th>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</li>
							</ul>
						</fieldset>
				
						</br>
				
						<ul class="form-style-1" style="padding: 0px 10px 1px 0px; text-align: right;">
							<li>
								<label> 
									Troco: 
									<input type="text" style="width: 20%">
									Total Pendente: 
									<input type="text" style="width: 20%">
									Total Recebido: 
									<input type="text" style="width: 20%">
								</label>
							</li>
						</ul>
					</fieldset>
				</li>	
			</ul>
		</fieldset>			
	</br>
</form>