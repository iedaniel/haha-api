package com.hahaup.api.model.dto.cloudtips;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ReceiverResponse {

    private String userId;
    private String layoutId;

    @JsonProperty("data")
    private void fillFirstReceiver(Map<String, Object> data) {
        List<Object> created = (List<Object>) data.get("created");
        Map<String, String> receiver = (Map<String, String>) created.get(0);
        this.userId = receiver.get("userId");
        this.layoutId = receiver.get("layoutId");
    }
}
