package com.operacioncredito.app.expose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operacioncredito.app.business.IClienteProductoService;
import com.operacioncredito.app.models.ClienteProductosCreditoDTO;
import com.operacioncredito.app.models.CuentaCredito;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientesProductos")
public class ClienteProductosRestController {
	@Autowired
	private IClienteProductoService clienteProductosService;
	
	@GetMapping
	public Mono<ClienteProductosCreditoDTO> listarProductoxCliente(ClienteProductosCreditoDTO dto) {
		return clienteProductosService.findByCliente(dto.getCliente());
	}
	
	@PostMapping
	public Flux<CuentaCredito> registrarClienteProductosBancarios(@RequestBody ClienteProductosCreditoDTO clienteProductoDTO) {
		return clienteProductosService.saveClienteProductoDTOv2(clienteProductoDTO);
	}
	/*
	@Autowired
	private IClienteProductosService clienteProductosService;

	@GetMapping
	public Flux<ClienteProductos> listarAllClientes() {
		return clienteProductosService.findAll();
	}

	@GetMapping("/{id}")
	public Mono<ClienteProductos> buscarCliente(@PathVariable String id) {
		return clienteProductosService.finById(id);
	}

	@PostMapping
	public Mono<ClienteProductos> registrarCliente(@RequestBody ClienteProductos clienteProductos) {
		return clienteProductosService.save(clienteProductos);
	}

	@PutMapping
	public Mono<ClienteProductos> actualizarCliente(@RequestBody ClienteProductos clienteProductos) {
		return clienteProductosService.save(clienteProductos);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> eliminarCliente(@PathVariable String id){
		return clienteProductosService.deleteById(id);
	}
	*/
}
