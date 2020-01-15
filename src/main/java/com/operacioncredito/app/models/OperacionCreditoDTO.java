package com.operacioncredito.app.models;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperacionCreditoDTO {
	@NotNull
	private CuentaCredito cuentaBancaria;
	@NotNull
	private double monto;
}
