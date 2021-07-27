package com.ibm.zenithom.productreserve.service;

import com.ibm.zenithom.productreserve.model.Product;
import com.ibm.zenithom.productreserve.repository.ProductRepository;

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
