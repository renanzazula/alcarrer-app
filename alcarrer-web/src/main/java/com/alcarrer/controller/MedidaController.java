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
import com.alcarrer.model.Categoria;
import com.alcarrer.model.Marca;
import com.alcarrer.model.Medida;
import com.alcarrer.model.SubCategoria;
import com.alcarrer.service.categoria.CategoriaService;
import com.alcarrer.service.marca.MarcaService;
import com.alcarrer.service.medida.MedidaService;
import com.alcarrer.util.ObjectConversor;
import com.alcarrer.util.Util;

@Controller
public class MedidaController {

	private static final String VIEW = "medida";
	private static final String VIEW_COLSULTA = "consultarMedida";

	// @Autowired
	// MedidaValidator medidaValidator;

	@Autowired
	private MessageSource message;

	@Autowired
	private MedidaService medidaService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private MarcaService marcaService;

	// Set a form validator
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Marca.class, new ObjectConversor(Marca.class));
		binder.registerCustomEditor(Categoria.class, new ObjectConversor(Categoria.class));
		binder.registerCustomEditor(SubCategoria.class, new ObjectConversor(SubCategoria.class));
		binder.registerCustomEditor(Medida.class, new ObjectConversor(Medida.class));
		binder.registerCustomEditor(ArrayList.class, new ObjectConversor(ArrayList.class));
		// binder.setValidator(medidaValidator);
	}

	@RequestMapping(value = "/abrirMedida", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrir(ModelMap model) {
		model.addAttribute("medidaForm", carregaMedida(new Medida()));
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/abrirAlterarMedida", method = { RequestMethod.GET, RequestMethod.POST })
	public String abrirAlterar(@ModelAttribute("medidaForm") Medida medida, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		model.addAttribute("medidaForm", carregaMedida(medidaService.consultarByCodigo(medida)));
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW;
	}

	@RequestMapping(value = "/alterarMedida", method = { RequestMethod.GET, RequestMethod.POST })
	public String alterar(@ModelAttribute("medidaForm") @Validated Medida medida, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		medidaService.alterar(medida);
		model.addAttribute("mensagem", message.getMessage("global.alteracao", null, Locale.US));
		model.addAttribute("medidaList", medidaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	@RequestMapping(value = "/incluirMedida", method = { RequestMethod.GET, RequestMethod.POST })
	public String incluir(@ModelAttribute("medidaForm") @Validated Medida medida, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		medidaService.incluir(medida);
		model.addAttribute("mensagem", message.getMessage("global.inclusao", null, Locale.US));
		model.addAttribute("medidaList", medidaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return VIEW_COLSULTA;
	}

	@RequestMapping(value = "/consultarMedida", method = { RequestMethod.GET, RequestMethod.POST })
	public String consultarMedida(@ModelAttribute("medidaForm") Medida medida, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		
		List<Medida> m = medidaService.consultar();
		
		model.addAttribute("medidaList", m);
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	@RequestMapping(value = "/excluirMedida", method = { RequestMethod.GET, RequestMethod.POST })
	public String excluir(@ModelAttribute("medidaForm") @Validated Medida medida, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		medidaService.excluir(medida);
		model.addAttribute("mensagem", message.getMessage("global.exclusao", null, Locale.US));
		model.addAttribute("medidaList", medidaService.consultar());
		model.addAttribute("breadCrumbItens", breadCrumbList());
		return "redirect:"+VIEW_COLSULTA;
	}

	public List<BreadCrumb> breadCrumbList() {
		List<String> msg = new ArrayList<String>();
		msg.add("menu.cadastro");
		msg.add("menu.cadastro.medida");
		return Util.breadCrumbList(message, msg);
	}

	private Medida carregaMedida(Medida medida) {
		medida.setMarcas(marcaService.consultar());
		medida.setCategorias(categoriaService.consultar());
		if (medida.getItensTipoMedida() != null) {
			if(medida.getItensTipoMedida().size() > 0) {
				if(medida.getItensTipoMedida().get(0) != null) {
					if(medida.getItensTipoMedida().get(0).getCategoria() != null) {
						Categoria categoria = medida.getItensTipoMedida().get(0).getCategoria();
						medida.setSubCategorias(categoriaService.consultarByCodigo(categoria).getSubCategorias());
					}
				}
			}
		}
		return medida;
	}

}
