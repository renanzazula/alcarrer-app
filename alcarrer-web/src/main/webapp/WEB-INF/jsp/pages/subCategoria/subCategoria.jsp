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
		$('#incluirSubCategoria').on( 'click', function () {
			$("form[name='subCategoriaForm']").attr('action', 'incluirSubCategoria')
			$("form[name='subCategoriaForm']").submit();
		});

		$('#alterarSubCategoria').on( 'click', function () {
			$("form[name='subCategoriaForm']").attr('action', 'alterarSubCategoria')
			$("form[name='subCategoriaForm']").submit();
		});		

		$('#excluirSubCategoria').on( 'click', function () {
			$("form[name='subCategoriaForm']").attr('action', 'excluirSubCategoria')
			$("form[name='subCategoriaForm']").submit();
		});		

		$('#cancelarSubCategoria').on( 'click', function () {
			$("form[name='subCategoriaForm']").attr('action', 'abrirSubCategoria')
			$("form[name='subCategoriaForm']").submit();
		});		
		
	});
</script>
 
<form:form method="post" modelAttribute="subCategoriaForm" action="abrirSubCategoria" name="subCategoriaForm">
	</br>
	<fieldset>
		<legend>Gerenciar SubCategoría</legend>
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
				<input type="button" id="cancelarSubCategoria" value="Cancelar" />
				
				<c:if test="${subCategoriaForm.codigo == null}">
					<input type="button" id="incluirSubCategoria" value="Gravar" />
				</c:if>
				<c:if test="${subCategoriaForm.codigo != null}">
					<input type="button" id="excluirSubCategoria" value="Excluir" />
					
					<input type="button" id="alterarSubCategoria" value="Alterar" />
				</c:if>
				
			</li>
			
		</ul>
	</fieldset>	
	</br>
</form:form >