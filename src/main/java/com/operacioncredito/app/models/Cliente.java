package com.operacioncredito.app.models;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class Cliente {
	@Id
	private String idCliente;

	private String nombreCompleto;
	@NotEmpty
	private String apellidoPaterno;
	@NotEmpty
	private String apellidoMaterno;
	@NotEmpty
	private String dni;

	@Valid
	private TipoCliente tipoCliente;

	public Cliente(@NotEmpty String nombreCompleto, @NotEmpty String apellidoPaterno, @NotEmpty String apellidoMaterno,
			@NotEmpty String dni, @Valid TipoCliente tipoCliente) {
		super();
		this.nombreCompleto = nombreCompleto;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.dni = dni;
		this.tipoCliente = tipoCliente;
	}

	
}
