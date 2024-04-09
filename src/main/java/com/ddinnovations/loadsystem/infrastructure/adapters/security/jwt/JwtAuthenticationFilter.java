package com.ddinnovations.loadsystem.infrastructure.adapters.security.jwt;

import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.auth.AuthDtoRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.UserEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.auth.mapper.AuthMapper;
import com.ddinnovations.loadsystem.infrastructure.points.advice.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthDtoRepository authDtoRepository;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, BusinessException {
        ObjectMapper objectMapper = new ObjectMapper();
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = authHeader.split(" ")[1];
            String id = jwtService.extractUserName(jwt);
            UserEntity singIn = authDtoRepository.findById(id).get();
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    AuthMapper.authDtoAuth(singIn),
                    null,
                    singIn.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            String jsonResponse = objectMapper.writeValueAsString(new ErrorResponse("TU TOKEN EXPIRO", e.getMessage(), "", ""));
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(jsonResponse);
        } catch (Exception e) {
            String jsonResponse = objectMapper.writeValueAsString(new ErrorResponse("OCURRIO UN ERROR INICIA SESION NUEVAMENTE", e.getMessage(), "", ""));
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(jsonResponse);
        }
    }
}
