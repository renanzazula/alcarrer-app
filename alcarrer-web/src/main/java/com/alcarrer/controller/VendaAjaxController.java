package com.alcarrer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alcarrer.model.Venda;
import com.alcarrer.service.venda.VendaService;

@Controller
public class VendaAjaxController {

	@Autowired
	private VendaService vendaService;

	@ResponseBody
	@RequestMapping(value = "/consultarItensVenda")
	public Venda consultarItensVenda(@RequestBody Integer codigo) {
		Venda venda = vendaService.consultarByCodigo(new Venda(codigo));
		return venda;
	}
}
