package com.operationcredit.app.dto;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerBankingProduct {
	private String idCustomerBankingProduct;
	private Customer customer;
	private BankingProduct bankingProduct;
	private Bank bank;
	private String accountNumber;
	private String accountNumberCCI;
	private String pass;
	private Double balance;
	private boolean state;
    private Random random;
}
