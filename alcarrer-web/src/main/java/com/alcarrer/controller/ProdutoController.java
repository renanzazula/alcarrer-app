package com.alcarrer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alcarrer.controller.validator.ProdutoValidator;
import com.alcarrer.model.BreadCrumb;
import com.alcarrer.model.Categoria;
import com.alcarrer.model.Dominio;
import com.alcarrer.model.Fornecedor;
import com.alcarrer.model.Marca;
import com.alcarrer.model.Medida;
import com.alcarrer.model.Produto;
import com.alcarrer.model.SubCategoria;
import com.alcarrer.service.categoria.CategoriaService;
import com.alcarrer.service.dominio.DominioService;
import com.alcarrer.service.fornecedor.FornecedorService;
import com.alcarrer.service.marca.MarcaService;
import com.alcarrer.service.medida.MedidaService;
import com.alcarrer.service.produto.ProdutoService;
import com.alcarrer.util.ObjectConversor;
import com.alcarrer.util.Util;

@Controller
public class ProdutoController {

	private static final String VIEW = "produto";
	private static final String VIEW_COLSULTA = "consultarProduto";

	@Autowired
	private ProdutoValidator produtoValidator;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private MarcaService marcaService;

	@Autowired
	private MedidaService medidaService;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private DominioService dominioService;

	@Autowired
	private MessageSource message;

	// Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

//		DecimalFormat df = new DecimalFormat();
//		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
//		dfs.setDecimalSeparator(',');
//		df.setGroupingUsed(false);
//		df.setDecimalFormatSymbols(dfs);
//		df.setMaximumFractionDigits(32);
//		df.setMaximumIntegerDigits(32);
//		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, df, true));
		binder.registerCustomEditor(Fornecedor.class, new ObjectConversor(Fornecedor.class));
		binder.registerCustomEditor(Marca.class, new ObjectConversor(Marca.class));
		binder.registerCustomEditor(Categoria.class, new ObjectConversor(Categoria.class));
		binder.registerCustomEditor(SubCategoria.class, new ObjectConversor(SubCategoria.class));
		binder.registerCustomEditor(Medida.class, new ObjectConversor(Medida.class));
		binder.registerCustomEditor(Dominio.class, new ObjectConversor(Dominio.class));
		binder.registerCustomEditor(ArrayList.class, new ObjectConversor(ArrayList.class));
		binder.setValidator(produtoValidator);
	}

	@RequestMapping(value = "/abrirProduto", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrir(@ModelAttribute("produtoForm") Produto produtoForm, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		model.addAttribute("produtoForm", carregaProduto(new Produto()));
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/incluirProduto", method = { RequestMethod.GET, RequestMethod.POST })
	public String incluir(@ModelAttribute("produtoForm") @Validated Produto produtoForm, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		// if (result.hasErrors()) {
		// produtoForm = carregaProduto(produtoForm);
		// model.addAttribute("produtoForm", produtoForm);
		// return VIEW;
		// }
		//
		// // Caso possua o codigo indicado ou id invalido.
		// if(produtoService.validarCodigoProduto(produtoForm) == true) {
		// produtoForm = carregaProduto(produtoForm);
		// model.addAttribute("produtoForm", produtoForm);
		// model.addAttribute("codigoInvalido" ,
		// message.getMessage("NotEmpty.produtoForm.codigo.invalido", null, Locale.US));
		// return VIEW;
		// }

		produtoService.incluir(produtoForm);
		model.addAttribute("mensagem", message.getMessage("global.inclusao", null, Locale.US));
		model.addAttribute("list", produtoService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/alterarProduto", method = { RequestMethod.GET, RequestMethod.POST })
	public String alterar(@ModelAttribute("produtoForm") @Validated Produto produtoForm, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		// model.addAttribute("alterar", "true");
		// if (result.hasErrors()) {
		// produtoForm = carregaProduto(produtoForm);
		// model.addAttribute("produtoForm", produtoForm);
		// return VIEW;
		// }

		produtoService.alterar(produtoForm);
		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
		model.addAttribute("list", produtoService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/excluirProduto", method = { RequestMethod.GET, RequestMethod.POST })
	public String excluir(@ModelAttribute("produtoForm") Produto produtoForm, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		produtoService.excluir(produtoForm);
		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
		model.addAttribute("list", produtoService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/abrirAlterarProduto", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterar(@ModelAttribute("produtoForm") Produto produtoForm, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		model.addAttribute("alterar", "true");
		if (result.hasErrors()) {
			produtoForm = carregaProduto(produtoForm);
			model.addAttribute("produtoForm", produtoForm);
			return VIEW;
		}

		Produto produto = produtoService.consultarByCodigo(produtoForm);
		produto = carregaProduto(produto);

		model.addAttribute("produtoForm", produto);
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/consultarProduto", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarProduto(@ModelAttribute("produtoForm") Produto produtoForm, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		model.addAttribute("list", produtoService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	private Produto carregaProduto(Produto produto) {
		produto.setFornecedores(fornecedorService.consultar());
		produto.setMarcas(marcaService.consultar());
		produto.setCategorias(categoriaService.consultar());

		List<Medida> listMedida = medidaService.consultar().stream()
				.filter(medida -> !medida.getItensTipoMedida().isEmpty()).collect(Collectors.toList());

		produto.setMedidas(listMedida);
		produto.setDominios(dominioService.consultar());

		if (produto.getProdutoHasItensTipoMedida() != null) {
			produto.getProdutoHasItensTipoMedida().forEach(produtoHasItensTipoMedida -> {
				HashMap<Integer, Dominio> dominiosMap = new HashMap<>();
				if (produtoHasItensTipoMedida.getDominios() != null) {
					dominioService.consultar().forEach(action -> {
						if (!dominiosMap.containsKey(action.getCodigo())) {
							dominiosMap.put(action.getCodigo(), action);
						}
					});
					produtoHasItensTipoMedida.getDominios().forEach(dominio -> {
						if (dominiosMap.containsKey(dominio.getCodigo())) {
							dominiosMap.get(dominio.getCodigo()).setChecked(true);
						}
					});
					produtoHasItensTipoMedida.getDominios().clear();
					produtoHasItensTipoMedida.getDominios().addAll(dominiosMap.values());
				}
			});
		}

		if (produto.getCategoria() != null) {
			produto.setSubCategorias(categoriaService.consultarByCodigo(produto.getCategoria()).getSubCategorias());
		}

		return produto;
	}

	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.produto");
		return Util.breadCrumbList(message, msg);
	}

}
