<script src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<style>
.navigation {
	background-color:  red;
	width: 100%;
}

.nav {
	margin: 0px;
	padding: 0px;
	list-style: none;
	text-align: center;
}

.nav li {
	float: left;
	width: 160px;
	position: relative;
}

.nav li a {
	background: #000000;
	color: #ffffff;
	display: block;
	padding: 7px 8px;
	text-decoration: none;
	border-top: 1px solid #000000;
}

.nav li a:hover {
	color: #808080;
}

/*=== submenu ===*/
.nav ul {
	display: none;
	position: absolute;
	margin-left: 0px;
	list-style: none;
	padding: 0px;
}

.nav ul li {
	width: 160px;
	float: left;
}

.nav ul a {
	display: block;
	height: 15px;
	padding: 7px 8px;
	color: #ffffff;
	text-decoration: none;
	border-bottom: 1px solid #808080;
}

.nav ul li a:hover {
	color: #808080;
}
</style>

<script>
	/*  jQuery ready function. Specify a function to execute when the DOM is fully loaded.  */
	$(document).ready(
	/* This is the function that will get executed after the DOM is fully loaded */
	function() {
		/* Next part of code handles hovering effect and submenu appearing */
		$('.nav li').hover(function() { //appearing on hover
			$('ul', this).fadeIn();
		}, function() { //disappearing on hover
			$('ul', this).fadeOut();
		});
	});
</script>

<!-- Use this navigation div as your menu bar div -->
<div class="navigation" style="width: 100%">
	<ul class="nav">
		<li>
			<a href="caixa">Caixa</a>
		</li>
		
		<li>
			<a href="#">Cadastros</a>
			<ul>
				<li><a href="abrirProduto">Produto</a></li>
				<li><a href="abrirMedida">Medida</a></li>
				<li><a href="abrirMarca">Marca</a></li>
				<li><a href="abrirFornecedor">Fornecedor</a></li>
				<li><a href="abrirCategoria">Categoria</a></li>
				<li><a href="abrirSubCategoria">SubCategoría</a></li>
				<li><a href="abrirFormasDePagamento">Formas de Pagamento</a></li>
				<li><a href="abrirDominio">Dominios</a></li>
			</ul>
		</li>
		<li>
			<a href="#">Consultas</a>
			<ul>
				<li><a href="consultarProduto">Produto</a></li>
				<li><a href="consultarMedida">Medida</a></li>
				<li><a href="consultarMarca">Marca</a></li>
				<li><a href="consultarFornecedor">Fornecedor</a></li>
				<li><a href="consultarCategoria">Categoria</a></li>
				<li><a href="consultarSubCategoria">SubCategoría</a></li>
				<li><a href="consultarFormasDePagamento">Formas de Pagamento</a></li>
				<li><a href="consultarVendas">Vendas</a></li>
				<li><a href="consultarRetirada">Retirada</a></li>
				<li><a href="consultarDominio">Dominios</a></li>
			</ul>
		</li>
		
		<li>
			<a href="#">Venda</a>
			<ul>
				<li><a href="abrirVenda">Registra Venda</a></li>
				<li><a href="abrirCancelarVenda">Cancelar Venda</a></li>
			</ul>
		</li>
		
		<li>
			<a href="abrirRetirada">Retirada</a>
			<ul>
				<li><a href="abrirRetirada">Efetuar Retirada</a></li>
				<li><a href="abrirCancelarRetirada">Cancelar Retirada</a></li>
			</ul>
		</li>
		
		<li>
			<a href="abrirRecebimento">Recebimento</a>
			<ul>
				<li><a href="abrirRecebimento">Recebimento</a></li>
			</ul>
		</li>
		
		<li><a href="abrirRelatorios">Relatorios</a></li>
	</ul>
</div>
