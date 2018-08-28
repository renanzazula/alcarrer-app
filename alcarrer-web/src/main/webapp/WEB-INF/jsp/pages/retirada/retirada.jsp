<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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

<spring:url value="/resources/jquery.dataTables.min.js" var="jqueryDataTablesMinJs" />
<script type="text/javascript" src="${jqueryDataTablesMinJs}"></script>

<script type="text/javascript">
	$(document).ready(function(){
		
	  	$.extend( true, $.fn.dataTable.defaults, {
		    "searching": false,
		    "ordering": false
		} );

	  	var table = $('#tableRetirada').DataTable({
			"lengthMenu" : [ [ 5, 7, -1 ], [ 5, 7, "All" ] ],
	  		"bLengthChange": false,
	  		"bInfo" : false,
	  		"language": {
  				"decimal": ",",
            	"thousands": ".",
	  	    	"emptyTable": "No data available in table!!"
	  	    }
		});

	  	$('#tableRetirada').on( 'click', 'tbody tr', function () {
  			table.row( this ).remove().draw();
	  	} );

	});
</script>
<form>
</br>
<fieldset>
	<legend>Dados</legend>
		<ul class="form-style-1" >
			<li>	
				<fieldset>
					<legend>Dados</legend>
					<ul class="form-style-1" >
						<li>	
							<label>
								Código:
								<input type="text" style="width: 10%">			 
								
								Funcionario: 
								<input type="text" style="width: 38%">
								
								Data:
								<input type="text" style="width: 15%">
							 	
							 	Hora: 
								<input type="text" style="width: 15%">
							</label>
						</li>
					</ul>
				</fieldset>
				</br>
				<fieldset>
					<legend>Retirada</legend>
					<ul class="form-style-1" >
						
						<li>
							<label>Descrição:<span class="required">*</span></label>
							<textarea name="descricao" id="descricao" class="field-textarea" style="width: 100%; margin: 0px; height: 32px;"></textarea>
						</li>
						
						<li>
							<label>
								Data:<span class="required">*</span>
								<input type="text" id="datepicker" style="width: 10%">		
								Valor:<span class="required">*</span>
								<input type="text" class="money" style="width: 15%">
								<input type="button" id="registrarRecebimento" value="Registrar Retirada" style="width: 30%" />
							</label>	 
						</li>
					</ul>						
					
					<fieldset>
						<legend>Retiradas</legend>			
						<ul class="form-style-1" >
							<li>
								<table id="tableRetirada" class="display" cellspacing="0" width="98%">
									<thead>
										<tr>
											<th>Descrição Retirada</th>
											<th>Valor Retirada</th>												
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
								Total Retirada:
								<input type="text" style="width: 20%">
								Valor em Caixa:
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