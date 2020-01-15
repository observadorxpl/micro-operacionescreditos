package com.operacioncredito.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.operacioncredito.app.models.Cliente;
import com.operacioncredito.app.models.CuentaCredito;

import reactor.core.publisher.Flux;
@Repository
public interface ICuentaCreditoRepo extends ReactiveMongoRepository<CuentaCredito, String>{
	Flux<CuentaCredito> findByCliente(Cliente cliente);
}
