package com.alcarrer.service.venda;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcarrer.entity.ProdutoHasItensTipoMedidaEntity;
import com.alcarrer.entity.VendaEntity;
import com.alcarrer.entity.VendaHasItemProdutoEntity;
import com.alcarrer.function.JpaFunctions;
import com.alcarrer.model.Venda;
import com.alcarrer.model.VendaHasItemProduto;
import com.alcarrer.repository.CaixaRepository;
import com.alcarrer.repository.ClienteRepository;
import com.alcarrer.repository.FormaDePagamentoRepository;
import com.alcarrer.repository.ProdutoHasItensTipoMedidaRepository;
import com.alcarrer.repository.VendaRepository;

@Service
public class VendaServiceImpl implements VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private FormaDePagamentoRepository formaDePagamentoRepository;

	@Autowired
	private CaixaRepository caixaRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ProdutoHasItensTipoMedidaRepository produtoHasItensTipoMedidaRepository;

	@Override
	@Transactional
	public Venda incluir(Venda venda) {
		
		Integer quantidadeTotalEstoque = 0;		
		VendaEntity vendaDB = new VendaEntity();

		// itens medida
		Set<VendaHasItemProdutoEntity> vendaHasItemProdutoSet = new HashSet<>();
		for (VendaHasItemProduto itemVenda :venda.getVendaHasItemProduto()) {
			VendaHasItemProdutoEntity vendaHasItemProduto = new VendaHasItemProdutoEntity();
 
			Integer codigo = getProdutoHasItensTipoMedida(
					itemVenda.getProdutoHasItensTipoMedida().getItensTipoMedida().getCodigo(),
					itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());
			
			ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida = produtoHasItensTipoMedidaRepository.getOne(codigo);
			quantidadeTotalEstoque = (quantidadeTotalEstoque + itemVenda.getProdutoHasItensTipoMedida().getQuantidade()); 
			vendaHasItemProduto.setQuantidade(itemVenda.getProdutoHasItensTipoMedida().getQuantidade());
			vendaHasItemProduto.setValorUnitario(itemVenda.getProdutoHasItensTipoMedida().getValorUnitario());
			vendaHasItemProduto.setProdutoHasItensTipoMedida(produtoHasItensTipoMedida);
			vendaHasItemProduto.setVenda(vendaDB);
			vendaHasItemProdutoSet.add(vendaHasItemProduto);
		}
		
		vendaDB.setVendaHasItemProduto(vendaHasItemProdutoSet);
		vendaDB.setQuantidade(quantidadeTotalEstoque);
		vendaDB.setSubTotal(venda.getSubTotal());
		vendaDB.setValorPendente(venda.getValorPendente());
		vendaDB.setValorPago(venda.getValorPago());
		vendaDB.setDesconto(venda.getDesconto());
		vendaDB.setTotalApagar(venda.getTotalApagar());
		vendaDB.setTroco(venda.getTroco());
		vendaDB.setPagamento(venda.getPagamento());
		vendaDB.setValorTotal(venda.getValorTotal());
		vendaDB.setFormaDePagamento(formaDePagamentoRepository.getOne(venda.getFormaDePagamento().getCodigo()));
		vendaDB.setCliente(clienteRepository.getOne(1));
		vendaDB.setCaixa(caixaRepository.getOne(1));
		Venda vResult = JpaFunctions.vendaToVendaEntity.apply(vendaRepository.saveAndFlush(vendaDB));
		 
		/**
		 * Efetuar baixa no estoque...		
		 */
		baixaNoEstoque(venda);
		
		return vResult;
	}

	/**
	 * Remove quantidade tabela produto_has_itens_tipo_medida 
	 * 
	 * produto_has_itens_tipo_medida
	 * 
	 * @param produto_has_itens_tipo_medida_codigo
	 */
	@Transactional
	private void baixaNoEstoque(Venda venda) {
		venda.getVendaHasItemProduto().forEach(itemVenda -> {
 			Integer codigo = getProdutoHasItensTipoMedida(itemVenda.getProdutoHasItensTipoMedida().getItensTipoMedida().getCodigo(), itemVenda.getProdutoHasItensTipoMedida().getProduto().getCodigo());
			ProdutoHasItensTipoMedidaEntity produtoHasItensTipoMedida = produtoHasItensTipoMedidaRepository.getOne(codigo);
			produtoHasItensTipoMedida.setQuantidade(produtoHasItensTipoMedida.getQuantidade() - itemVenda.getProdutoHasItensTipoMedida().getQuantidade());
			produtoHasItensTipoMedidaRepository.saveAndFlush(produtoHasItensTipoMedida);
		});
	}
	
	@Transactional(readOnly = true)
	private Integer getProdutoHasItensTipoMedida(Integer itemTipoMedidaCodigo, Integer produtoCodigo) {
		return produtoHasItensTipoMedidaRepository
				.findByItensTipoMedidaCodigoAndProdutoCodigo(itemTipoMedidaCodigo, produtoCodigo).getCodigo();
	}

	@Override
	public Venda alterar(Venda venda) {
		VendaEntity vendaDB = vendaRepository.findOne(venda.getCodigo());

		// itens mideida

		// status; Definicao o que sera status...

		vendaDB.setQuantidade(venda.getQuantidade());
		vendaDB.setSubTotal(venda.getSubTotal());
		vendaDB.setValorPendente(venda.getValorPendente());
		vendaDB.setValorPago(venda.getValorPago());
		vendaDB.setDesconto(venda.getDesconto());
		vendaDB.setTotalApagar(venda.getTotalApagar());
		vendaDB.setTroco(venda.getTroco());
		vendaDB.setPagamento(venda.getPagamento());
		vendaDB.setValorTotal(venda.getValorTotal());
		vendaDB.setFormaDePagamento(formaDePagamentoRepository.getOne(venda.getFormaDePagamento().getCodigo()));
		vendaDB.setCliente(clienteRepository.getOne(venda.getCliente().getCodigo()));
		vendaDB.setCaixa(caixaRepository.getOne(venda.getCaixa().getCodigo()));
		return JpaFunctions.vendaToVendaEntity.apply(vendaRepository.saveAndFlush(vendaDB));

	}

	@Override
	public void excluir(Venda venda) {
		VendaEntity vendaDB = vendaRepository.findOne(venda.getCodigo());
		vendaRepository.delete(vendaDB);
	}

	@Override
	public Venda consultarByCodigo(Venda venda) {
		return JpaFunctions.vendaToVendaEntity.apply(vendaRepository.getOne(venda.getCodigo()));
	}

	@Override
	public List<Venda> consultar() {
		return vendaRepository.findAll().stream().map(JpaFunctions.vendaToVendaEntity).collect(Collectors.toList());
	}

	@Override
	public long gerarCodigoVenda() {
		return 0;
	}
}
