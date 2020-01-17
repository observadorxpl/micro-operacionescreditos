package com.operacioncredito.app.business;

import com.operacioncredito.app.models.MovimientoCredito;
import com.operacioncredito.app.util.ICRUD;

import reactor.core.publisher.Flux;

public interface IMovimientoService extends ICRUD<MovimientoCredito>{
	Flux<MovimientoCredito> listarMovimientosCliente(String idCliente);
}
