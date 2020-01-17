package com.operacioncredito.app.expose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operacioncredito.app.business.IMovimientoService;
import com.operacioncredito.app.models.Cliente;
import com.operacioncredito.app.models.MovimientoCredito;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/movimientos")
public class MovimientoRestController {
	
	@Autowired
	private IMovimientoService service;
	
	@GetMapping("/{idCliente}")
	public Flux<MovimientoCredito> listarMovimientos(@PathVariable String idCliente){
		return service.listarMovimientosCliente(idCliente);
	}
}
