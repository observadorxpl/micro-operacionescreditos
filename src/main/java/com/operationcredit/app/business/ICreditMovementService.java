package com.operationcredit.app.business;

import com.operationcredit.app.models.MovementCredit;
import com.operationcredit.app.util.ICRUD;

import reactor.core.publisher.Flux;

public interface ICreditMovementService extends ICRUD<MovementCredit>{
	Flux<MovementCredit> listarMovimientosCliente(String idCliente);
}
