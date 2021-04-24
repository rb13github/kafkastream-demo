package com.ibm.kafkastream.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.ibm.kafkastream.convertor.ProductReservationConverter;
import com.ibm.kafkastream.model.ProductListenerDTO;
import com.ibm.kafkastream.model.ProductReservedStatusDTO;
import com.ibm.kafkastream.stream.ProductReservationProducerStream;
//import com.ibm.orchestator.order.converter.OrderInfoConverter;
//import com.ibm.orchestator.order.entity.OrderInfo;
//import com.ibm.orchestator.order.model.OrderInfoDTO;
//import com.ibm.orchestator.order.model.OrderInfoEventDTO;
//import com.ibm.orchestator.order.repository.OrderInfoRepository;
//import com.ibm.orchestator.order.service.OrderOrchestrationService;
//import com.ibm.orchestator.order.stream.OrderEventStream;

@Service
public class ProductReservationServiceImpl implements ProductReservationService {

//	@Autowired
//	OrderInfoRepository orderInfoRepository;
//	
//	OrderInfoEventDTO eventDto=null;
	
    private ProductReservationProducerStream productReservationProducerStream;
    
    public ProductReservationServiceImpl(ProductReservationProducerStream productReservationProducerStream) {
    	this.productReservationProducerStream= productReservationProducerStream;
    }
	
    @Override
	public ProductReservedStatusDTO create(ProductListenerDTO dto) {

    	//publish the DTO on topic
    	publishProductReservationEvent(dto);
		
		ProductReservationConverter converter=new ProductReservationConverter();
		ProductReservedStatusDTO productReservedStatusDTO=converter.convert(dto);
		
		return productReservedStatusDTO;
		//return null;
	}
    
	
    public void publishProductReservationEvent(final ProductListenerDTO dto) {
        
    //	dto.setAction("RESERVE");
        MessageChannel messageChannel = productReservationProducerStream.publishProductReserve();
        messageChannel.send(MessageBuilder
                .withPayload(dto)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
        	System.out.println("publish DTO  "+dto);
        	System.out.println("publish Topic  "+productReservationProducerStream.PRODUCT_RESRVE_PRODUCER);
        
    }

	
//    public void orderEvent(final OrderInfoEventDTO dto) {
//       
//    	dto.setAction("RESERVE");
//        MessageChannel messageChannel = orderStreams.outboundOrder();
//        messageChannel.send(MessageBuilder
//                .withPayload(dto)
//                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
//                .build());
//        	System.out.println("sent "+dto);
//        
//    }
	
	
}
