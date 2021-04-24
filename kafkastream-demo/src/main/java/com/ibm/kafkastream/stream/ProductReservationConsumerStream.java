package com.ibm.kafkastream.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ProductReservationConsumerStream {
	//String PAYMENT_INPUT="payment_listener_event";
	String PRODUCT_RESRVE_CONSUME_INPUT= "product-reserve-consumer";
	
	//@Input(PAYMENT_INPUT)
	// SubscribableChannel payment();
	    
	 @Input(PRODUCT_RESRVE_CONSUME_INPUT)
	 SubscribableChannel product();


}
