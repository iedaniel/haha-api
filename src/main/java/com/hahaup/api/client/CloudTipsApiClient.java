package com.hahaup.api.client;

import com.hahaup.api.model.dto.cloudtips.CTPartner;
import com.hahaup.api.model.dto.cloudtips.PlacesResponse;
import com.hahaup.api.model.dto.cloudtips.ReceiverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "cloudTipsApiClient", url = "${cloudtips.url.api}")
public interface CloudTipsApiClient {

    @GetMapping("/api/places")
    PlacesResponse getPlaces(@RequestHeader("Authorization") String authToken);

    @PostMapping("/api/receivers/create-many")
    ReceiverResponse createReceiver(@RequestHeader("Authorization") String authToken,
                                    @RequestBody CTPartner request);
}
