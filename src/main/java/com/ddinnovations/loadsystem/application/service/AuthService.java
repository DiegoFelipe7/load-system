package com.ddinnovations.loadsystem.application.service;

import com.ddinnovations.loadsystem.application.usecase.AuthUseCase;
import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.dto.LoginDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.TokenDTO;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthUseCase {
    private final AuthRepository authRepository;
    @Override
    public TokenDTO singIn(LoginDTO loginDTO) {
        return authRepository.singIn(loginDTO);
    }

    @Override
    public ResponseGlobal<User> singUp(User user) {
        return authRepository.singUp(user);
    }

    @Override
    public ResponseGlobal<User> inactive(String id) {
        return authRepository.inactive(id);
    }

    @Override
    public TokenDTO refreshToken(User user) {
        return authRepository.refreshToken(user);
    }
}
