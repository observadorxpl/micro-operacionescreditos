package com.operacioncredito.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteProductosCreditoDTO {
	private Cliente cliente;
	private List<ProductoCredito> productosCreditos;
	@JsonIgnore
	private ProductoCredito producto;
}
