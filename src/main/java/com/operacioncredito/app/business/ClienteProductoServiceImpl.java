package com.operacioncredito.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.operacioncredito.app.models.Cliente;
import com.operacioncredito.app.models.ClienteProductoCredito;
import com.operacioncredito.app.models.ProductoCredito;
import com.operacioncredito.app.repository.IClienteProductoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteProductoServiceImpl implements IClienteProductoService {

	@Autowired
	private IClienteProductoRepository clienteProductoRepo;
	
	@Override
	public Flux<ClienteProductoCredito> findAll() {
		return clienteProductoRepo.findAll();
	}
	
	@Override
	public Mono<ClienteProductoCredito> finById(String id) {
		return clienteProductoRepo.findById(id);
	}

	@Override
	public Mono<ClienteProductoCredito> save(ClienteProductoCredito t) {
		//List<Producto> productos = new ArrayList<>();
		//Mono<Cliente> clienteMono = clienteRepo.findById(t.getCliente().getIdCliente());
		/*
		
		return ClienteProductoRepo.findByCliente(t.getCliente())
		.map(cliente -> {
			System.out.println(cliente.toString());
			return cliente.getProductos();
		}).flatMap(productos ->{
			System.out.println("flatmap");
			t.getProductos().forEach(pro1 -> {
				System.out.println("foreach 1");
				productos.forEach(pro2 -> {
					System.out.println("foreach 2");
					if(pro1.getIdProducto() == pro2.getIdProducto()) {
						flag = true;
					}
				});
			});
			if(flag = true) {
				System.out.println("flag true");
				flag = false;
				return Mono.error(new InterruptedException("No puede asignar mas de una vez el mismo tipo de producto"));
			}
			return ClienteProductoRepo.save(t);
		});
		
		
*/
/*
		return clienteMono
		.defaultIfEmpty(new Cliente())
		.flatMap(c -> {
			if(c.getIdCliente() == null) {
				return Mono.error(new InterruptedException("El cliente no existe"));
			}
			return Mono.just(c);
		}).flatMap(cl -> ClienteProductoRepo.save(t));
		*/
		return null;
	}

		
		/*
		.flatMap(c -> {
			t.setCliente(c);
			for (int i = 0; i < t.getProductos().size(); i++) {
				for (int j = 0; j < t.getProductos().size(); j++) {
					Producto pro = t.getProductos().get(j);
					Mono<Producto> productoMono = productoRepo.findById(pro.getIdProducto());
					productoMono
					.flatMap(p -> {
						System.out.println("FlatMap");
						if(p == null) {
							System.out.println("No existe producto **");
							return Mono.error(new InterruptedException("No existe el producto"));
						}
						if(pro.getIdProducto() == t.getProductos().get(i).getIdProducto()) {
							return Mono.error(new InterruptedException("No existe el producto"));
						}
						return Mono.just(p);
					}).subscribe();
				}
			}
			t.setProductos(productos);
			return ClienteProductoRepo.save(t);
		});
			*/
		
				/*
			t.getProductos().forEach(pro -> {
				Mono<Producto> productoMono = productoRepo.findById(pro.getIdProducto());
				productoMono
				.flatMap(p -> {
					System.out.println("FlatMap");
					if(p == null) {
						System.out.println("No existe producto **");
						return Mono.error(new InterruptedException("No existe el producto"));
					}
					if(t.getProductos().get(contador).getIdProducto()==pro.getIdProducto()) {
						
					}
					if(t.getProductos().get(i).getIdProducto() == pro.getIdProducto()) {
						
					}
					System.out.println(p.toString());
					System.out.println("productosAdd");
					productos.add(p);
					return Mono.just(p);
				}).subscribe();
			});
			}
			t.setProductos(productos);
			return ClienteProductoRepo.save(t);
		});
		*/
		
		/*
		t.getProductos().forEach(p -> {
			
			
			Mono<Producto> productoMono = productoRepo.findById(p.getIdProducto())
			productoMono
			.defaultIfEmpty(new Producto())
			.flatMap(p -> {
				if(p.g== null) {
					return Mono.error(new InterruptedException("El cliente no existe"));
				}
				return Mono.just(c);
			})
			.
			*/
			
		/*	
		});
		for (int i = 0; i < t.getProductos().size(); i++) {
			t.getProductos().forEach(pro -> {
				if(t.getProductos().get(0).getCodigoProducto() == pro.getCodigoProducto()) {
					flag = 1;
				}
			});
		}
		if(flag == 1) {
			flag = 0;
			return Mono.just(new ClienteProducto());
		}
		return ClienteProductoRepo.save(t);
		*/
	

	@Override
	public Mono<Void> delete(ClienteProductoCredito t) {
		return clienteProductoRepo.delete(t);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return clienteProductoRepo.deleteById(id);
	}




	



	@Override
	public Flux<ClienteProductoCredito> findByCliente(String idCliente) {
		return WebClient.builder().baseUrl("http://localhost:8099/micro-clientes/clientes/").build().get()
				.uri(idCliente).retrieve().bodyToMono(Cliente.class).log().flatMapMany(cli -> {
					return clienteProductoRepo.findByCliente(cli);
				});
	}
	
	@Override
	public Mono<ClienteProductoCredito> saveClienteProductoCredito(ClienteProductoCredito clienteProductoCredito) {
		return WebClient.builder().baseUrl("http://localhost:8099/micro-clientes/clientes/").build().get()
				.uri(clienteProductoCredito.getCliente().getIdCliente()).retrieve().bodyToMono(Cliente.class).log()
				.flatMap(cl -> {
					clienteProductoCredito.setCliente(cl);
					return WebClient.builder().baseUrl("http://localhost:8099/micro-credito/productos/").build().get()
							.uri(clienteProductoCredito.getProductoCredito().getIdProducto()).retrieve()
							.bodyToMono(ProductoCredito.class).log();
				}).flatMap(p -> {
					clienteProductoCredito.setProductoCredito(p);
					clienteProductoCredito.setNumeroTarjeta(clienteProductoCredito.generarNumeroTarjeta("333", 10));
					clienteProductoCredito.setClave(clienteProductoCredito.generarNumeroTarjeta("9", 4));
					clienteProductoCredito.setSaldo(clienteProductoCredito.getLineaCredito());
					return clienteProductoRepo.save(clienteProductoCredito);
				});
	}
}
