package com.hahaup.api.client;

import com.hahaup.api.model.dto.cloudtips.CTPartner;
import com.hahaup.api.model.dto.cloudtips.PlacesResponse;
import com.hahaup.api.model.dto.cloudtips.ReceiverResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "cloudTipsApiClient", url = "${cloudtips.url.api}")
public interface CloudTipsApiClient {

    @GetMapping("/api/places")
    PlacesResponse getPlaces(@RequestHeader("Authorization") String authToken);

    @PostMapping("/api/receivers/create-many")
    ReceiverResponse createReceiver(@RequestHeader("Authorization") String authToken,
                                    @RequestBody CTPartner request);

    @PostMapping(value = "/api/receivers/{userId}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    PhotoResponse addPhoto(@RequestHeader("Authorization") String token,
                           @PathVariable String userId,
                           @RequestPart("FormFile") MultipartFile file);
}
