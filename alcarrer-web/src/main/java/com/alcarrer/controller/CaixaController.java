package com.alcarrer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alcarrer.enums.StatusCaixaEnum;
import com.alcarrer.model.BreadCrumb;
import com.alcarrer.model.Caixa;
import com.alcarrer.service.caixa.CaixaService;
import com.alcarrer.util.Util;

@Controller
public class CaixaController {

	 
	private static final String VIEW = "caixa";
	private static final String ERROR = "erro";
	private static final String ABRIR_CAIXA_VIEW = "abrirCaixa";
	private static final String FECHAR_CAIXA_VIEW = "fecharCaixa";

	@Autowired
	private MessageSource message;
	
	@Autowired
	private CaixaService caixaService;
	
	/**
	 * Metodo caixa abre a tela abrir caixa
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/caixa", method = { RequestMethod.GET, RequestMethod.POST })
	public String carregarCaixa(@ModelAttribute("caixaForm") @Validated Caixa caixa, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		
		// buscar se possui caixa aberto
		caixa = caixaService.buscarUltimoCaixa();
		if (caixa.getStatus().toString().equalsIgnoreCase("F")) {
			
			model.addAttribute("caixaForm", caixa);
			model.addAttribute("breadCrumbItens", breadCrumbList("F"));
			return VIEW;

		} else if (caixa.getStatus().equals("A")) {
			model.addAttribute("caixaForm", caixa);
			model.addAttribute("breadCrumbItens", breadCrumbList("A"));
			return VIEW;

		} else {
			// Envia para pagina de erro.
			return ERROR;
		}
	}

	/**
	 * Metodo caixa abre a tela abrir caixa
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carrergarAbrirCaixa", method = { RequestMethod.GET, RequestMethod.POST })
	public String carrergarAbrirCaixa(@ModelAttribute("caixaForm") @Validated Caixa caixa, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		if (caixa.getStatus() == null || caixa.getStatus().toString().equalsIgnoreCase("F")) {
			caixa.setCodigo(caixaService.gerarCodigoCaixa());
			caixa.setDataAbertura(new java.util.Date());
			caixa.setHoraAbertura(new java.util.Date());
			caixa.setValorInicial(new Double(0));
			caixa.setValorFinal(new Double(0));
			caixa.setTotal(new Double(0));
			caixa.setStatus(StatusCaixaEnum.F.toString());
			model.addAttribute("caixaForm", caixa);
			model.addAttribute("breadCrumbItens", breadCrumbList("F"));

			return ABRIR_CAIXA_VIEW;
		} else {
			// Envia para pagina de erro
			return ERROR;
		}
	}

	// Metodo responsavel por abrir o caixa
	@RequestMapping(value = "/abrirCaixa", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirCaixa(@ModelAttribute("caixaForm") @Validated Caixa caixa, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		Caixa caixaAberto = caixaService.abrirCaixa(caixa);
		model.addAttribute("caixaForm", caixaAberto);
		
		/**
		 * BreadCrumbItens
		 */
		model.addAttribute("breadCrumbItens", breadCrumbList("A"));
		return VIEW;
	}

	/**
	 * Metodo Fechar Caixa Realizar update no caixa aberto
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/fecharCaixa", method = { RequestMethod.GET, RequestMethod.POST })
	public String fecharCaixa(@ModelAttribute("caixaForm") @Validated Caixa caixa, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {

		if (caixa.getValorInicial() == null) {
			caixa.setValorInicial(new Double(0));
		}
 		if (caixa.getValorFinal() == null) {
			caixa.setValorFinal(new Double(0));
		}
 		caixa.setStatus(StatusCaixaEnum.F.toString());
		caixa.setTotal(Util.somaValores(caixa.getValorInicial(), caixa.getValorFinal()));
  		caixaService.fecharCaixa(caixa);
 		model.addAttribute("breadCrumbItens", breadCrumbList("F"));
 		return VIEW;
	}

	/**
	 * Metodo caixa abre a tela abrir caixa
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carregarFecharCaixa", method = { RequestMethod.GET, RequestMethod.POST })
	public String carrergarFecharCaixa(@ModelAttribute("caixaForm") @Validated Caixa caixa, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {
		
		// carregar dados do ultima caixa aberto. buscar banco
		caixa = caixaService.buscarUltimoCaixa();
		caixa.setTotal(Util.somaValores(caixa.getValorInicial(), caixa.getValorFinal()));
		model.addAttribute("caixaForm", caixa);
		model.addAttribute("breadCrumbItens", breadCrumbList("F"));
		return FECHAR_CAIXA_VIEW;
	}

	/**
	 * Metodo devolvo lista breadCrumb
	 * 
	 * @param statusCaixa
	 * @return
	 */
	private List<BreadCrumb> breadCrumbList(String statusCaixa) {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.caixa");

		if (statusCaixa.equalsIgnoreCase("F")) {
			msg.add("menu.caixa.abrir");
		} else if (statusCaixa.equalsIgnoreCase("A")) {
			msg.add("menu.caixa.fechar");
		}
		return Util.breadCrumbList(message, msg);
	}

}
