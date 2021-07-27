package com.ibm.zenithom.productreserve.model;

import java.util.List;

import com.ibm.zenithom.productreserve.model.ProductDTO;

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
		
	 public String  toString()
	 {
		 return "{customerNo="+customerNo+",orderNo="+orderNo+",productStatus="+productStatus+"}";
	 }
	    
	    
}
