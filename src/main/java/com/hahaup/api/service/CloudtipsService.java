package com.hahaup.api.service;

import com.hahaup.api.client.CloudTipsApiClient;
import com.hahaup.api.client.CloudTipsIdentityClient;
import com.hahaup.api.client.PhotoResponse;
import com.hahaup.api.logic.mapper.PartnerMapper;
import com.hahaup.api.model.dto.cloudtips.CTPartner;
import com.hahaup.api.model.dto.cloudtips.ReceiverResponse;
import com.hahaup.api.model.dto.partner.CreatePartnerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class CloudtipsService {

    private final CloudTipsIdentityClient cloudTipsIdentityClient;
    private final CloudTipsApiClient cloudTipsApiClient;
    private final PartnerMapper partnerMapper;

    @Value("${cloudtips.username}")
    private String username;
    @Value("${cloudtips.password}")
    private String password;

    public ReceiverResponse createPartner(CreatePartnerRequest request) {
        String token = "Bearer " + cloudTipsIdentityClient.getAccessToken(
                "password",
                "Partner",
                username,
                password
        ).getToken();
        String placeId = cloudTipsApiClient.getPlaces(token).getId();
        CTPartner ctPartner = partnerMapper.createCTPartner(request, placeId);
        return cloudTipsApiClient.createReceiver(token, ctPartner);
    }

    public PhotoResponse addPhoto(String userId, MultipartFile file) {
        String token = "Bearer " + cloudTipsIdentityClient.getAccessToken(
                "password",
                "Partner",
                username,
                password
        ).getToken();
        return cloudTipsApiClient.addPhoto(token, userId, file);
    }
}
