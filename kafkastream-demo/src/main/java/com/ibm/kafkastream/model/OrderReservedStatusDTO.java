package com.ibm.kafkastream.model;

import java.util.List;

import com.ibm.kafkastream.model.ProductDTO;

// oms-product-status topic

public class OrderReservedStatusDTO {

	
	 	private Long orderNo;
	 	private Long customerNo;
	    private String productStatus; // RESERVED or  REJECTED 
	    
	    
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
		
	    
	    
	    
}
