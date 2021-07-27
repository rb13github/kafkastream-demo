package com.ibm.zenithom.productreserve.service;
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

import com.ibm.zenithom.productreserve.convertor.OrderReservationConverter;
import com.ibm.zenithom.productreserve.model.OrderInfoEventDTO;
import com.ibm.zenithom.productreserve.model.OrderReservedStatusDTO;
import com.ibm.zenithom.productreserve.model.OrderReservedStatusDTODetail;
import com.ibm.zenithom.productreserve.model.Product;
import com.ibm.zenithom.productreserve.model.ProductDTO;
import com.ibm.zenithom.productreserve.model.ProductListenerDTO;
import com.ibm.zenithom.productreserve.stream.ProductReservationProducerStream;

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
	//product found and reserved
	@Value("${product.status.reserve}")
   String prodReserved;
    //product not found		
	@Value("${product.status.reject}")
	String prodRejected;
	
//	@Autowired
//	OrderInfoRepository orderInfoRepository;
//	
//	OrderInfoEventDTO eventDto=null;
	@Value("${spring.cloud.stream.bindings.product-status-producer.destination}") 
	String topic_product_status_producer;
	
	@Value("${spring.cloud.stream.bindings.product-reserve-consumer.destination}")
	String topic_product_reserve_consumer;
	
    private ProductReservationProducerStream productReservationProducerStream;
    
    @Autowired
    private ProductService productService;
    
    public OrderReservationServiceImpl(ProductReservationProducerStream productReservationProducerStream) {
    	this.productReservationProducerStream= productReservationProducerStream;
    }
	
    @Override
	public OrderReservedStatusDTO create(OrderInfoEventDTO dto) {
    	
    	System.out.println(" OrderReservationServiceImpl create:START ");
    	
    	logger.info(" logger OrderReservationServiceImpl create:START" );
    	//publish the DTO on topic
    	publishProductReservationEvent(dto);
		
		OrderReservationConverter converter=new OrderReservationConverter();
		OrderReservedStatusDTO orderReservedStatusDTO=converter.convert(dto);
		
		
    	System.out.println(" OrderReservationServiceImpl create:END  ");
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
				
				
			orderReservedStatusDTODetail.setProductStatus(prodReserved);
			
			System.out.println(" OrderReservationServiceImpl checkReservation PRODUCT FOUND status "+prodReserved);
			}
			else {
				
				orderReservedStatusDTODetail.setProductStatus(prodRejected);
				
				System.out.println(" OrderReservationServiceImpl  checkReservation PRODUCT QUANTITY is zero status "+prodRejected);
			
			}
			
		}
			else			{
				
				
				orderReservedStatusDTODetail.setProductStatus(prodRejected);
				
				System.out.println(" OrderReservationServiceImpl checkReservation PRODUCT NOT FOUND status "+prodRejected);
				
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
            	System.out.println("publish OrderReservedStatusDTO   "+dto.toString() );
            	System.out.println("publish Topic property "+productReservationProducerStream.PRODUCT_RESRVE_STATUS_PRODUCER);
            
            	System.out.println("publish on topic (bindings.product-status-producer.destination)  "+topic_product_status_producer );
        }
    
    
	
    public void publishProductReservationEvent(final OrderInfoEventDTO dto) {
        
    	System.out.println(" OrderReservationServiceImpl.publishProductReservationEvent :START  ");
    //	dto.setAction("RESERVE");
        MessageChannel messageChannel = productReservationProducerStream.publishProductReserve();
       
        System.out.println("publish to Topic:BEFORE");
        messageChannel.send(MessageBuilder
                .withPayload(dto)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
        System.out.println("publish tp Topic:AFTER");
        	System.out.println("published  OrderInfoEventDTO   "+dto);
        	System.out.println("publish Topic property  "+productReservationProducerStream.PRODUCT_RESRVE_PRODUCER);
        
        	System.out.println("publish on topic (bindings.product-reserve-consumer.destination)  "+topic_product_reserve_consumer );
        	
        	System.out.println(" OrderReservationServiceImpl.publishProductReservationEvent :END  ");
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
