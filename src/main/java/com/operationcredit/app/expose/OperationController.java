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

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/operations")
public class OperationController {
	@Autowired
	private IOperationService operacionesService;
	
	@PostMapping(value = "/abono")
	public Mono<MovementCredit> abono(@RequestBody @Valid OperacionCreditoDTO dto){
		return operacionesService.abono(dto);
	}
	
	@PostMapping(value = "/consumo")
	public Mono<MovementCredit> consumo(@RequestBody @Valid OperacionCreditoDTO dto){
		return operacionesService.consumo(dto);
	}
}
