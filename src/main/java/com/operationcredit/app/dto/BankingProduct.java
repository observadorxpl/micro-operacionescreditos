package com.operationcredit.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankingProduct {
	private String idProduct;
	private String description;
	private Integer numMaxDeposit;
	private Integer numMaxWithdrawal;
	private BankingProductType productType;
	private Double commision;
	private Integer productCode;
	private Bank bank;
}
