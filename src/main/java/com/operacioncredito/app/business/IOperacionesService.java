package com.operacioncredito.app.business;

import com.operacioncredito.app.models.CuentaCredito;
import com.operacioncredito.app.models.OperacionCreditoDTO;

import reactor.core.publisher.Mono;

public interface IOperacionesService{
	public Mono<CuentaCredito> consumo (OperacionCreditoDTO dto);
	public Mono<CuentaCredito> abono (OperacionCreditoDTO dto);
}
