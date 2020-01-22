package com.operationcredit.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.operationcredit.app.models.CreditProduct;
import com.operationcredit.app.models.Customer;
import com.operationcredit.app.models.CustomerCreditProduct;
import com.operationcredit.app.repository.ICustomerCreditProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerProductServiceImpl implements ICustomerProductService {

	@Autowired
	private ICustomerCreditProductRepository clienteProductoRepo;
	
	@Override
	public Flux<CustomerCreditProduct> findAll() {
		return clienteProductoRepo.findAll();
	}
	
	@Override
	public Mono<CustomerCreditProduct> finById(String id) {
		return clienteProductoRepo.findById(id);
	}

	@Override
	public Mono<CustomerCreditProduct> save(CustomerCreditProduct t) {
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
	public Mono<Void> delete(CustomerCreditProduct t) {
		return clienteProductoRepo.delete(t);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return clienteProductoRepo.deleteById(id);
	}




	



	@Override
	public Flux<CustomerCreditProduct> findByCliente(String idCliente) {
		return WebClient.builder().baseUrl("http://servicio-zuul-server:8099/micro-clientes/customers/").build().get()
				.uri(idCliente).retrieve().bodyToMono(Customer.class).log().flatMapMany(cli -> {
					return clienteProductoRepo.findByCustomer(cli);
				});
	}
	
	@Override
	public Mono<CustomerCreditProduct> saveClienteProductoCredito(CustomerCreditProduct clienteProductoCredito) {
		return WebClient.builder().baseUrl("http://servicio-zuul-server:8099/micro-clientes/customers/").build().get()
				.uri(clienteProductoCredito.getCustomer().getIdCustomer()).retrieve().bodyToMono(Customer.class).log()
				.flatMap(cl -> {
					clienteProductoCredito.setCustomer(cl);
					return WebClient.builder().baseUrl("http://servicio-zuul-server:8099/micro-credito/products/").build().get()
							.uri(clienteProductoCredito.getCreditProduct().getIdProduct()).retrieve()
							.bodyToMono(CreditProduct.class).log();
				}).flatMap(p -> {
					clienteProductoCredito.setCreditProduct(p);
					clienteProductoCredito.setCardNumber(clienteProductoCredito.generarNumeroTarjeta("333", 10));
					clienteProductoCredito.setPass(clienteProductoCredito.generarNumeroTarjeta("9", 4));
					clienteProductoCredito.setBalance(clienteProductoCredito.getLineCredit());
					return clienteProductoRepo.save(clienteProductoCredito);
				});
	}
}
