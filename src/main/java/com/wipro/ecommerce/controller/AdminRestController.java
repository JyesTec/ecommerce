package com.wipro.ecommerce.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecommerce.dto.PaymentDTO;
import com.wipro.ecommerce.dto.SubCategoryDTO;
import com.wipro.ecommerce.entity.Admin;
import com.wipro.ecommerce.entity.Category;
import com.wipro.ecommerce.entity.Customer;
import com.wipro.ecommerce.entity.Order;
import com.wipro.ecommerce.entity.OrderItem;
import com.wipro.ecommerce.entity.Payment;
import com.wipro.ecommerce.entity.Product;
import com.wipro.ecommerce.entity.Seller;
import com.wipro.ecommerce.entity.SubCategory;
import com.wipro.ecommerce.exception.AdminNotFoundException;
import com.wipro.ecommerce.exception.CategoryNotFoundException;
import com.wipro.ecommerce.exception.CustomerNotFoundException;
import com.wipro.ecommerce.exception.ProductNotFoundException;
import com.wipro.ecommerce.exception.SellerNotFoundException;
import com.wipro.ecommerce.exception.SubCategoryNotFoundException;
import com.wipro.ecommerce.service.IAdminService;
@CrossOrigin("localhost://4200")
@RestController
@RequestMapping("/api/admin")
public class AdminRestController {
	 private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);
	
	@Autowired
	IAdminService service;
	
//	@Autowired
//	JwtService jwtService;
	
//	@Autowired
//	AuthenticationManager authenticationManager;
//	
	
//	@PostMapping("/login/authenticate")
//	public Object authenticateAndGetTokent(@RequestBody  AuthRequest authRequest) throws AdminNotFoundException {
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//		String token = null;
//		if(authentication.isAuthenticated()) {
//				  // call generate token method from jwtService class	
//			     token=jwtService.generateToken(authRequest.getUsername());		
//			log.info("Tokent : "+token);
//			System.out.println(authentication.getDetails());
//			
//			
//			  }
//				else{
//					
//					log.info("invalid");
//			
//					 throw new UsernameNotFoundException("UserName or Password in Invalid / Invalid Request");	
//				}
//		
//		Optional<Admin> admin=service.fetchAdminDetails(authRequest.getUsername());
//		System.out.println(admin);
//		 Map<String, Object> object = new HashMap<>();
//		 object.put("token", token);
//		 object.put("data", admin);
//		 
//				return object;	 
//	 }
//	@PostMapping("/addAdmin")
////	@PreAuthorize("hasAuthority('admin')")
//	public String addAdmin(@RequestBody AdminDTO admindto) {
//		return service.addAdmin(admindto);
//	}
	
	
	@GetMapping("/viewAllProduct")
//	@PreAuthorize("hasAuthority('admin')")
	public List<Product> viewAllProducts() {
		return service.viewAllProducts();
	}
	
	@GetMapping("/viewAllCategory")
//	@PreAuthorize("hasAuthority('admin')")
	public List<Category> viewAllCategory(){
		return service.viewAllCategory();
	}

	@GetMapping("/viewAllSubCategory")
//	@PreAuthorize("hasAuthority('admin')")
	public List<SubCategory> viewAllSubCategory(){
		return service.viewAllSubCategory();
	}
	
	@GetMapping("/viewAllSeller")
//	@PreAuthorize("hasAuthority('admin')")
	public List<Seller> viewAllSeller(){
		return service.viewAllSeller();
	}
	
	@GetMapping("/viewAllCustomer")
//	@PreAuthorize("hasAuthority('admin')")
	public List<Customer> viewAllCustomer(){
		return service.viewAllCustomer();
	}
	
	@GetMapping("/viewAllPayment")
//	@PreAuthorize("hasAuthority('admin')")
	public List<Payment> viewAllPayment(){
		return service.viewAllPayment();
	}
	
	@DeleteMapping("/deleteProduct/{id}")
//	@PreAuthorize("hasAuthority('admin')")
	public String deleteProduct(@PathVariable int id) throws ProductNotFoundException {
		return service.deleteProduct(id);
	}
	
	@DeleteMapping("/deleteCategory/{id}")
//	@PreAuthorize("hasAuthority('admin')")
	public String deleteCategory(@PathVariable int id) throws CategoryNotFoundException {
		return service.deleteCategory(id);
	}
	
	@DeleteMapping("/deleteSubCategory/{id}")
//	@PreAuthorize("hasAuthority('admin')")
	public String deleteSubCategory(@PathVariable int id) throws SubCategoryNotFoundException {
		return service.deleteSubCategory(id);
	}
	

	 @DeleteMapping("/deleteSeller/{id}")
//	 @PreAuthorize("hasAuthority('admin')")
	    public  String deleteSeller(@PathVariable int id) throws SellerNotFoundException {
	                return service.deleteSeller(id);
	        }
	    
	        @DeleteMapping("/deleteCustomer/{id}")
	    public String deleteCustomer(@PathVariable int id) throws CustomerNotFoundException {
	                return service.deleteCustomer(id);
	            
	    }

	
	@GetMapping("/viewAllOrder")
//	@PreAuthorize("hasAuthority('admin')")
	public List<Order> viewAllOrders() {
		return service.viewAllOrder();
	}

     @PostMapping("/addPayment")
//     @PreAuthorize("hasAuthority('admin')")
      public Payment addPayment(@RequestBody PaymentDTO payment) {
	   return service.addPayment(payment);
	   
     }
     @PostMapping("/addCategory")
//	   @PreAuthorize("hasAuthority('admin')")
	   public Category addCategory(@RequestBody Category category) {
		   return service.addCategory(category);
		   
	   }
	   @PostMapping("/addSubCategory")
//	   @PreAuthorize("hasAuthority('admin')")
	    public SubCategory addSubCategory(@RequestBody SubCategoryDTO subcategorydto) {
	    	return service.addSubCategory(subcategorydto);
	    
}
	   @GetMapping("/viewAdminById/{adminId}")
//	   @PreAuthorize("hasAuthority('admin')")
	   public Admin viewAdminById(@PathVariable int adminId) throws AdminNotFoundException {
		   return service.viewAdminById(adminId);
	   }

	   @GetMapping("/getOrderItemsByOrderId/{orderId}")
//	   @PreAuthorize("hasAuthority('admin')")
	   public List<OrderItem> getOrderItemsByOrderId(@PathVariable int orderId){
		   return service.getOrderItemsByOrderId(orderId);
	   }
	
	
}