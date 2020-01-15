package com.operacioncredito.app.expose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operacioncredito.app.business.ICuentaCredito;
import com.operacioncredito.app.models.Cliente;
import com.operacioncredito.app.models.CuentaCredito;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/cuentas")
public class CuentaBancariaRestController {
	@Autowired
	private ICuentaCredito cuentaService;
	
	@GetMapping
	public Flux<CuentaCredito> listarCuentaXCliente(@RequestBody Cliente cliente) {
		System.out.println(cliente);
		return cuentaService.listarProductosXCliente(cliente);
	}
}
