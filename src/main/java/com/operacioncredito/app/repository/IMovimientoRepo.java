package com.operacioncredito.app.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.operacioncredito.app.models.MovimientoCredito;

import reactor.core.publisher.Flux;

public interface IMovimientoRepo extends ReactiveMongoRepository<MovimientoCredito, String>{
	@Query("{'clienteProductoCredito.cliente.idCliente' : ?0}")
	Flux<MovimientoCredito> buscarPorIdCliente(String idCliente);
}
