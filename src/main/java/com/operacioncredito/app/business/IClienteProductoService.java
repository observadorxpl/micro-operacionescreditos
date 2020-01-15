package com.operacioncredito.app.business;

import com.operacioncredito.app.models.Cliente;
import com.operacioncredito.app.models.ClienteProductoCredito;
import com.operacioncredito.app.models.ClienteProductosCreditoDTO;
import com.operacioncredito.app.models.CuentaCredito;
import com.operacioncredito.app.util.ICRUD;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClienteProductoService extends ICRUD<ClienteProductoCredito>{
	public Mono<ClienteProductosCreditoDTO> saveClienteProductoDTO(ClienteProductosCreditoDTO dto);
	public Flux<CuentaCredito> saveClienteProductoDTOv2(ClienteProductosCreditoDTO dto);
	public Flux<CuentaCredito> saveClienteProductoDTOv3(ClienteProductosCreditoDTO dto);
	public Flux<CuentaCredito> saveClienteProductoDTOv4(ClienteProductosCreditoDTO dto);

	public Mono<ClienteProductosCreditoDTO> findByCliente(Cliente cliente);
	public Mono<ClienteProductosCreditoDTO> findByClientev2(Cliente cliente);


}
