package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.auth;

import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.dto.LoginDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.TokenDTO;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.repository.AuthRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.auth.mapper.AuthMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.UserEntity;
import com.ddinnovations.loadsystem.infrastructure.adapters.security.jwt.JwtService;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepositoryAdapter extends AdapterOperations<User, UserEntity, String, AuthDtoRepository> implements AuthRepository {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    protected AuthRepositoryAdapter(AuthDtoRepository repository, ObjectMapper mapper, AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder) {
        super(repository, mapper, d -> mapper.map(d, User.UserBuilder.class).build());
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenDTO singIn(LoginDTO loginDTO) {
        UserEntity userEntity = repository.findByEmailEqualsIgnoreCase(loginDTO.email())
                .orElseThrow(() -> new BusinessException(BusinessException.Type.USER_NOT_EXIST));

        if (!userEntity.isState()) {
            throw new BusinessException(BusinessException.Type.INACTIVE_USER);
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userEntity.getEmail(),
                loginDTO.password()
        );
        authenticationManager.authenticate(authenticationToken);
        return new TokenDTO(jwtService.generateToken(userEntity, 4L),
                jwtService.generateToken(userEntity, 5L));

    }

    @Override
    public ResponseGlobal<User> singUp(User user) {
        if (repository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new BusinessException(BusinessException.Type.USER_EXIST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = this.repository.save(AuthMapper.authAuthDto(user));
        return new ResponseGlobal<>(AuthMapper.authDtoAuth(this.repository.save(userEntity)));

    }

    @Override
    public ResponseGlobal<User> inactive(String id) {
        UserEntity auth = repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.USER_NOT_EXIST));
        auth.setState(!auth.isState());
        return new ResponseGlobal<>(AuthMapper.authDtoAuth(auth));
    }

    @Override
    public TokenDTO refreshToken(String token) {
        String id = jwtService.extractUserName(token);

        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.USER_NOT_EXIST));

        return new TokenDTO(jwtService.generateToken(userEntity, 4L),
                jwtService.generateToken(userEntity, 5L));
    }


}
