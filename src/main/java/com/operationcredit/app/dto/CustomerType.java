package com.operationcredit.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerType {
	private String idCustomerType;
	private String description;
	private Integer customerTypeCode;
}
