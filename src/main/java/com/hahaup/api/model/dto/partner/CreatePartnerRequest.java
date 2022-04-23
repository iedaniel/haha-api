package com.hahaup.api.model.dto.partner;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreatePartnerRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String fio;
    @NotBlank
    private String description;
    @NotBlank
    private String phoneNumber;
}
