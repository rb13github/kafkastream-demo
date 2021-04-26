package com.ibm.kafkastream.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.kafkastream.model.OrderInfoEventDTO;
import com.ibm.kafkastream.model.OrderReservedStatusDTO;
import com.ibm.kafkastream.model.ProductListenerDTO;
import com.ibm.kafkastream.service.OrderReservationService;




import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("reserve")
public class ProductReservedController {
	
	@Autowired
	OrderReservationService orderReservationService;
	
	@PostMapping("/")
	@ApiOperation("This will reserve products under Order entity")
	public ResponseEntity<OrderReservedStatusDTO> order(@RequestBody OrderInfoEventDTO dto) {
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orderReservationService.create(dto));
	}

}
