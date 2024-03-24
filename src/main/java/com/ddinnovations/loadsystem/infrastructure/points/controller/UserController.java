package com.ddinnovations.loadsystem.infrastructure.points.controller;

import com.ddinnovations.loadsystem.application.service.UserService;
import com.ddinnovations.loadsystem.domain.entity.User;
import com.ddinnovations.loadsystem.domain.entity.dto.UpdatePassword;
import com.ddinnovations.loadsystem.domain.entity.params.ParamsUser;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobal;
import com.ddinnovations.loadsystem.domain.entity.response.ResponseGlobalPagination;
import com.ddinnovations.loadsystem.infrastructure.adapters.security.config.CurrenUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CurrenUsers currenUsers;

    @PostMapping()
    public ResponseGlobal<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping()
    public ResponseGlobalPagination<List<User>> findAllUsers(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
            @RequestParam(value = "sort", defaultValue = "createdAt", required = false) String sort,
            @RequestParam(value = "filterCriteriaText", defaultValue = "", required = false) String filterCriteriaText,
            @RequestParam(value = "email", defaultValue = "", required = false) String email) {
        return userService.findAllUser(new ParamsUser(page, limit, Sort.by(sort), filterCriteriaText, email));
    }

    @GetMapping(path = "/{id}")
    public ResponseGlobal<User> findByIdUser(@PathVariable("id") String id) {
        return userService.findByIdUser(id);
    }


    @PatchMapping(path = "/change-password")
    public ResponseGlobal<User> changePassword(@RequestBody UpdatePassword updatePassword) {
        return userService.changePassword(currenUsers.getCurrentUser().getId(), updatePassword);
    }

    @PutMapping(path = "/{id}")
    public ResponseGlobal<User> update(@PathVariable("id") String id, @RequestBody User user) {
        return userService.update(id, user);
    }
}
