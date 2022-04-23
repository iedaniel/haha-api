package com.hahaup.api.service.repository;

import com.hahaup.api.model.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {

    PartnerEntity findByExternalUserId(String userId);

    PartnerEntity findByUsername(String username);
}