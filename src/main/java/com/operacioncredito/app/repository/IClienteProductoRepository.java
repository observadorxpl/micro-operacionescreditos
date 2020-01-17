package com.operacioncredito.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.operacioncredito.app.models.Cliente;
import com.operacioncredito.app.models.ClienteProductoCredito;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface IClienteProductoRepository extends ReactiveMongoRepository<ClienteProductoCredito, String>{
	public Flux<ClienteProductoCredito> findByCliente(Cliente cliente);
	public Flux<ClienteProductoCredito> findByCliente(String idCliente);

	public Mono<ClienteProductoCredito> findByNumeroTarjeta(String numeroTarjetaCredito);
	
}
