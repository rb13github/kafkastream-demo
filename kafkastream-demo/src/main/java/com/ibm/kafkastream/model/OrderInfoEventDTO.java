package com.ibm.kafkastream.model;

import java.util.List;

public class OrderInfoEventDTO {
	
	private  Long orderNumber;
	
	private Long customerNumber;
	
	private List<ProductDTO> product;
	
	private String orderStatus;
	
	private String action;
	
	private Long totalPrice;

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Long customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<ProductDTO> getProduct() {
		return product;
	}

	public void setProduct(List<ProductDTO> product) {
		this.product = product;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderInfoEventDTO [orderNumber=" + orderNumber + ", customerNumber=" + customerNumber + ", product="
				+ product + ", orderStatus=" + orderStatus + ", action=" + action + ", totalPrice=" + totalPrice + "]";
	}
	
	

}
