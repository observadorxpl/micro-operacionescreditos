package com.operationcredit.app.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.operationcredit.app.models.Bank;
import com.operationcredit.app.models.CreditProduct;
import com.operationcredit.app.models.Customer;
import com.operationcredit.app.models.CustomerCreditProduct;
import com.operationcredit.app.repository.ICustomerCreditProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerProductServiceImpl implements ICustomerProductService {
	
	@Value("${com.bootcamp.gateway.url}")
	private String gatewayUrlPort;
	
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
	public Mono<Void> delete(CustomerCreditProduct t) {
		return clienteProductoRepo.delete(t);
	}

	@Override
	public Mono<Void> deleteById(String id) {
		return clienteProductoRepo.deleteById(id);
	}

	@Override
	public Flux<CustomerCreditProduct> findByCliente(String idCliente) {
		return WebClient.builder().baseUrl("http://"+gatewayUrlPort+"/micro-clientes/customers/").build().get()
				.uri(idCliente).retrieve().bodyToMono(Customer.class).log().flatMapMany(cli -> {
					return clienteProductoRepo.findByCustomer(cli);
				});
	}
	
	@Override
	public Mono<CustomerCreditProduct> saveClienteProductoCredito(CustomerCreditProduct clienteProductoCredito) {
		return WebClient.builder().baseUrl("http://"+gatewayUrlPort+"/micro-clientes/customers/").build().get()
				.uri(clienteProductoCredito.getCustomer().getIdCustomer()).retrieve().bodyToMono(Customer.class).log()
				.flatMap(cli -> {
					clienteProductoCredito.setCustomer(cli);
					return WebClient.builder().baseUrl("http://"+gatewayUrlPort+"/micro-credito/products/").build().get()
							.uri(clienteProductoCredito.getCreditProduct().getIdProduct()).retrieve()
							.bodyToMono(CreditProduct.class).log();
				}).flatMap(pro -> {
					clienteProductoCredito.setCreditProduct(pro);
					return WebClient.builder().baseUrl("http://" + gatewayUrlPort + "/micro-banco/bank/").build().get()
							.uri(clienteProductoCredito.getBank().getIdBank()).retrieve().bodyToMono(Bank.class).log();
				})
				.flatMap(bank -> {
					clienteProductoCredito.setBank(bank);
					clienteProductoCredito.setCardNumber(clienteProductoCredito.generarNumeroTarjeta("333", 10));
					clienteProductoCredito.setPass(clienteProductoCredito.generarNumeroTarjeta("9", 4));
					clienteProductoCredito.setBalance(clienteProductoCredito.getLineCredit());
					return clienteProductoRepo.save(clienteProductoCredito);
				});
	}
	@Override
	public Flux<CustomerCreditProduct> listarDeudasCreditos(String dni){
		return clienteProductoRepo.buscarDeudas(dni);
	}

	@Override
	public Mono<CustomerCreditProduct> save(CustomerCreditProduct t) {
		return clienteProductoRepo.save(t);
	}

	@Override
	public Mono<CustomerCreditProduct> buscarPorNumeroTarjeta(String numeroTarjeta) {
		return clienteProductoRepo.findByCardNumber(numeroTarjeta);
	}
}
