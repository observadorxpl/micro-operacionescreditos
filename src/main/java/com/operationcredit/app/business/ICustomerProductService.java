package com.operationcredit.app.business;

import com.operationcredit.app.models.CustomerCreditProduct;
import com.operationcredit.app.util.ICRUD;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerProductService extends ICRUD<CustomerCreditProduct>{
	public Mono<CustomerCreditProduct> saveClienteProductoCredito(CustomerCreditProduct clienteProductoCredito);
	public Flux<CustomerCreditProduct> findByCliente(String idCliente);
	public Flux<CustomerCreditProduct> listarDeudasCreditos(String dni);

}
