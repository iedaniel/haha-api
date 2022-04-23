package com.hahaup.api.service;

import com.hahaup.api.model.dto.user.UsernamePasswordDto;
import com.hahaup.api.model.entity.UserEntity;
import com.hahaup.api.service.jwt.JwtProvider;
import com.hahaup.api.service.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public void login(UsernamePasswordDto request, HttpServletResponse response) {
        UserEntity user = usersRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new RuntimeException("Пользователь не найден");
        }
        boolean matches = encoder.matches(request.getPassword(), user.getPassword());
        if (!matches) {
            throw new RuntimeException("Неверный пароль");
        }
        response.setHeader("Authorization", jwtProvider.generateToken(
                user.getUsername(), "USER"
        ));
    }

    @Transactional
    public void register(UsernamePasswordDto request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        usersRepository.save(user);
    }
}
