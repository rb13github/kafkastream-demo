package com.ibm.zenithom.productreserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.zenithom.productreserve.model.OrderInfoEventDTO;
import com.ibm.zenithom.productreserve.model.OrderReservedStatusDTO;
import com.ibm.zenithom.productreserve.model.ProductListenerDTO;
import com.ibm.zenithom.productreserve.service.OrderReservationService;

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
		
		 System.out.println(" ProductReservedController.order:START");
		return ResponseEntity.status(HttpStatus.CREATED).body(orderReservationService.create(dto));
	}

}
