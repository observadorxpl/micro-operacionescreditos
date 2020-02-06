package com.operationcredit.app.expose;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operationcredit.app.business.ICustomerProductService;
import com.operationcredit.app.models.CustomerCreditProduct;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Api(value = "Credit Operation Microservice")
@RequestMapping("/customers-products")
public class CustomerProductController {
	@Autowired
	private ICustomerProductService clienteProductosService;

	@GetMapping("/{idCliente}")
	@ApiOperation(value = "List products by client", notes="List all products by client's id")
	public Flux<CustomerCreditProduct> listarProductoxCliente(@PathVariable String idCliente) {
		return clienteProductosService.findByCliente(idCliente);
	}
	
	@GetMapping("/deudas/{dni}")
	@ApiOperation(value = "List debts", notes="List debts by client's dni")
	public Flux<CustomerCreditProduct> listarDeudas(@PathVariable String dni) {
		return clienteProductosService.listarDeudasCreditos(dni);
	}
	
	@PostMapping
	@ApiOperation(value = "Save a customer with a product", notes = "Save and return a customer with a product, need customer, product and bank references, min(ids)")
	public Mono<CustomerCreditProduct> registrarClienteProductoCredito(
			@RequestBody @Valid CustomerCreditProduct clienteProductoCredito) {
		return clienteProductosService.saveClienteProductoCredito(clienteProductoCredito);
	}
	
	@GetMapping("/card/{numeroTarjeta}")
	@ApiOperation(value = "Find customer-product by num tarjeta", notes="Find customer-product by num tarjeta")
	public Mono<CustomerCreditProduct> buscarPorNumeroTarjeta(@PathVariable String numeroTarjeta) {
		return clienteProductosService.buscarPorNumeroTarjeta(numeroTarjeta);
	}
	
	@PutMapping
	@ApiOperation(value = "Update customer-product", notes="Find customer-product")
	public Mono<CustomerCreditProduct> actualizarClienteProductoCredito(@RequestBody CustomerCreditProduct customerCreditProduct){
		return clienteProductosService.save(customerCreditProduct);
	}

}
