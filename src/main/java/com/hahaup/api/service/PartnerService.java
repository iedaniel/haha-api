package com.hahaup.api.service;

import com.hahaup.api.logic.mapper.PartnerMapper;
import com.hahaup.api.model.dto.IdDto;
import com.hahaup.api.model.dto.partner.CreatePartnerRequest;
import com.hahaup.api.model.dto.partner.PartnerResponse;
import com.hahaup.api.model.entity.PartnerEntity;
import com.hahaup.api.service.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerMapper partnerMapper;
    private final PartnerRepository partnerRepository;

    @Transactional
    public IdDto<String> createPartner(CreatePartnerRequest request) {
        //todo подумать про разделение партнеров по place в cloudtips
        PartnerEntity partnerEntity = partnerMapper.newPartnerToEntity(request);
        // todo api cloudtips
        partnerEntity.setExternalUserId("2342342");
        partnerEntity.setExternalLayoutId("6778978");
        // todo api cloudtips
        partnerRepository.save(partnerEntity);
        return new IdDto<>(partnerEntity.getExternalUserId());
    }

    @Transactional
    public void addPhoto(String externalUserId, MultipartFile file) {
        PartnerEntity partner = partnerRepository.findByExternalUserId(externalUserId);
        if (partner == null) {
            throw new RuntimeException("Партнер не найден в системе");
        }
        //todo api cloudtips
        partner.setPhotoUrl("string");
    }

    public PartnerResponse getInfo(String username) {
        PartnerEntity partner = partnerRepository.findByUsername(username);
        if (partner == null) {
            throw new RuntimeException("Партнер не найден в системе");
        }
        return partnerMapper.partnerResponse(partner);
    }

    public List<PartnerResponse> getAllPartners() {
        return partnerMapper.partnersResponse(partnerRepository.findAll());
    }
}