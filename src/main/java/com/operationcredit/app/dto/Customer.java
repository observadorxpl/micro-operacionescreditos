package com.operationcredit.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
	private String idCustomer;
	private String firstName;
	private String lastnamePaternal;
	private String lastnameMaternal;
	private String dni;
	private CustomerType customerType;
	private Bank bank;
}
