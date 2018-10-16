package com.alcarrer.function;

import com.alcarrer.function.jpa.CaixaToCaixaEntityFunction;
import com.alcarrer.function.jpa.CategoriatoCategoriaEntityFunction;
import com.alcarrer.function.jpa.ClienteToClienteEntityFunction;
import com.alcarrer.function.jpa.DominioToDominioEntityFunction;
import com.alcarrer.function.jpa.FormasDePagamentoToFormasDePagamentoEntityFunction;
import com.alcarrer.function.jpa.FornecedorToFornecedorEntityFunction;
import com.alcarrer.function.jpa.ItensTipoMedidaToItensTipoMedidaEntityFunction;
import com.alcarrer.function.jpa.MarcaToMarcaEntityFunction;
import com.alcarrer.function.jpa.MedidaToMedidaEntityFunction;
import com.alcarrer.function.jpa.ProdutoToProdutoEntityFunction;
import com.alcarrer.function.jpa.ProdutoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntityFunction;
import com.alcarrer.function.jpa.SubCategoriaToSubCategoriaEntityFunction;
import com.alcarrer.function.jpa.VendaToVendaEntityFunction;
import com.alcarrer.function.jpa.VendaHasItemProdutoToVendaHasItemProdutoEntityFunction;

public class JpaFunctions {

	public JpaFunctions() {

	}

	public static final CategoriatoCategoriaEntityFunction categoriatoCategoriaEntity = new CategoriatoCategoriaEntityFunction();
	public static final SubCategoriaToSubCategoriaEntityFunction subCategoriaToSubCategoriaEntity = new SubCategoriaToSubCategoriaEntityFunction();
	public static final FornecedorToFornecedorEntityFunction fornecedortoFornecedorEntity = new FornecedorToFornecedorEntityFunction();
	public static final MarcaToMarcaEntityFunction marcaToMarcaEntity = new MarcaToMarcaEntityFunction();
	public static final MedidaToMedidaEntityFunction medidaToMedidaEntity = new MedidaToMedidaEntityFunction();
	public static final ItensTipoMedidaToItensTipoMedidaEntityFunction itensTipoMedidaToItensTipoMedidaEntity = new ItensTipoMedidaToItensTipoMedidaEntityFunction();
	public static final ProdutoToProdutoEntityFunction produtoToProdutoEntity = new ProdutoToProdutoEntityFunction();
	public static final FormasDePagamentoToFormasDePagamentoEntityFunction formasDePagamentoToFormasDePagamentoEntity = new FormasDePagamentoToFormasDePagamentoEntityFunction();
	public static final ProdutoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntityFunction produtoHasItensTipoMedidaTOProdutoHasItensTipoMedidaEntity = new ProdutoHasItensTipoMedidaToProdutoHasItensTipoMedidaEntityFunction();
	public static final DominioToDominioEntityFunction dominioToDominioEntity = new DominioToDominioEntityFunction();
	public static final VendaToVendaEntityFunction vendaToVendaEntity = new VendaToVendaEntityFunction();
	public static final ClienteToClienteEntityFunction clienteTOClienteEntity = new ClienteToClienteEntityFunction();
	public static final CaixaToCaixaEntityFunction caixaToCaixaEntity = new CaixaToCaixaEntityFunction();
	public static final VendaHasItemProdutoToVendaHasItemProdutoEntityFunction vendaHasItemProdutoToVendaHasItemEntity = new VendaHasItemProdutoToVendaHasItemProdutoEntityFunction();

}
