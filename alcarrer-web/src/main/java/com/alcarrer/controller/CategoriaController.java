package com.alcarrer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alcarrer.controller.validator.CategoriaValidator;
import com.alcarrer.model.BreadCrumb;
import com.alcarrer.model.Categoria;
import com.alcarrer.model.SubCategoria;
import com.alcarrer.service.categoria.CategoriaService;
import com.alcarrer.service.subCategoria.SubCategoriaService;
import com.alcarrer.util.Util;

@Controller
public class CategoriaController {

	private static final String VIEW = "categoria";
	private static final String VIEW_COLSULTA = "consultarCategoria";

	@Autowired
	private CategoriaValidator categoriaValidator;

	@Autowired
	private MessageSource message;

	@Autowired
	private SubCategoriaService subCategoriaService;

	@Autowired
	private CategoriaService categoriaService;

	// Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(categoriaValidator);
	}

	@RequestMapping(value = "/abrirCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrir(ModelMap model) {
		Categoria categoria = new Categoria();

		model.addAttribute("listSubCategoria", carregarSubCategorias());
		model.addAttribute("categoriaForm", categoria);
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/alterarCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String alterar(@ModelAttribute("categoriaForm") @Validated Categoria categoria, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		categoriaService.alterar(categoria);
		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
		model.addAttribute("categoriaList", categoriaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/excluirCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String excluir(@ModelAttribute("categoriaForm") Categoria categoria, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		categoriaService.excluir(categoria);
		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
		model.addAttribute("categoriaList", categoriaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/incluirCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String incluir(@ModelAttribute("categoriaForm") @Validated Categoria categoria, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		// Tratamento caso nao tenha SubCategoria.
		categoriaService.incluir(categoria);
		model.addAttribute("categoriaList", categoriaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/abrirAlterarCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterarCategoria(Model model, Categoria categoria, final RedirectAttributes redirectAttributes) {
		model.addAttribute("categoriaForm", categoriaService.consultarByCodigo(categoria));
		model.addAttribute("listSubCategoria", carregarSubCategorias());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/consultarCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarSubCategoria(@ModelAttribute("categoriaForm") @Validated Categoria categoria,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		model.addAttribute("categoriaList", categoriaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	/**
	 * Carrega Todas subCategorias
	 * 
	 * @param categoria
	 * @return
	 */
	public List<SubCategoria> carregarSubCategorias() {
		return subCategoriaService.consultar();
	}

	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.categoria");
		return Util.breadCrumbList(message, msg);
	}

}
