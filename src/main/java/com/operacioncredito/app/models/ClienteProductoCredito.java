package com.operacioncredito.app.models;

import javax.validation.constraints.NotNull;

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
public class ClienteProductoCredito{
	@Id
	private String clienteProductosId;
	@NotNull
	private Cliente cliente;
	@NotNull
	private ProductoCredito productoCredito;
	
	public ClienteProductoCredito(@NotNull Cliente cliente, @NotNull ProductoCredito productoCredito) {
		super();
		this.cliente = cliente;
		this.productoCredito = productoCredito;
	}


}
