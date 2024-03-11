package com.ddinnovations.loadsystem.application.service;

import com.ddinnovations.loadsystem.application.usecase.ResetPasswordUseCase;
import com.ddinnovations.loadsystem.domain.entity.dto.ConfirmPasswordDTO;
import com.ddinnovations.loadsystem.domain.repository.ResetPasswordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetPasswordService implements ResetPasswordUseCase {
    private final ResetPasswordRepository resetPasswordRepository;

    @Override
    public void forgotPassword(String email) {
        resetPasswordRepository.forgotPassword(email);
    }

    @Override
    public void changePassword(String token, ConfirmPasswordDTO confirmPasswordDTO) {
        resetPasswordRepository.changePassword(token,confirmPasswordDTO);
    }
}
