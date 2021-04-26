package com.ibm.kafkastream.service;




import com.ibm.kafkastream.model.OrderInfoEventDTO;
import com.ibm.kafkastream.model.OrderReservedStatusDTO;
import com.ibm.kafkastream.model.ProductListenerDTO;




public interface OrderReservationService {
	
	public OrderReservedStatusDTO create(OrderInfoEventDTO dto);
	public OrderReservedStatusDTO checkReservation(OrderInfoEventDTO dto);
}
