package com.ibm.zenithom.productreserve.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
/*
 * 
 * it will publish the message on the topic mentioned in @Output
 */
public interface ProductReservationProducerStream {

	String PRODUCT_RESRVE_PRODUCER= "product-reserve-producer";
			    
	 @Output(PRODUCT_RESRVE_PRODUCER)
	 MessageChannel publishProductReserve();


		String PRODUCT_RESRVE_STATUS_PRODUCER= "product-status-producer";
				    
		 @Output(PRODUCT_RESRVE_STATUS_PRODUCER)
		 MessageChannel publishProductReserveStatus();

}
