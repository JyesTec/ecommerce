package com.wipro.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecommerce.dto.OrderDTO;
import com.wipro.ecommerce.dto.OrderItemDTO;
import com.wipro.ecommerce.dto.PaymentDTO;
import com.wipro.ecommerce.dto.ProductDTO;
import com.wipro.ecommerce.dto.SellerDTO;
import com.wipro.ecommerce.dto.SubCategoryDTO;
import com.wipro.ecommerce.entity.Category;
import com.wipro.ecommerce.entity.Order;
import com.wipro.ecommerce.entity.OrderItem;
import com.wipro.ecommerce.entity.Product;
import com.wipro.ecommerce.entity.Seller;
import com.wipro.ecommerce.entity.SubCategory;
import com.wipro.ecommerce.exception.OrderNotFoundException;
import com.wipro.ecommerce.exception.ProductNotFoundException;
import com.wipro.ecommerce.exception.SellerNotFoundException;
import com.wipro.ecommerce.exception.SubCategoryNotFoundException;
import com.wipro.ecommerce.service.IOrderItemService;
import com.wipro.ecommerce.service.IOrderService;
import com.wipro.ecommerce.service.IPaymentService;
import com.wipro.ecommerce.service.ISellerService;
import com.wipro.ecommerce.service.ISubCategoryService;

import jakarta.validation.Valid;

//@CrossOrigin("localhost://4200")
@RestController
@RequestMapping("api/seller")
public class SellerRestController {
	
	 private static final Logger log = LoggerFactory.getLogger(SellerRestController.class);
	@Autowired
	ISellerService service;

	@Autowired
	IOrderService orderService;
	
	@Autowired
	IPaymentService paymentService;
	
	@Autowired 
	IOrderItemService orderItemService;
	
	@Autowired
	ISubCategoryService subCategoryService;
	
	//run
	@PostMapping("/register")
	public SellerDTO registerSeller(@RequestBody @Valid SellerDTO sellerDTO) {
		return service.registerSeller(sellerDTO);
	}
	
    //run
	@PutMapping("/update")
    public Seller updateSeller(@RequestBody @Valid  SellerDTO sellerDTO) throws SellerNotFoundException{

		return service.updateSeller(sellerDTO);
	}


	@GetMapping("/getAllOrder")
	public List<Order> getAllOrder(){
		return service.getAllOrder();
	}
	
	
	//run
	@GetMapping("/getAllCategory")
	public List<Category> getAllCategory(){
		return service.getAllCategory();
	}
	
	//run
	@GetMapping("/getAllSubCategory")
	public List<SubCategory> getAllSubCategory(){
		return service.getAllSubCategory();
	}
	
	@GetMapping("/markProductOutOfStock/{sellerId}/{productId}")
	public ProductDTO markProductOutOfStock(@PathVariable int sellerId,@PathVariable int productId) throws ProductNotFoundException{
		return service.markProductOutOfStock(sellerId, productId);
	}
	
	//run
	@GetMapping("/viewMyProducts/{sellerId}")
	public List<Product> viewMyProducts(@PathVariable int sellerId) throws ProductNotFoundException{
		return service.viewMyProducts(sellerId);
	}
	
	//run
	@GetMapping("/getSellerById/{sellerId}")
	public SellerDTO getSellerById(@PathVariable int sellerId) throws SellerNotFoundException{
		return service.getSellerById(sellerId);
	}
	
	//run
	@GetMapping("/getSubCategoryById/{subCategoryId}")
	public SubCategoryDTO getSubcategoryById(@PathVariable int subCategoryId) throws SubCategoryNotFoundException{
		return service.getSubcategoryById(subCategoryId);
	}
	
	
	@GetMapping("/getOrdersBySellerId/{sellerId}")
	public List<Integer> getOrdersBySellerId(@PathVariable int sellerId){
		return service.getOrdersBySellerId(sellerId);
	}
	
	
	@GetMapping("/getOrdersDetailsBySellerId/{sellerId}")
	public List<OrderDTO> getOrdersDetailsBySellerId(@PathVariable int sellerId) throws OrderNotFoundException {
	    List<Integer> orderIds = service.getOrdersBySellerId(sellerId);
	    List<OrderDTO> orderDetails = new ArrayList<>();
	    for (Integer orderId : orderIds) {
	        OrderDTO order = orderService.getOrderById(orderId);
	        orderDetails.add(order);
	    }  
	    return orderDetails;
	}
	
	
	@PutMapping("/updateOrder")
	 public Order updateOrder(@RequestBody OrderDTO orderDTO) throws OrderNotFoundException{
		return orderService.updateOrder(orderDTO);
	}
	
	
	@GetMapping("/getPaymentDetailsBySellerId/{sellerId}")
	public List<Integer> getPaymentsOfSeller(@PathVariable int sellerId){
		return service.getPaymentsOfSeller(sellerId);
	}
	
	
	@GetMapping("/viewMyPayments/{sellerId}")
	public List<PaymentDTO> viewMyPayments(@PathVariable int sellerId){
		List<Integer> paymentIds = service.getPaymentsOfSeller(sellerId);
		List<PaymentDTO> paymentDetails = new ArrayList<>();
		for(Integer paymentId : paymentIds) {
			PaymentDTO paymentDTO = paymentService.getPaymentById(paymentId);
			paymentDetails.add(paymentDTO);
		}
		return paymentDetails;
	}
	
	@GetMapping("viewAllOrderItemsOfSellerInOrder/{orderId}/{sellerId}")
	public List<OrderItem> viewAllOrderItemsOfSellerInOrder(@PathVariable int orderId,@PathVariable int sellerId){
		return orderItemService.viewAllOrderItemsOfSellerInOrder(orderId, sellerId);
	}
	
	
	
	@GetMapping("/getOrderById/{orderId}")
	public OrderDTO getOrderById(@PathVariable int orderId) throws OrderNotFoundException {
		return orderService.getOrderById(orderId);
	}
	


}
