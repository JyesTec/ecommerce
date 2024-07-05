package com.wipro.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecommerce.dto.CustomerDTO;
import com.wipro.ecommerce.entity.CartItem;
import com.wipro.ecommerce.entity.Category;
import com.wipro.ecommerce.entity.Order;
import com.wipro.ecommerce.entity.Product;
import com.wipro.ecommerce.entity.SubCategory;
import com.wipro.ecommerce.exception.OrderNotFoundException;
import com.wipro.ecommerce.exception.ProductNotFoundException;
import com.wipro.ecommerce.service.ICustomerService;

import jakarta.validation.Valid;
@CrossOrigin("localhost://4200")
@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
	private static final Logger log = LoggerFactory.getLogger(CustomerRestController.class);
	@Autowired
	ICustomerService service;
	
//	@Autowired
//	JwtService jwtService;
	
//	@Autowired
//	AuthenticationManager authenticationManager;
	
//	@PostMapping("/login/authenticate")
//	public Object  authenticateAndGetTokent(@RequestBody  AuthRequest authRequest) throws CustomerNotFoundException {
//		System.out.println("weeeeeeeeeeeeeeee");
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//		String token = null;
//		System.out.println("hiiiiiiiiiiii");
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
//	
//				
//				}
//		System.out.println("Token"+token);
//	Optional<Customer> customer=service.fetchCustomerDetails(authRequest.getUsername());
////	Optional<Customer> customer=service.fetchCustomerDetails(authRequest.getUsername());
//		System.out.println("customer"+customer);
//		 Map<String, Object> object = new HashMap<>();
//		 object.put("token", token);
//		 object.put("data", customer);
//				return object;	 
//	 }
	
	@PostMapping("/register")
	public String registerCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
		return service.registerCustomer(customerDTO);
	}
	
	@PostMapping("/addProductToCustomerCart/{customerId}/{productId}/{quantity}")
//	//@PreAuthorize("hasAuthority('customer')")
	public String addProductToCustomerCart(@PathVariable int customerId,@PathVariable int productId,@PathVariable int quantity) throws ProductNotFoundException{
		return service.addProductToCustomerCart(customerId, productId, quantity);
	}	
	
	
    @GetMapping("/viewAllProduct")
//    //@PreAuthorize("hasAuthority('customer')")
    public List<Product> getAllProduct(){
    	return service.getAllProduct();
    }
    
    @GetMapping("/viewAllCategory")
//    //@PreAuthorize("hasAuthority('customer')")
    public List<Category> getAllCategory(){
    	return service.getAllCategory();
    }
    
    @GetMapping("/viewAllSubCategory")
//    //@PreAuthorize("hasAuthority('customer')")
    public List<SubCategory> getAllSubCategory(){
    	return service.getAllSubCategory();
    }
    
    @GetMapping("/viewProductByName/{name}")
//    //@PreAuthorize("hasAuthority('customer')")
    public Product getProductByName(@PathVariable String name) {
    	return service.getProductByName(name);
    }
    
    
    @GetMapping("/viewCategoryByName/{name}")
//    //@PreAuthorize("hasAuthority('customer')")
    public Category getCategorybyName(@PathVariable String name) {
    	return service.getCategorybyName(name);
    }
    
    @GetMapping("/viewSubCategoryByName/{name}")
//    //@PreAuthorize("hasAuthority('customer')")
    public SubCategory getSubCategoryByName(@PathVariable String name) {
    	return service.getSubCategoryByName(name);
    }
    @GetMapping("/viewCartitems/{customerId}")
//    //@PreAuthorize("hasAuthority('customer')")
    public List<CartItem> viewCartitems(@PathVariable int customerId){
    	return service.viewCartitems(customerId);
    }

    @GetMapping("/viewProductByBrand/{brand}")
//    //@PreAuthorize("hasAuthority('customer')")
    public List<Product> getProductsByBrand(@PathVariable String brand){
    	return service.getProductsByBrand(brand);
    }
    
    @GetMapping("/viewProductByPriceRange/{min}/{max}")
//    //@PreAuthorize("hasAuthority('customer')")
    public List<Product> getProductsByPriceRange(@PathVariable double min,@PathVariable double max){
    	return service.getProductsByPriceRange(min, max);
    }
    @PostMapping("/deleteProductFromCustomerCart/{customerId}/{productId}")
    //@PreAuthorize("hasAuthority('customer')")
    public String deleteProductFromCustomerCart(@PathVariable int customerId,@PathVariable int productId) throws ProductNotFoundException {
    	return service.deleteProductFromCustomerCart(customerId, productId);
    }
    @GetMapping("/viewProductsBySubCategoryName/{subcategoryName}")
    //@PreAuthorize("hasAuthority('customer')")
    public List<Product> viewProductsBySubCategoryName(@PathVariable String subcategoryName){
    	return service.viewProductsBySubCategoryName(subcategoryName);
    }
    @GetMapping("/viewOrdersByCustomerId/{customerId}")
    //@PreAuthorize("hasAuthority('customer')")
    public List<Order> viewOrdersByCustomerId(@PathVariable int customerId){
    	return service.viewOrderByCustomerId(customerId);
    }
    @PostMapping("/placeOrder/{customerId}/{paymentMethod}/{otp}")
	//@PreAuthorize("hasAuthority('customer')")
	public String placeOrder(@PathVariable int customerId,@PathVariable String paymentMethod,@PathVariable String otp) throws OrderNotFoundException, ProductNotFoundException{

		return service.placeOrder(customerId,paymentMethod,otp);
	}
    
//    @PostMapping("/sendingOTP/{username}/{phoneNumber}")
//    //@PreAuthorize("hasAuthority('customer')")
//    public String sendingOTP(@PathVariable String username,@PathVariable String phoneNumber) {
//    	return service.sendingOTP(username, phoneNumber);
//    }
    
}
