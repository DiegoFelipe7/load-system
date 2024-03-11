package com.ddinnovations.loadsystem.application.usecase;

import com.ddinnovations.loadsystem.domain.entity.dto.ConfirmPasswordDTO;

public interface ResetPasswordUseCase {
    void forgotPassword(String email);

    void changePassword(String token, ConfirmPasswordDTO confirmPasswordDTO);

}
