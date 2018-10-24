<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<spring:url value="/resources/external/jquery/jquery.js" var="jqueryJs" />
<script type="text/javascript" src="${jqueryJs}"></script>

<spring:url value="/resources/jquery-ui.js" var="jqueryUiJs" />
<script type="text/javascript" src="${jqueryUiJs}"></script>

<spring:url value="/resources/jquery.mask.js" var="jqueryMaskJs" />
<script type="text/javascript" src="${jqueryMaskJs}"></script>

<spring:url value="/resources/jquery.mask.min.js" var="jqueryMaskMinJs" />
<script type="text/javascript" src="${jqueryMaskMinJs}"></script>

<spring:url value="/resources/jquery.maskMoney.js" var="jqueryMaskMoneyJs" />
<script type="text/javascript" src="${jqueryMaskMoneyJs}"></script>

<script type="text/javascript">
	$(document).ready(function(){
		 
// 		$("#valorInicial").maskMoney();	
// 		$("#valorInicial").focus();

		$("#valorFinal").maskMoney();
		$("#valorFinal").focus();
 	  	
	});
</script>

<spring:url value="/fecharCaixa" var="fecharCaixaActionUrl" />

<form:form method="post" modelAttribute="caixaForm" action="${fecharCaixaActionUrl}">
	</br>
	<fieldset>
		<legend>Gerenciar Caixa</legend>
		<ul class="form-style-1">
			<li>
				<fieldset>
					<legend  style=" font-weight: bold;">Dados Abertura Caixa</legend>
					<ul class="form-style-1">
						<li>
							<table style="width:100%">
								<tr>
									<td>
										<label>Código</label>
										<form:input path="codigo" type="text" class="field-long"
											id="codigo" placeholder="codigo" style="color:red; text-align:center;" 
											readonly="true"/> 	
									</td>
									<td>
										<label>Data Abertura</label>
										<form:input path="dataAbertura"
											type="text" class="field-long" id="dataAbertura"
											placeholder="dataAbertura" style="color:red; text-align:center;" 
											readonly="true"/>  	 
						 			</td>
									<td>
										<label>Hora Abertura</label>
										<form:input path="horaAbertura"
											type="text" class="field-long" id="horaAbertura"
											placeholder="Hora Abertura" style="color:red; text-align:center;" 
											readonly="true"/> 													 
						 			</td>
						 		</tr>
						 	</table>
						</li>
						<li>
							<label>Valor Inicial</label> 
							 	<form:input path="valorInicial" type="text" class="field-long" 
									id="valorInicial" placeholder="0,00" readonly="true"
									data-thousands="." data-decimal=","
									style="text-align:center; font-size: 150%; font-weight: bold;"/>							
 
 						</li>
 					</ul>
				</fieldset>	
				
				<fieldset>
					<legend  style="font-weight: bold;">Dados Fechamento do Caixa</legend>
					<ul class="form-style-1">
						<li>
							<table style="width:100%">
								<tr>
									<td>
										<label>Data fechamento</label>
										<form:input path="dataFechamento"
											type="text" class="field-long" id="dataFechamento"
											placeholder="Data Fechamento Caixa" style="color:red; text-align:center;" 
											readonly="true"/>  													 
						 			</td>
									<td>
										<label>Hora fechamento</label>
										<form:input path="horaFechamento"
											type="text" class="field-long" id="horaFechamento"
											placeholder="Hora Fechamento Caixa" style="color:red; text-align:center;" 
											readonly="true"/> 
						 			</td>
						 		</tr>
						 	</table>
						</li>
						
						<li>
							<label>Valor Final</label>
							<form:input path="valorFinal"
								type="text" class="field-long" id="valorFinal"
								placeholder="0,00" data-thousands="." data-decimal=","
								style="text-align:center; font-size: 150%; font-weight: bold;"/> 	 
						</li>
					 	
					 	<li>
					 		<input type="submit" value="Fechar Caixa" class="field-long"/>
					 	</li>					 	 
					</ul>
				</fieldset>				 								
			</li>
		</ul>
	</fieldset>
	</br>
</form:form>