package com.ibm.zenithom.productreserve.stream;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.ibm.zenithom.productreserve.model.OrderInfoEventDTO;
import com.ibm.zenithom.productreserve.model.ProductListenerDTO;
import com.ibm.zenithom.productreserve.service.OrderReservationService;

/*This class is used to log the event for Topic
 */
@Component
public class TopicEventLoggerListener {
	
	
	
		
//	@SuppressWarnings("deprecation")
	//@StreamListener(ProductReservationConsumerStream.PRODUCT_RESRVE_CONSUME_INPUT)
    public void handle(Message<OrderInfoEventDTO> m) {
		
		OrderInfoEventDTO payload = m.getPayload();
		

	       System.out.println(" consume event from topic :TopicEventLoggerListener.handle()   OrderInfoEventDTO Payload on Consumer:  "+payload);
	       
	      

		
	}


}
