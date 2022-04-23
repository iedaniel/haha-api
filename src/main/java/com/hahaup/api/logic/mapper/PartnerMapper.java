package com.hahaup.api.logic.mapper;

import com.hahaup.api.model.dto.partner.CreatePartnerRequest;
import com.hahaup.api.model.dto.partner.PartnerResponse;
import com.hahaup.api.model.entity.PartnerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public abstract class PartnerMapper {

    public abstract PartnerEntity newPartnerToEntity(CreatePartnerRequest source);

    public abstract PartnerResponse partnerResponse(PartnerEntity source);

    public abstract List<PartnerResponse> partnersResponse(List<PartnerEntity> source);
}
