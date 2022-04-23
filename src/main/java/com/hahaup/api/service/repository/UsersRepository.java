package com.hahaup.api.service.repository;

import com.hahaup.api.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
