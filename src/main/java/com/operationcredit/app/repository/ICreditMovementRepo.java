package com.operationcredit.app.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.operationcredit.app.models.MovementCredit;

import reactor.core.publisher.Flux;

public interface ICreditMovementRepo extends ReactiveMongoRepository<MovementCredit, String>{
	@Query("{'customerCreditProduct.customer.idCustomer' : ?0}")
	Flux<MovementCredit> buscarPorIdCliente(String idCliente);
}
