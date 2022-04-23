package com.hahaup.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hahaup.api.model.dto.BaseResponse;
import com.hahaup.api.service.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
public class AuthorizationFilter extends HttpFilter {

    private static final String[] exclusions = {
            "/user/login",
            "/user/register",
            "/partner/public/**"
    };

    private static final String[] BLACK_LIST_USERS = {

    };

    private final JwtProvider jwtProvider;
    private final AntPathMatcher antPathMatcher;
    private final ObjectMapper mapper;

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        boolean exclusion = Arrays.stream(exclusions)
                .anyMatch(e -> antPathMatcher.match(e, requestURI));
        if (!exclusion) {
            String token = Optional.ofNullable(request.getHeader("Authorization"))
                    .map(s -> s.replace("Bearer ", ""))
                    .orElse(null);
            if (token == null || !jwtProvider.validateToken(token)) {
                PrintWriter printWriter = response.getWriter();
                printWriter.print(mapper.writeValueAsString(new BaseResponse<>(new RuntimeException("Invalid token"))));
                printWriter.flush();
                return;
            } else {
                String loginFromToken = jwtProvider.getLoginFromToken(token);
                if (Arrays.asList(BLACK_LIST_USERS).contains(loginFromToken)) {
                    throw new RuntimeException("Доступ ограничен");
                }
                RequestContextHolder.getRequestAttributes()
                        .setAttribute("login", loginFromToken, 0);
                RequestContextHolder.getRequestAttributes()
                        .setAttribute("type", jwtProvider.getTypeFromToken(token), 0);
            }
        }
        chain.doFilter(request, response);
    }
}
