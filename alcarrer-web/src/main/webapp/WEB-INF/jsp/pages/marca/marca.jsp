<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
	$(document).ready(function(){
		
		$('#incluirMarca').on( 'click', function () {
			$("form[name='marcaForm']").attr('action', 'incluirMarca');
			$("form[name='marcaForm']").submit();
		});		

		$('#alterarMarca').on( 'click', function () {
			$("form[name='marcaForm']").attr('action', 'alterarMarca')
			$("form[name='marcaForm']").submit();
		});	
		
		$('#excluirMarca').on( 'click', function () {
			$("form[name='marcaForm']").attr('action', 'excluirMarca')
			$("form[name='marcaForm']").submit();
		});	

		$('#cancelarMarca').on( 'click', function () {
			$("form[name='marcaForm']").attr('action', 'abrirMarca')
			$("form[name='marcaForm']").submit();
		});
		
	});
</script>
 
<form:form method="post" modelAttribute="marcaForm" action="abrirMarca" name="marcaForm">
	<br>
	<fieldset>
		<legend>Gerenciar Marca</legend>
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
				<input type="button" id="cancelarMarca" value="Cancelar" />
				
				<c:if test="${marcaForm.codigo == null}">
					<input type="button" id="incluirMarca" value="Gravar" />
				</c:if>
				<c:if test="${marcaForm.codigo != null}">
					<input type="button" id="excluirMarca" value="Excluir" />
					<input type="button" id="alterarMarca" value="Alterar" />
				</c:if>
				
			</li>
			
		</ul>
	</fieldset>	
	<br>
</form:form >