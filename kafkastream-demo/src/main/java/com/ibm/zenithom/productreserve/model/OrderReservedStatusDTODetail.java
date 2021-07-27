package com.ibm.zenithom.productreserve.model;

import java.util.List;

import com.ibm.zenithom.productreserve.model.ProductDTO;


public class OrderReservedStatusDTODetail extends OrderReservedStatusDTO {

	
	 	
	    
	    private List<Product> products;
	    
	    public List<Product> getProducts() {
			return products;
		}
		public void setProducts(List<Product> products) {
			this.products = products;
		}
		
}
