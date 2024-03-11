package com.ddinnovations.loadsystem.domain.repository;

import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.dto.LoginDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.TokenDTO;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;

public interface AuthRepository {
    TokenDTO singIn(LoginDTO loginDTO);
    ResponseGlobal<User> singUp(User user);

    ResponseGlobal<User> inactive(String id);

    TokenDTO refreshToken(User user);
}
