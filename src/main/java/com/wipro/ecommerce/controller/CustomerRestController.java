package com.wipro.ecommerce.controller;

import java.util.List;

import org.apache.commons.logging.Log;
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
import com.wipro.ecommerce.dto.CustomerDTO;
import com.wipro.ecommerce.entity.CartItem;
import com.wipro.ecommerce.entity.Category;
import com.wipro.ecommerce.entity.Customer;
import com.wipro.ecommerce.entity.Order;
import com.wipro.ecommerce.entity.Product;
import com.wipro.ecommerce.entity.SubCategory;
import com.wipro.ecommerce.exception.CustomerNotFoundException;
import com.wipro.ecommerce.exception.OrderNotFoundException;
import com.wipro.ecommerce.exception.ProductNotFoundException;
import com.wipro.ecommerce.service.AdminServiceImp;
import com.wipro.ecommerce.service.ICustomerService;

import jakarta.validation.Valid;
@CrossOrigin("localhost://4200")
@RestController
@RequestMapping("/api/Customer")
public class CustomerRestController {
	private static final Logger log = LoggerFactory.getLogger(CustomerRestController.class);
	@Autowired
	ICustomerService service;
	@Autowired
	AdminServiceImp adminServiceImp;
	
	//run
	@PostMapping("/register")
	public String registerCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
		return service.registerCustomer(customerDTO);
	}
	
	@PutMapping("/update")
	public String updateCustomer(@RequestBody @Valid CustomerDTO customerDTO) throws CustomerNotFoundException  {
		return service.updateCustomer(customerDTO);
	}
	
	@DeleteMapping("/deleteByCustomer/{Id}")
	
	public String deleteCustomer(@PathVariable int Id ) throws CustomerNotFoundException {
		log.info("======="+Id);
		return service.deleteCustomerById(Id);
		
	}
//	@PostMapping("/")
//	public void addProducts(@RequestBody)
	
	@PostMapping("/addProductToCustomerCart/{customerId}/{productId}/{quantity}")
	public String addProductToCustomerCart(@PathVariable int customerId,@PathVariable int productId,@PathVariable int quantity) throws ProductNotFoundException{
		return service.addProductToCustomerCart(customerId, productId, quantity);
	}	
	
	//run
    @GetMapping("/viewAllProduct")
    public List<Product> getAllProduct(){
    	return service.getAllProduct();
    }
    
    //run
    @GetMapping("/viewAllCategory")
    public List<Category> getAllCategory(){
    	return service.getAllCategory();
    }
    
    //run
    @GetMapping("/viewAllSubCategory")
    public List<SubCategory> getAllSubCategory(){
    	return service.getAllSubCategory();
    }
    
    //run
    @GetMapping("/viewProductByName/{name}")
    public Product getProductByName(@PathVariable String name) {
    	return service.getProductByName(name);
    }
    
    //run
    @GetMapping("/viewCategoryByName/{name}")
    public Category getCategorybyName(@PathVariable String name) {
    	return service.getCategorybyName(name);
    }
    
    //run
    @GetMapping("/viewSubCategoryByName/{name}")
    public SubCategory getSubCategoryByName(@PathVariable String name) {
    	return service.getSubCategoryByName(name);
    }
    
    
    @GetMapping("/viewCartitems/{customerId}")
    public List<CartItem> viewCartitems(@PathVariable int customerId){
    	return service.viewCartitems(customerId);
    }

    //run
    @GetMapping("/viewProductByBrand/{brand}")
    public List<Product> getProductsByBrand(@PathVariable String brand){
    	return service.getProductsByBrand(brand);
    }
    
    @GetMapping("/viewProductByPriceRange/{min}/{max}")
    public List<Product> getProductsByPriceRange(@PathVariable double min,@PathVariable double max){
    	return service.getProductsByPriceRange(min, max);
    }
    
    
    @PostMapping("/deleteProductFromCustomerCart/{customerId}/{productId}")
    public String deleteProductFromCustomerCart(@PathVariable int customerId,@PathVariable int productId) throws ProductNotFoundException {
    	return service.deleteProductFromCustomerCart(customerId, productId);
    }
    //run
    @GetMapping("/viewProductsBySubCategoryName/{subcategoryName}")
    public List<Product> viewProductsBySubCategoryName(@PathVariable String subcategoryName){
    	return service.viewProductsBySubCategoryName(subcategoryName);
    }
    @GetMapping("/viewOrdersByCustomerId/{customerId}")
    public List<Order> viewOrdersByCustomerId(@PathVariable int customerId){
    	return service.viewOrderByCustomerId(customerId);
    }
    
    @PostMapping("/placeOrder/{customerId}/{paymentMethod}/{otp}")
	public String placeOrder(@PathVariable int customerId,@PathVariable String paymentMethod,@PathVariable String otp) throws OrderNotFoundException, ProductNotFoundException{

		return service.placeOrder(customerId,paymentMethod,otp);
	}
    

    
}
