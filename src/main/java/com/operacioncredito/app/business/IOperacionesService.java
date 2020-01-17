package com.operacioncredito.app.business;

import com.operacioncredito.app.models.MovimientoCredito;
import com.operacioncredito.app.models.OperacionCreditoDTO;

import reactor.core.publisher.Mono;

public interface IOperacionesService{
	public Mono<MovimientoCredito> consumo (OperacionCreditoDTO dto);
	public Mono<MovimientoCredito> abono (OperacionCreditoDTO dto);
}
