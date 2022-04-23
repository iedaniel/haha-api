package com.hahaup.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hahaup.api.service.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final JwtProvider jwtProvider;
    @Value("${app.allowed.urls}")
    private String allowedUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(getAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private AuthorizationFilter getAuthorizationFilter() {
        return new AuthorizationFilter(
                jwtProvider,
                new AntPathMatcher(),
                new ObjectMapper()
        );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins(allowedUrl.split(","))
                .exposedHeaders("Authorization")
                .allowedHeaders("Authorization", "Invite", "Content-Type", "X-Content-HMAC")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}