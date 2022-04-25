package com.hahaup.api.model.dto.partner;

import lombok.Data;

@Data
public class PartnerResponse {

    private String fio;
    private String username;
    private String description;
    private String photoUrl;
    private String externalUserId;
    private String externalLayoutId;
}
