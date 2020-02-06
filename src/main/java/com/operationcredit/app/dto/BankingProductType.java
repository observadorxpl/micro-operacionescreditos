package com.operationcredit.app.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankingProductType {
	private String idProductType;

	@NotEmpty
	private String description;

	public BankingProductType(@NotEmpty String description) {
		super();
		this.description = description;
	}

}
