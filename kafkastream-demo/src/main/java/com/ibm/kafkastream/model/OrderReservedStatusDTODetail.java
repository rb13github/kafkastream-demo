package com.ibm.kafkastream.model;

import java.util.List;

import com.ibm.kafkastream.model.ProductDTO;


public class OrderReservedStatusDTODetail extends OrderReservedStatusDTO {

	
	 	
	    
	    private List<Product> products;
	    
	    public List<Product> getProducts() {
			return products;
		}
		public void setProducts(List<Product> products) {
			this.products = products;
		}
		
}
