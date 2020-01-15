package com.operacioncredito.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.operacioncredito.app.models.Cliente;
import com.operacioncredito.app.models.MovimientoCredito;
import com.operacioncredito.app.repository.IMovimientoRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovimientoServiceImpl implements IMovimientoService{

	@Autowired
	private IMovimientoRepo movimientoRepo;
	

	@Override
	public Flux<MovimientoCredito> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<MovimientoCredito> finById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<MovimientoCredito> save(MovimientoCredito t) {
		return movimientoRepo.save(t);
	}

	@Override
	public Mono<Void> delete(MovimientoCredito t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<MovimientoCredito> listarMovimientosCliente(Cliente cliente) {
		return WebClient.builder().baseUrl("http://localhost:8099/micro-clientes/clientes/").build()
		.get().uri(cliente.getIdCliente()).retrieve().bodyToMono(Cliente.class).log().flux()
		.defaultIfEmpty(new Cliente())
		.flatMap(c -> {
			if(c.getIdCliente() == null) {
				return Mono.error(new InterruptedException("El cliente no existe"));
			}
			return Flux.just(c);
		})
		.flatMap(c -> {
			return movimientoRepo.findByCliente(c);
		});
	}

}
