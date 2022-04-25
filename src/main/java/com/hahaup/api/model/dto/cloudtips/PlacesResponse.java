package com.hahaup.api.model.dto.cloudtips;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PlacesResponse {

    private String id;

    @JsonProperty("data")
    private void fillFirstPlace(Map<String, Object> data) {
        List<Object> items = (List<Object>) data.get("items");
        Map<String, Object> item = (Map<String, Object>) items.get(0);
        this.id = (String) item.get("id");
    }
}
