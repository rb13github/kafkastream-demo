package com.ibm.kafkastream.convertor;

import java.util.ArrayList;
import java.util.List;

import com.ibm.kafkastream.model.OrderInfoEventDTO;
import com.ibm.kafkastream.model.OrderReservedStatusDTO;
import com.ibm.kafkastream.model.OrderReservedStatusDTODetail;
import com.ibm.kafkastream.model.Product;
import com.ibm.kafkastream.model.ProductDTO;
//import com.ibm.orchestator.order.entity.OrderInfo;
//import com.ibm.orchestator.order.entity.Product;
//import com.ibm.orchestator.order.model.OrderInfoDTO;
//import com.ibm.orchestator.order.model.OrderInfoEventDTO;
//import com.ibm.orchestator.order.model.ProductDTO;
//import com.ibm.orchestator.order.util.OrderStatusEnum;

public class OrderReservationConverter {
	
	
	
	public OrderReservedStatusDTODetail convert(OrderInfoEventDTO dto) {
		
		OrderReservedStatusDTODetail entity=new OrderReservedStatusDTODetail();
		
		entity.setCustomerNo(dto.getCustomerNumber());
		entity.setOrderNo(dto.getOrderNumber());
	    List<ProductDTO> productDto=dto.getProduct();
		
	     entity.setProducts(productConvert(productDto));
		//entity.setTotalPrice(dto.getTotalPrice());
		entity.setProductStatus("RESERVATION INITIATED");
		
		return entity;
		
	}
	
	public List<Product> productConvert(List<ProductDTO> productDTO){
		List<Product> listProduct=new ArrayList<>();
		for(ProductDTO orderProductDTO:productDTO) {
			Product entity=new Product();
			//TODO: Event puhlisher should pass this value currently on name is passed
			entity.setId((long) 101);
			
			//TODO: as id is not passed by order service this is used to check the  quantity
			entity.setName(orderProductDTO.getProductName());
			
			entity.setPrice(orderProductDTO.getPrice().floatValue());
			
			//TODO: Event publisher order service should pass this value
			entity.setQuantity(orderProductDTO.getQuantity());
			listProduct.add(entity);
		}
		
		return listProduct;
	}
	
	
public OrderReservedStatusDTO convert(OrderReservedStatusDTODetail dto) {
		
	OrderReservedStatusDTO orderReservedStatusDTO=new OrderReservedStatusDTO();
		
	orderReservedStatusDTO.setCustomerNo(dto.getCustomerNo());
	orderReservedStatusDTO.setOrderNo(dto.getOrderNo());
	    
		//entity.setTotalPrice(dto.getTotalPrice());
	orderReservedStatusDTO.setProductStatus(dto.getProductStatus());
		
		return orderReservedStatusDTO;
		
	}


//	public OrderInfoEventDTO convertToDTO(OrderInfo entity) {
//		
//		OrderInfoEventDTO dto=new OrderInfoEventDTO();
//		dto.setCustomerNumber(entity.getCustomerNumber());
//		dto.setOrderNumber(entity.getOrderNumber());
//		List<Product> product=entity.getProduct();
//		dto.setProduct(productDToConvert(product));
//		dto.setOrderStatus(entity.getStatus());
//		dto.setTotalPrice(entity.getTotalPrice());
//		
//		return dto;
//		
//	}
//	
//	public List<ProductDTO> productDToConvert(List<Product> product){
//		List<ProductDTO> listProductDTO=new ArrayList<>();
//		for(Product product1:product) {
//			ProductDTO dto=new ProductDTO();
//			dto.setProductName(product1.getProductName());
//			dto.setPrice(product1.getPrice());
//			dto.setQuantity(product1.getQuantity());
//			listProductDTO.add(dto);
//		}
//		
//		return listProductDTO;
//	}

}
