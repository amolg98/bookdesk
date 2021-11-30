package com.amolgupta.bookdesk.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amolgupta.bookdesk.security.services.UserDetailsServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            System.out.println("jwt is " + jwt);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUsernameFromJwtToken(jwt);
                System.out.println("username is " + username);

                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
                // System.out.println("User details are " + userDetails.toString());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // System.out.println("authentication is " + authentication.toString());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }

        response.setHeader( "Access-Control-Allow-Origin", "*" );
        response.setHeader( "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS" );
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept, x-requested-with, Cache-Control");
        response.addHeader( "Access-Control-Allow-Credentials", "true" );
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer")) {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}
