package com.ibm.kafkastream.convertor;

import java.util.ArrayList;
import java.util.List;


import com.ibm.kafkastream.model.ProductListenerDTO;
import com.ibm.kafkastream.model.ProductReservedStatusDTO;
//import com.ibm.orchestator.order.entity.OrderInfo;
//import com.ibm.orchestator.order.entity.Product;
//import com.ibm.orchestator.order.model.OrderInfoDTO;
//import com.ibm.orchestator.order.model.OrderInfoEventDTO;
//import com.ibm.orchestator.order.model.ProductDTO;
//import com.ibm.orchestator.order.util.OrderStatusEnum;

public class ProductReservationConverter {
	
	
	
	public ProductReservedStatusDTO convert(ProductListenerDTO dto) {
		
		ProductReservedStatusDTO entity=new ProductReservedStatusDTO();
		
		entity.setCustomerNo(dto.getCustomerNo());
		entity.setOrderNo(dto.getOrderNo());
	//	List<ProductDTO> productDto=dto.getProduct();
		
	//	entity.setProduct(productConvert(productDto));
		//entity.setTotalPrice(dto.getTotalPrice());
		entity.setProductStatus("RESERVE");
		
		return entity;
		
	}
	
//	public List<Product> productConvert(List<ProductDTO> productDTO){
//		List<Product> listProduct=new ArrayList<>();
//		for(ProductDTO product:productDTO) {
//			Product entity=new Product();
//			entity.setProductName(product.getProductName());
//			entity.setPrice(product.getPrice());
//			entity.setQuantity(product.getQuantity());
//			listProduct.add(entity);
//		}
//		
//		return listProduct;
//	}
	
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
