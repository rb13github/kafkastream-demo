package com.ibm.kafkastream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ibm.kafkastream.model.ProductListenerDTO;
import com.ibm.kafkastream.model.ProductReservedStatusDTO;
import com.ibm.kafkastream.service.ProductReservationService;



import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("reserve")
public class ProductReservedController {
	
	@Autowired
	ProductReservationService orderOrchestrationService;
	
	@PostMapping("/")
	@ApiOperation("This will reserve product entity")
	public ResponseEntity<ProductReservedStatusDTO> order(@RequestBody ProductListenerDTO dto) {
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orderOrchestrationService.create(dto));
	}

}