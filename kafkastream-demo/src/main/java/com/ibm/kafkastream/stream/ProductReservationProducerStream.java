package com.ibm.kafkastream.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductReservationProducerStream {

	String PRODUCT_RESRVE_PRODUCER= "product-reserve-consumer";
			    
	 @Output(PRODUCT_RESRVE_PRODUCER)
	 MessageChannel publishProductReserve();


		String PRODUCT_RESRVE_STATUS_PRODUCER= "product-status-producer";
				    
		 @Output(PRODUCT_RESRVE_STATUS_PRODUCER)
		 MessageChannel publishProductReserveStatus();

}
