package com.tp7.flutter.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.IOException;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {

	    String header = request.getHeader("Authorization");

	    if (header == null || !header.startsWith(SecParams.PREFIX)) {
	        filterChain.doFilter(request, response);
	        return;
	    }

	    try {
	        String token = header.replace(SecParams.PREFIX, "");

	        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecParams.SECRET)).build();
	        DecodedJWT decodedJWT = verifier.verify(token);

	        String username = decodedJWT.getSubject();

	        if (username != null) {
	        
	        	UsernamePasswordAuthenticationToken auth =
	        	        new UsernamePasswordAuthenticationToken(
	        	                username,
	        	                null,
	        	                List.of(new SimpleGrantedAuthority("ROLE_USER"))
	        	        );

	            SecurityContextHolder.getContext().setAuthentication(auth);
	        }

	    } catch (Exception e) {
	        SecurityContextHolder.clearContext();
	    }

	    filterChain.doFilter(request, response);
	}

}
