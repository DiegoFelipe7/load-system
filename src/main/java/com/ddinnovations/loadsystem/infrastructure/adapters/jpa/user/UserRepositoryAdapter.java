package com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user;

import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsUser;
import com.ddinnovations.loadsystem.domain.entity.response.Pagination;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.domain.repository.UserRepository;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.clients.mapper.ClientMapper;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.filters.UserSpecification;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.helpers.AdapterOperations;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.mapper.UserMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryAdapter extends AdapterOperations<User, UserEntity, String, UserDtoRepository> implements UserRepository {
    private final PasswordEncoder passwordEncoder;

    protected UserRepositoryAdapter(UserDtoRepository repository, ObjectMapper mapper, PasswordEncoder passwordEncoder) {
        super(repository, mapper, d -> mapper.map(d, User.UserBuilder.class).build());
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseGlobalPagination<List<User>> findAllUser(ParamsUser params) {
        UserSpecification specification = new UserSpecification(params.getFilterCriteriaText(), params.getEmail());
        PageRequest pages = PageRequest.of(params.getPage(), params.getLimit(), params.getSort());
        List<User> users = repository.findAll(specification, pages)
                .stream()
                .map(UserMapper::userDtoAUser)
                .toList();
        return new ResponseGlobalPagination<>(users, new Pagination(params.getPage(), params.getLimit(), ((int) repository.count())));

    }

    @Override
    public ResponseGlobal<User> findByIdUser(String id) {
        UserEntity userEntity = this.getByIdUser(id);
        return new ResponseGlobal<>(UserMapper.userDtoAUser(userEntity));
    }

    @Override
    public ResponseGlobal<User> createUser(User user) {
        if (repository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new BusinessException(BusinessException.Type.USER_EXIST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = UserMapper.userAUserDto(user);
        return new ResponseGlobal<>(UserMapper.userDtoAUser(repository.save(userEntity)));
    }

    @Override
    public ResponseGlobal<User> update(String id, User user) {
        UserEntity userEntity = getByIdUser(id);
        userEntity.setFistName(user.getFistName());
        user.setLastName(user.getLastName());
        if (!userEntity.getEmail().equalsIgnoreCase(user.getEmail())) {
            if (repository.existsByEmailIgnoreCase(user.getEmail())) {
                throw new BusinessException(BusinessException.Type.USER_EXIST);
            }
            userEntity.setEmail(user.getEmail());
        }

        return new ResponseGlobal<>(UserMapper.userDtoAUser(repository.save(userEntity)));
    }

    @Override
    public ResponseGlobal<User> inactiveUser(String id) {
        UserEntity userEntity = this.getByIdUser(id);
        userEntity.setState(!userEntity.isState());
        return new ResponseGlobal<>(UserMapper.userDtoAUser(repository.save(userEntity)));
    }

    private UserEntity getByIdUser(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(BusinessException.Type.USER_EXIST));
    }
}
