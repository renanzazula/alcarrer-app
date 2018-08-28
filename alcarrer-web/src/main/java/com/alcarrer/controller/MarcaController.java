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

import com.alcarrer.model.BreadCrumb;
import com.alcarrer.model.Marca;
import com.alcarrer.service.marca.MarcaService;
import com.alcarrer.util.Util;

@Controller
public class MarcaController {

	private static final String VIEW = "marca";
	private static final String VIEW_COLSULTA = "consultarMarca";

	@Autowired
	com.alcarrer.controller.validator.MarcaValidator marcaValidator;

	@Autowired
	private MessageSource message;

	@Autowired
	MarcaService marcaService;

	// Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(marcaValidator);
	}

	@RequestMapping(value = "/abrirMarca", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrir(ModelMap model) {
		model.addAttribute("marcaForm", new Marca());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/abrirAlterarMarca", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterar(@ModelAttribute("marcaForm") Marca marca, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		Marca retorno = marcaService.consultarByCodigo(marca);
		model.addAttribute("marcaForm", retorno);
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/alterarMarca", method = { RequestMethod.GET, RequestMethod.POST })
	public String altearar(@ModelAttribute("marcaForm") @Validated Marca marca, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		marcaService.alterar(marca);
		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
		model.addAttribute("list", marcaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/incluirMarca", method = { RequestMethod.GET, RequestMethod.POST })
	public String incluir(@ModelAttribute("marcaForm") @Validated Marca marca, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		marcaService.incluir(marca);
		model.addAttribute("mensagem", message.getMessage("global.inclusao", null, Locale.US));
		model.addAttribute("list", marcaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/consultarMarca", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarMarca(@ModelAttribute("marcaForm") Marca marca, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		model.addAttribute("list", marcaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/excluirMarca", method = { RequestMethod.GET, RequestMethod.POST })
	public String excluir(@ModelAttribute("marcaForm") @Validated Marca marca, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		marcaService.excluir(marca);
		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
		model.addAttribute("list", marcaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.marca");
		return Util.breadCrumbList(message, msg);
	}

}
