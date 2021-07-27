package com.ibm.zenithom.productreserve.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ProductReservationStatusProducerStream {
	//String PAYMENT_INPUT="payment_listener_event";
String PRODUCT_STATUS_PRODUCER= "product-status-producer";
	

	    @Output(PRODUCT_STATUS_PRODUCER)
	    MessageChannel publishStatus();
	    
	


}
