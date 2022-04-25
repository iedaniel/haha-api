package com.hahaup.api.logic.mapper;

import com.hahaup.api.model.dto.cloudtips.CTPartner;
import com.hahaup.api.model.dto.cloudtips.Receiver;
import com.hahaup.api.model.dto.partner.CreatePartnerRequest;
import com.hahaup.api.model.dto.partner.PartnerResponse;
import com.hahaup.api.model.entity.PartnerEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Arrays;
import java.util.List;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public abstract class PartnerMapper {

    public abstract PartnerEntity newPartnerToEntity(CreatePartnerRequest source);

    public abstract PartnerResponse partnerResponse(PartnerEntity source);

    public abstract List<PartnerResponse> partnersResponse(List<PartnerEntity> source);

    @Mapping(target = "placeId", expression = "java(placeId)")
    @Mapping(target = "receivers", expression = "java(createReceivers(source))")
    public abstract CTPartner createCTPartner(CreatePartnerRequest source,
                                              @Context String placeId);

    @Mapping(target = "name", source = "source.fio")
    public abstract Receiver createReceiver(CreatePartnerRequest source);

    protected List<Receiver> createReceivers(CreatePartnerRequest source) {
        return Arrays.asList(createReceiver(source));
    }
}
