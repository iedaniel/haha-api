package com.hahaup.api.client;

import com.hahaup.api.model.dto.cloudtips.IdentityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name = "cloudTipsIdentityClient", url = "${cloudtips.url.identity}")
public interface CloudTipsIdentityClient {

    @PostMapping(value = "/connect/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    IdentityResponse getAccessToken(@RequestPart("Grant_type") String gt,
                                    @RequestPart("Client_id") String partner,
                                    @RequestPart("UserName") String username,
                                    @RequestPart("Password") String password);
}
