package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.AuthService;
import com.ddinnovations.loadsystem.application.service.ResetPasswordService;
import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.dto.ConfirmPasswordDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.Email;
import com.ddinnovations.loadsystem.domain.entity.dto.LoginDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.TokenDTO;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/auth" )
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ResetPasswordService resetPasswordService;

    @PostMapping(path = "/singIn")
    public TokenDTO singIn(@RequestBody LoginDTO loginDTO) {
        return authService.singIn(loginDTO);
    }

    @PostMapping(path = "/singUp")
    public ResponseGlobal<User> singUp(@RequestBody User user) {
        return authService.singUp(user);
    }

    @PostMapping(path = "/forgot-password")
    public void forgotPassword(@RequestBody Email email) {
        resetPasswordService.forgotPassword(email.email());
    }

    @PostMapping(path = "/change-password/{id}")
    public void forgotPassword(@PathVariable("id") String id, @RequestBody ConfirmPasswordDTO confirmPassword) {
        resetPasswordService.changePassword(id,confirmPassword);
    }
}
