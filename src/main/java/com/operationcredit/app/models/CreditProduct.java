package com.operationcredit.app.models;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreditProduct {
	@Id
	private String idProduct;
	
	@NotEmpty
	private String description;
	
	@NotEmpty
	private Date createAt;
		
	private double interest;
	@NotEmpty
	private Integer productCode;
	@Valid
	private CreditProductType productType;
	
	@Valid
	private Bank bank;


}
