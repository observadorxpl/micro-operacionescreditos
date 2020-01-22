package com.operationcredit.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.operationcredit.app.models.Customer;
import com.operationcredit.app.models.MovementCredit;
import com.operationcredit.app.repository.ICreditMovementRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditMovementServiceImpl implements ICreditMovementService{

	@Autowired
	private ICreditMovementRepo movimientoRepo;
	

	@Override
	public Flux<MovementCredit> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<MovementCredit> finById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<MovementCredit> save(MovementCredit t) {
		return movimientoRepo.save(t);
	}

	@Override
	public Mono<Void> delete(MovementCredit t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<MovementCredit> listarMovimientosCliente(String idCliente) {
		return WebClient.builder().baseUrl("http://servicio-zuul-server:8099/micro-clientes/customers/").build()
		.get().uri(idCliente).retrieve().bodyToMono(Customer.class).log().flux()
		.defaultIfEmpty(new Customer())
		.flatMap(c -> {
			if(c.getIdCustomer() == null) {
				return Mono.error(new InterruptedException("El cliente no existe"));
			}
			return Flux.just(c);
		})
		.flatMap(c -> {
			return movimientoRepo.buscarPorIdCliente(c.getIdCustomer());
		});
	}

}
