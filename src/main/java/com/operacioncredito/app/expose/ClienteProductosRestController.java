package com.operacioncredito.app.expose;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operacioncredito.app.business.IClienteProductoService;
import com.operacioncredito.app.models.ClienteProductoCredito;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientes-productos")
public class ClienteProductosRestController {
	@Autowired
	private IClienteProductoService clienteProductosService;

	@GetMapping("/{idCliente}")
	public Flux<ClienteProductoCredito> listarProductoxCliente(@PathVariable String idCliente) {
		return clienteProductosService.findByCliente(idCliente);
	}

	@PostMapping
	public Mono<ClienteProductoCredito> registrarClienteProductoCredito(
			@RequestBody @Valid ClienteProductoCredito clienteProductoCredito) {
		return clienteProductosService.saveClienteProductoCredito(clienteProductoCredito);
	}
	/*
	 * @Autowired private IClienteProductosService clienteProductosService;
	 * 
	 * @GetMapping public Flux<ClienteProductos> listarAllClientes() { return
	 * clienteProductosService.findAll(); }
	 * 
	 * @GetMapping("/{id}") public Mono<ClienteProductos>
	 * buscarCliente(@PathVariable String id) { return
	 * clienteProductosService.finById(id); }
	 * 
	 * @PostMapping public Mono<ClienteProductos> registrarCliente(@RequestBody
	 * ClienteProductos clienteProductos) { return
	 * clienteProductosService.save(clienteProductos); }
	 * 
	 * @PutMapping public Mono<ClienteProductos> actualizarCliente(@RequestBody
	 * ClienteProductos clienteProductos) { return
	 * clienteProductosService.save(clienteProductos); }
	 * 
	 * @DeleteMapping("/{id}") public Mono<Void> eliminarCliente(@PathVariable
	 * String id){ return clienteProductosService.deleteById(id); }
	 */
}
