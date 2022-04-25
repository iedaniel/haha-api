package com.hahaup.api.service;

import com.hahaup.api.client.CloudTipsApiClient;
import com.hahaup.api.client.CloudTipsIdentityClient;
import com.hahaup.api.logic.mapper.PartnerMapper;
import com.hahaup.api.model.dto.cloudtips.CTPartner;
import com.hahaup.api.model.dto.cloudtips.ReceiverResponse;
import com.hahaup.api.model.dto.partner.CreatePartnerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CloudtipsService {

    private final CloudTipsIdentityClient cloudTipsIdentityClient;
    private final CloudTipsApiClient cloudTipsApiClient;
    private final PartnerMapper partnerMapper;

    public ReceiverResponse createPartner(CreatePartnerRequest request) {
        String token = "Bearer " + cloudTipsIdentityClient.getAccessToken(
                "password",
                "Partner",
                "ie.daniel1999@gmail.com",
                "hahaup2022"
        ).getToken();
        String placeId = cloudTipsApiClient.getPlaces(token).getId();
        CTPartner ctPartner = partnerMapper.createCTPartner(request, placeId);
        return cloudTipsApiClient.createReceiver(token, ctPartner);
    }
}
