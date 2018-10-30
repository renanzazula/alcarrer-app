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
		
		$('#incluirFormasDePagamento').on( 'click', function () {
			$("form[name='formasDePagamentoForm']").attr('action', 'incluirFormasDePagamento');
			$("form[name='formasDePagamentoForm']").submit();
		});		

		$('#alterarFormasDePagamento').on( 'click', function () {
			$("form[name='formasDePagamentoForm']").attr('action', 'alterarFormasDePagamento')
			$("form[name='formasDePagamentoForm']").submit();
		});	
		
		$('#excluirFormasDePagamento').on( 'click', function () {
			$("form[name='formasDePagamentoForm']").attr('action', 'excluirFormasDePagamento')
			$("form[name='formasDePagamentoForm']").submit();
		});	

		$('#cancelarFormasDePagamento').on( 'click', function () {
			$("form[name='formasDePagamentoForm']").attr('action', 'abrirFormasDePagamento')
			$("form[name='formasDePagamentoForm']").submit();
		});
		
	});
</script>
 
<form:form method="post" modelAttribute="formasDePagamentoForm" action="abrirFormasDePagamento" name="formasDePagamentoForm">
	<br>
	<fieldset>
		<legend>Gerenciar FormasDePagamento</legend>
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
			
			<li>
				<label>Porcentagem Desconto<span class="required">*</span></label>
				<form:input path="porcentagemDesconto" type="text" class="field-long"
							  id="porcentagemDesconto" placeholder="Porcentagem Desconto"/>				 
			</li>
			
			<li>
				<form:errors path="porcentagemDesconto" cssClass="required"/>
			</li>
			
			<li class="text-align-right">
				<input type="button" id="cancelarFormasDePagamento" value="Cancelar" />
				
				<c:if test="${formasDePagamentoForm.codigo == null}">
					<input type="button" id="incluirFormasDePagamento" value="Gravar" />
				</c:if>
				<c:if test="${formasDePagamentoForm.codigo != null}">
					<input type="button" id="excluirFormasDePagamento" value="Excluir" />
					<input type="button" id="alterarFormasDePagamento" value="Alterar" />
				</c:if>
				
			</li>
			
		</ul>
	</fieldset>	
	<br>
</form:form >