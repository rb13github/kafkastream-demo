package com.ibm.zenithom.productreserve.model;



public class ProductListenerDTO {
	
	private Long orderNo;
	private Long customerNo;
	private String productStatus;
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getCustomerNo() {
		return customerNo;
	}
	public void setCustomerNo(Long customerNo) {
		this.customerNo = customerNo;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	@Override
	public String toString() {
		return "ProductListenerDTO [orderNo=" + orderNo + ", customerNo=" + customerNo + ", productStatus="
				+ productStatus + "]";
	}
	
	
	

	

}
