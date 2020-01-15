package com.operacioncredito.app.business;

import com.operacioncredito.app.models.Cliente;
import com.operacioncredito.app.models.CuentaCredito;
import com.operacioncredito.app.util.ICRUD;

import reactor.core.publisher.Flux;

public interface ICuentaCredito extends ICRUD<CuentaCredito>{
	public Flux<CuentaCredito> listarProductosXCliente(Cliente cliente);

}
