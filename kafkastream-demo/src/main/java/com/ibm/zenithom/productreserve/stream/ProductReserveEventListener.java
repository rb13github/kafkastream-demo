package com.ibm.zenithom.productreserve.stream;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.ibm.zenithom.productreserve.model.OrderInfoEventDTO;
import com.ibm.zenithom.productreserve.model.ProductListenerDTO;
import com.ibm.zenithom.productreserve.service.OrderReservationService;

/*This class is will used to listen to the topic which mentioned the PRODUCT_RESRVE_CONSUME_INPUT
 * and consume the event. 
 * It will then call the checkreservation to match the product info. 
 * if that product is availsalble then it will set the out put accordingly.
 * 
 */
@Component
public class ProductReserveEventListener {
	
	
	
	@Autowired
	OrderReservationService orderReservationService;
	
	@SuppressWarnings("deprecation")
	@StreamListener(ProductReservationConsumerStream.PRODUCT_RESRVE_CONSUME_INPUT)
    public void handle(Message<OrderInfoEventDTO> m) {
		
		OrderInfoEventDTO payload = m.getPayload();
		

	       System.out.println(" consume event from topic :ProductReserveEventListener.handle()  Mesage ProductEventListener OrderInfoEventDTO Payload on Consumer:  "+payload);
	       
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
