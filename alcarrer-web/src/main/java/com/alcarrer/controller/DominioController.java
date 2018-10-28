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

import com.alcarrer.controller.validator.DominioValidator;
import com.alcarrer.model.BreadCrumb;
import com.alcarrer.model.Dominio;
import com.alcarrer.service.dominio.DominioService;
import com.alcarrer.util.Util;

@Controller
public class DominioController {

	private static final String VIEW = "dominio";
	private static final String VIEW_COLSULTA = "consultarDominio";

	@Autowired
	private DominioValidator dominioValidator;

	@Autowired
	private MessageSource message;

	@Autowired
	private DominioService dominioService;

	// Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(dominioValidator);
	}

	@RequestMapping(value = "/abrirDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrir(ModelMap model) {
		model.addAttribute("dominioForm", new Dominio());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/abrirAlterarDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterar(@ModelAttribute("dominioForm") Dominio dominio, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		Dominio retorno = dominioService.consultarByCodigo(dominio);
		model.addAttribute("dominioForm", retorno);
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/alterarDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String altearar(@ModelAttribute("dominioForm") @Validated Dominio dominio, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		dominioService.alterar(dominio);
		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
		model.addAttribute("list", dominioService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	@RequestMapping(value = "/incluirDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String incluir(@ModelAttribute("dominioForm") @Validated Dominio dominio, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		dominioService.incluir(dominio);
		model.addAttribute("mensagem", message.getMessage("global.inclusao", null, Locale.US));
		model.addAttribute("list", dominioService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	@RequestMapping(value = "/consultarDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarDominio(@ModelAttribute("dominioForm") Dominio dominio, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		model.addAttribute("list", dominioService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/excluirDominio", method = { RequestMethod.GET, RequestMethod.POST })
	public String excluir(@ModelAttribute("dominioForm") @Validated Dominio dominio, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		dominioService.excluir(dominio);
		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
		model.addAttribute("list", dominioService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.dominio");
		return Util.breadCrumbList(message, msg);
	}

}
