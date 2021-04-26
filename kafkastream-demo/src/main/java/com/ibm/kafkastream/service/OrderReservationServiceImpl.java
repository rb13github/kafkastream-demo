package com.ibm.kafkastream.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.ibm.kafkastream.convertor.OrderReservationConverter;
import com.ibm.kafkastream.model.OrderInfoEventDTO;
import com.ibm.kafkastream.model.OrderReservedStatusDTO;
import com.ibm.kafkastream.model.OrderReservedStatusDTODetail;
import com.ibm.kafkastream.model.Product;
import com.ibm.kafkastream.model.ProductDTO;
import com.ibm.kafkastream.model.ProductListenerDTO;
import com.ibm.kafkastream.stream.ProductReservationProducerStream;

//import com.ibm.orchestator.order.converter.OrderInfoConverter;
//import com.ibm.orchestator.order.entity.OrderInfo;
//import com.ibm.orchestator.order.model.OrderInfoDTO;
//import com.ibm.orchestator.order.model.OrderInfoEventDTO;
//import com.ibm.orchestator.order.repository.OrderInfoRepository;
//import com.ibm.orchestator.order.service.OrderOrchestrationService;
//import com.ibm.orchestator.order.stream.OrderEventStream;

@Service
public class OrderReservationServiceImpl implements OrderReservationService {

	
	private final Logger logger =org.slf4j.LoggerFactory.getLogger(this.getClass());
//	@Autowired
//	OrderInfoRepository orderInfoRepository;
//	
//	OrderInfoEventDTO eventDto=null;
	@Value("${bindings.product-status-producer.destination}") 
	String topic_product_status_producer;
	
	@Value("${bindings.product-reserve-consumer.destination}")
	String topic_product_reserve_consumer;
	
    private ProductReservationProducerStream productReservationProducerStream;
    
    @Autowired
    private ProductService productService;
    
    public OrderReservationServiceImpl(ProductReservationProducerStream productReservationProducerStream) {
    	this.productReservationProducerStream= productReservationProducerStream;
    }
	
    @Override
	public OrderReservedStatusDTO create(OrderInfoEventDTO dto) {
    	
    	System.out.println(" OrderReservationServiceImpl create:IN  ");
    	
    	logger.info(" logger OrderReservationServiceImpl create:IN" );
    	//publish the DTO on topic
    	publishProductReservationEvent(dto);
		
		OrderReservationConverter converter=new OrderReservationConverter();
		OrderReservedStatusDTO orderReservedStatusDTO=converter.convert(dto);
		
		
    	System.out.println(" OrderReservationServiceImpl create:OUT  ");
		return orderReservedStatusDTO;
		
		
	}
    
    @Override
   	public OrderReservedStatusDTO checkReservation(OrderInfoEventDTO dto) {

    	System.out.println(" OrderReservationServiceImpl checkReservation : IN  ");
   		
   		OrderReservationConverter converter=new OrderReservationConverter();
   		OrderReservedStatusDTODetail orderReservedStatusDTODetail=converter.convert(dto);
   		
   		List<Product> listProduct=orderReservedStatusDTODetail.getProducts();
   		
   		
   		//List<Product> listProduct=new ArrayList<>();
		for(Product product:listProduct) {
			
			Optional<Product>productdb=	productService.getProductByName(product.getName());
			String reserveStatus="";
			if(productdb.isPresent())
			{
			System.out.println(" OrderReservationServiceImpl checkReservation fetch from DB quantity:   "+productdb.get().getQuantity());
			
			System.out.println(" OrderReservationServiceImpl checkReservation quantity in order:   "+product.getQuantity());
			
			//entity.setPrice(orderProductDTO.getPrice());
			
			//TODO: Event publisher order service should pass this value
			//entity.setQuantity(product.getQuantity());
			
			Long balanceQuantitiy=(productdb.get().getQuantity())-product.getQuantity();
			System.out.println(" OrderReservationServiceImpl checkReservation fetch from DB balanceQuantitiy:   "+balanceQuantitiy);
			
		
			if (balanceQuantitiy > 0 )		{
				
				reserveStatus="RESERVE";
			orderReservedStatusDTODetail.setProductStatus(reserveStatus);
			
			System.out.println(" OrderReservationServiceImpl checkReservation status "+reserveStatus);
			}
			else {
				reserveStatus="QUANTITY is FULL UNABLE to reserve";
				orderReservedStatusDTODetail.setProductStatus(reserveStatus);
				
				System.out.println(" OrderReservationServiceImpl checkReservation status "+reserveStatus);
			
			}
			
		}
			else			{
				
				reserveStatus="PRODUCT NOT UNAVAILABLE";
				orderReservedStatusDTODetail.setProductStatus(reserveStatus);
				
				System.out.println(" OrderReservationServiceImpl checkReservation status "+reserveStatus);
				
			}
		}
		
		
		
		OrderReservedStatusDTO	orderReservedStatusDTO= converter.convert(orderReservedStatusDTODetail);
   		
     	//publish the DTO on topic
   		publishOrderReservationStatusEvent(orderReservedStatusDTO);
       	
   		System.out.println(" OrderReservationServiceImpl checkReservation : OUT  ");
   		return orderReservedStatusDTO;
   		//return null;
   	}
       
    public void publishOrderReservationStatusEvent(final OrderReservedStatusDTO dto) {
        
    	System.out.println(" publishOrderReservationStatusEvent IN  ");
    	
        //	dto.setAction("RESERVE");
    	//TODO: publish in out queue ofr order service to consume
            MessageChannel messageChannel = productReservationProducerStream.publishProductReserveStatus();
            messageChannel.send(MessageBuilder
                    .withPayload(dto)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build());
            	System.out.println("publish OrderReservedStatusDTO   "+dto );
            	System.out.println("publish Topic property "+productReservationProducerStream.PRODUCT_RESRVE_STATUS_PRODUCER);
            
            	System.out.println("publish on topic (bindings.product-status-producer.destination)  "+topic_product_status_producer );
        }
    
    
	
    public void publishProductReservationEvent(final OrderInfoEventDTO dto) {
        
    	System.out.println(" publishProductReservationEvent IN  ");
    //	dto.setAction("RESERVE");
        MessageChannel messageChannel = productReservationProducerStream.publishProductReserve();
        messageChannel.send(MessageBuilder
                .withPayload(dto)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
        	System.out.println("publish OrderInfoEventDTO   "+dto);
        	System.out.println("publish Topic property  "+productReservationProducerStream.PRODUCT_RESRVE_PRODUCER);
        
        	System.out.println("publish on topic (bindings.product-reserve-consumer.destination)  "+topic_product_reserve_consumer );
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
