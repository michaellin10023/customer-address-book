package com.example.mycrudapp.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsernamePasswordAuthentiationReq authentiationReq = new ObjectMapper().
                readValue(request.getInputStream(), UsernamePasswordAuthentiationReq.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(authentiationReq.getUsername(), authentiationReq.getPassword());

            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String key = "thisisasupersecurekeythisisasupersecurekeythisisasupersecurekeythisisasupersecurekey";
        String jwt = Jwts.builder()
                                .setSubject(authResult.getName())
                                .claim("authorities", authResult.getAuthorities())
                                .setIssuedAt(new java.util.Date())
                                .setExpiration(Date.valueOf(LocalDate.now().plusWeeks(2)))
                                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                                .compact();

        response.addHeader("Authorization", "Bearer " + jwt);
    }

}
