package com.ibm.zenithom.productreserve.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/*
 * this will consume the event on from the Topic 
 * 
 */

public interface ProductReservationConsumerStream {
	//String PAYMENT_INPUT="payment_listener_event";
	String PRODUCT_RESRVE_CONSUME_INPUT= "product-reserve-consumer";
	
	//@Input(PAYMENT_INPUT)
	// SubscribableChannel payment();
	    
	 @Input(PRODUCT_RESRVE_CONSUME_INPUT)
	 SubscribableChannel consume_reserve_product();


}
