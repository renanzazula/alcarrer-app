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

import com.alcarrer.controller.validator.FornecedorValidator;
import com.alcarrer.model.BreadCrumb;
import com.alcarrer.model.Fornecedor;
import com.alcarrer.service.fornecedor.FornecedorService;
import com.alcarrer.util.Util;

@Controller
public class FornecedorController {

	private static final String VIEW = "fornecedor";

	private static final String VIEW_COLSULTA = "consultarFornecedor";

	@Autowired
	private MessageSource message;

	@Autowired
	private FornecedorService fornecedorService;

	@Autowired
	private FornecedorValidator fornecedorValidator;

	// Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(fornecedorValidator);
	}

	@RequestMapping(value = "/abrirFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrir(ModelMap model) {
		model.addAttribute("fornecedorForm", new Fornecedor());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/incluirFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String incluir(@ModelAttribute("fornecedorForm") @Validated Fornecedor fornecedor, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		fornecedorService.incluir(fornecedor);
		model.addAttribute("list", fornecedorService.consultar());
		model.addAttribute("fornecedorForm", new Fornecedor());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/abrirAlterarFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterar(@ModelAttribute("fornecedorForm") Fornecedor fornecedor, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		Fornecedor retorno = fornecedorService.consultarByCodigo(fornecedor);
		model.addAttribute("fornecedorForm", retorno);
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/consultarFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarFornecedor(@ModelAttribute("fornecedorForm") Fornecedor Fornecedor, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		model.addAttribute("list", fornecedorService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/alterarFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String alterar(@ModelAttribute("fornecedorForm") @Validated Fornecedor fornecedor, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		fornecedorService.alterar(fornecedor);
		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
		model.addAttribute("list", fornecedorService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/excluirFornecedor", method = { RequestMethod.GET, RequestMethod.POST })
	public String excluir(@ModelAttribute("fornecedorForm") @Validated Fornecedor fornecedor, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		fornecedorService.excluir(fornecedor);
		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
		model.addAttribute("list", fornecedorService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.fornecedor");
		return Util.breadCrumbList(message, msg);
	}
}
