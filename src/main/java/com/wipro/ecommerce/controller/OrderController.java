package com.wipro.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecommerce.dto.OrderDTO;
import com.wipro.ecommerce.dto.OrderItemDTO;
import com.wipro.ecommerce.dto.PaymentDTO;
import com.wipro.ecommerce.entity.Order;
import com.wipro.ecommerce.entity.OrderItem;
import com.wipro.ecommerce.entity.Payment;
import com.wipro.ecommerce.service.OrderItemServiceImp;
import com.wipro.ecommerce.service.OrderServiceImp;
import com.wipro.ecommerce.service.PaymentServiceImp;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	@Autowired
	OrderServiceImp orderServiceImp;
	
	@Autowired
	OrderItemServiceImp orderItemServiceImp;
	
	 @Autowired
	   private PaymentServiceImp paymentServiceImp;
	
	@PostMapping("/addOrder")
	public Order addOrder(@RequestBody OrderDTO orderDTO) {
		return orderServiceImp.addOrder(orderDTO);
		
	}
	
	  @PostMapping("/addPayment")
	    public Payment addPayment(@RequestBody @Valid PaymentDTO paymentDTO) {
	        return paymentServiceImp.addPayment(paymentDTO);
	    }
	  
	  @PostMapping("/addOrderItem")
		public OrderItem addOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
			return orderItemServiceImp.addOrderItem(orderItemDTO);
		}
	  
	  @PutMapping("/updateOrderItem")
		public OrderItem updateOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
			return orderItemServiceImp.updateOrderItem(orderItemDTO);
		}

}
