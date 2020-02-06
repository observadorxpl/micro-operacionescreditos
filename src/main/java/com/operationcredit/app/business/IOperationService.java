package com.operationcredit.app.business;

import com.operationcredit.app.models.MovementCredit;
import com.operationcredit.app.models.OperacionCreditoDTO;

import reactor.core.publisher.Mono;

public interface IOperationService{
	public Mono<MovementCredit> consumo (OperacionCreditoDTO dto);
	public Mono<MovementCredit> abono (OperacionCreditoDTO dto);
	//public Mono<MovementCredit> pagoTarjetaCredito(OperacionCreditoDTO dto);
}
