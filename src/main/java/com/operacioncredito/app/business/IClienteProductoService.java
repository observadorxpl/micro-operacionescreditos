package com.operacioncredito.app.business;

import com.operacioncredito.app.models.ClienteProductoCredito;
import com.operacioncredito.app.util.ICRUD;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClienteProductoService extends ICRUD<ClienteProductoCredito>{
	public Mono<ClienteProductoCredito> saveClienteProductoCredito(ClienteProductoCredito clienteProductoCredito);
	public Flux<ClienteProductoCredito> findByCliente(String idCliente);


}
