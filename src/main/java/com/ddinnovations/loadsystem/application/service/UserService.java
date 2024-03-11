package com.ddinnovations.loadsystem.application.service;

import com.ddinnovations.loadsystem.application.usecase.UserUseCase;
import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsUser;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserUseCase {
    private final UserRepository userRepository;

    @Override
    public ResponseGlobalPagination<List<User>> findAllUser(ParamsUser params) {
        return userRepository.findAllUser(params);
    }

    @Override
    public ResponseGlobal<User> findByIdUser(String id) {
        return userRepository.findByIdUser(id);
    }

    @Override
    public ResponseGlobal<User> createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public ResponseGlobal<User> update(String id, User user) {
        return userRepository.update(id, user);
    }

    @Override
    public ResponseGlobal<User> inactiveUser(String id) {
        return userRepository.inactiveUser(id);
    }
}
