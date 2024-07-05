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

@CrossOrigin("localhost://4200")
@RestController
@RequestMapping("api/seller")
public class SellerRestController {
	
	 private static final Logger log = LoggerFactory.getLogger(SellerRestController.class);
	@Autowired
	ISellerService service;
//	@Autowired
//	ProductDTO productdto;
//	@Autowired
//	JwtService jwtService;
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IPaymentService paymentService;
	
//	@Autowired
//	AuthenticationManager authenticationManager;
	
	@Autowired 
	IOrderItemService orderItemService;
	
	@Autowired
	ISubCategoryService subCategoryService;
	
	
//	@PostMapping("/login/authenticate")
//	public Object  authenticateAndGetTokent(@RequestBody  AuthRequest authRequest) throws SellerNotFoundException {
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//		String token = null;
//		if(authentication.isAuthenticated()) {
//				  // call generate token method from jwtService class	
//			     token=jwtService.generateToken(authRequest.getUsername());		
//			
//			log.info("Tokent : "+token);
//			  }
//				else{
//					
//					log.info("invalid");
//			
//					 throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");	
//				}
//		Optional<Seller> seller=service.fetchSellerDetails(authRequest.getUsername());
//		
//		 Map<String, Object> object = new HashMap<>();
//		 object.put("token", token);
//		 object.put("data", seller);
//				return object;	 
//	 }
	
	@PostMapping("/register")
	public SellerDTO registerSeller(@RequestBody @Valid SellerDTO sellerDTO) {
		return service.registerSeller(sellerDTO);
	}
	

	@PutMapping("/update")
	//@PreAuthorize("hasAuthority('seller')")
    public Seller updateSeller(@RequestBody @Valid  SellerDTO sellerDTO) throws SellerNotFoundException{

		return service.updateSeller(sellerDTO);
	}


	@GetMapping("/getAllOrder")
	//@PreAuthorize("hasAuthority('seller')")
	public List<Order> getAllOrder(){
		return service.getAllOrder();
	}
	
	@GetMapping("/getAllProduct")
	//@PreAuthorize("hasAuthority('seller')")
	public List<Product> getAllProduct(){
		return service.getAllProduct();
	}
	
	@GetMapping("/getAllCategory")
	//@PreAuthorize("hasAuthority('seller')")
	public List<Category> getAllCategory(){
		return service.getAllCategory();
	}
	
	@GetMapping("/getAllSubCategory")
	//@PreAuthorize("hasAuthority('seller')")
	public List<SubCategory> getAllSubCategory(){
		return service.getAllSubCategory();
	}
	
	@PostMapping("/addProduct")
	//@PreAuthorize("hasAuthority('seller')")
	public Product addProduct(@RequestBody ProductDTO productdto) throws SellerNotFoundException, SubCategoryNotFoundException {
		SellerDTO sellerdto = service.getSellerById(productdto.getSellerId());
		Seller seller = service.updateSeller(sellerdto);
		SubCategoryDTO subCategoryDto = subCategoryService.getSubCategoryById(productdto.getSubCateegoryId());
		SubCategory subCategory = subCategoryService.updateSubCategory(subCategoryDto);
	    productdto.setSeller(seller);
	    productdto.setSubCategory(subCategory);
		return service.addProduct(productdto);
	}
	
	@PostMapping("/updateProduct")
	//@PreAuthorize("hasAuthority('seller')")
	public Product updateProduct(@RequestBody ProductDTO productdto) throws ProductNotFoundException, SellerNotFoundException, SubCategoryNotFoundException {
		SellerDTO sellerdto = service.getSellerById(productdto.getSellerId());
		Seller seller = service.updateSeller(sellerdto);
		SubCategoryDTO subCategoryDto = subCategoryService.getSubCategoryById(productdto.getSubCateegoryId());
		SubCategory subCategory = subCategoryService.updateSubCategory(subCategoryDto);
	    productdto.setSeller(seller);
	    productdto.setSubCategory(subCategory);
		return service.updateProduct(productdto);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	//@PreAuthorize("hasAuthority('seller')")
	public String deleteProduct(@PathVariable int id) throws ProductNotFoundException {
		return service.deleteProduct(id);
	}
	
	@GetMapping("/getProductByName/{name}")
	//@PreAuthorize("hasAuthority('seller')")
	public Product getProductbyName(@PathVariable String name) throws ProductNotFoundException {
		return service.getProductbyName(name);
	}
	
	@GetMapping("/getProductByID/{id}")
	//@PreAuthorize("hasAuthority('seller')")
	public ProductDTO getProductById(@PathVariable int id) throws ProductNotFoundException {
		return service.getProductById(id);
	}
	
	@GetMapping("/markProductOutOfStock/{sellerId}/{productId}")
	//@PreAuthorize("hasAuthority('seller')")
	public ProductDTO markProductOutOfStock(@PathVariable int sellerId,@PathVariable int productId) throws ProductNotFoundException{
		return service.markProductOutOfStock(sellerId, productId);
	}
	@GetMapping("/viewMyProducts/{sellerId}")
	//@PreAuthorize("hasAuthority('seller')")
	public List<Product> viewMyProducts(@PathVariable int sellerId) throws ProductNotFoundException{
		return service.viewMyProducts(sellerId);
	}
	
	@GetMapping("/getSellerById/{sellerId}")
	//@PreAuthorize("hasAuthority('seller')")
	public SellerDTO getSellerById(@PathVariable int sellerId) throws SellerNotFoundException{
		return service.getSellerById(sellerId);
	}
	
	@GetMapping("/getSubCategoryById/{subCategoryId}")
	//@PreAuthorize("hasAuthority('seller')")
	public SubCategoryDTO getSubcategoryById(@PathVariable int subCategoryId) throws SubCategoryNotFoundException{
		return service.getSubcategoryById(subCategoryId);
	}
	
	@GetMapping("/getOrdersBySellerId/{sellerId}")
	//@PreAuthorize("hasAuthority('seller')")
	public List<Integer> getOrdersBySellerId(@PathVariable int sellerId){
		return service.getOrdersBySellerId(sellerId);
	}
	
	@GetMapping("/getOrdersDetailsBySellerId/{sellerId}")
	//@PreAuthorize("hasAuthority('seller')")
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
	//@PreAuthorize("hasAuthority('seller')")
	 public Order updateOrder(@RequestBody OrderDTO orderDTO) throws OrderNotFoundException{
		return orderService.updateOrder(orderDTO);
	}
	
	@GetMapping("/getPaymentDetailsBySellerId/{sellerId}")
	//@PreAuthorize("hasAuthority('seller')")
	public List<Integer> getPaymentsOfSeller(@PathVariable int sellerId){
		return service.getPaymentsOfSeller(sellerId);
	}
	
	@GetMapping("/viewMyPayments/{sellerId}")
	//@PreAuthorize("hasAuthority('seller')")
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
	//@PreAuthorize("hasAuthority('seller')")
	public List<OrderItem> viewAllOrderItemsOfSellerInOrder(@PathVariable int orderId,@PathVariable int sellerId){
		return orderItemService.viewAllOrderItemsOfSellerInOrder(orderId, sellerId);
	}
	
	@PutMapping("updateOrderItem")
	//@PreAuthorize("hasAuthority('seller')")
	public OrderItem updateOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
		return orderItemService.updateOrderItem(orderItemDTO);
	}
	
	@GetMapping("/getOrderById/{orderId}")
	//@PreAuthorize("hasAuthority('seller')")
	public OrderDTO getOrderById(@PathVariable int orderId) throws OrderNotFoundException {
		return orderService.getOrderById(orderId);
	}
	


}
