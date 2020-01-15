package com.operacioncredito.app.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.operacioncredito.app.models.CuentaCredito;
import com.operacioncredito.app.models.MovimientoCredito;
import com.operacioncredito.app.models.OperacionCreditoDTO;
import com.operacioncredito.app.repository.ICuentaCreditoRepo;
import com.operacioncredito.app.repository.IMovimientoRepo;

import reactor.core.publisher.Mono;

@Service
public class OperacionesServiceImpl implements IOperacionesService{

	@Autowired
	private ICuentaCreditoRepo cuentaRepo;
	
	@Autowired 
	private IMovimientoRepo repo;
	
	@Override
	public Mono<CuentaCredito> consumo(OperacionCreditoDTO dto) {
		return cuentaRepo.findById(dto.getCuentaBancaria().getIdCuentaFinanciera())
		.flatMap(c -> {
			MovimientoCredito mov = new MovimientoCredito(c, c.getCliente(), dto.getMonto(), "Consumo", new Date());
			if(c.getSaldo() > dto.getMonto()) {
				c.setSaldo(c.getSaldo()-dto.getMonto());
				repo.save(mov).subscribe();
				 return cuentaRepo.save(c);
			}
			return Mono.error(new InterruptedException("No tiene el saldo suficiente para retirar"));
		});
	}

	@Override
	public Mono<CuentaCredito> abono(OperacionCreditoDTO dto) {
		return cuentaRepo.findById(dto.getCuentaBancaria().getIdCuentaFinanciera())
				.flatMap(c -> {
					MovimientoCredito mov = new MovimientoCredito(c,c.getCliente(), dto.getMonto(), "Abono", new Date());
					c.setSaldo(c.getSaldo() + dto.getMonto());
					repo.save(mov).subscribe();
					return cuentaRepo.save(c);
				});
	}

}
