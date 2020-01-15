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
public class ProductoCredito {
	@Id
	private String idProducto;
	
	@NotEmpty
	private String descripcion;
	
	@NotEmpty
	private Integer codigoProducto;
	
	@Valid
	private TipoProducto tipoProducto;
	public ProductoCredito(@NotEmpty String descripcion, @Valid TipoProducto tipoProducto ,@NotEmpty Integer codigoProducto) {
		super();
		this.descripcion = descripcion;
		this.tipoProducto = tipoProducto;
		this.codigoProducto = codigoProducto;
	}


	
}
