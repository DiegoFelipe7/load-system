package com.ddinnovations.loadsystem.domain.repository;

import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsUser;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;

import java.util.List;

public interface UserRepository {
    ResponseGlobalPagination<List<User>> findAllUser(ParamsUser params);

    ResponseGlobal<User> findByIdUser(String id);

    ResponseGlobal<User> createUser(User user);

    ResponseGlobal<User> update(String id, User user);

    ResponseGlobal<User> inactiveUser(String id);
}
