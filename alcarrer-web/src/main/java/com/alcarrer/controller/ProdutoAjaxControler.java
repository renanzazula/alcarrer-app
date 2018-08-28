package com.alcarrer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alcarrer.model.Categoria;
import com.alcarrer.model.Dominio;
import com.alcarrer.model.ItensTipoMedida;
import com.alcarrer.model.Medida;
import com.alcarrer.model.Produto;
import com.alcarrer.model.ProdutoHasItensTipoMedida;
import com.alcarrer.model.SubCategoria;
import com.alcarrer.service.categoria.CategoriaService;
import com.alcarrer.service.dominio.DominioService;
import com.alcarrer.service.medida.MedidaService;
import com.alcarrer.service.produto.ProdutoService;

@Controller
public class ProdutoAjaxControler {

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private MedidaService medidaService;
	
	@Autowired
	private DominioService dominioService;
	
	@ResponseBody
	@RequestMapping(value = "/ajaxConsultaSubCategoriaByCategoria")
	public List<SubCategoria> consultaSubCategoriaByCategoria(@RequestBody Categoria categoria) {
		Categoria listSubCategorias = categoriaService.consultarByCodigo(categoria);
 		return listSubCategorias.getSubCategorias();
	}

  	@ResponseBody
	@RequestMapping(value = "/addicionarProduto")
    public Produto addicionarProduto(@RequestBody String barCodep) {
  		Produto barCode = new Produto();
  		barCode.setBarCode(barCodep);
  		Produto produtoDB = produtoService.consultarByBarCode(barCode); 
  	 
  		return  produtoDB;
	}
  	
  	@ResponseBody
	@RequestMapping(value = "/ajaxConsultarItensMedidaByCategoria")
    public List<Medida> ajaxConsultarItensMedidaByCategoria(@RequestBody Produto produto) {
  		return  medidaService.consultarByCategoriaSubCategoriaMarca(produto);
	}
  	
  	@ResponseBody
	@RequestMapping(value = "/ajaxConsultarItensMedidaByProdutoCodigo")
  	public List<ProdutoHasItensTipoMedida> ajaxConsultarItensMedidaByProdutoCodigo(@RequestBody Produto produto){
  		Produto produtoDB = produtoService.consultarByCodigo(produto);
  		return produtoDB.getProdutoHasItensTipoMedida();
  	}
  	
  	@ResponseBody
	@RequestMapping(value = "/ajaxConsultarItensMedidaByMedidaCodigo")
  	public List<ItensTipoMedida> ajaxConsultarItensMedidaByMedidaCodigo(@RequestBody Medida medida){
  		Medida medidas = medidaService.consultarByCodigo(medida);
  		return medidas.getItensTipoMedida();
  	}
  	
  	@ResponseBody
	@RequestMapping(value = "/ajaxObterDominios")
  	public List<Dominio> ajaxObterDominios(){
  		 return dominioService.consultar();
  	}
}
