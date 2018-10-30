<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <spring:url value="/resources/external/jquery/jquery.js" var="jqueryJs" /> --%>
<%-- <script type="text/javascript" src="${jqueryJs}"></script> --%>

<%-- <spring:url value="/resources/jquery-ui.js" var="jqueryUiJs" /> --%>
<%-- <script type="text/javascript" src="${jqueryUiJs}"></script> --%>

<script type="text/javascript">
	$(document).ready(function(){
		
		$('#incluirFornecedor').on( 'click', function () {
			$("form[name='fornecedorForm']").attr('action', 'incluirFornecedor')
			$("form[name='fornecedorForm']").submit();
		});

		$('#alterarFornecedor').on( 'click', function () {
			$("form[name='fornecedorForm']").attr('action', 'alterarFornecedor')
			$("form[name='fornecedorForm']").submit();
		});	

		$('#excluirFornecedor').on( 'click', function () {
			$("form[name='fornecedorForm']").attr('action', 'excluirFornecedor')
			$("form[name='fornecedorForm']").submit();
		});

		$('#cancelarFornecedor').on( 'click', function () {
			$("form[name='fornecedorForm']").attr('action', 'abrirFornecedor')
			$("form[name='fornecedorForm']").submit();
		});

	});
</script>
 
<form:form method="post" modelAttribute="fornecedorForm" action="abrirFornecedor" name="fornecedorForm">
	</br>
	<fieldset>
		<legend>Gerenciar Fornecedor</legend>
		<ul class="form-style-1">
			
			<form:hidden path="codigo"/>
			
			<li>
				<label>Nome:<span class="required">*</span></label>
				<form:input path="nome" type="text" class="field-long" id="nome" placeholder="Nome"/>
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
				<input type="button" id="cancelarFornecedor" value="Cancelar" />
				<c:if test="${fornecedorForm.codigo == null}">
					<input type="button" id="incluirFornecedor" value="Gravar" />
				</c:if>
				<c:if test="${fornecedorForm.codigo != null}">
					<input type="button" id="excluirFornecedor" value="Excluir" />
					
					<input type="button" id="alterarFornecedor" value="Alterar" />
				</c:if>
			</li>
		</ul>
	</fieldset>
	</br>	
</form:form>