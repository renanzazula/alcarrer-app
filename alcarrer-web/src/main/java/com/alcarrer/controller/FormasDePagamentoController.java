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
import com.alcarrer.model.FormasDePagamento;
import com.alcarrer.service.FormasDePagamento.FormasDePagamentoService;
import com.alcarrer.util.Util;

@Controller
public class FormasDePagamentoController {

	private static final String VIEW = "formasDePagamento";
	private static final String VIEW_COLSULTA = "consultarFormasDePagamento";
	
	
//	@Autowired
//	MarcaValidator marcaValidator;
	
	@Autowired
    private MessageSource message;
	
	@Autowired
	FormasDePagamentoService formasDePagamentoService;
		
	//Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
//		binder.setValidator(marcaValidator);
	}
	
	@RequestMapping(value = "/abrirFormasDePagamento", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrir(ModelMap model) {
		model.addAttribute("formasDePagamentoForm", new FormasDePagamento());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}
	
	@RequestMapping(value = "/abrirAlterarFormasDePagamento", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterar(@ModelAttribute("formasDePagamentoForm") FormasDePagamento formaDePagamento,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		FormasDePagamento retorno = formasDePagamentoService.consultarByCodigo(formaDePagamento);
		model.addAttribute("formasDePagamentoForm", retorno);
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}
	
	@RequestMapping(value = "/alterarFormasDePagamento", method = { RequestMethod.GET, RequestMethod.POST })
	public String altearar(@ModelAttribute("formasDePagamentoForm") @Validated FormasDePagamento formaDePagamento,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		formasDePagamentoService.alterar(formaDePagamento);
		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
		model.addAttribute("list", formasDePagamentoService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}
	
	@RequestMapping(value = "/incluirFormasDePagamento", method = { RequestMethod.GET, RequestMethod.POST })
	public String incluir(@ModelAttribute("formasDePagamentoForm") @Validated FormasDePagamento formaDePagamento,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		formasDePagamentoService.incluir(formaDePagamento);
		model.addAttribute("mensagem", message.getMessage("global.inclusao", null, Locale.US));
		model.addAttribute("list", formasDePagamentoService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}
	
	@RequestMapping(value = "/consultarFormasDePagamento", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarFormasDePagamento(@ModelAttribute("formasDePagamentoForm") FormasDePagamento formaDePagamento,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		model.addAttribute("list", formasDePagamentoService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}
	
	@RequestMapping(value = "/excluirFormasDePagamento", method = { RequestMethod.GET, RequestMethod.POST })
	public String excluir(@ModelAttribute("formasDePagamentoForm") @Validated FormasDePagamento formasDePagamento,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
		formasDePagamentoService.excluir(formasDePagamento);
		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
		model.addAttribute("list", formasDePagamentoService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}
	
	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.formasDePagamento");
		return Util.breadCrumbList(message, msg);
	}
	
}
