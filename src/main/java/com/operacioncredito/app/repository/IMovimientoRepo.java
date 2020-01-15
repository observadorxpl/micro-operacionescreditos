package com.operacioncredito.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.operacioncredito.app.models.Cliente;
import com.operacioncredito.app.models.MovimientoCredito;

import reactor.core.publisher.Flux;

public interface IMovimientoRepo extends ReactiveMongoRepository<MovimientoCredito, String>{
	Flux<MovimientoCredito> findByCliente(Cliente cliente);
}
