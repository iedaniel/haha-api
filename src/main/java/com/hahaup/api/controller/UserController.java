package com.hahaup.api.controller;

import com.hahaup.api.model.dto.BaseResponse;
import com.hahaup.api.model.dto.user.UsernamePasswordDto;
import com.hahaup.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public BaseResponse<?> login(@RequestBody UsernamePasswordDto request,
                                 HttpServletResponse response) {
        request.validate();
        userService.login(request, response);
        return new BaseResponse<>();
    }

    @PostMapping("/register")
    public BaseResponse<?> register(@RequestBody UsernamePasswordDto request) {
        request.validate();
        userService.register(request);
        return new BaseResponse<>();
    }
}
