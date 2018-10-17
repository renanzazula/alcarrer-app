
package com.alcarrer.controller.venda;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
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

import com.alcarrer.model.Cliente;
import com.alcarrer.model.FormasDePagamento;
import com.alcarrer.model.Produto;
import com.alcarrer.model.Venda;
import com.alcarrer.model.VendaFiltro;
import com.alcarrer.util.ObjectConversor;

@Controller
public class VendaController extends BaseVendaController {

	// Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
		binder.registerCustomEditor(VendaFiltro.class, new ObjectConversor(VendaFiltro.class));
		// binder.setValidator(vendaValidator);
	}

	/**
	 * Metodo popula dados para venda
	 * 
	 * @return
	 */
	private Venda newVenda() {
		Venda venda = new Venda();
		venda.setFormasDePagamento(carregarFormaDePagamento());
		return venda;
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

	@RequestMapping(value = "/abrirAlterarVenda", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterarSubCategoria(Model model, Venda venda, final RedirectAttributes redirectAttributes) {
		Venda vendaForm = vendaService.consultarByCodigo(venda);
		vendaForm.setFormasDePagamento(carregarFormaDePagamento());
		model.addAttribute("vendaForm", vendaForm);
		model.addAttribute("breadCrumbItens", breadCrumbList(VIEW));
		return VIEW;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cancelarVenda", method = { RequestMethod.GET, RequestMethod.POST })
	public String cancelar(@ModelAttribute("vendaForm") Venda venda, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		vendaService.cancelar(venda);
		venda.setFormasDePagamento(carregarFormaDePagamento());
		model.addAttribute("vendaForm", new Venda());
		model.addAttribute("listStatusVenda", carregarStatusVenda());
		model.addAttribute("list", vendaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList(VIEW_COLSULTA));
		return VIEW_COLSULTA;
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
		
		model.addAttribute("vendafiltro", new VendaFiltro());
		model.addAttribute("listFormaDePagamento", carregarFormaDePagamento());
		model.addAttribute("listStatusVenda", carregarStatusVenda());
		model.addAttribute("list", vendaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList(VIEW_COLSULTA));
		
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/alterarVenda", method = { RequestMethod.GET, RequestMethod.POST })
	public String alterarVenda(@ModelAttribute("vendaForm") Venda venda, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		vendaService.incluir(venda);
		model.addAttribute("vendaForm", new Venda());
		model.addAttribute("list", vendaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList(FINALIZAR_VENDA_VIEW));
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/filtrarVendas", method = { RequestMethod.GET, RequestMethod.POST })
	public String filtrarVendas(@ModelAttribute("vendaFiltro") VendaFiltro vendaFiltro, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) {

		model.addAttribute("listStatusVenda", carregarStatusVenda());
		model.addAttribute("listFormaDePagamento", carregarFormaDePagamento());
		model.addAttribute("list", vendaService.filtrarVenda(vendaFiltroToVenda(vendaFiltro)));
		model.addAttribute("breadCrumbItens", breadCrumbList(VIEW));
		model.addAttribute("vendafiltro", new VendaFiltro());
		return VIEW_COLSULTA;
	}

}
