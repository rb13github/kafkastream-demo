package com.ibm.kafkastream.service;




import com.ibm.kafkastream.model.ProductListenerDTO;
import com.ibm.kafkastream.model.ProductReservedStatusDTO;



public interface ProductReservationService {
	
	public ProductReservedStatusDTO create(ProductListenerDTO dto);

}
