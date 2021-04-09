package com.bootcamp.finalProject.security;

import com.bootcamp.finalProject.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JWTRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String username = null;
        String jwt = null;

        try {
            jwt = jwtUtil.resolveToken(httpServletRequest);
            if (jwt != null && jwtUtil.validateToken(jwt)) {
                Authentication auth = jwtUtil.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){

        }

        /*
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }




        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
            if(jwtUtil.validateToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities());

                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
         */

        filterChain.doFilter(httpServletRequest,httpServletResponse);


    }
}
