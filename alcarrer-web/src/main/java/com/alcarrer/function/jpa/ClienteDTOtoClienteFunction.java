package com.alcarrer.function.jpa;

import java.util.function.Function;

import com.alcarrer.entity.ClienteEntity;
import com.alcarrer.model.Cliente;

public class ClienteDTOtoClienteFunction implements Function<ClienteEntity, Cliente> {

	@Override
	public Cliente apply(ClienteEntity input) {
		Cliente output = new Cliente();
		output.setCodigo(input.getCodigo());
		return output;
	}

}
