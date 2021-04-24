package com.ibm.kafkastream.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProductReservationProducerStream {
	//String PAYMENT_INPUT="payment_listener_event";
	String PRODUCT_RESRVE_PRODUCER= "product-reserve-consumer";
	
	//@Input(PAYMENT_INPUT)
	// SubscribableChannel payment();
	    
	 @Output(PRODUCT_RESRVE_PRODUCER)
	 MessageChannel publishProductReserve();


}
