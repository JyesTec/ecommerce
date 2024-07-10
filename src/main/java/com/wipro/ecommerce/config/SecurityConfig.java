package com.wipro.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.wipro.ecommerce.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	@Autowired
	JwtAuthFilter authFilter;

	@Bean
	// authentication
	public UserDetailsService userDetailsService() {

		return (UserDetailsService) new AdminCustomerSellerInfoDetailsService();
	}

	@Bean
	public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

		return http.cors().and().csrf().disable()

				.authorizeHttpRequests(
						requests -> requests
								.requestMatchers("api/customer/register", "api/seller/register","api/admin/addAdmin","api/admin/viewAdminById/**",
										"/api/admin/login/authenticate", "/api/customer/login/authenticate","api/seller/addProduct",
										"/api/admin/viewAllProduct","/api/admin/viewAllCategory","/api/admin/viewAllSubCategory",
										"/api/admin/viewAllSeller","/api/admin/viewAllCustomer","/api/admin/viewAllPayment",
										"/api/admin/deleteProduct/{id}","/api/admin/deleteCategory/{id}","/api/admin/deleteSubCategory/{id}",
										"api/seller/deleteSeller/{id}","api/seller/deleteCustomer/{id}","api/seller/viewAllOrder",
										"api/seller/addPayment","api/seller/addCategory","api/seller/addSubCategory","api/seller/viewAdminById/{adminId}",
										"api/seller/getOrderItemsByOrderId/{orderId}","api/seller/addProductToCustomerCart/{customerId}/{productId}/{quantity}","api/seller/viewSubCategoryByName/{name}","api/seller/viewCartitems/{customerId}",
										"api/seller/viewAllSubCategory","api/seller/viewProductByName/{name}","api/seller/viewCategoryByName/{name}","api/seller/addPayment","api/seller/addPayment",
										"api/seller/viewProductByPriceRange/{min}/{max}","api/seller//viewProductByBrand/{brand}","api/seller/deleteProductFromCustomerCart/{customerId}/{productId}","api/seller/viewProductsBySubCategoryName/{subcategoryName}",
										"api/seller/viewOrdersByCustomerId/{customerId}","api/seller/placeOrder/{customerId}/{paymentMethod}/{otp}","api/seller/getAllProduct","api/seller/getAllCategory","api/seller/getAllSubCategory","api/seller/getAllSubCategory",
										"api/seller/addProduct","api/seller/updateProduct","api/seller/eleteProduct/{id}","api/seller/getProductByName/{name}","api/seller/getProductByID/{id}","api/seller/markProductOutOfStock/{sellerId}/{productId}",
										"api/seller/viewMyProducts/{sellerId}","api/seller/getSellerById/{sellerId}","api/seller/getSubCategoryById/{subCategoryId}",
										"api/seller/getOrdersBySellerId/{sellerId}","api/seller/getOrderById/{orderId}","api/seller/updateOrderItem","api/seller/viewAllOrderItemsOfSellerInOrder/{orderId}/{sellerId}",
										"/api/seller/login/authenticate", "/swagger-ui/**", "/v3/api-docs/**")
								.permitAll())
				.authorizeHttpRequests(
						requests -> requests.requestMatchers("api/admin/**", "api/customer/**", "api/seller/**")

								.authenticated()) // .formLogin().and().build();

				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).build();

	}

	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

		return config.getAuthenticationManager();

	}

}
