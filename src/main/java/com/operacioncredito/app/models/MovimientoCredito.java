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
	
	private String numeroCuentaOrigen;
	
	private ClienteProductoCredito clienteProductoCredito;
	
	private double monto;
	
	@NotEmpty // CONSUMO, ABONO
	private String tipoMovimiento;
	
	@NotEmpty
	private Date fechaMovimiento;

	public MovimientoCredito(String numeroCuentaOrigen, ClienteProductoCredito clienteProductoCredito, double monto,
			@NotEmpty String tipoMovimiento, @NotEmpty Date fechaMovimiento) {
		super();
		this.numeroCuentaOrigen = numeroCuentaOrigen;
		this.clienteProductoCredito = clienteProductoCredito;
		this.monto = monto;
		this.tipoMovimiento = tipoMovimiento;
		this.fechaMovimiento = fechaMovimiento;
	}

	
}
