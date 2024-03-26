package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.AuthService;
import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.dto.LoginDTO;
import com.ddinnovations.loadsystem.domain.entity.dto.TokenDTO;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/auth" )
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/singIn")
    public TokenDTO singIn(@RequestBody LoginDTO loginDTO) {
        return authService.singIn(loginDTO);
    }

    @PostMapping(path = "/singUp")
    public ResponseGlobal<User> singUp(@RequestBody User user) {
        return authService.singUp(user);
    }


    @PostMapping(path = "/refresh")
    public TokenDTO refreshToken(@RequestBody TokenDTO tokenDTO) {
        return authService.refreshToken(tokenDTO.refreshToken());
    }
}
