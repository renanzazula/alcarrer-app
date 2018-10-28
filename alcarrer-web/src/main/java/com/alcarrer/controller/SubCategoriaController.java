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

import com.alcarrer.controller.validator.SubCategoriaValidator;
import com.alcarrer.model.BreadCrumb;
import com.alcarrer.model.SubCategoria;
import com.alcarrer.service.subCategoria.SubCategoriaService;
import com.alcarrer.util.Util;

@Controller
public class SubCategoriaController {


	private static final String VIEW = "subCategoria";
	private static final String VIEW_COLSULTA = "consultarSubCategoria";

	@Autowired
	SubCategoriaValidator subCategoriaValidator;

	@Autowired
	SubCategoriaService subCategoriaService;

	@Autowired
	private MessageSource message;

	// Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(subCategoriaValidator);
	}

	@RequestMapping(value = "/abrirSubCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrir(ModelMap model) {
		model.addAttribute("subCategoriaForm", new SubCategoria());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/incluirSubCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String incluir(@ModelAttribute("subCategoriaForm") @Validated SubCategoria subCategoria,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		subCategoriaService.incluir(subCategoria);
		model.addAttribute("list", subCategoriaService.consultar());
		model.addAttribute("mensagem", message.getMessage("global.inclusao", null, Locale.US));
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	@RequestMapping(value = "/alterarSubCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String alterar(@ModelAttribute("subCategoriaForm") @Validated SubCategoria subCategoria,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		subCategoriaService.alterar(subCategoria);
		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
		model.addAttribute("list", subCategoriaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	@RequestMapping(value = "/consultarSubCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarSubCategoria(@ModelAttribute("subCategoriaForm") @Validated SubCategoria subCategoria,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		model.addAttribute("list", subCategoriaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/abrirAlterarSubCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterarSubCategoria(Model model, SubCategoria subCategoria,
			final RedirectAttributes redirectAttributes) {
		SubCategoria retorno = subCategoriaService.consultarByCodigo(subCategoria.getCodigo());
		model.addAttribute("subCategoriaForm", retorno);
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}
	
	@RequestMapping(value = "/excluirSubCategoria", method = { RequestMethod.GET, RequestMethod.POST })
	public String excluir(@ModelAttribute("subCategoriaForm") @Validated SubCategoria subCategoria,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {		
		subCategoriaService.excluir(subCategoria);
		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
		model.addAttribute("list", subCategoriaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.subcategoria");
		return Util.breadCrumbList(message, msg);
	}

}
