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


<spring:url value="/abrirCaixa" var="abrirCaixaActionUrl" />

<script type="text/javascript">
	$(document).ready(function(){

		$('#abrirCaixa').on( 'click', function () {
	  		$("form[name='caixaForm']").attr('action', '${abrirCaixaActionUrl}')
	  		$("form[name='caixaForm']").submit();
	  	});
 		$("#valorInicial").maskMoney();	
		$("#valorInicial").focus();
	});
</script>

<form:form method="post" modelAttribute="caixaForm" action="${abrirCaixaActionUrl}" name="caixaForm">
	</br>
	<fieldset>
		<legend>Gerenciar Caixa</legend>
		<ul class="form-style-1">
			<li>
				<fieldset>
					<legend  style=" font-weight: bold;">Dados do Caixa</legend>
					<ul class="form-style-1">
						<li>
							<table style="width:100%">
								<tr>
<!-- 									<td> -->
<!-- 										<label>Código</label> -->
<%-- 										<form:input path="codigo" type="text" class="field-long" --%>
<%-- 											id="codigo" placeholder="codigo" style="color:red; text-align:center;"  --%>
<%-- 											readonly="true"/> 	 --%>
<!-- 									</td> -->
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
											readonly="true" /> 													 
						 			</td>
						 		</tr>
						 	</table>
						</li>
						<li>
							<label>Valor Inicial</label> 
							<form:input path="valorInicial" type="text" class="field-long" 
								id="valorInicial" placeholder="0,00" data-thousands="." data-decimal=","
								style="text-align:center; font-size: 150%; font-weight: bold;"/>							
 						</li>
						<li>
						 	<label>Status:<span class="required">*</span></label> 
							<form:hidden path="status"  id="status" value="F"/>
							<input type="text" value="Fechado" class="field-long"
								placeholder="status" style="text-align: center; background-color: red;">
						</li>
						<li>
					 		<input type="button" value="Abrir Caixa" class="field-long" id="abrirCaixa"/>
 					 	</li>
					</ul>
				</fieldset>													
			</li>
		</ul>
	</fieldset>
	
</form:form>