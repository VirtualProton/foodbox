package com.foodbox.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.foodbox.filter.JwtFilter;
import com.foodbox.service.UserService;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private StoreService storeService;
	
	@Autowired
	private JwtFilter jwtFilter;
//	@Autowired
//	private Store_JwtFilter store_JwtFilter;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.userDetailsService(storeService).passwordEncoder(null);
		auth.userDetailsService(userService).passwordEncoder(null);	
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();	
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception{
			return authenticationConfiguration.getAuthenticationManager();
		}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/authenticate","/otp","/api/category/get_category","/api/location/add_location","/api/location/get_location",
				"/api/otp/send","/api/otp/verify","/api/food/get_food","/api/food/add_food","/api/food/update_food","/api/food/delete_food","/api/file/upload",
				"/api/store/addstore","/api/order/get_orders","/api/store/auth","/api/store/getDetails","/api/store/addDetails").permitAll().anyRequest().authenticated()
		.and().exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//		http.addFilterBefore(store_JwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
}

