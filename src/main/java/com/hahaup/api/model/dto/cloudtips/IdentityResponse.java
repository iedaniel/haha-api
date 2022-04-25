package com.hahaup.api.model.dto.cloudtips;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class IdentityResponse {

    @JsonAlias("access_token")
    private String token;
}
