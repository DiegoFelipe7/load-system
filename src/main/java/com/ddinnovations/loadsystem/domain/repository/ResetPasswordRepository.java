package com.ddinnovations.loadsystem.domain.repository;


import com.ddinnovations.loadsystem.domain.entity.dto.ConfirmPasswordDTO;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;

public interface ResetPasswordRepository {
    void forgotPassword(String email);

    void changePassword(String token, ConfirmPasswordDTO confirmPasswordDTO);
}
