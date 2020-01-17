package com.operacioncredito.app.expose;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operacioncredito.app.business.IOperacionesService;
import com.operacioncredito.app.models.MovimientoCredito;
import com.operacioncredito.app.models.OperacionCreditoDTO;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/operaciones")
public class OperacionesRestController {
	@Autowired
	private IOperacionesService operacionesService;
	
	@PostMapping(value = "/abono")
	public Mono<MovimientoCredito> abono(@RequestBody @Valid OperacionCreditoDTO dto){
		return operacionesService.abono(dto);
	}
	
	@PostMapping(value = "/consumo")
	public Mono<MovimientoCredito> consumo(@RequestBody @Valid OperacionCreditoDTO dto){
		return operacionesService.consumo(dto);
	}
}
