package com.operationcredit.app.expose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operationcredit.app.business.ICreditMovementService;
import com.operationcredit.app.models.MovementCredit;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/movements")
public class MovementController {
	
	@Autowired
	private ICreditMovementService service;
	
	@GetMapping("/{idCliente}")
	public Flux<MovementCredit> listarMovimientos(@PathVariable String idCliente){
		return service.listarMovimientosCliente(idCliente);
	}
}
