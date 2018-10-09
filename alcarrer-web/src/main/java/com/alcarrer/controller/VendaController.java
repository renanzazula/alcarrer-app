
package com.alcarrer.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alcarrer.controller.validator.VendaValidator;
import com.alcarrer.model.BreadCrumb;
import com.alcarrer.model.Cliente;
import com.alcarrer.model.FormasDePagamento;
import com.alcarrer.model.Produto;
import com.alcarrer.model.Venda;
import com.alcarrer.service.FormasDePagamento.FormasDePagamentoService;
import com.alcarrer.service.venda.VendaService;
import com.alcarrer.util.ObjectConversor;
import com.alcarrer.util.Util;

@Controller
public class VendaController {

	private static final String VIEW = "venda";
	private static final String FINALIZAR_VENDA_VIEW = "finalizarVenda";
	private static final String VIEW_COLSULTA = "consultarVendas";

	@Autowired
	private VendaValidator vendaValidator;

	@Autowired
	private MessageSource message;

	@Autowired
	private FormasDePagamentoService formasDePagamentoService;
	
	@Autowired
	private VendaService vendaService;
	
	// Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(',');
		df.setGroupingUsed(false);
		df.setDecimalFormatSymbols(dfs);
		df.setMaximumFractionDigits(32);
		df.setMaximumIntegerDigits(32);
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, df, true));
		binder.registerCustomEditor(Long.class, new ObjectConversor(Long.class));
		binder.registerCustomEditor(FormasDePagamento.class, new ObjectConversor(FormasDePagamento.class));
		binder.registerCustomEditor(Produto.class, new ObjectConversor(Produto.class));
		binder.registerCustomEditor(ArrayList.class, new ObjectConversor(ArrayList.class));
		binder.registerCustomEditor(Venda.class, new ObjectConversor(Venda.class));
		binder.registerCustomEditor(Cliente.class, new ObjectConversor(Cliente.class));
		binder.registerCustomEditor(Integer.class, new ObjectConversor(Integer.class));
		binder.setValidator(vendaValidator);
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/abrirVenda", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrir(ModelMap model) {
		model.addAttribute("vendaForm", newVenda());
		model.addAttribute("breadCrumbItens", breadCrumbList(VIEW));
		return VIEW;
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cancelarVenda", method = { RequestMethod.GET, RequestMethod.POST })
	public String cancelar(ModelMap model) {
		model.addAttribute("vendaForm", newVenda());
		model.addAttribute("breadCrumbItens", breadCrumbList(VIEW));
		return VIEW;
	}
	
	/**
	 * TODO: Finalizar Venda (incluir venda e gerar recibo menssagem)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/finalizarVenda", method = { RequestMethod.GET, RequestMethod.POST })
	public String finalizarVenda(@ModelAttribute("vendaForm") Venda venda, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		vendaService.incluir(venda);
		model.addAttribute("vendaForm", venda);
		model.addAttribute("breadCrumbItens", breadCrumbList(FINALIZAR_VENDA_VIEW));
		return VIEW_COLSULTA;
	}
	
	@RequestMapping(value = "/consultarVendas", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarVenda(@ModelAttribute("vendaForm") Venda vendaForm, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) { 		 
		model.addAttribute("list", vendaService.consultar());		
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	
	/**
	 * 
	 * @param stepVenda
	 * @return
	 */
	public List<BreadCrumb> breadCrumbList(String stepVenda) {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.venda");
		if (stepVenda.equalsIgnoreCase(VIEW)) {
			msg.add("menu.venda.itensVenda");
		} else {
			msg.add("menu.venda.itensVenda");
			msg.add("menu.venda.finalizar");
		}
		return Util.breadCrumbList(message, msg);
	}

	/**
	 * Metodo popula dados para venda
	 * 
	 * @return
	 */
	private Venda newVenda() {
		Venda venda = new Venda();
		venda.setCodigo(numeroVenda());
//		venda.setData(null); 
//		venda.setHora(null);
		venda.setFormasDePagamento(carregarFormaDePagamento()); 
		return venda;
	}

	/**
	 * 
	 * @return
	 */
	private Integer numeroVenda() {
		return 1;
	}

	/**
	 * Preenche combo Forma de Pagamento
	 * @retur
	 */
	public List<FormasDePagamento> carregarFormaDePagamento() {
		return formasDePagamentoService.consultar();
	}
		
	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.venda");
		return Util.breadCrumbList(message, msg);
	}
}
