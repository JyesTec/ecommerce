//package com.wipro.ecommerce.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
//@Configuration
//@EnableWebSecurity
//public class AppSecurityConfig extends WebSecurityConfiguration{
//	
//	@Autowired
//	 UserDetailsService  userDetailsService;
//	@Autowired
//	  UserDetailsService  userDetailsService;
//	

//		@Bean
//		public AuthenticationProvider    authProvider() {
//			
//			
//			DaoAuthenticationProvider provider=		new DaoAuthenticationProvider();
//			
//			provider.setUserDetailsService(userDetailsService);
//			//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//			
//			provider.setPasswordEncoder(new BCryptPasswordEncoder());
//			
//			return provider;
//		}
	


