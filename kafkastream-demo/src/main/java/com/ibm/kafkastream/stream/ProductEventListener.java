package com.ibm.kafkastream.stream;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.ibm.kafkastream.model.OrderInfoEventDTO;
import com.ibm.kafkastream.model.ProductListenerDTO;
import com.ibm.kafkastream.service.OrderReservationService;


@Component
public class ProductEventListener {
	
	private final String prodCon="RESERVED";
	
	private final String prodRej="REJECTED";
	
	@Autowired
	OrderReservationService orderReservationService;
	
	@SuppressWarnings("deprecation")
	@StreamListener(ProductReservationConsumerStream.PRODUCT_RESRVE_CONSUME_INPUT)
    public void handle(Message<OrderInfoEventDTO> m) {
		
		OrderInfoEventDTO payload = m.getPayload();
		

	       System.out.println(" handle  Mesage ProductEventListener OrderInfoEventDTO Payload on Consumer:  "+payload);
	       
	      orderReservationService.checkReservation(payload);
	       
//		
//		Optional<OrderInfo> orderInfo;
//		
//		if(null!=payload) {
//			
//			orderInfo= orderInfoRepository.findByOrderNumber(payload.getOrderNo());
//			
//			if(null!= orderInfo &&  payload.getProductStatus().equals(prodCon)) {
//				orderInfo.get().setStatus(OrderStatusEnum.ProdConf.status);
//				
//			}
//		
//			else if(payload.getProductStatus().equals(prodRej)) {
//				orderInfo.get().setStatus(OrderStatusEnum.ProdNAva.status);
//				
//			}
//			orderInfoRepository.save(orderInfo.get());
//		
//		}



		
	}


}
