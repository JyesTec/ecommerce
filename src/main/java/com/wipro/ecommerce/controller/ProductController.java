package com.wipro.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.ecommerce.dto.ProductDTO;
import com.wipro.ecommerce.dto.SellerDTO;
import com.wipro.ecommerce.dto.SubCategoryDTO;
import com.wipro.ecommerce.entity.Product;
import com.wipro.ecommerce.entity.Seller;
import com.wipro.ecommerce.entity.SubCategory;
import com.wipro.ecommerce.exception.ProductNotFoundException;
import com.wipro.ecommerce.exception.SellerNotFoundException;
import com.wipro.ecommerce.exception.SubCategoryNotFoundException;
import com.wipro.ecommerce.service.ISellerService;
import com.wipro.ecommerce.service.ISubCategoryService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ISellerService service;
	
	@Autowired
	ISubCategoryService subCategoryService;
	
	//run
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody ProductDTO productdto) throws SellerNotFoundException, SubCategoryNotFoundException {
		SellerDTO sellerdto = service.getSellerById(productdto.getSellerId());
		Seller seller = service.updateSeller(sellerdto);
		SubCategoryDTO subCategoryDto = subCategoryService.getSubCategoryById(productdto.getSubCateegoryId());
		SubCategory subCategory = subCategoryService.updateSubCategory(subCategoryDto);
	    productdto.setSeller(seller);
	    productdto.setSubCategory(subCategory);
		return service.addProduct(productdto);
	}
	
	//run
	@PostMapping("/updateProduct")
	public Product updateProduct(@RequestBody ProductDTO productdto) throws ProductNotFoundException, SellerNotFoundException, SubCategoryNotFoundException {
		SellerDTO sellerdto = service.getSellerById(productdto.getSellerId());
		Seller seller = service.updateSeller(sellerdto);
		SubCategoryDTO subCategoryDto = subCategoryService.getSubCategoryById(productdto.getSubCateegoryId());
		SubCategory subCategory = subCategoryService.updateSubCategory(subCategoryDto);
	    productdto.setSeller(seller);
	    productdto.setSubCategory(subCategory);
		return service.updateProduct(productdto);
	}
	
	//run
	@DeleteMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable int id) throws ProductNotFoundException {
		return service.deleteProduct(id);
	}
	
	//run
	@GetMapping("/getProductByName/{name}")
	public Product getProductbyName(@PathVariable String name) throws ProductNotFoundException {
		return service.getProductbyName(name);
	}
	
	//run
	@GetMapping("/getProductByID/{id}")
	public ProductDTO getProductById(@PathVariable int id) throws ProductNotFoundException {
		return service.getProductById(id);
	}
	
	//run
	@GetMapping("/getAllProduct")
	public List<Product> getAllProduct(){
		return service.getAllProduct();

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
}
