package com.alcarrer.function.jpa;

import java.util.function.Function;
import java.util.stream.Collectors;

import com.alcarrer.entity.ProdutoEntity;
import com.alcarrer.entity.ProdutoHasItensTipoMedidaEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Produto;
import com.alcarrer.model.ProdutoHasItensTipoMedida;

public class ProdutoHasItensTipoMedidaDTOtoprodutoHasItensTipoMedidaFunction
		implements Function<ProdutoHasItensTipoMedidaEntity, ProdutoHasItensTipoMedida> {

	@Override
	public ProdutoHasItensTipoMedida apply(ProdutoHasItensTipoMedidaEntity input) {
		ProdutoHasItensTipoMedida output = new ProdutoHasItensTipoMedida();
		output.setCodigo(input.getCodigo());
		if( input.getDominios() != null) {
			output.setDominios( input.getDominios().stream().map(JpaFunctions.dominioToDominioEntity).collect(Collectors.toList()));		
		}
		if (input.getItensTipoMedida() != null) {
			output.setItensTipoMedida(JpaFunctions.itensTipoMedidaToItensTipoMedidaEntity.apply(input.getItensTipoMedida()));
		}
	
		output.setProduto(produtoEntityToProduto(input.getProduto()));		
		output.setQuantidade(input.getQuantidade());
		output.setValorUnitario(input.getValorUnitario());
		return output;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private Produto produtoEntityToProduto(ProdutoEntity input){
		Produto output = new Produto();
		output.setCodigo(input.getCodigo());
		output.setBarCode(input.getBarCode());
		output.setNome(input.getNome());
		output.setStatus(input.getStatus());
		output.setDescricao(input.getDescricao());
		output.setPreco(input.getPreco());
		output.setPrecoVenda(input.getPrecoVenda());
		output.setPrecoCusto(input.getPrecoCusto());
		output.setPrecoOferta(input.getPrecoOferta());
		output.setDesconto(input.getDesconto());
		output.setPeso(input.getPeso());
		output.setPorcentagem(input.getPorcentagem());
		output.setPorcentagemDesconto(input.getPorcentagemDesconto());
		output.setDataHoraCadastro(input.getDataHoraCadastro());
		
		if(input.getFornecedor() != null) {
			output.setFornecedor(JpaFunctions.fornecedortoFornecedorEntity.apply(input.getFornecedor()));
		}

		if(input.getCategoria() !=  null) {
			output.setCategoria(JpaFunctions.categoriaToCategoriaEntity.apply(input.getCategoria()));
		}
		
		if(input.getSubCategoria() != null) {
			output.setSubCategoria(JpaFunctions.subCategoriaToSubCategoriaEntity.apply(input.getSubCategoria()));
		}
		
		if(input.getMedida() != null) {
			output.setMedida(JpaFunctions.medidaToMedidaEntity.apply(input.getMedida()));
		}
		
		if (input.getMarca() != null) {
			output.setMarca(JpaFunctions.marcaToMarcaEntity.apply(input.getMarca()));
		}
		return output;
	} 

}
