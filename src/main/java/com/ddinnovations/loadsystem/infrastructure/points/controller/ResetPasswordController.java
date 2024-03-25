package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.ResetPasswordService;
import com.ddinnovations.loadsystem.domain.entity.dto.ConfirmPasswordDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/password" )
@RequiredArgsConstructor
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    @PostMapping(path = "/forgot")
    public void forgotPassword(@RequestBody Email email) {
        resetPasswordService.forgotPassword(email.email());
    }

    @PostMapping(path = "/reset/{id}")
    public void resetPassword(@PathVariable("id") String id, @RequestBody ConfirmPasswordDTO confirmPassword) {
        resetPasswordService.changePassword(id, confirmPassword);
    }
}
