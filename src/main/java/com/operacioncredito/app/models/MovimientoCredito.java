package com.operacioncredito.app.models;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovimientoCredito {
	@Id
	private String IdMovimiento;
	

	private CuentaCredito cuentaBancaria;
	
	private Cliente cliente;
	
	private double monto;
	
	@NotEmpty
	private String tipoMovimiento;
	
	@NotEmpty
	private Date fechaMovimiento;

	public MovimientoCredito(CuentaCredito cuentaBancaria, Cliente cliente, double monto, @NotEmpty String tipoMovimiento,
			@NotEmpty Date fechaMovimiento) {
		super();
		this.cuentaBancaria = cuentaBancaria;
		this.cliente = cliente;
		this.monto = monto;
		this.tipoMovimiento = tipoMovimiento;
		this.fechaMovimiento = fechaMovimiento;
	}


	
}