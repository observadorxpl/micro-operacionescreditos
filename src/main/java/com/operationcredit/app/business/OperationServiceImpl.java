package com.operationcredit.app.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.operationcredit.app.dto.CustomerBankingProduct;
import com.operationcredit.app.models.Customer;
import com.operationcredit.app.models.MovementCredit;
import com.operationcredit.app.models.OperacionCreditoDTO;
import com.operationcredit.app.repository.ICreditMovementRepo;
import com.operationcredit.app.repository.ICustomerCreditProductRepository;

import reactor.core.publisher.Mono;

@Service
public class OperationServiceImpl implements IOperationService {
	@Value("${com.bootcamp.gateway.url}")
	private String gatewayUrlPort;

	@Autowired
	private ICustomerCreditProductRepository clienteProductoRepo;

	@Autowired
	private ICreditMovementRepo repo;

	@Override
	public Mono<MovementCredit> consumo(OperacionCreditoDTO dto) {
		return clienteProductoRepo.findByCardNumber(dto.getNumeroTarjetaDestino()).flatMap(clPro -> {
			if (clPro.getBalance() >= dto.getMonto()) {
				clPro.setBalance(clPro.getBalance() - dto.getMonto());
				return clienteProductoRepo.save(clPro);
			}
			return Mono.error(new InterruptedException("No tiene el saldo suficiente para consumir"));
		}).flatMap(clPro -> {
			MovementCredit mov = new MovementCredit(dto.getNumeroCuentaOrigen(), clPro, dto.getMonto(), "Consumo",
					new Date());
			return repo.save(mov);
		});
	}
	// Abono a la tarjeta de credito, se actualiza el saldo.
	// Los comentarios son para no validar que el saldo exceda la linea de credito
	@Override
	public Mono<MovementCredit> abono(OperacionCreditoDTO dto) {
		return clienteProductoRepo.findByCardNumber(dto.getNumeroTarjetaDestino()).flatMap(clPro -> {
			//if (clPro.getLineCredit() - clPro.getBalance() >= dto.getMonto()) {
				clPro.setBalance(clPro.getBalance() + dto.getMonto());
				return clienteProductoRepo.save(clPro);
			//}
			//return Mono.error(new InterruptedException("El monto a abonar exede la linea de credito"));
		}).flatMap(clPro -> {
			MovementCredit mov = new MovementCredit(dto.getNumeroCuentaOrigen(), clPro, dto.getMonto(), "Abono",
					new Date());
			return repo.save(mov);
		});
	}

}
