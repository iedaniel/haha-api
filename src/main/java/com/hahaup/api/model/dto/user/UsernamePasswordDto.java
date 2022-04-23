package com.hahaup.api.model.dto.user;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;

@Data
public class UsernamePasswordDto {

    private String username;
    private String password;

    public void validate() {
        if (Strings.isBlank(username)) {
            throw new RuntimeException("Логин не может быть пустым");
        }
        if (Strings.isBlank(password)) {
            throw new RuntimeException("Пароль не может быть пустым");
        }
    }
}
