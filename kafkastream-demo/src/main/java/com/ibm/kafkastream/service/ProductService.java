package com.ibm.kafkastream.service;

import com.ibm.kafkastream.model.Product;
import com.ibm.kafkastream.repository.ProductRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


	@Autowired
	ProductRepository productRepository;
	
	public Optional<Product> getProduct(Long productCode)
	
	{
		
		return productRepository.findById(productCode);
	}
	
public Optional<Product> getProductByName(String productName)
	
	{
		
		return productRepository.findByName(productName);
	}

}
