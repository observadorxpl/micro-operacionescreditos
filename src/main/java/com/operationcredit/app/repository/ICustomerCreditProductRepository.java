package com.operationcredit.app.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.operationcredit.app.models.Customer;
import com.operationcredit.app.models.CustomerCreditProduct;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface ICustomerCreditProductRepository extends ReactiveMongoRepository<CustomerCreditProduct, String>{
	public Flux<CustomerCreditProduct> findByCustomer(Customer customer);
	public Flux<CustomerCreditProduct> findByCustomer(String idCustomer);

	public Mono<CustomerCreditProduct> findByCardNumber(String numeroTarjetaCredito);
	
	
	
	@Query(" {'customer.dni' : ?0 , $where : 'this.balance < this.lineCredit'} ")
	public Flux<CustomerCreditProduct> buscarDeudas(String dni);

}  