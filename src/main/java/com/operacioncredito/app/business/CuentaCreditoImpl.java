package com.operacioncredito.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.operacioncredito.app.models.Cliente;
import com.operacioncredito.app.models.CuentaCredito;
import com.operacioncredito.app.repository.ICuentaCreditoRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class CuentaCreditoImpl implements ICuentaCredito{	
	@Autowired
	private ICuentaCreditoRepo cuentaRepo;
	

	@Override
	public Flux<CuentaCredito> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<CuentaCredito> finById(String id) {
		return cuentaRepo.findById(id);
	}
	
	
	@Override
	public Mono<CuentaCredito> save(CuentaCredito cuenta) {
		cuenta.setNumeroCuenta(cuenta.generarNumeroCuenta("876", 10));
		cuenta.setClave(cuenta.getClave());
		cuenta.setSaldo(0.00);
		cuenta.setTipoCuenta("Basic");
		cuenta.setEstado(true);
		return cuentaRepo.save(cuenta);
	}

	@Override
	public Mono<Void> delete(CuentaCredito t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mono<Void> deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flux<CuentaCredito> listarProductosXCliente(Cliente cliente) {
		return WebClient.builder().baseUrl("http://localhost:8099/micro-clientes/clientes/").build()
		.get().uri(cliente.getIdCliente()).retrieve().bodyToMono(Cliente.class).log()
		.flatMapMany(c -> {
			return cuentaRepo.findByCliente(c);
		});
	}


}
