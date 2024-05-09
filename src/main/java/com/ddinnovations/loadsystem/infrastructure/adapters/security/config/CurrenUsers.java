package com.ddinnovations.loadsystem.infrastructure.adapters.security.config;


import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.common.BusinessException;
import com.ddinnovations.loadsystem.infrastructure.adapters.jpa.user.mapper.UserMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrenUsers {
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof User user) {
            return UserMapper.userLogin(user);
        }

        throw new BusinessException(BusinessException.Type.USER_NOT_EXIST);
    }
}
