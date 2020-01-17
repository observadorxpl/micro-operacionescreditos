package com.operacioncredito.app.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operacioncredito.app.models.MovimientoCredito;
import com.operacioncredito.app.models.OperacionCreditoDTO;
import com.operacioncredito.app.repository.IClienteProductoRepository;
import com.operacioncredito.app.repository.IMovimientoRepo;

import reactor.core.publisher.Mono;

@Service
public class OperacionesServiceImpl implements IOperacionesService {

	@Autowired
	private IClienteProductoRepository clienteProductoRepo;

	@Autowired
	private IMovimientoRepo repo;

	@Override
	public Mono<MovimientoCredito> consumo(OperacionCreditoDTO dto) {
		return clienteProductoRepo.findByNumeroTarjeta(dto.getNumeroTarjetaDestino()).flatMap(clPro -> {
			if (clPro.getSaldo() >= dto.getMonto()) {
				clPro.setSaldo(clPro.getSaldo() - dto.getMonto());
				return clienteProductoRepo.save(clPro);
			}
			return Mono.error(new InterruptedException("No tiene el saldo suficiente para consumir"));
		}).flatMap(clPro -> {
			MovimientoCredito mov = new MovimientoCredito(dto.getNumeroCuentaOrigen(), clPro, dto.getMonto(), "Consumo",
					new Date());
			return repo.save(mov);
		});
	}

	@Override
	public Mono<MovimientoCredito> abono(OperacionCreditoDTO dto) {
		return clienteProductoRepo.findByNumeroTarjeta(dto.getNumeroTarjetaDestino()).flatMap(clPro -> {
			if (clPro.getLineaCredito() - clPro.getSaldo() >= dto.getMonto()) {
				clPro.setSaldo(clPro.getSaldo() + dto.getMonto());
				return clienteProductoRepo.save(clPro);
			}
			return Mono.error(new InterruptedException("El monto a abonar exede la linea de credito"));
		}).flatMap(clPro -> {
			MovimientoCredito mov = new MovimientoCredito(dto.getNumeroCuentaOrigen(), clPro, dto.getMonto(), "Abono",
					new Date());
			return repo.save(mov);
		});
	}

}
