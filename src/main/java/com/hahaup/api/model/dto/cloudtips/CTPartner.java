package com.hahaup.api.model.dto.cloudtips;

import lombok.Data;

import java.util.List;

@Data
public class CTPartner {

    private String placeId;
    private List<Receiver> receivers;
}
