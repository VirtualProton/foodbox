//package com.foodbox.filter;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.foodbox.service.StoreService;
//import com.foodbox.util.JwtUtil;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class Store_JwtFilter extends OncePerRequestFilter {
//	
//	@Autowired
//	private JwtUtil jwtUtil;
//	
//	@Autowired
//	private StoreService storeService;
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String authorizationHeader = request.getHeader("Authorization");
//		String token = null;
//		String userName = null;
//		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//			token = authorizationHeader.substring(7);
//			userName = jwtUtil.extractUsername(token);
//		}
//		if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
//			UserDetails userDetails = storeService.loadUserByUsername(userName);
//			if(jwtUtil.validateToekn(token, userDetails)) {
//				
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext()
//				.setAuthentication(usernamePasswordAuthenticationToken);			
//				}
//		}
//		filterChain.doFilter(request, response);
//	}
//
//}
