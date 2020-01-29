package com.operationcredit.app.expose;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operationcredit.app.business.IOperationService;
import com.operationcredit.app.models.MovementCredit;
import com.operationcredit.app.models.OperacionCreditoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

@RestController
@Api(value = "Credit Operation Microservice")
@RequestMapping(value = "/operations")
public class OperationController {
	@Autowired
	private IOperationService operacionesService;
	
	@PostMapping(value = "/abono")
	@ApiOperation(value = "Make a pay", notes="Make a pay, need the structure dto: numeroCuentaOrigen, numeroCuentaDestino and monto")
	public Mono<MovementCredit> abono(@RequestBody @Valid OperacionCreditoDTO dto){
		return operacionesService.abono(dto);
	}
	
	@PostMapping(value = "/consumo")
	@ApiOperation(value = "Make a consume", notes="Make a consume, need the structure dto: numeroCuentaOrigen, numeroCuentaDestino and monto")
	public Mono<MovementCredit> consumo(@RequestBody @Valid OperacionCreditoDTO dto){
		return operacionesService.consumo(dto);
	}
}
