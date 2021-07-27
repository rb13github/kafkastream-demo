package com.ibm.zenithom.productreserve.service;




import com.ibm.zenithom.productreserve.model.OrderInfoEventDTO;
import com.ibm.zenithom.productreserve.model.OrderReservedStatusDTO;
import com.ibm.zenithom.productreserve.model.ProductListenerDTO;




public interface OrderReservationService {
	
	public OrderReservedStatusDTO create(OrderInfoEventDTO dto);
	public OrderReservedStatusDTO checkReservation(OrderInfoEventDTO dto);
}
