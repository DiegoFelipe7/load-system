package com.ddinnovations.loadsystem.infrastructure.adapters.security.jwt;

import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.auth.AuthDtoRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.UserEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.auth.mapper.AuthMapper;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

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
    }
}
