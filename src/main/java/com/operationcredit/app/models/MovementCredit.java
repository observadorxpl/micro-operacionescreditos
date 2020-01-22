package com.operationcredit.app.models;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MovementCredit {
	@Id
	private String idMovement;
	
	private String accountNumberOrigin;
	
	private CustomerCreditProduct customerCreditProduct;
	
	private double monto;
	
	@NotEmpty // CONSUMO, ABONO
	private String operationType;
	
	@NotEmpty
	@NotEmpty
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date movementDate;

	public MovementCredit(String accountNumberOrigin, CustomerCreditProduct customerCreditProduct, double monto,
			@NotEmpty String operationType, @NotEmpty @NotEmpty Date movementDate) {
		super();
		this.accountNumberOrigin = accountNumberOrigin;
		this.customerCreditProduct = customerCreditProduct;
		this.monto = monto;
		this.operationType = operationType;
		this.movementDate = movementDate;
	}

	public String getIdMovement() {
		return idMovement;
	}

	public void setIdMovement(String idMovement) {
		this.idMovement = idMovement;
	}

	public String getAccountNumberOrigin() {
		return accountNumberOrigin;
	}

	public void setAccountNumberOrigin(String accountNumberOrigin) {
		this.accountNumberOrigin = accountNumberOrigin;
	}

	public CustomerCreditProduct getCustomerCreditProduct() {
		return customerCreditProduct;
	}

	public void setCustomerCreditProduct(CustomerCreditProduct customerCreditProduct) {
		this.customerCreditProduct = customerCreditProduct;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	public Date getMovementDate() {
		return movementDate;
	}
	public void setMovementDate(Date movementDate) {
		this.movementDate = movementDate;
	}

	
}
