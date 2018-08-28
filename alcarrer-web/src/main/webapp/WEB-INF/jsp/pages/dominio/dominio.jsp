<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<spring:url value="/resources/external/jquery/jquery.js" var="jqueryJs" />
<script type="text/javascript" src="${jqueryJs}"></script>

<spring:url value="/resources/jquery-ui.js" var="jqueryUiJs" />
<script type="text/javascript" src="${jqueryUiJs}"></script>

<script type="text/javascript">
	$(document).ready(function(){
		
		$('#incluirDominio').on( 'click', function () {
			$("form[name='dominioForm']").attr('action', 'incluirDominio');
			$("form[name='dominioForm']").submit();
		});		

		$('#alterarDominio').on( 'click', function () {
			$("form[name='dominioForm']").attr('action', 'alterarDominio')
			$("form[name='dominioForm']").submit();
		});	
		
		$('#excluirDominio').on( 'click', function () {
			$("form[name='dominioForm']").attr('action', 'excluirDominio')
			$("form[name='dominioForm']").submit();
		});	

		$('#cancelarDominio').on( 'click', function () {
			$("form[name='dominioForm']").attr('action', 'abrirDominio')
			$("form[name='dominioForm']").submit();
		});
		
	});
</script>
 
<form:form method="post" modelAttribute="dominioForm" action="abrirDominio" name="dominioForm">
	<br>
	<fieldset>
		<legend>Gerenciar Dominio</legend>
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
			
			<li class="text-align-right">
				<input type="button" id="cancelarDominio" value="Cancelar" />
				
				<c:if test="${dominioForm.codigo == null}">
					<input type="button" id="incluirDominio" value="Gravar" />
				</c:if>
				<c:if test="${dominioForm.codigo != null}">
					<input type="button" id="excluirDominio" value="Excluir" />
					<input type="button" id="alterarDominio" value="Alterar" />
				</c:if>
				
			</li>
			
		</ul>
	</fieldset>	
	<br>
</form:form >